package sherwood.puzzle.boards;

import sherwood.puzzle.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    private Board current, goal;

    public Solver (Board current, Board goal) {
        this.current = current;
        this.goal = goal;
    }

    public List<Pair<Integer>> solve () {
        Set<Board> seen = new HashSet<>();
        Queue<Board> result = new LinkedList<>();
        List<Pair<Integer>> out = new ArrayList<>();
        result.offer(current);
        while (true) {
            Board next = result.remove();
            for (Pair<Integer> a : Pair.range(current.dimensions().first, current.dimensions().last).collect(Collectors.toList())) {
                Board mutation = next.flipAllConnectedTo(a);
                out.add(a);
                if (mutation.equals(goal)) {
                    System.out.println("found the board");
                    return out;
                }
                out.remove(out.size() - 1);
                if (!seen.contains(mutation))
                    result.offer(mutation);
                seen.add(mutation);
            }
        }
    }
}

