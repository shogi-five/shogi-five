package shogi_five.view;


import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import shogi_five.view.View;


public class DrawGame extends JPanel{
    
    private View view;
    private Board board;
    private Pit firstPit;
    private Pit secondPit;

    public DrawGame(View view, int height) {
        super();

        
        this.setPreferredSize(new Dimension(height*2, height)); // サイズ指定
        
        this.view = view;
        this.board = new Board(this.view, height);
        this.firstPit = new Pit(this.view, height*5/6, false);
        this.secondPit = new Pit(this.view,  height*5/6, true);
        this.add(this.secondPit);
        this.add(this.board);
        this.add(this.firstPit);
    }

    void setPiece(int position, PieceKind pieceKind, boolean isDownward) {
        if (position < 25) {
            this.board.setPiece(position, pieceKind, isDownward);
        }
        else if (position < 35) {
            this.secondPit.setPiece(position, pieceKind);
        }
        else {
            this.firstPit.setPiece(position, pieceKind);
        }
    }

    public Box getBox(int position) {
        if (position < 25) {
            return this.board.getBox(position);
        }
        else if (position < 35) {
            return this.secondPit.getBox(position);
        }
        else {
            return this.firstPit.getBox(position);
        }
    }
}


class Board extends JPanel {
    private static final Image img = Toolkit.getDefaultToolkit().getImage("board.png");
    
    private View view;
    private Grid grid;

    Board(View view, int size) {
        super();

        this.view = view;
        this.setPreferredSize(new Dimension(size, size)); // サイズ指定
        
        // 位置調整
        JPanel column = new JPanel();
        column.setPreferredSize(new Dimension(size*5/6, size*5/60-10));
        column.setBackground(new Color(0, 0, 0, 0)); // 透過
        this.add(column);

        // グリッド
        this.grid = new Grid(this.view, 5, 5, size/6, 0);
        this.add(this.grid);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, this);
    }


    void setPiece(int position, PieceKind pieceKind, boolean isDownward) {
        this.grid.setPiece(position, pieceKind, isDownward);
    }

    public Box getBox(int position) {
        return this.grid.getBox(position);
    }
}





class Pit extends JPanel {

    
    private static final Image img = Toolkit.getDefaultToolkit().getImage("assets\\img\\pit.png");
    
    private View view;
    private Grid grid;
    private boolean dir;

    public Pit(View view, int height, boolean dir) {

        this.view = view;
        this.dir = dir;
        this.grid = new Grid(this.view, 5, 2, height/5, dir ? 25 : 35);
        this.add(grid);
    }


    void setPiece(int position, PieceKind pieceKind) {
        this.grid.setPiece(position, pieceKind, this.dir);
        
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

    public Box getBox(int position) {
        return this.grid.getBox(position);
    }
}



class Grid extends JPanel {
    private View view;
    private Box boxes[];
    private int pos_offset;

    Grid (View view, int row, int column, int boxSize, int pos_offset) {
        
        
        super(new GridLayout(row, column));

        this.view = view;
        this.boxes = new Box[row*column];
        this.pos_offset = pos_offset;

        
        this.setPreferredSize(new Dimension(column*boxSize, row*boxSize));
        this.setBackground(new Color(0, 0, 0, 0)); // 透過
        

        for (int i = 0; i < row*column; i++) {
            Box sq = new Box(this.view, i+pos_offset, boxSize);
            this.boxes[i] = sq;
            this.add(sq);
        }
    }

    void setPiece(int position, PieceKind pieceKind, boolean isDownward) {
        this.boxes[position-this.pos_offset].setPiece(pieceKind, isDownward);
    }

    public Box getBox(int position) {
        return this.boxes[position - this.pos_offset];
    }
}


