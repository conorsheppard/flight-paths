package com.conorsheppard;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        findAllPaths(fromIndex, toIndex, new ArrayList<>(), 0);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FlightPathApp()).execute(args);
        System.exit(exitCode);
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

    private void findAllPaths(int from, int to, List<Integer> path, int cost) {
        path.add(from);

        if (from == to) {
            System.out.println(formatPath(path) + ": " + cost);
        } else {
            for (int next = from + 1; next < costs.length; next++) {
                if (costs[from][next] > 0) {
                    findAllPaths(next, to, new ArrayList<>(path), cost + costs[from][next]);
                }
            }
        }
    }

    private String formatPath(List<Integer> path) {
        return path.stream()
                .map(this::getCityName)
                .collect(Collectors.joining(" → "));
    }

    private String getCityName(int index) {
        return cityToIndex.entrySet().stream()
                .filter(e -> e.getValue() == index)
                .map(Map.Entry::getKey)
                .map(this::capitalize)
                .findFirst()
                .orElse("Unknown");
    }
}
