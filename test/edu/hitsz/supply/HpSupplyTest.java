package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


class HpSupplyTest {
    private HpSupply hpSupply;
    private HeroAircraft heroAircraft;

    @BeforeEach
    void setUp() {
        hpSupply = new HpSupply(Main.WINDOW_WIDTH / 2,0,0,50,30);
    }

    @AfterEach
    void tearDown() {
        hpSupply = null;
    }

    @DisplayName("Test HpSupplyCrash method")
    @Test
    void crash() {
        heroAircraft = HeroAircraft.getInstance(Main.WINDOW_WIDTH / 2,0,0,0,100);
        assertTrue(hpSupply.crash(heroAircraft));
        hpSupply.forward();
        assertFalse(hpSupply.crash(heroAircraft));
    }

    @DisplayName("Test function method")
    @Test
    void function() {
        heroAircraft = HeroAircraft.getInstance(Main.WINDOW_WIDTH / 2,0,0,0,100);
        assumeTrue(hpSupply.crash(heroAircraft));
        assertEquals(30,hpSupply.function());
    }
}