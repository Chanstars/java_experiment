package edu.hitsz.supply;

/**
 * 火力道具生成工厂
 * @author hitsz
 */
public class FireSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY, int speedX, int speedY){
        AbstractSupply supply = new FireSupply(locationX, locationY, speedX, speedY);
        return supply;
    }
}
