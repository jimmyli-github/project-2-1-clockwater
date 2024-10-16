package puzzles.water;

import puzzles.common.solver.Configuration;

import java.util.*;

/**
 * The WaterConfig for the Water class. It provides all
 * the information needed to find a solution with solver.
 */
public class WaterConfig implements Configuration {
    /** The goal amount **/
    private int amount;
    /** The list of buckets that states the max amount of gallons **/
    private List<Integer> buckets;
    /** The list of buckets with the current amount of water **/
    private List<Integer> currBuckets;

    /**
     * The WaterConfig constructor which sets amount, buckets, and currBuckets to its private variable.
     * @param amount The goal amount
     * @param buckets The list of buckets that states the max amount of gallons
     * @param currBuckets The list of buckets with the current amount of water
     */
    public WaterConfig(int amount, List<Integer> buckets, List<Integer> currBuckets) {
        this.amount = amount;
        this.buckets = buckets;
        this.currBuckets = currBuckets;
    }

    /**
     * The getter method for currBuckets.
     * @return The list of buckets with the current amount of water
     */
    public List<Integer> getCurrBuckets() {
        return currBuckets;
    }

    /**
     * Checks to see if a solution is found by seeing if amount is in currBuckets.
     * @return boolean for whether a solution is found
     */
    @Override
    public boolean isSolution() {
        return currBuckets.contains(amount);
    }

    /**
     * Gets the neighbor buckets. One of the neighbors is when the bucket is
     * filled to the max. The second neighbor is when the water in the bucket
     * is dumped out to 0 gallons of water. The third neighbor is when the
     * water in the bucket is poured into another bucket. Conditions are checked
     * to ensure that the neighbor is possible.
     * @return a collection full of neighbor buckets
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> collection = new LinkedList<>();
        for (int i = 0; i < currBuckets.size(); i++) {
            if (!Objects.equals(currBuckets.get(i), buckets.get(i))) {
                List<Integer> neighbor = new ArrayList<>(currBuckets);
                neighbor.set(i, buckets.get(i));
                collection.add(new WaterConfig(amount, buckets, neighbor));
            }
            if (currBuckets.get(i) != 0) {
                List<Integer> neighbor = new ArrayList<>(currBuckets);
                neighbor.set(i, 0);
                collection.add(new WaterConfig(amount, buckets, neighbor));
            }
            if (currBuckets.get(i) != 0) {
                for (int j = 0; j < currBuckets.size(); j++) {
                    if (i != j && !Objects.equals(currBuckets.get(j), buckets.get(j))) {
                        int diff = buckets.get(j) - currBuckets.get(j);
                        List<Integer> neighbor = new ArrayList<>(currBuckets);
                        if (currBuckets.get(i) >= diff) {
                            neighbor.set(j, buckets.get(j));
                            neighbor.set(i, currBuckets.get(i) - diff);
                        }
                        else {
                            neighbor.set(j, currBuckets.get(j) + currBuckets.get(i));
                            neighbor.set(i, 0);
                        }
                        collection.add(new WaterConfig(amount, buckets, neighbor));
                    }
                }
            }
        }
        return collection;
    }

    /**
     * Checks to see if this is equal to another WaterConfig. It
     * first checks if other is an instanceof WaterConfig. It returns
     * true if both clockConfigs have the same currBuckets and amount.
     * @param other the object being compared to with this
     * @return boolean that shows if this is equal to other
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof WaterConfig waterConfig) {
            for (int i = 0; i < currBuckets.size(); i++) {
                if (!Objects.equals(currBuckets.get(i), waterConfig.currBuckets.get(i))) {
                    return false;
                }
            }
            return amount == waterConfig.amount;
        }
        return false;
    }

    /**
     * Creates a hashcode by adding amount, the hashcode of buckets, and the hashcode of currBuckets.
     * @return an int representing a hashcode
     */
    @Override
    public int hashCode() {
        return amount + buckets.hashCode() + currBuckets.hashCode();
    }

    /**
     * A string representation of waterConfig with its
     * amount and buckets.
     * @return a string that represents the waterConfig object
     */
    @Override
    public String toString() {
        return "Amount: " + amount + ", Buckets: " + buckets;
    }
}
