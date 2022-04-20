package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShoot;

/**
 * 火力道具
 * @author hitsz
 */
public class FireSupply extends AbstractSupply{
    public FireSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    };

    @Override
    public void function(){
        heroSupply.setShootNum(3);
        heroSupply.context.setStrategy(new ScatterShoot());
    }
}
