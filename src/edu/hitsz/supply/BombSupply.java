package edu.hitsz.supply;

public class BombSupply extends Supply {

    public BombSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int function(){
        System.out.println("BombSupply active!\n");
        return 0;
    }
}
