package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 精英敌机制造工厂
 * @author hitsz
 */
public class EliteEnemyFactory implements EnemyAircraftFactory{
    @Override
    public AbstractAircraft createEnemyAircraft(){
        AbstractAircraft enemy;
        enemy = new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                0,
                10,
                100);
        return enemy;
    }
}
