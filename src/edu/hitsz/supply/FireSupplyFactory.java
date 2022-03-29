package edu.hitsz.supply;

/**
 * 火力道具生成工厂
 * @author hitsz
 */
public class FireSupplyFactory implements SupplyFactory{
    @Override
    public Supply createSupply(int locationX, int locationY, int speedX, int speedY, int power){
        Supply supply = new FireSupply(locationX, locationY, speedX, speedY, power);
        return supply;
    }
}
