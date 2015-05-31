package sherwood.puzzle.boards;

import sherwood.puzzle.Pair;
import sherwood.puzzle.sherwood.util.DeepCopy;

import java.util.*;

public class Board {

    private int rows, columns;
    protected Map<Pair<Integer>, Set<Pair<Integer>>> map;
    public boolean[][] actual;

    protected Board copy () {
        Board b = new Board(rows, columns);
        DeepCopy.deepCopy(actual, b.actual);
        this.map.entrySet().forEach(a -> {
            Set<Pair<Integer>> ns = new HashSet<>();
            ns.addAll(a.getValue());
            b.map.put(a.getKey(), ns);
        });
        return b;
    }

    public Board (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.actual = new boolean[rows][columns];
        this.map = new HashMap<>(rows * columns);

        Pair.range(rows, columns).forEach(a -> map.put(a, new HashSet<>()));
    }

    protected Board edge (Pair<Integer> from, Pair<Integer> to) {
        Board b = copy();
        b.map.get(from).add(to);
        return b;
    }

    protected Board remove (Pair<Integer> from, Pair<Integer> to) {
        Board b = copy();
        b.map.get(from).remove(to);
        return b;
    }

    public Set<Pair<Integer>> edgesConnectedTo (Pair<Integer> point) {
        return map.getOrDefault(point, new HashSet<>());
    }

    public Board flipAllConnectedTo (Pair<Integer> point) {
        Board b = copy();
        for (Pair<Integer> pair : edgesConnectedTo(point))
            b = b.flip(pair);
        return b;
    }

    public Pair<Integer> dimensions () {
        return new Pair<>(rows, columns);
    }

    public Board flip (Pair<Integer> pair) {
        Board b = copy();
        b.actual[pair.first][pair.last] = !b.actual[pair.first][pair.last];
        return b;
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
