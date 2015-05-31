package sherwood.puzzle;

import java.util.stream.Stream;

public class Pair<K> {

    public final K first, last;

    public Pair (K first, K last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        if (last != null ? !last.equals(pair.last) : pair.last != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    public static Stream<Pair<Integer>> range (int rows, int columns) {
        return Stream.iterate(new Pair<>(0, 0), a ->
                        a.first >= rows - 1 ? new Pair<>(0, a.last + 1) : new Pair<>(a.first + 1, a.last)
        ).limit(rows * columns);
    }

    @Override
    public String toString () {
        return "[" + first + ", " + last + "]";
    }

    public boolean validIntegerPair (int rows, int columns) {
        if (!(first instanceof Integer && last instanceof Integer)) {
            return false;
        }
        return (Integer) first >= 0 && (Integer) first < rows &&
                (Integer) last >= 0 && (Integer) last < columns;
    }
}
