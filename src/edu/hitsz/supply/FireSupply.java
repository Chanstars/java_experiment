package edu.hitsz.supply;

/**
 * 火力道具
 * @author hitsz
 */
public class FireSupply extends Supply{

    public FireSupply(int locationX, int locationY, int speedX, int speedY, int power){
        super(locationX, locationY, speedX, speedY, power);
    };

    @Override
    public int function(){
        System.out.println("FireSupply active!\n");
        return power;
    }
}
