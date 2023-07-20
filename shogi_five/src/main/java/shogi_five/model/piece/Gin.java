package shogi_five.model.piece;

import java.util.ArrayList;
import shogi_five.model.*;

/*
 * 飛車の駒
 */

public class Gin extends Piece{
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    /*
     * コンストラクタ
     */
    public Gin(int position, boolean owner){
<<<<<<< HEAD
        if (owner){
            setPieceClass(5);
        }else{
            setPieceClass(15);
=======
        this.pieceClass = 3;
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
        if(this.owner){//先手の動き
            if (this.position == 0){
                moveList.add(6);
            } else if (this.position == 4){
                moveList.add(8);
            } else if (this.position == 20){
                moveList.add(15);
                moveList.add(16);
            } else if (this.position == 24){
                moveList.add(18);
                moveList.add(19);
            } else if (this.position % 5 == 0){//左端の列
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 5 + 1);//右下
            } else if (this.position % 5 == 4){//右端の列
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position + 5 - 1);//左下
            } else if (this.position < 5){//一番上の行
                moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5 - 1);//左下
            } else if (this.position > 19 && this.position < 25){//一番下の行
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 - 1);//左上
            }else{//その他
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position + 5 - 1);//左下
            }
        }else{//後手の動き
            if (this.position == 0){
                moveList.add(5);
                moveList.add(6);
            } else if (this.position == 4){
                moveList.add(8);
                moveList.add(9);
            } else if (this.position == 20){
                moveList.add(16);
            } else if (this.position == 24){
                moveList.add(18);
            } else if (this.position % 5 == 0){//左端の列
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
            } else if (this.position % 5 == 4){//右端の列
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position + 5 - 1);//左下
                moveList.add(this.position + 5);//下
            } else if (this.position < 5){//一番上の行
                moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
                moveList.add(this.position + 5 - 1);//左下
            } else if (this.position > 19 && this.position < 25){//一番下の行
                moveList.add(this.position + 5 + 1);//右上
                moveList.add(this.position + 5 - 1);//左上
            }else{//全方向
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position + 5 - 1);//左下
            }
        }

        //自分の駒がある時、moveListから取り除く
        for(int i = 0;i < moveList.size();i++){
            if((board.getPiece(moveList.get(i)) != null)&&(board.getPiece(moveList.get(i)).getOwner() == this.owner)){
                moveList.remove(i);
                i--;
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