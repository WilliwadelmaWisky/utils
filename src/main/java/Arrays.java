package com.github.williwadelmawisky.util;

/**
 * A collection of helper functions for working with arrays.
 * 
 * @version 26.2.2025
 */
public abstract class Arrays {

    /**
     * Finds the first index of an element that matches the given function.
     * @param array An array to search through.
     * @param proc A function that defines the condition.
     * @param <T> A type of the array.
     * @return The index of the first found element. If no valid element is found, function returns -1.
     */
    public static <T> int indexFunc(final T[] array, final Predicate<T> proc) {
        for (int i = 0; i < array.length; i++) {
            if (proc.invoke(array[i]))
                return i;
        }

        return -1;
    }

    /**
     * Finds the first index of an element that matches with the input element.
     * @param array An array to search through.
     * @param element An element to search.
     * @param <T> A type of the array.
     * @return The index of the first found element. If no valid element is found, function returns -1.
     */
    public static <T> int index(final T[] array, final T element) {
        final Predicate<T> proc = elem -> elem.equals(element);
        return index(array, proc);
    }

    /**
     * Checks if an element that matches the given function is present in an array.
     * @param array An array to search through.
     * @param proc A function that defines the condition.
     * @param <T> A type of the array.
     * @return True/False depending on if any elements that matches the given function were found.
     */
    public static <T> boolean containsFunc(final T[] array, final Predicate<T> proc) {
        return indexFunc(array, proc) != -1;
    }

    /**
     * Checks if an element is present in an array.
     * @param array An array to search through.
     * @param match An element to search.
     * @param <T> A type of the array.
     * @return True/False depending on if the given element is found.
     */
    public static <T> boolean contains(final T[] array, final T element) {
        final Predicate<T> proc = elem -> elem.equals(element);
        return containsFunc(array, proc);
    }
}