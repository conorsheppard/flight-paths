package com.flightpaths;

import com.flightpaths.app.FlightPathsCalculator;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "flight-paths", mixinStandardHelpOptions = true, version = "1.0",
        description = "Shows flight costs between Game of Thrones cities")
public class Main implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Departure city")
    private String from;

    @CommandLine.Parameters(index = "1", description = "Destination city")
    private String to;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        var costs = new int[][]{{0, 15, 80, 90}, {0, 0, 40, 50}, {0, 0, 0, 70}, {0, 0, 0, 0}};
        Map<String, Integer> cities = Map.of("castle black", 0, "winterfell", 1, "riverrun", 2, "king's landing", 3);
        var flightPaths = new FlightPathsCalculator(costs, cities);
        var paths = new ArrayList<String>();
        int exitCode = flightPaths.getAllPaths(from, to, paths);
        if (exitCode != 0) return exitCode;
        paths.forEach(System.out::println);
        return 0;
    }
}
