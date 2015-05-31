package sherwood.puzzle.boards;

import sherwood.puzzle.Pair;
import sherwood.puzzle.boards.adjacency.AdjacencyBoard;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    private Board current, goal;

    public Solver (Board current, Board goal) {
        this.current = current;
        this.goal = goal;
    }

    public List<Pair<Integer>> solve () {
        int i=0;
        //Set<Board> seen = new HashSet<>();
        Queue<Board> result = new LinkedList<>();
        result.offer(current);
        while (true) {
            Board next = new AdjacencyBoard(result.poll());
            System.out.println("before = " + next);
            for (Pair<Integer> a : Pair.range(current.dimensions().height, current.dimensions().width).collect(Collectors.toList())) {
                System.out.println(a);
                Board mutation = new AdjacencyBoard(next);
                mutation.edgesConnectedTo(a).forEach(mutation::flip);

                System.out.println(mutation);
                if (mutation.equals(goal)) {
                    System.out.println("found the board");
                    return null;
                }
                result.offer(mutation);
            }
            System.out.println("\n\ndone perm... NEXT ===\n");
            i++;
            if (i == 2)
                return null;
        }
    }
}

