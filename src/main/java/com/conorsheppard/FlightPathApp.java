package com.conorsheppard;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "flight-paths", mixinStandardHelpOptions = true, version = "1.0",
        description = "Calculates flight path costs between cities.")
public class FlightPathApp implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Start city index (0 = Castle Black)")
    private int from;

    @CommandLine.Parameters(index = "1", description = "Destination city index (3 = King's Landing)")
    private int to;

    private static final int[][] costs = {
            {0, 15, 80, 90},
            {0, 0, 40, 50},
            {0, 0, 0, 70},
            {0, 0, 0, 0}
    };

    private static final String[] cities = {
            "Castle Black", "Winterfell", "Riverrun", "King's Landing"
    };

    @Override
    public Integer call() {
        if (from < 0 || to >= costs.length || from >= to) {
            System.out.printf("Invalid flight path from %s to %s.%n", cities[from], cities[to]);
            return 1;
        }
        int cost = costs[from][to];
        System.out.printf("Flying from %s to %s costs %d Silver Stags.%n", cities[from], cities[to], cost);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FlightPathApp()).execute(args);
        System.exit(exitCode);
    }
}
