package edu.hitsz.aircraft;

/**
 * 精英敌机制造工厂
 * @author hitsz
 */
public class EliteEnemyFactory implements EnemyAircraftFactory{
    @Override
    public AbstractAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        AbstractAircraft enemy;
        enemy = new EliteEnemy(locationX, locationY, speedX, speedY, hp);
        return enemy;
    }
}
