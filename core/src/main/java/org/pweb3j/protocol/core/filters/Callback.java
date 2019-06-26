package org.pweb3j.protocol.core.filters;

/**
 * Filter callback interface.
 */
public interface Callback<T> {
    void onEvent(T value);
}
