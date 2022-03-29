package edu.hitsz.supply;

/**
 * 炸弹道具
 * @author hitsz
 */
public class BombSupply extends Supply {

    public BombSupply(int locationX, int locationY, int speedX, int speedY, int power){
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public int function(){
        System.out.println("BombSupply active!\n");
        return power;
    }
}
