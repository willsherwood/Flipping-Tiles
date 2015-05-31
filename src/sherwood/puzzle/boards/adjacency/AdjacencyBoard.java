package sherwood.puzzle.boards.adjacency;

import sherwood.puzzle.Pair;
import sherwood.puzzle.boards.Board;
import sherwood.puzzle.sherwood.util.DeepCopy;

public class AdjacencyBoard extends Board {

    @Override
    protected Board copy () {
        Board b = new AdjacencyBoard(dimensions().first, dimensions().last);
        DeepCopy.deepCopy(this.actual, b.actual);
        return b;
    }

    public AdjacencyBoard (int rows, int columns) {
        super(rows, columns);
        build();
    }

    private void build() {
        Pair.range(dimensions().first, dimensions().last).forEach(a -> {
            for (int dy = -1; dy <= 1; dy++)
                for (int dx = -1; dx <= 1; dx++) {
                    if (Math.abs(dy) == Math.abs(dx) && dx != 0)
                        continue;
                    Pair<Integer> pair = new Pair<>(a.first + dy, a.last + dx);
                    if (pair.validIntegerPair(dimensions().first, dimensions().last)) {
                        map.get(a).add(pair);
                    }
                }
        });
    }
}
