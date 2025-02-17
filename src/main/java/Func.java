package com.github.williwadelmawisky.utils;

/**
 * A simple function class with no input values and a single output value
 * @param <TOut> Type of the output value
 *
 * @version 18.6.2024
 */
@FunctionalInterface
public interface Func<TOut> {

    /**
     * Calls the function
     * @return Output value
     */
    TOut invoke();
}
