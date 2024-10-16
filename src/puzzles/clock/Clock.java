package puzzles.clock;

import puzzles.common.solver.*;
import java.util.List;

/**
 * Main class for the clock puzzle.
 *
 * @author Jimmy Li
 */
public class Clock {
    /**
     * Run an instance of the clock puzzle.
     *
     * @param args [0]: the number of hours in the clock;
     *             [1]: the starting hour;
     *             [2]: the finish hour.
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println(("Usage: java Clock hours start finish"));
        } else {
            // TODO YOUR MAIN CODE HERE
            int hours = Integer.parseInt(args[0]);
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);
            ClockConfig clockConfig = new ClockConfig(hours, start, end);
            Solver solver = new Solver();
            System.out.println(clockConfig);
            List<Configuration> list = solver.solve(clockConfig);
            System.out.println("Total configs: " + solver.getTotalConfig());
            System.out.println("Unique configs: " + solver.getUniqueConfigs());
            if (list.isEmpty()) {
                System.out.println("No solution");
            }
            else {
                int count = 0;
                for (Configuration configuration : list) {
                    ClockConfig clockConfig1 = (ClockConfig) configuration;
                    System.out.println("Step " + count++ + ": " + clockConfig1.getStart());
                }
            }
        }
    }
}
