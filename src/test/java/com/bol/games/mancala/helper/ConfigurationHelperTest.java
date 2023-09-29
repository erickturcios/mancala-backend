package com.bol.games.mancala.helper;

import com.bol.games.mancala.constant.UnitTest;
import com.bol.games.mancala.dto.ConfigurationDto;
import com.bol.games.mancala.jpa.Configuration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@UnitTest
class ConfigurationHelperTest {


    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";

    @Test
    @Order(1)
    void toEntity() {
        var stones = 5;
        var config = new ConfigurationDto(100L, SESSION,
                stones, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);

        var entity = ConfigurationHelper.toEntity(config);
        assertEquals(config.getGameSession(), entity.getGameSession());
        assertEquals(config.getAutorotate(), entity.getAutorotate());
        assertEquals(config.getId(), entity.getId());
        assertEquals(config.getStepBackAllowed(), entity.getStepBackAllowed());
        assertEquals(config.getAlias1(), entity.getAlias1());
        assertEquals(config.getAlias2(), entity.getAlias2());
        assertEquals(config.getNumberOfStones(), entity.getNumberOfStones());
    }

    @Test
    @Order(1)
    void toDto() {
        var stones = 5;
        var config = new Configuration(100L, SESSION,
                stones, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);

        var dto = ConfigurationHelper.toDto(config);
        assertEquals(config.getGameSession(), dto.getGameSession());
        assertEquals(config.getAutorotate(), dto.getAutorotate());
        assertEquals(config.getId(), dto.getId());
        assertEquals(config.getStepBackAllowed(), dto.getStepBackAllowed());
        assertEquals(config.getAlias1(), dto.getAlias1());
        assertEquals(config.getAlias2(), dto.getAlias2());
        assertEquals(config.getNumberOfStones(), dto.getNumberOfStones());
    }
}