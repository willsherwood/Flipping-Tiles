package sherwood.puzzle.sherwood.util;

public class DeepCopy {

    public static void deepCopy(boolean[][] from, boolean[][] to) {
        for (int r=0; r<from.length; r++)
            for (int c=0; c<from[r].length; c++)
                to[r][c] = from[r][c];
    }
}
