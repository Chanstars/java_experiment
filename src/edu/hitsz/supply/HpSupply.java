package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
/**
 * 生命恢复道具
 * @author hitsz
 */
public class HpSupply extends AbstractSupply{

    /**
     * 生命恢复量
     * @author hitsz
     */
    protected int hpPower;
    public HpSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.hpPower = 25;
    }
    @Override
    public void function(){
        heroSupply.decreaseHp(-hpPower);
    }
}
