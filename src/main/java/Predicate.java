package com.github.williwadelmawisky.utils;

/**
 * A simple function class with a single input value and a boolean output value
 * @param <TIn> Type of the input value
 *
 * @version 18.6.2024
 */
@FunctionalInterface
public interface Predicate<TIn> {

    /**
     * Calls the function
     * @param arg Value of the input
     * @return True or False
     */
    boolean invoke(TIn arg);
}
