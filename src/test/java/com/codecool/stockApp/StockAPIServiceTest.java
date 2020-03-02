package com.codecool.stockApp;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class StockAPIServiceTest {

    private Logger logger;
    private RemoteURLReader remoteURLReader;
    private StockAPIService stockAPIService;

    @BeforeEach
    public void setup() throws IOException {
        logger = mock(Logger.class);
        remoteURLReader = mock(RemoteURLReader.class);
        stockAPIService = new StockAPIService(remoteURLReader);

    }

    @Test // everything works
    void testGetPriceNormalValues() throws IOException {
        when(remoteURLReader.readFromUrl("https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl")).thenReturn("" +
                "{\n" +
                "            \"symbol\" : \"AAPL\",\n" +
                "                \"price\" : 273.0\n" +
                "        }");

        assertEquals(stockAPIService.getPrice("aapl"), 273.0);
    }

    @Test // readFromURL threw an exception
    void testGetPriceServerDown() throws IOException {
        when(remoteURLReader.readFromUrl("https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl")).thenThrow(new IOException());
        
        Assertions.assertThrows(IOException.class, () -> {
                stockAPIService.getPrice("aapl");
        });

    }

    @Test // readFromURL returned wrong JSON
    void testGetPriceMalformedResponse() throws IOException {
        when(remoteURLReader.readFromUrl("https://financialmodelingprep.com/api/v3/stock/real-time-price/aapl")).thenReturn("" +
                "{\n" +
                "            \"symbol\" : \"AAPL\",\n" +
                "                \"prie\" : 273.0\n" +
                "        }");

        Assertions.assertThrows(JSONException.class,  () -> { stockAPIService.getPrice("aapl"); });
    }

}