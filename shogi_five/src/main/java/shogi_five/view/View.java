package shogi_five.view;

import java.util.Optional;
import shogi_five.controller.GameController;
import shogi_five.model.Board;
import shogi_five.model.piece.Piece;
import shogi_five.view.PieceKind;

public class View {
    private GameController gameController;
    private MainFrame frame;
    private Cursor cursor;

    private Board board;

    private Box src;
    private Box des;



    public View(GameController gameController) {
        this.gameController = gameController;

        this.frame = new MainFrame(this);
        this.cursor = new Cursor(this);
        this.src = null;
        this.des = null;
    }

    


    /*
     * 盤面を表示
     */
    public void printBoard(Board board) {
        this.board = board;
        this.src = null;
        this.des = null;
        for (int i = 0; i < 45; i++) {
            Piece p = board.getPiece(i);

            
            this.frame.setPiece(i, PieceKind.from(p), !p.getOwner());
        }
        this.frame.repaint();
    }

    /*
     * 選択した駒の位置を取得
     */
    public Piece getMovePice(){
        if (this.src == null || this.board == null) {
            return null;
        }
        return this.board.getPiece(this.src.getPosition());
    }

    /*
     * 選択した駒の移動先を取得
     */
    public Piece getMovePosition(){
        if (this.src == null || this.board == null) {
            return null;
        }

        Piece p = PieceKind.into(this.src);
        p.setPosition(this.des.getPosition());
        return p;
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public void cursorEvent(Box src, Box des) {
        this.src = src;
        this.des = des;
        
    }

}
