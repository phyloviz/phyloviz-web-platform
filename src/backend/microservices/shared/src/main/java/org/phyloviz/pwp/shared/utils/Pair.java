package org.phyloviz.pwp.shared.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, E> {
    private T first;
    private E second;

    public static <T, E> Pair<T, E> of(T first, E second) {
        return new Pair<>(first, second);
    }
}
