package com.codecool.stockApp;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// TODO
class TraderTest {

    private Logger logger;
    private RemoteURLReader remoteURLReader;
    private StockAPIService stockAPIService;
    private Trader trader;


    @BeforeEach
    void setup() throws IOException {
        logger = mock(Logger.class);
        remoteURLReader = mock(RemoteURLReader.class);
        stockAPIService = mock(StockAPIService.class);
        stockAPIService.setRemoteURLReader(remoteURLReader);

        trader = new Trader(logger, stockAPIService);

        when(stockAPIService.getPrice("aapl")).thenReturn((double) 250);
    }



    @Test // Bid was lower than price, buy should return false.
    void testBidLowerThanPrice() throws IOException {

        assertFalse(trader.buy("aapl", 200));


    }

    @Test // bid was equal or higher than price, buy() should return true.
    void testBidHigherThanPrice() throws IOException {

        assertTrue(trader.buy("aapl", 300));
    }
}