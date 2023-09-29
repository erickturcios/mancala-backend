package com.bol.games.mancala.controllers;

import com.bol.games.mancala.constant.IntegrationTest;
import com.bol.games.mancala.dto.ConfigurationDto;
import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.dto.MovementDto;
import com.bol.games.mancala.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@IntegrationTest
class GameControllerTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
    @Autowired
    private MockMvc mvc;

    @MockBean
    GameService gameService;

    @Test
    @Order(1)
    void createGame() throws Exception {
        final int stones = 5;

        int[] p1 = new int[]{
                stones,
                stones,
                stones,
                stones,
                stones,
                stones,
        };

        int[] p2 = new int[]{
                stones,
                stones,
                stones,
                stones,
                stones,
                stones,
        };

        var gameDto = new GameDto(
                7L, SESSION,
                p1, 0,
                p2, 0,
                1,
                0,
                new Date(),
                null);

        var config = new ConfigurationDto(100L, SESSION,
                stones, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);
        gameDto.setConfiguration(config);

        doReturn(gameDto).when(gameService).createGame(SESSION);

        mvc.perform(MockMvcRequestBuilders
                        .post("/game/"+SESSION)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.numberOfStones", is(config.getNumberOfStones())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.stepBackAllowed", is(config.getStepBackAllowed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.autorotate", is(config.getAutorotate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[0]", is(p1[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[1]", is(p1[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[2]", is(p1[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[3]", is(p1[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[4]", is(p1[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[5]", is(p1[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[0]", is(p2[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[1]", is(p2[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[2]", is(p2[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[3]", is(p2[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[4]", is(p2[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[5]", is(p2[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer1", is(gameDto.getTotalPlayer1())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer2", is(gameDto.getTotalPlayer2())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerToMoveNext", is(gameDto.getPlayerToMoveNext())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner", is(gameDto.getWinner())))

        ;
    }

    @Test
    @Order(2)
    void move01()  throws Exception {

        int[] p1 = new int[]{
                0,
                7,
                7,
                7,
                7,
                7,
        };

        int[] p2 = new int[]{
                6,
                6,
                6,
                6,
                6,
                6,
        };

        var gameDto = new GameDto(
                7L, SESSION,
                p1, 1,
                p2, 0,
                1,
                0,
                new Date(),
                null);

        var config = new ConfigurationDto(100L, SESSION,
                6, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);
        gameDto.setConfiguration(config);

        doReturn(gameDto).when(gameService).move(SESSION, 1, 0);

        var movement = new MovementDto(SESSION, 1, 0);

        mvc.perform(MockMvcRequestBuilders
                        .put("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(movement)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.numberOfStones", is(config.getNumberOfStones())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.stepBackAllowed", is(config.getStepBackAllowed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.autorotate", is(config.getAutorotate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[0]", is(p1[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[1]", is(p1[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[2]", is(p1[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[3]", is(p1[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[4]", is(p1[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[5]", is(p1[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[0]", is(p2[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[1]", is(p2[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[2]", is(p2[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[3]", is(p2[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[4]", is(p2[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[5]", is(p2[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer1", is(gameDto.getTotalPlayer1())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer2", is(gameDto.getTotalPlayer2())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerToMoveNext", is(gameDto.getPlayerToMoveNext())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner", is(gameDto.getWinner())))

        ;
    }

    @Test
    void undoLastMovement()  throws Exception {
        final int stones = 5;

        int[] p1 = new int[]{
                stones,
                stones,
                stones,
                stones,
                stones,
                stones,
        };

        int[] p2 = new int[]{
                stones,
                stones,
                stones,
                stones,
                stones,
                stones,
        };

        var gameDto = new GameDto(
                7L, SESSION,
                p1, 0,
                p2, 0,
                1,
                0,
                new Date(),
                null);

        var config = new ConfigurationDto(100L, SESSION,
                stones, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);
        gameDto.setConfiguration(config);

        doReturn(gameDto).when(gameService).undoLastMovement(SESSION);

        mvc.perform(MockMvcRequestBuilders
                        .put("/game/undoMovement/"+SESSION)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.gameSession", is(gameDto.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.numberOfStones", is(config.getNumberOfStones())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.stepBackAllowed", is(config.getStepBackAllowed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.configuration.autorotate", is(config.getAutorotate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[0]", is(p1[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[1]", is(p1[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[2]", is(p1[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[3]", is(p1[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[4]", is(p1[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player01[5]", is(p1[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[0]", is(p2[0])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[1]", is(p2[1])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[2]", is(p2[2])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[3]", is(p2[3])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[4]", is(p2[4])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player02[5]", is(p2[5])))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer1", is(gameDto.getTotalPlayer1())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPlayer2", is(gameDto.getTotalPlayer2())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerToMoveNext", is(gameDto.getPlayerToMoveNext())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner", is(gameDto.getWinner())))

        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}