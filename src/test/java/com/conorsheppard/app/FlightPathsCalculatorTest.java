package com.conorsheppard.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FlightPathsCalculatorTest {

    @Test
    void testAllPathsCastleBlackToKingsLanding() {
        var costs = new int[][]{{0, 15, 80, 90}, {0, 0, 40, 50}, {0, 0, 0, 70}, {0, 0, 0, 0}};
        Map<String, Integer> cities = Map.of("castle black", 0, "winterfell", 1, "riverrun", 2, "king's landing", 3);
        var app = new FlightPathsCalculator(costs, cities);
        var paths = new ArrayList<String>();
        var exitCode = app.getAllPaths("castle black", "king's landing", paths);

        assertEquals(0, exitCode);
        assertFalse(paths.isEmpty());
        assertEquals(4, paths.size());
        assertTrue(paths.contains("Castle Black -> Winterfell -> Riverrun -> King's Landing: 125"));
        assertTrue(paths.contains("Castle Black -> Winterfell -> King's Landing: 65"));
        assertTrue(paths.contains("Castle Black -> Riverrun -> King's Landing: 150"));
        assertTrue(paths.contains("Castle Black -> King's Landing: 90"));
    }
//
//    @Test
//    void testNoPathsWinterfellToCastleBlack() {
//        FlightPathsCalculator app = new FlightPathsCalculator();
//        Exception ex = assertThrows(IllegalArgumentException.class,
//                () -> app.getAllPaths("winterfell", "castle black", new ArrayList<>()));
//        assertTrue(ex.getMessage().contains("Invalid direction"));
//    }
//
//    @Test
//    void testUnknownCity() {
//        FlightPathsCalculator app = new FlightPathsCalculator();
//        assertThrows(IllegalArgumentException.class, () ->
//                app.getAllPaths("essos", "king's landing"));
//    }
}
