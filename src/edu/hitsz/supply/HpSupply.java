package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

public class HpSupply extends Supply{

    private int hpPower;

    public HpSupply(int locationX, int locationY, int speedX, int speedY, int hpPower){
        super(locationX, locationY, speedX, speedY);
        this.hpPower = hpPower;
    }
    @Override
    public int function(){return hpPower;}
}
