package puzzles.common.solver;

import java.util.*;

/**
 * A solver class that keep tracks of unique configs, total configs,
 * and looks for a solution using BFS.
 */
public class Solver {
    /** The number of unique configs **/
    private int uniqueConfigs;
    /** The number of total configs **/
    private int totalConfig;

    /**
     * A common solver that employs the BFS (breadth first search) algorithm in
     * order to find the least number of moves it takes to reach the solution.
     * @param configuration The configuration of a specific puzzle
     * @return The path of the solution if there is one
     */
    public List<Configuration> solve(Configuration configuration) {
        List<Configuration> queue = new LinkedList<>();
        queue.add(configuration);

        Map<Configuration, Configuration> predecessor = new HashMap<>();
        predecessor.put(configuration, null);

        Configuration end = null;
        totalConfig = 1;
        while (!queue.isEmpty()) {
            Configuration current = queue.remove(0);
            if (current.isSolution()) {
                end = current;
                break;
            }
            for (Configuration config : current.getNeighbors()) {
                totalConfig++;
                if (!predecessor.containsKey(config)) {
                    predecessor.put(config, current);
                    queue.add(config);
                }
            }
        }

        uniqueConfigs = predecessor.size();

        List<Configuration> path = new LinkedList<>();
        if(predecessor.containsKey(end)) {
            Configuration curr = end;
            while (curr != configuration) {
                path.add(0, curr);
                curr = predecessor.get(curr);
            }
            path.add(0, configuration);
        }

        return path;
    }

    /**
     * The getter method for unique configs.
     * @return the number of unique configs
     */
    public int getUniqueConfigs() {
        return uniqueConfigs;
    }

    /**
     * The getter method for total configs.
     * @return the number of total configs
     */
    public int getTotalConfig() {
        return totalConfig;
    }
}