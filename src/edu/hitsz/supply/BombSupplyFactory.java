package edu.hitsz.supply;

/**
 * 炸弹道具生成工厂
 * @author hitsz
 */
public class BombSupplyFactory implements SupplyFactory{
    @Override
    public Supply createSupply(int locationX, int locationY, int speedX, int speedY, int power){
        Supply supply = new BombSupply(locationX, locationY, speedX, speedY, power);
        return supply;
    }
}
