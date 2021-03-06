package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.strategy.CommonShoot;
import edu.hitsz.strategy.ShootContext;
import edu.hitsz.supply.*;
import edu.hitsz.scoretable.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import thread.MusicThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractSupply> supplies;
    private final MusicThread bgmThread = new MusicThread("src\\audio\\bgm.wav");
    private MusicThread bossThread = new MusicThread("src\\audio\\bgm_boss.wav");

    private final int enemyMaxNumber = 5;
    private int bossFlag = 0;

    private boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 400;
    private int cycleTime = 0;


    public Game() {
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.context = new ShootContext(new CommonShoot());
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        supplies = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        bgmThread.setBegin();
        bgmThread.start();
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {
            time += timeInterval;
            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                double rand1 = Math.random()*3;
                EnemyAircraftFactory enemyAircraftFactory;
                AbstractAircraft newEnemy;
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    if(score%100 == 0&&score != 0){
                        if(bossFlag == 0){
                            bossThread.start();
                            enemyAircraftFactory = new BossEnemyFactory();
                            newEnemy = enemyAircraftFactory.createEnemyAircraft();
                            enemyAircrafts.add(newEnemy);
                            bossFlag = 1;
                        }
                    }
                    else if(rand1<=2) {
                        enemyAircraftFactory = new MobEnemyFactory();
                        newEnemy = enemyAircraftFactory.createEnemyAircraft();
                        enemyAircrafts.add(newEnemy);
                    }
                    else {
                        enemyAircraftFactory = new EliteEnemyFactory();
                        newEnemy = enemyAircraftFactory.createEnemyAircraft();
                        enemyAircrafts.add(newEnemy);
                    }
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            suppliesMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                new MusicThread("src\\audio\\game_over.wav").start();
                bgmThread.setStop();
                bossThread.setStop();
                try {
                    this.setVisible(false);
                    synchronized (Main.MAIN_LOCK) {
                        // 选定难度，通知主线程结束等待
                        Main.MAIN_LOCK.notify();
                    }
                }catch(Exception i){
                    i.printStackTrace();
                }
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // 精英机射击
        for(int i=0;i<enemyAircrafts.size();i++) {
            enemyBullets.addAll(enemyAircrafts.get(i).shoot());
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.fire());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void suppliesMoveAction() {
        for (AbstractSupply supply : supplies) {
            supply.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        for (BaseBullet eliteBullet : enemyBullets) {
            if (eliteBullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(eliteBullet)){
                heroAircraft.decreaseHp(eliteBullet.getPower());
                eliteBullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet heroBullet : heroBullets) {
            if (heroBullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(heroBullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    new MusicThread("src\\audio\\bullet_hit.wav").start();
                    enemyAircraft.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    // 精英敌机坠毁概率产生道具
                    if (enemyAircraft.notValid()) {
                        SupplyFactory supplyFactory;
                        AbstractSupply supply;
                        double rand2 = Math.random()*10;
                        if(enemyAircraft.getClass()==BossEnemy.class){
                            bossFlag = 0;
                            bossThread.setStop();
                        }
                        else if(rand2<5){}
                        else if(rand2>=5&&rand2<8&&enemyAircraft.getClass()==EliteEnemy.class){
                            supplyFactory = new HpSupplyFactory();
                            supply = supplyFactory.createSupply(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,
                                    enemyAircraft.getSpeedY());
                            supplies.add(supply);
                        }
                        else if(rand2>=8&&rand2<9&&enemyAircraft.getClass()==EliteEnemy.class){
                            supplyFactory = new FireSupplyFactory();
                            supply = supplyFactory.createSupply(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,
                                    enemyAircraft.getSpeedY());
                            supplies.add(supply);
                        }
                        else if(rand2>=9&&rand2<10&&enemyAircraft.getClass()==EliteEnemy.class){
                            supplyFactory = new BombSupplyFactory();
                            supply = supplyFactory.createSupply(enemyAircraft.getLocationX(), enemyAircraft.getLocationY(), 0,
                                    enemyAircraft.getSpeedY());
                            supplies.add(supply);
                        }
                        else {}
                        score += 10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 英雄机获得道具，道具生效
        for (AbstractSupply supply : supplies){
            if(supply.notValid()){
                continue;
            }
            if(heroAircraft.crash(supply)){
                supply.function();
                supply.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        supplies.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, supplies);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    public int getScore(){
        return score;
    }
}
