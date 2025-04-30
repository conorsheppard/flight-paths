package com.conorsheppard.dfs;

import java.util.*;

public class FlightPathsDFS {
    static String[] cities = {"Castle Black", "Winterfell", "Riverrun", "King's Landing"};

    public static void main(String[] args) {
        int[][] costs = {
                {0, 15, 80, 90},
                {0, 0, 40, 50},
                {0, 0, 0, 70},
                {0, 0, 0, 0}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start city:");
        String start = scanner.nextLine();
        System.out.println("Enter end city:");
        String end = scanner.nextLine();

        int startIndex = findCityIndex(start);
        int endIndex = findCityIndex(end);

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Invalid cities entered.");
            return;
        }

        List<String> path = new ArrayList<>();
        path.add(cities[startIndex]);
        dfs(costs, startIndex, endIndex, 0, path);
    }

    static void dfs(int[][] costs, int current, int destination, int totalCost, List<String> path) {
        if (current == destination) {
            System.out.println(String.join(" -> ", path) + " : " + totalCost + " Silver Stags");
            return;
        }

        for (int next = current + 1; next < costs.length; next++) {
            if (costs[current][next] > 0) {
                path.add(cities[next]);
                dfs(costs, next, destination, totalCost + costs[current][next], path);
                path.remove(path.size() - 1); // backtrack
            }
        }
    }

    static int findCityIndex(String city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(city)) {
                return i;
            }
        }
        return -1;
    }
}
