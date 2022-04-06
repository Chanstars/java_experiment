package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {
    private HeroAircraft heroAircraft;
    @BeforeEach
    void setUp() {
        heroAircraft = HeroAircraft.getInstance(
                Main.WINDOW_WIDTH / 2,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                0, 0, 100);
    }

    @AfterEach
    void tearDown() {
        heroAircraft = null;
    }

    @DisplayName("Test HeroAircraftDecreaseHp method")
    @Test
    void decreaseHp() {
        heroAircraft.decreaseHp(50);
        assertEquals(50,heroAircraft.getHp());
        heroAircraft.decreaseHp(100);
        assertEquals(0,heroAircraft.getHp());
        heroAircraft.decreaseHp(-150);
        assertEquals(100,heroAircraft.getHp());

    }

    @DisplayName("Test HeroAircraftShoot method")
    @Test
    void shoot() {
        assertNotNull(heroAircraft.shoot());
        for(BaseBullet heroBullet : heroAircraft.shoot()){
            assertAll(
                    () -> assertEquals(heroAircraft.getLocationX(),heroBullet.getLocationX()),
                    () -> assertEquals(heroAircraft.getLocationY() -2,heroBullet.getLocationY()),
                    () -> assertEquals(0,heroBullet.getSpeedX()),
                    () -> assertEquals(heroAircraft.getSpeedY() -5,heroBullet.getSpeedY())
            );
        }
    }
}