package edu.hitsz.supply;

public class FireSupply extends Supply{

    public FireSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int function(){
        System.out.println("FireSupply active!\n");
        return 0;
    }
}
