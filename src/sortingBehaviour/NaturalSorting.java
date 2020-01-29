package sortingBehaviour;

import java.util.Comparator;
import java.util.stream.Stream;

public class NaturalSorting implements ISortingBehaviour {

    @Override
    public <T> Stream<T> sort(Stream<T> input, Comparator<T> comparer) {
        return input.sorted(comparer);
    }

    @Override
    public <T extends Comparable<T>> Stream<T> sort(Stream<T> input) {
        return input.sorted();
    }
}
