package com.github.williwadelmawisky.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper functions to work with lists.
 * 
 * @version 17.2.2025
 */
public abstract class Lists {

    /**
     * Converts a list of some type to a list of some other type
     * @param in The original list to turn to new values (original list not modified)
     * @param proc A function that modifes the original array element to new array element
     * @param <TIn> A type of the input list
     * @param <TOut> A type of the output list
     * @return A list of modified in elements
     */
    public static <TIn, TOut> List<TOut> map(final List<TIn> in, final Callback<TIn, TOut> proc) {
        final List<TOut> out = new ArrayList<>();
        in.forEach(value -> out.add(proc.invoke(value)))
        return out;
    }

    /**
     * Gets elements of the list that match the given predicate
     * @param in The original list to search through (original list not modified)
     * @param proc A function that checks if the array elements matches the condition
     * @param <TIn> A type of the input and output lists
     * @return A list of matched elements
     */
    public static <TIn> List<TIn> filter(final List<TIn> in, final Predicate<TIn> proc) {
        final List<TIn> out = new ArrayList<>();
        in.forEach(value -> {
            if (proc.invoke(value))
                out.add(value);
        });
        return out;
    }

    /**
     * Finds the first index of an element in a list that matches the given predicate
     * @param in The original list to search through (original list not modified)
     * @param proc A function that checks if the array elements matches the condition
     * @param <T> A type of the input list
     * @return An index of the valid element, -1 if no valid element is found.
     */
    public static <T> int indexFunc(final List<T> in, final Predicate<T> proc) {
        for (int i = 0; i < in.size(); i++) {
            if (proc.invoke(in.get(i)))
                return i;
        }

        return -1;
    }

    /**
     * Checks if the input list contains an item that matches with the predicate
     * @param in The original list to search through (original list not modified)
     * @param proc A function that checks if the array elements matches the condition
     * @param <T> A type of the input list
     * @return A true/false value depending on if the valid element is found
     */
    public static <T> boolean containsFunc(final List<T> in, final Predicate<T> proc) {
        return indexFunc(in, proc) != -1;
    }
}