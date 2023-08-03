package shogi_five.model.piece;

import java.util.ArrayList;
import shogi_five.model.*;

/*
 * 角の駒
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
        if (owner){
            setPieceClass(3);
        }else{
            setPieceClass(13);
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
        //成っている・成っていない共通の動き
         //右下への移動
        for(int i = this.position ; i < 20; i = i + 6){
            //positionが右端の場合forから抜ける
            if (this.position % 5 == 4){
                break;
            }

            //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i + 6) != null) && (board.getPiece(i + 6).getOwner() == this.owner)){
                break;
            }

            //移動先が右端の場合移動先を追加してforから抜ける
            if ((i + 6)  % 5 == 4) {
                moveList.add(i + 6);
                break;
            }

            //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i + 6) != null) && (board.getPiece(i + 6).getOwner() != this.owner)){
                moveList.add(i + 6);
                break;
            }

            moveList.add(i + 6);
        }

        //左下への移動
        for(int i = this.position ; i < 20; i = i + 4){
           //positionが左端の場合forから抜ける
            if (this.position % 5 == 0){
               break;
            }

           //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i + 4) != null) && (board.getPiece(i + 4).getOwner() == this.owner)){
               break;
            }

           //移動先が左端の場合移動先を追加してforから抜ける
            if ((i + 4)  % 5 == 0) {
               moveList.add(i + 4);
               break;
            }

           //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i + 4) != null) && (board.getPiece(i + 4).getOwner() != this.owner)){
               moveList.add(i + 4);
               break;
            }

            moveList.add(i + 4);
        }


       //右上への移動
        for(int i = this.position ; i > 4; i = i - 4){
            //positionが右端の場合forから抜ける
            if (this.position % 5 == 4){
                break;
            }

            //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i - 4) != null) && (board.getPiece(i - 4).getOwner() == this.owner)){
                break;
            }

            //移動先が右端の場合移動先を追加してforから抜ける
            if ((i - 4)  % 5 == 4) {
                moveList.add(i - 4);
                break;
            }

            //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i - 4) != null) && (board.getPiece(i - 4).getOwner() != this.owner)){
                moveList.add(i - 4);
                break;
            }

            moveList.add(i - 4);
        }

        //左上への移動
        for(int i = this.position ; i > 4; i = i - 6){
            //positionが左端の場合forから抜ける
            if (this.position % 5 == 0){
                break;
            }

            //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i - 6) != null) && (board.getPiece(i - 6).getOwner() == this.owner)){
                break;
            }

            //移動先が左端の場合移動先を追加してforから抜ける
            if ((i - 6)  % 5 == 0) {
                moveList.add(i - 6);
                break;
            }

            //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i - 6) != null) && (board.getPiece(i - 6).getOwner() != this.owner)){
                moveList.add(i - 6);
                break;
            }

            moveList.add(i - 6);
        }

        //成っているときの動き
        if (this.promote == true){
            //上への動き(マイナス方向)
            if (this.position > 4){
                //移動先に自分の駒がないとき
                if ((board.getPiece(this.position - 5) == null) || (board.getPiece(this.position - 5).getOwner() != this.owner)){
                    moveList.add(this.position - 5);
                }
            }

            //下への動き(プラス方向)
            if (this.position < 20){
                //移動先に自分の駒がないとき
                if ((board.getPiece(this.position + 5) == null) || (board.getPiece(this.position + 5).getOwner() != this.owner)){
                    moveList.add(this.position + 5);
                }
            }

            //右への動き(プラス方向)
            if (this.position % 5 != 4){
                //移動先に自分の駒がないとき
                if ((board.getPiece(this.position + 1) == null) || (board.getPiece(this.position + 1).getOwner() != this.owner)){
                    moveList.add(this.position + 1);
                }
            }

            //左への動き(マイナス方向)
            if (this.position % 5 != 0){
                //移動先に自分の駒がないとき
                if ((board.getPiece(this.position - 1) == null) || (board.getPiece(this.position - 1).getOwner() != this.owner)){
                    moveList.add(this.position - 1);
                }
            }

        }

    return moveList;
}

    /*
     * ディープコピー
     */
    @Override
    public Kaku clone(){
        Kaku cloned = (Kaku)super.clone();
        cloned.position = position;
        cloned.owner = owner;
        cloned.pieceClass = pieceClass;
        cloned.promote = promote;
        return cloned;
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
        if (promote){//成り
            if (this.owner){
                setPieceClass(8);
            }else{
                setPieceClass(18);
            }
        }else{
            if (this.owner){
                setPieceClass(3);
            }else{
                setPieceClass(13);
            }
        }
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
