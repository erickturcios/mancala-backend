package com.bol.games.mancala.controllers;

import com.bol.games.mancala.constant.IntegrationTest;
import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.service.ConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConfigurationController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@IntegrationTest
class ConfigurationControllerTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConfigurationService configurationService;

    @Test
    @Order(1)
    void getList() throws Exception {


        var initialList = new ArrayList<>();
        initialList.add(
                new Configuration(1L, "Session-01",
                        3, true, false,
                        "Alias-01-01", "Alias-02-01",
                        new Date(), null)
        );
        initialList.add(
                new Configuration(2L, "Session-02",
                        4, false, false,
                        "Alias-01-02", "Alias-02-02",
                        new Date(), null)
        );
        initialList.add(
                new Configuration(3L, "Session-03",
                        6, true, true,
                        "Alias-01-03", "Alias-02-03",
                        new Date(), null)
        );

        int pageSize = 10;
        int pageNumber = 0;

        doReturn(initialList).when(configurationService).getList(pageNumber, pageSize);

        mvc.perform(MockMvcRequestBuilders
                        .get("/configuration?pageNumber=0&size=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
        ;
    }

    @Test
    @Order(2)
    void getOne() throws Exception {

        var config = new Configuration(1L, SESSION,
                        3, true, false,
                        "Alias-01-01", "Alias-02-01",
                        new Date(), null);

        doReturn(config).when(configurationService).getOne(SESSION);

        mvc.perform(MockMvcRequestBuilders
                        .get("/configuration/" + SESSION)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(SESSION)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfStones", is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stepBackAllowed", is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autorotate", is(false)))
        ;
    }

    @Test
    @Order(3)
    void getOneNotFound() throws Exception {
        String any = "any";
        doReturn(null).when(configurationService).getOne(anyString());

        mvc.perform(MockMvcRequestBuilders
                        .get("/configuration/any")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }


    @Test
    @Order(4)
    void getOrCreate() throws Exception {

        var config = new Configuration(100L, SESSION,
                3, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);

        doReturn(config).when(configurationService).getOrCreate(SESSION);

        mvc.perform(MockMvcRequestBuilders
                        .post("/configuration/"+SESSION)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(config.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfStones", is(config.getNumberOfStones())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stepBackAllowed", is(config.getStepBackAllowed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autorotate", is(config.getAutorotate())))
        ;
    }

    @Test
    @Order(5)
    void save()  throws Exception{
        var config = new Configuration(100L, SESSION,
                3, true, false,
                "Alias-01-01", "Alias-02-01",
                new Date(), null);

        doReturn(config).when(configurationService).save(config);

        mvc.perform(MockMvcRequestBuilders
                        .post("/configuration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(config))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameSession", is(config.getGameSession())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfStones", is(config.getNumberOfStones())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stepBackAllowed", is(config.getStepBackAllowed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autorotate", is(config.getAutorotate())))
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