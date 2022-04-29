package edu.hitsz.supply;

import thread.MusicThread;

/**
 * 炸弹道具
 * @author hitsz
 */
public class BombSupply extends AbstractSupply {

    public BombSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void function(){
        new MusicThread("src\\audio\\get_supply.wav").start();
        new MusicThread("src\\audio\\bomb_explosion.wav").start();
        System.out.println("BombSupply active!\n");
    }
}
