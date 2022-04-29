package thread;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShoot;
import edu.hitsz.strategy.CommonShoot;

/**
 * 射击道具生效线程
 *
 * @author hitsz
 */
public class ShootThread implements Runnable {
    private volatile boolean exit = false;
    protected HeroAircraft heroThread = HeroAircraft.getInstance();
    @Override
    public void run(){
        while(!exit){
            try {
                heroThread.setShootNum(3);
                heroThread.context.setStrategy(new ScatterShoot());
                Thread.sleep(5000);
                heroThread.setShootNum(1);
                heroThread.context.setStrategy(new CommonShoot());
                exit = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
