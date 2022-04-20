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
        hpSupply = new HpSupply(Main.WINDOW_WIDTH / 2,0,0,50);
    }

    @AfterEach
    void tearDown() {
        hpSupply = null;
    }

    @DisplayName("Test HpSupplyCrash method")
    @Test
    void crash() {
        heroAircraft = HeroAircraft.getInstance();
    }

    @DisplayName("Test function method")
    @Test
    void function() {
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.decreaseHp(50);
        hpSupply.function();
        assumeTrue(hpSupply.crash(heroAircraft));
        assertEquals(75,heroAircraft.getHp());
    }
}