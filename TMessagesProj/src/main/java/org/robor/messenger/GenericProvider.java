package org.robor.messenger;

public interface GenericProvider<F, T> {
    T provide(F obj);
}
