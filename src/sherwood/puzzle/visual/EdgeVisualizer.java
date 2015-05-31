package sherwood.puzzle.visual;

import sherwood.puzzle.Pair;
import sherwood.puzzle.boards.Board;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class EdgeVisualizer extends JComponent {

    private Board board;
    private Set<Pair<Integer>> toDraw;

    public EdgeVisualizer (Board board) {
        this.board = board;
        setPreferredSize(new Dimension(601, 601));
    }

    public void select (Pair<Integer> mouse) {
        Pair<Integer> pair = coordinates(mouse);
        if (!pair.validIntegerPair(board.dimensions().first, board.dimensions().last))
            return;
        toDraw = new HashSet<>();
        toDraw.addAll(board.edgesConnectedTo(pair));
    }

    public void deselect () {

    }

    @Override
    public void paint (Graphics g) {
        Dimension window = getSize();
        int C = window.width - 1;
        int R = window.height - 1;
        int RR = board.dimensions().first;
        int CC = board.dimensions().last;
        for (int r = 0; r < RR; r++) {
            for (int c = 0; c < CC; c++) {
                if (board.set(new Pair<>(r, c)))
                    g.setColor(Color.RED.brighter().brighter().brighter().brighter());
                else
                    g.setColor(Color.GREEN.brighter().brighter().brighter().brighter());
                g.fillRect(c * C / CC, r * R / RR, C / CC, R / RR);
            }
        }
        if (toDraw != null)
            toDraw.forEach(a -> {
                g.setColor(Color.GRAY);
                g.drawOval(a.last * C / CC + C / CC / 3 - C / CC / 6, a.first * R / RR + R / RR / 6, C / CC * 2 / 3, R / RR * 2 / 3);
            });
    }

    public void flip (Pair<Integer> mouse) {
        Pair<Integer> pair = coordinates(mouse);
        if (!pair.validIntegerPair(board.dimensions().first, board.dimensions().last))
            return;
        board.edgesConnectedTo(pair).forEach(board::flip);
    }

    private Pair<Integer> coordinates (Pair<Integer> mouse) {
        int r = mouse.first * board.dimensions().first / getSize().height;
        int c = mouse.last * board.dimensions().last / getSize().width;
        return new Pair<>(r, c);
    }
}
