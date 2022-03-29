package edu.hitsz.aircraft;

/**
 * 普通敌机制造工厂
 * @author hitsz
 */
public class MobEnemyFactory implements EnemyAircraftFactory{
    @Override
    public AbstractAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        AbstractAircraft enemy;
        enemy = new MobEnemy(locationX, locationY, speedX, speedY, hp);
        return enemy;
    }
}
