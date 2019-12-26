package org.tryndusi.routing.sa;

public interface Evaluator<T> {

    double evaluate(T[] candidate);
}
