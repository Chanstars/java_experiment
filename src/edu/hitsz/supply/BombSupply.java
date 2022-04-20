package edu.hitsz.supply;

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
        System.out.println("BombSupply active!\n");
    }
}
