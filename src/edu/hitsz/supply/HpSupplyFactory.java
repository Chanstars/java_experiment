package edu.hitsz.supply;

/**
 * 生命恢复道具生成工厂
 * @author hitsz
 */
public class HpSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY, int speedX, int speedY, int power){
        AbstractSupply supply = new HpSupply(locationX, locationY, speedX, speedY, power);
        return supply;
    }
}
