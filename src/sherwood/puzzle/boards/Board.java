package sherwood.puzzle.boards;

import sherwood.puzzle.Pair;

import java.awt.Dimension;
import java.util.*;

public class Board {

    private int rows, columns;
    private Map<Pair<Integer>, Set<Pair<Integer>>> map;
    public boolean[][] actual;

    public Board (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.actual = new boolean[rows][columns];
        this.map = new HashMap<>(rows * columns);

        Pair.range(rows, columns).forEach(a -> map.put(a, new HashSet<>()));
    }

    protected boolean edge (Pair<Integer> from, Pair<Integer> to) {
        return map.get(from).add(to);
    }

    protected boolean remove (Pair<Integer> from, Pair<Integer> to) {
        return map.get(from).remove(to);
    }

    public Set<Pair<Integer>> edgesConnectedTo (Pair<Integer> point) {
        return map.getOrDefault(point, new HashSet<>());
    }

    public Dimension dimensions () {
        return new Dimension(rows, columns);
    }

    public void flip (Pair<Integer> pair) {
        actual[pair.first][pair.last] = !actual[pair.first][pair.last];
    }

    public boolean set (Pair<Integer> pair) {
        return actual[pair.first][pair.last];
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        if (columns != board.columns) return false;
        if (rows != board.rows) return false;

        return Arrays.deepEquals(actual, board.actual);
    }

    @Override
    public int hashCode () {
        int result = rows;
        result = 31 * result + columns;
        result = 31 * result + Arrays.deepHashCode(actual);
        return result;
    }

    @Override
    public String toString () {
        return "Board{" +
                "actual=" + Arrays.deepToString(actual) +
                '}';
    }
}
