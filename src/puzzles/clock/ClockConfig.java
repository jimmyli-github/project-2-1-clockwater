package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The ClockConfig for the Clock class. It provides all
 * the information needed to find a solution with solver.
 */
public class ClockConfig implements Configuration {
    /** The hours in the clock **/
    private int hours;
    /** The starting hour **/
    private int start;
    /** The ending hour **/
    private int end;

    /**
     * The ClockConfig constructor which sets hours, start, and end to its private variable.
     * @param hours The hours in the clock
     * @param start The starting hour
     * @param end The ending hour
     */
    public ClockConfig(int hours, int start, int end) {
        this.hours = hours;
        this.start = start;
        this.end = end;
    }

    /**
     * The getter method for start.
     * @return The starting hour
     */
    public int getStart() {
        return start;
    }

    /**
     * Checks to see if a solution is found by seeing if start is equal to end.
     * @return boolean for whether a solution is found
     */
    @Override
    public boolean isSolution() {
        return this.start == end;
    }

    /**
     * Gets the neighbor hours. The neighbors are the hours before and after start.
     * The neighbor hours will also adjust itself for if before is less than 1 and if after
     * is greater than hours.
     * @return a collection full of neighbor hours
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        int before = start - 1;
        if (before < 1) {
            before = hours;
        }
        int after = start + 1;
        if (after > hours) {
            after = 1;
        }
        Collection<Configuration> collection = new LinkedList<>();
        collection.add(new ClockConfig(hours, before, end));
        collection.add(new ClockConfig(hours, after, end));
        return collection;
    }

    /**
     * Checks to see if this is equal to another clockConfig. It
     * first checks if other is an instanceof clockConfig. It returns
     * true if both clockConfigs have the same hours, start, and end.
     * Otherwise, it will return false.
     * @param other the object being compared to with this
     * @return boolean that shows if this is equal to other
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ClockConfig clockConfig) {
            return hours == clockConfig.hours && start == clockConfig.start && end == clockConfig.end;
        }
        return false;
    }

    /**
     * Creates a hashcode by adding hours, start, and end.
     * @return an int representing a hashcode
     */
    @Override
    public int hashCode() {
        return hours + start + end;
    }

    /**
     * A string representation of clockConfig with its
     * hours, start, and end variables.
     * @return a string that represents the clockConfig object
     */
    @Override
    public String toString() {
        return "Hours: " + hours + ", Start: " + start + ", End: " + end;
    }
}
