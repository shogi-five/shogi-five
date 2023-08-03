package shogi_five.model.piece;

import java.util.ArrayList;
import shogi_five.model.*;

/*
 * 飛車の駒
 */

public class Hisha extends Piece{
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    /*
     * コンストラクタ
     */
    public Hisha(int position, boolean owner){
        if (owner){
            setPieceClass(2);
        }else{
            setPieceClass(12);
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
    //縦の動き
         //一番下の段ではないとき,下への移動(プラス方向の移動)を追加
        for(int i = this.position ; i < 20; i = i + 5){
            //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i + 5) != null) && (board.getPiece(i + 5).getOwner() == this.owner)){
                break;
            }
            //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i + 5) != null) && (board.getPiece(i + 5).getOwner() != this.owner)){
                moveList.add(i + 5);
                break;

            }
            moveList.add(i + 5);
        }

        //一番上の段ではないとき,上への移動(マイナス方向の移動)を追加
        for (int i = this.position  ; i > 4; i = i - 5){
            //移動先に自分の駒があるときfor文から抜ける
            if ((board.getPiece(i - 5) != null) && (board.getPiece(i - 5).getOwner() == this.owner)){
                break;
            }
            //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
            if ((board.getPiece(i - 5) != null) && (board.getPiece(i - 5).getOwner() != this.owner)){
                moveList.add(i - 5);
                break;
            }
            moveList.add(i - 5);
        }

        //横の動き
            //右方向(プラス方向)への移動
            for (int i = 0; i < 4 - (this.position % 5); i++){
                if ((board.getPiece(this.position + (i + 1)) != null) && (board.getPiece(this.position + (i + 1)).getOwner() == this.owner)){
                    break;
                }
                //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
                 if ((board.getPiece(this.position + (i + 1)) != null) && (board.getPiece(this.position + (i + 1)).getOwner() != this.owner)){
                    moveList.add(this.position + (i + 1));
                    break;
                }

                moveList.add(this.position + (i + 1));
            }


            //左方向(マイナス方向の移動)への移動
            for (int i = 0; i < this.position % 5 ; i++){
                //移動先に自分の駒があるときfor文から抜ける
                if ((board.getPiece(this.position - (i + 1)) != null) && (board.getPiece(this.position - (i + 1)).getOwner() == this.owner)){
                    break;
                }
                //移動先に相手の駒があるとき,移動先を追加してfor文から抜ける
                 if ((board.getPiece(this.position - (i + 1)) != null) && (board.getPiece(this.position - (i + 1)).getOwner() != this.owner)){
                    moveList.add(this.position - (i + 1));
                    break;
                }

                moveList.add(this.position - (i + 1));
            }

        //成っているときの動き
        if (this.promote == true){

            //左上の動き(マイナス6方向)
            if ((this.position % 5 !=  0) && (this.position > 5)){
                //自分の駒がないとき
                if ((board.getPiece(this.position - 6)  == null) || (board.getPiece(this.position - 6).getOwner() != this.owner)){
                    moveList.add(this.position - 6);
                }
            }

             //右上の動き(マイナス4方向)
            if ((this.position % 5 !=  4) && (this.position > 5)){
                //自分の駒がないとき
                if ((board.getPiece(this.position - 4)  == null) || (board.getPiece(this.position - 4).getOwner() != this.owner)){
                    moveList.add(this.position - 4);
                }
            }

            //右下の動き(プラス6方向)
            if ((this.position % 5 !=  4) && (this.position < 20)){
                //自分の駒がないとき
                if ((board.getPiece(this.position + 6)  == null) || (board.getPiece(this.position + 6).getOwner() != this.owner)){
                    moveList.add(this.position + 6);
                }
            }

            //左下の動き(プラス4方向)
            if ((this.position % 5 !=  0) && (this.position < 20)){
                //自分の駒がないとき
                if ((board.getPiece(this.position + 4)  == null) || (board.getPiece(this.position + 4).getOwner() != this.owner)){
                    moveList.add(this.position + 4);
                }
            }
        }

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
