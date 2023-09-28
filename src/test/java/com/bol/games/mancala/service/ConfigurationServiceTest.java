package com.bol.games.mancala.service;

import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.repository.ConfigurationRepository;
import com.bol.games.mancala.repository.GameSessionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConfigurationServiceTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";

    @Autowired
    ConfigurationServiceImpl configurationService;

    @MockBean
    ConfigurationRepository configurationRepository;
    @MockBean
    private GameSessionRepository gameSessionRepository;
    @Mock
    private Pageable pageableMock;

    @BeforeAll
    static void setUp() {
    }

    @Test
    @Order(1)
    void getList() {

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

        var pageRequest = PageRequest.of(pageNumber, pageSize);
        var page = new PageImpl<>(initialList);

        //mock response
        doReturn(page).when(configurationRepository).findAll(pageRequest);

        //Test
        var config = configurationService.getList(pageNumber,pageSize);

        //assertion
        assertEquals(3, config.size() );
    }

    @Test
    @Order(2)
    void getOne() {
        var config = new Configuration(1L, SESSION,
                3, true, false,
                "Alias: Player 1", "Alias: Player 2",
                new Date(), null);

        doReturn(config).when(configurationRepository).findByGameSession(SESSION);
        doReturn(true).when(gameSessionRepository).existsBySessionId(SESSION);

        var obtainedConfig = configurationService.getOne(SESSION);

        assertEquals(1L, obtainedConfig.getId());
        assertEquals(SESSION, obtainedConfig.getGameSession());
        assertEquals(3, obtainedConfig.getNumberOfStones());

    }

    @Test
    void getOrCreate() {

        var config = new Configuration(2L, SESSION,
                3, true, false,
                "Alias: Player 1 Modified", "Alias: Player 2 Modified",
                new Date(), null);

        doReturn(true).when(gameSessionRepository).existsBySessionId(SESSION);
        doReturn(config).when(configurationRepository).findByGameSession(SESSION);

        var obj = this.configurationService.getOrCreate(SESSION);

        assertEquals(2L, obj.getId());
        assertEquals(SESSION, obj.getGameSession());
        assertEquals(3, obj.getNumberOfStones());
        assertEquals("Alias: Player 1 Modified", obj.getAlias1());
        assertEquals("Alias: Player 2 Modified", obj.getAlias2());
    }

    @Test
    void save() {

        var configOld = new Configuration(2L, SESSION,
                3, true, false,
                "Alias: Player 1", "Alias: Player 2",
                new Date(), null);
        var configNew = new Configuration(2L, SESSION,
                3, true, false,
                "Alias: Player 1 Modified", "Alias: Player 2 Modified",
                new Date(), null);

        doReturn(configOld).when(configurationRepository).findByGameSession(SESSION);
        doReturn(configNew).when(configurationRepository).save(configNew);
        var obj = this.configurationService.save(configNew);

        assertEquals(2L, obj.getId());
        assertEquals(SESSION, obj.getGameSession());
        assertEquals(3, obj.getNumberOfStones());
        assertEquals("Alias: Player 1 Modified", obj.getAlias1());
        assertEquals("Alias: Player 2 Modified", obj.getAlias2());

    }
}