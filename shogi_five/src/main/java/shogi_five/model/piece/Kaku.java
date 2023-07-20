package shogi_five.model.piece;

import java.util.ArrayList;
import shogi_five.model.*;

/*
 * 飛車の駒
 */

public class Kaku extends Piece{
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    /*
     * コンストラクタ
     */
    public Kaku(int position, boolean owner){
<<<<<<< HEAD
        if (owner){
            setPieceClass(3);
        }else{
            setPieceClass(13);
=======
        this.pieceClass = 4;
        if(owner){
            this.pieceClass += 6;
>>>>>>> origin/master
        }
        this.position = position;
        this.promote = false;
        this.owner = owner;
    }

    /*
     * 動きを定義する
     */
    public ArrayList<Integer> move(Board board){
        ArrayList<Integer> moveList = new ArrayList<>();

        return moveList;
    }


    /*
     * 成りのゲッター
     */
    public boolean getPromote(){
        return this.promote;
    }

    /*
     * 成りのセッター
     */
    public void setPromote(boolean promote){
        this.promote = promote;
    }

    /*
     * 位置のセッター
     */
    public void setPosition(int position){
        this.position = position;
    }
    /*
     * 位置のゲッター
     */
    public int getPosition(){
        return this.position;
    }
    /*
     * 所有者のゲッター
     */
    public boolean getOwner(){
        return this.owner;
    }
    /*
     * 所有者のセッター
     */
    public void setOwner(boolean owner){
        this.owner = owner;
    }    
    /*
     * pieceClassのゲッター
     */
    public int getPieceClass(){
        return this.pieceClass;
    }
    /*
     * pieceClassのセッター
     */
    public void setPieceClass(int pieceClass){
        this.pieceClass = pieceClass;
    }
}