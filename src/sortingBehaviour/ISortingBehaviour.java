package sortingBehaviour;

import java.util.Comparator;
import java.util.stream.Stream;

public interface ISortingBehaviour {
    <T> Stream<T> sort(Stream<T> input, Comparator<T> comparer);

    <T extends Comparable<T>> Stream<T> sort(Stream<T> input);
}
