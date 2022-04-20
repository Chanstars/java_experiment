package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * Boss敌机制造工厂
 * @author hitsz
 */
public class BossEnemyFactory implements EnemyAircraftFactory{
    @Override
    public AbstractAircraft createEnemyAircraft(){
        AbstractAircraft enemy;
        enemy = new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                1,
                0,
                500);
        return enemy;
    }
}
