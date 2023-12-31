package shogi_five.model.piece;

import java.util.ArrayList;

import shogi_five.model.Board;
import shogi_five.model.Human;

/**
 * 将棋の駒を表す抽象クラス
 */

public abstract class Piece implements Cloneable{
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    public abstract ArrayList<Integer> move(Board board);
    public abstract void setPosition(int position);
    public abstract void setPromote(boolean promote);
    public abstract void setOwner(boolean owner);
    public abstract boolean getOwner();
    public abstract int getPieceClass();
    public abstract boolean getPromote();
    public abstract int getPosition();

    /*
     * ディープコピー
     */
    @Override
    public Piece clone(){
        try{
            Piece cloned = (Piece)super.clone();
            cloned.position = position;
            cloned.owner = owner;
            cloned.pieceClass = pieceClass;
            cloned.promote = promote;
            return cloned;
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("クローンに失敗しました",e);
        }
    }

}
