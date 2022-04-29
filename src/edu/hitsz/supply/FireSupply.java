package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShoot;
import thread.MusicThread;
import thread.ShootThread;

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
        new MusicThread("src\\audio\\get_supply.wav").start();
        new Thread(new ShootThread()).start();
    }
}
