package shogi_five.view;

import shogi_five.view.View;
import shogi_five.view.Box;


/**
 * どの駒が押されたか管理する
 */
public class Cursor {

    private View view;
    private Box pickedBox;
    private Box enteredBox;
    private Box promote;

    public Cursor(View view) {
        this.view = view;
        this.pickedBox = null;
        this.enteredBox = null;
        this.promote = null;
    }

    public View getView() {
        return this.view;
    }

    public Box getPickedBox() {
        return this.pickedBox;
    }

    public Box getEnteredBox() {
        return this.enteredBox;
    }

    private void setPromote(Box box) {
        if (box == null) {return;}
        PieceKind kind = box.getPiece();
        PieceKind back = kind.back();
        if (kind == back || 
            box.getPosition() >= 25
            ) {
            return;
        }
        this.promote = box;
        this.promote.setPiece(
            back,
            this.promote.isDownward());
    }

    private void resetPromote() {
        if (this.promote != null) {
            this.promote.setPiece(
                this.promote.getPiece().reversed(),
                this.promote.isDownward());
            this.promote = null;
        }
    }



    void pressed(Box box) {
        this.pickedBox = box;

        if (this.promote != box) {
            this.resetPromote();
        }
    }

    void released(Box box) {
        if (this.pickedBox != box) {
            return;
        }
        this.pickedBox = null;

        Box src = box;
        Box des = this.getEnteredBox();
        Box pro = this.promote;
        
        this.resetPromote();
        if (src == null || des == null) {
            return;
        }
        if (pro != src && src == des) {
            this.setPromote(src);
        }
        if (src == des || des.getPosition() >= 25) {
            return;
        }

        this.view.cursorEvent(src, des);

    }


    void entered(Box box) {
        this.enteredBox = box;
    }

    void exited(Box box) {
        if (this.enteredBox == box) {
            this.enteredBox = null;
        }
    }

    
}
