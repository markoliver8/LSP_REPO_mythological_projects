
package org.howard.edu.lsp.assignment6;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers.
 * Duplicate values are not allowed.
 * Provides basic set operations and comparisons.
 *
 * @author John Oliver
 */
public class IntegerSet {

    // Stores the integers in the set.
    private HashSet<Integer> elements;

    /**
     * Constructs an empty IntegerSet.
     */
    public IntegerSet() {
        elements = new HashSet<Integer>();
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    public int length() {
        return elements.size();
    }

    /**
     * Checks whether two sets contain the same elements.
     *
     * @param b the set being compared
     * @return true if both sets contain the same elements
     */
    public boolean equals(IntegerSet b) {
        return elements.equals(b.elements);
    }

    /**
     * Checks whether a value exists in the set.
     *
     * @param value the integer to search for
     * @return true if the value exists
     */
    public boolean contains(int value) {
        return elements.contains(value);
    }

    /**
     * Returns the largest integer in the set.
     *
     * @return the largest integer
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }

        int largest = Integer.MIN_VALUE;

        for (int value : elements) {
            if (value > largest) {
                largest = value;
            }
        }

        return largest;
    }

    /**
     * Returns the smallest integer in the set.
     *
     * @return the smallest integer
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }

        int smallest = Integer.MAX_VALUE;

        for (int value : elements) {
            if (value < smallest) {
                smallest = value;
            }
        }

        return smallest;
    }

    /**
     * Adds an integer to the set.
     * Duplicate values are ignored.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        elements.add(item);
    }

    /**
     * Removes an integer from the set.
     * Does nothing if the integer is absent.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        elements.remove(item);
    }

    /**
     * Returns a new set containing elements from either set.
     *
     * @param b the second set
     * @return a new set containing the union
     */
    public IntegerSet union(IntegerSet b) {
        IntegerSet result = new IntegerSet();

        result.elements.addAll(this.elements);
        result.elements.addAll(b.elements);

        return result;
    }

    /**
     * Returns a new set containing elements shared by both sets.
     *
     * @param b the second set
     * @return a new set containing the intersection
     */
    public IntegerSet intersect(IntegerSet b) {
        IntegerSet result = new IntegerSet();

        for (int value : elements) {
            if (b.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns elements in the current set that are not in b.
     *
     * @param b the second set
     * @return a new set containing the difference
     */
    public IntegerSet diff(IntegerSet b) {
        IntegerSet result = new IntegerSet();

        for (int value : elements) {
            if (!b.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns elements in b that are not in the current set.
     *
     * @param b the second set
     * @return a new set containing the complement
     */
    public IntegerSet complement(IntegerSet b) {
        IntegerSet result = new IntegerSet();

        for (int value : b.elements) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Checks whether every element in the current set exists in b.
     *
     * @param b the second set
     * @return true if the current set is a subset of b
     */
    public boolean isSubsetOf(IntegerSet b) {
        for (int value : elements) {
            if (!b.contains(value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether two sets have no elements in common.
     *
     * @param b the second set
     * @return true if the sets are disjoint
     */
    public boolean isDisjoint(IntegerSet b) {
        for (int value : elements) {
            if (b.contains(value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a new set containing elements that occur in
     * exactly one of the two sets.
     *
     * @param b the second set
     * @return a new set containing the symmetric difference
     */
    public IntegerSet symmetricDiff(IntegerSet b) {
        IntegerSet result = new IntegerSet();

        for (int value : elements) {
            if (!b.contains(value)) {
                result.add(value);
            }
        }

        for (int value : b.elements) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Checks whether the set contains no elements.
     *
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns the elements in ascending numerical order.
     *
     * @return a formatted string representing the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<Integer>(elements);

        Collections.sort(sorted);

        return sorted.toString();
    }
}
