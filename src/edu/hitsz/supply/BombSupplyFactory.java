package edu.hitsz.supply;

/**
 * 炸弹道具生成工厂
 * @author hitsz
 */
public class BombSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY, int speedX, int speedY, int power){
        AbstractSupply supply = new BombSupply(locationX, locationY, speedX, speedY, power);
        return supply;
    }
}
