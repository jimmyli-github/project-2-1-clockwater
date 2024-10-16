package puzzles.water;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the water buckets puzzle.
 *
 * @author Jimmy Li
 */
public class Water {

    /**
     * Run an instance of the water buckets puzzle.
     *
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    ("Usage: java Water amount bucket1 bucket2 ...")
            );
        } else {
            // TODO YOUR MAIN CODE HERE
            int amount = Integer.parseInt(args[0]);
            List<Integer> buckets = new ArrayList<>();
            for (int i = 1; i < args.length; i++) {
                buckets.add(Integer.valueOf(args[i]));
            }
            List<Integer> currbuckets = new ArrayList<>();
            for (int i = 0; i < buckets.size(); i++) {
                currbuckets.add(0);
            }
            WaterConfig waterConfig = new WaterConfig(amount, buckets, currbuckets);
            Solver solver = new Solver();
            System.out.println(waterConfig);
            List<Configuration> list = solver.solve(waterConfig);
            System.out.println("Total configs: " + solver.getTotalConfig());
            System.out.println("Unique configs: " + solver.getUniqueConfigs());
            if (list.isEmpty()) {
                System.out.println("No solution");
            }
            else {
                int count = 0;
                for (Configuration configuration : list) {
                    WaterConfig waterConfig1 = (WaterConfig) configuration;
                    System.out.println("Step " + count++ + ": " + waterConfig1.getCurrBuckets());
                }
            }
        }
    }
}
