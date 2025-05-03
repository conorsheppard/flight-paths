package com.flightpaths.app;

import com.flightpaths.testutil.SystemOutCaptureExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SystemOutCaptureExtension.class)
class FlightPathsCalculatorTest {
    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        "castle black",
                        "king's landing",
                        new int[][]{
                                {0, 15, 80, 90},
                                {0, 0, 40, 50},
                                {0, 0, 0, 70},
                                {0, 0, 0, 0}
                        },
                        Map.of(
                                "castle black", 0,
                                "winterfell", 1,
                                "riverrun", 2,
                                "king's landing", 3
                        ),
                        0,
                        "",
                        new String[]{
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing: 125",
                                "Castle Black -> Winterfell -> King's Landing: 65",
                                "Castle Black -> Riverrun -> King's Landing: 150",
                                "Castle Black -> King's Landing: 90"
                        }
                ),
                Arguments.of(
                        "a",
                        "d",
                        new int[][]{
                                {0, 10, 0, 30},
                                {0, 0, 20, 0},
                                {0, 0, 0, 10},
                                {0, 0, 0, 0}
                        },
                        Map.of("a", 0, "b", 1, "c", 2, "d", 3),
                        0,
                        "",
                        new String[]{
                                "A -> B -> C -> D: 40",
                                "A -> D: 30"
                        }
                ),
                Arguments.of(
                        "",
                        "",
                        new int[][]{},
                        Map.of(),
                        1,
                        "Unknown city:",
                        new String[]{}
                ),
                Arguments.of(
                        "castle black",
                        "dorne",
                        new int[][]{
                                { 0,  10,  20,  30,  40,  50,  60,  70 },
                                { 0,   0,  10,  20,  30,  40,  50,  60 },
                                { 0,   0,   0,  10,  20,  30,  40,  50 },
                                { 0,   0,   0,   0,  10,  20,  30,  40 },
                                { 0,   0,   0,   0,   0,  10,  20,  30 },
                                { 0,   0,   0,   0,   0,   0,  10,  20 },
                                { 0,   0,   0,   0,   0,   0,   0,  10 },
                                { 0,   0,   0,   0,   0,   0,   0,   0 }
                        },
                        Map.of(
                                "castle black", 0,
                                "winterfell", 1,
                                "riverrun", 2,
                                "king's landing", 3,
                                "crownslands", 4,
                                "storm's end", 5,
                                "highgarden", 6,
                                "dorne", 7
                        ),
                        0,
                        "",
                        new String[]{
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Crownslands -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> King's Landing -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Crownslands -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Riverrun -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Crownslands -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> King's Landing -> Dorne: 70",
                                "Castle Black -> Winterfell -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Crownslands -> Dorne: 70",
                                "Castle Black -> Winterfell -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Storm's End -> Dorne: 70",
                                "Castle Black -> Winterfell -> Highgarden -> Dorne: 70",
                                "Castle Black -> Winterfell -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Crownslands -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Storm's End -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> King's Landing -> Dorne: 70",
                                "Castle Black -> Riverrun -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Riverrun -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> Crownslands -> Dorne: 70",
                                "Castle Black -> Riverrun -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> Storm's End -> Dorne: 70",
                                "Castle Black -> Riverrun -> Highgarden -> Dorne: 70",
                                "Castle Black -> Riverrun -> Dorne: 70",
                                "Castle Black -> King's Landing -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> King's Landing -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> King's Landing -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> King's Landing -> Crownslands -> Dorne: 70",
                                "Castle Black -> King's Landing -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> King's Landing -> Storm's End -> Dorne: 70",
                                "Castle Black -> King's Landing -> Highgarden -> Dorne: 70",
                                "Castle Black -> King's Landing -> Dorne: 70",
                                "Castle Black -> Crownslands -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Crownslands -> Storm's End -> Dorne: 70",
                                "Castle Black -> Crownslands -> Highgarden -> Dorne: 70",
                                "Castle Black -> Crownslands -> Dorne: 70",
                                "Castle Black -> Storm's End -> Highgarden -> Dorne: 70",
                                "Castle Black -> Storm's End -> Dorne: 70",
                                "Castle Black -> Highgarden -> Dorne: 70",
                                "Castle Black -> Dorne: 70"
                        }
                )

        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testAllPaths(String from, String to, int[][] costs, Map<String, Integer> cities, int expectedExitCode,
                      String errorMessage, String[] expectedPaths, SystemOutCaptureExtension out) {
        var app = new FlightPathsCalculator(costs, cities);
        var paths = new ArrayList<String>();
        var exitCode = app.getAllPaths(from, to, paths);
        if (exitCode == 1) assertEquals(errorMessage, out.getOutput());

        assertEquals(expectedExitCode, exitCode);
        assertEquals(expectedPaths.length, paths.size());
        for (String expected : expectedPaths) {
            assertTrue(paths.contains(expected));
        }
    }

    @Test
    void testNoPathsWinterfellToCastleBlack(SystemOutCaptureExtension out) {
        var app = new FlightPathsCalculator(new int[][] {{0, 15, 80, 90}, {0, 0, 40, 50}, {0, 0, 0, 70}, {0, 0, 0, 0}},
                Map.of("castle black", 0, "winterfell", 1, "riverrun", 2, "king's landing", 3));
        var exitCode = app.getAllPaths("winterfell", "castle black", new ArrayList<>());
        assertEquals(1, exitCode);
        assertEquals("Invalid direction: you can only fly north to south.", out.getOutput());
    }

    @Test
    void testUnknownCity(SystemOutCaptureExtension out) {
        var app = new FlightPathsCalculator(new int[][] {{0, 15, 80, 90}, {0, 0, 40, 50}, {0, 0, 0, 70}, {0, 0, 0, 0}},
                Map.of("castle black", 0, "winterfell", 1, "riverrun", 2, "king's landing", 3));
        int exitCode = app.getAllPaths("essos", "king's landing", new ArrayList<>());
        assertEquals(1, exitCode);
        assertEquals("Unknown city: essos", out.getOutput());
    }
}
