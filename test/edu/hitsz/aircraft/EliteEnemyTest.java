package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.HeroBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private EliteEnemy eliteEnemy;
    private AbstractFlyingObject FlyingObject_crash;
    private AbstractFlyingObject FlyingObject_notCrash;

    @BeforeEach
    void setUp() {
        eliteEnemy = new EliteEnemy(
                Main.WINDOW_WIDTH / 2,
                0,
                0,
                10,
                60
        );
    }

    @AfterEach
    void tearDown() {
        eliteEnemy = null;
    }

    @DisplayName("Test EliteEnemyDecreaseHp method")
    @Test
    void decreaseHp() {
        eliteEnemy.decreaseHp(30);
        assertEquals(30,eliteEnemy.getHp());
        eliteEnemy.decreaseHp(60);
        assertEquals(0,eliteEnemy.getHp());
    }

    @DisplayName("Test EliteEnemyCrash method")
    @Test
    void crash() {
        FlyingObject_crash = new HeroBullet(Main.WINDOW_WIDTH / 2,0,0,0,0);
        assertTrue(eliteEnemy.crash(FlyingObject_crash));
        FlyingObject_notCrash = new HeroBullet(Main.WINDOW_WIDTH / 2,Main.WINDOW_HEIGHT,0,0,0);
        assertFalse(eliteEnemy.crash(FlyingObject_notCrash));
    }
}