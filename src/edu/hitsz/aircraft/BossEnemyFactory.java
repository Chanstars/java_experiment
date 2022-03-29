package edu.hitsz.aircraft;

/**
 * Boss敌机制造工厂
 * @author hitsz
 */
public class BossEnemyFactory implements EnemyAircraftFactory{
    @Override
    public AbstractAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        AbstractAircraft enemy;
        enemy = new BossEnemy(locationX, locationY, speedX, speedY, hp);
        return enemy;
    }
}
