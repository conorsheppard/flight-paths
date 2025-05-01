package com.conorsheppard;

import picocli.CommandLine;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@CommandLine.Command(name = "flight-paths", mixinStandardHelpOptions = true, version = "1.0",
        description = "Shows flight costs between Game of Thrones cities")
public class FlightPathApp implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Departure city")
    private String from;

    @CommandLine.Parameters(index = "1", description = "Destination city")
    private String to;

    private static final int[][] costs = {
            {0, 15, 80, 90},
            {0, 0, 40, 50},
            {0, 0, 0, 70},
            {0, 0, 0, 0}
    };

    private static final Map<String, Integer> cityToIndex = Map.of(
            "castle black", 0,
            "winterfell", 1,
            "riverrun", 2,
            "king's landing", 3
    );

    @Override
    public Integer call() {
        int fromIndex = getCityIndex(from);
        int toIndex = getCityIndex(to);

        if (fromIndex >= toIndex) {
            System.out.printf("Invalid direction: you can only fly north to south.%n");
            return 1;
        }

        int cost = costs[fromIndex][toIndex];
        if (cost == 0) {
            System.out.printf("No direct flight from %s to %s.%n", capitalize(from), capitalize(to));
        } else {
            System.out.printf("Flying from %s to %s costs %d Silver Stags.%n",
                    capitalize(from), capitalize(to), cost);
        }

        return 0;
    }

    private int getCityIndex(String name) {
        String normalized = name.toLowerCase().trim();
        if (!cityToIndex.containsKey(normalized)) {
            throw new IllegalArgumentException("Unknown city: " + name);
        }
        return cityToIndex.get(normalized);
    }

    private String capitalize(String input) {
        return Arrays.stream(input.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FlightPathApp()).execute(args);
        System.exit(exitCode);
    }
}
