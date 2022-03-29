package edu.hitsz.supply;

/**
 * 道具生成工厂
 * @author hitsz
 */
public interface SupplyFactory {
    public Supply createSupply(int locationX, int locationY, int speedX, int speedY, int power);
}
