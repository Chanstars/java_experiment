package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
/**
 * 生命恢复道具
 * @author hitsz
 */
public class HpSupply extends Supply{

    public HpSupply(int locationX, int locationY, int speedX, int speedY, int power){
        super(locationX, locationY, speedX, speedY, power);
    }
    @Override
    public int function(){return power;}
}
