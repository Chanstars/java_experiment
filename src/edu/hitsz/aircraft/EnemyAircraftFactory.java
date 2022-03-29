package edu.hitsz.aircraft;
/**
 * 敌机制造工厂
 * @author hitsz
 */
public interface EnemyAircraftFactory {
    public AbstractAircraft createEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp);
}
