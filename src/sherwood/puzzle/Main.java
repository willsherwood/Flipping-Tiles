package sherwood.puzzle;

import sherwood.puzzle.boards.Board;
import sherwood.puzzle.boards.Solver;
import sherwood.puzzle.boards.adjacency.AdjacencyBoard;
import sherwood.puzzle.visual.EdgeVisualizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Main {

    public static void main (String[] args) {

        Board a = new AdjacencyBoard(3, 3);
        Board b = new AdjacencyBoard(3, 3);
        b = b.flipAllConnectedTo(new Pair<>(0, 0));
        b = b.flipAllConnectedTo(new Pair<>(2, 1));
        b = b.flipAllConnectedTo(new Pair<>(1, 1));

//        b = b.flipAllConnectedTo(new Pair<>(1, 2));
//        b = b.flipAllConnectedTo(new Pair<>(2, 2));

        System.out.println(b);
        System.out.println(new Solver(a, b).solve());


        System.exit(0);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        EdgeVisualizer visualizer = new EdgeVisualizer(new AdjacencyBoard(3, 3));
        panel.add(visualizer);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        visualizer.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved (MouseEvent e) {
                visualizer.select(new Pair<>(e.getY(), e.getX()));
            }
        });

        visualizer.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed (MouseEvent e) {
                visualizer.flip(new Pair<>(e.getY(), e.getX()));
            }
        });
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}
