package edu.hitsz.strategy;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 普通射击 (CommonShoot)
 * @author hitsz
 */

public class CommonShoot extends ShootStrategy {
    @Override
    public List<BaseBullet> shootStrategy(int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = hero.getLocationX();
        int y = hero.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = hero.getSpeedY() + direction*5;
        BaseBullet baseBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(baseBullet);
        }
        return res;
    }
}
