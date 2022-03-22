package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum;

    /**
     * 子弹伤害
     */
    private int power;

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    private int direction;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public void forward() {}

    public List<BaseBullet> shoot()  {return new LinkedList<>();}
}
