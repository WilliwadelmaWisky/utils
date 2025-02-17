package com.github.williwadelmawisky.utils;

/**
 * A simple function class with no input or output values
 * @param <TIn> Type of the input argument
 * 
 * @version 18.6.2024
 */
@FunctionalInterface
public interface EventHandler<TIn> {

    /**
     * Calls the function
     * @param arg Argument of the function
     */
    void invoke(TIn arg);
}
