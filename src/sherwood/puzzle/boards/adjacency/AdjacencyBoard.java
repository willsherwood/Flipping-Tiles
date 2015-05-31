package sherwood.puzzle.boards.adjacency;

import sherwood.puzzle.Pair;
import sherwood.puzzle.boards.Board;

public class AdjacencyBoard extends Board {

    public AdjacencyBoard (Board other) {
        this(other.dimensions().height, other.dimensions().width);
        this.actual = other.actual.clone();
    }

    public AdjacencyBoard (int rows, int columns) {
        super(rows, columns);
        Pair.range(rows, columns).forEach(a -> {
            for (int dy=-1; dy<=1; dy++)
                for(int dx=-1; dx<=1; dx++) {
                    if (Math.abs(dy) == Math.abs(dx) && dx != 0)
                        continue;
                    Pair<Integer> pair = new Pair<>(a.first + dy, a.last + dx);
                    if (pair.validIntegerPair(rows, columns))
                        edge(a, pair);
                }
        });
    }
}
