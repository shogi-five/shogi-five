package shogi_five.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import shogi_five.view.View;

class MainFrame extends JFrame {

    private View view;
    private JPanel panel;
    private DrawGame board;

    MainFrame(View view) {
        super("shogi5");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.view = view;
        this.panel = new JPanel();
        this.board = new DrawGame(this.view, 600);
        
        
        this.panel.add(this.board, BorderLayout.CENTER);        
        this.add(panel);
        
    }


    void setPiece(int position, PieceKind pieceKind, boolean dir) {
        this.board.setPiece(position, pieceKind, dir);
    }

    public Box getBox(int position) {
        return this.board.getBox(position);
    }
}