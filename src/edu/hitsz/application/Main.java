package edu.hitsz.application;

import edu.hitsz.swing.Hello;
import edu.hitsz.swing.Table;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object MAIN_LOCK = new Object();

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Hello");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //第一个界面:开始界面
        Hello helloFrame = new Hello();
        JPanel helloPanel = helloFrame.getPanel();
        frame.setContentPane(helloPanel);
        frame.setVisible(true);

        synchronized (MAIN_LOCK) {
            while (helloPanel.isVisible()) {
                // 主线程等待菜单面板关闭
                try {
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //移除开始界面
        frame.remove(helloPanel);

        //第二个界面:游戏界面
        Game game = new Game();
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        synchronized (MAIN_LOCK) {
            while (game.isVisible()) {
                // 主线程等待菜单面板关闭
                try {
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        frame.remove(game);

        try {
            Table tableFrame = new Table(game.getScore(),helloFrame.getMode());
            JPanel tablePanel = tableFrame.getPanel();
            frame.setContentPane(tablePanel);
            frame.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
