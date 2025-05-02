package com.flightpaths.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightPathsCalculator {
    private final int[][] costs;
    private final Map<String, Integer> cities;

    public FlightPathsCalculator(int[][] costs, Map<String, Integer> cities) {
        this.costs = costs;
        this.cities = cities;
    }

    public int getAllPaths(String departureCity, String destinationCity, List<String> results) {
        int fromCityIndex = getCityIndex(departureCity);
        int toCityIndex = getCityIndex(destinationCity);

        if (fromCityIndex >= toCityIndex) {
            System.out.printf("Invalid direction: you can only fly north to south.%n");
            return 1;
        }

        findAllPathsCollect(fromCityIndex, toCityIndex, new ArrayList<>(), 0, results);
        return 0;
    }

    private void findAllPathsCollect(int from, int to, List<Integer> path, int cost, List<String> results) {
        path.add(from);
        if (from == to) {
            results.add(formatPath(path) + ": " + cost);
        } else {
            for (int next = from + 1; next < costs.length; next++) {
                if (costs[from][next] > 0) {
                    findAllPathsCollect(next, to, new ArrayList<>(path), cost + costs[from][next], results);
                }
            }
        }
    }

    private String formatPath(List<Integer> path) {
        return path.stream()
                .map(this::getCityName)
                .collect(Collectors.joining(" -> "));
    }

    private String getCityName(int index) {
        return cities.entrySet().stream()
                .filter(e -> e.getValue() == index)
                .map(Map.Entry::getKey)
                .map(this::capitalize)
                .findFirst()
                .orElse("Unknown");
    }

    private String capitalize(String input) {
        return Arrays.stream(input.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    private int getCityIndex(String name) {
        String normalized = name.toLowerCase().trim();
        if (!cities.containsKey(normalized)) {
            throw new IllegalArgumentException("Unknown city: " + name);
        }
        return cities.get(normalized);
    }
}
