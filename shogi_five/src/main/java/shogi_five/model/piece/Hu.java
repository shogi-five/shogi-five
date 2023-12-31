package shogi_five.model.piece;

import java.util.ArrayList;
import shogi_five.model.*;

/*
 * 歩の駒
 */

public class Hu extends Piece{
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    /*
     * コンストラクタ
     */
    public Hu(int position, boolean owner){
        setPosition(position);   
        setOwner(owner);
        setPromote(false);
    }

    /*
     * 動きを定義する
     */
    public ArrayList<Integer> move(Board board){
        ArrayList<Integer> moveList = new ArrayList<>();

        if (!this.promote){//成っていない
            if (this.owner){//プレイヤーが所有
                moveList.add(this.position-5);
            }else{
                moveList.add(this.position+5);
            }
        } else {//成っているとき
            if (this.position == 0){
                moveList.add(1);
                moveList.add(5);
            } else if (this.position == 4){
                moveList.add(3);
                moveList.add(9);
            } else if (this.position == 20){
                moveList.add(15);
                moveList.add(16);
                moveList.add(21);
            } else if (this.position == 24){
                moveList.add(18);
                moveList.add(19);
                moveList.add(23);
            } else if (this.position % 5 == 0){//左端の列
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 1);//右
                //moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
            } else if (this.position % 5 == 4){//右端の列
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position - 1);//左
                //moveList.add(this.position + 5 - 1);//左下
                moveList.add(this.position + 5);//下
            } else if (this.position < 5){//一番上の行
                moveList.add(this.position + 1);//右
                //moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
                //moveList.add(this.position + 5 - 1);//左下
                moveList.add(this.position - 1);//左
            } else if (this.position > 19 && this.position < 25){//一番下の行
                moveList.add(this.position + 1);//右
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 - 1);//左上
                moveList.add(this.position - 1);//左
            }else{//全方向
                moveList.add(this.position - 5);//上
                moveList.add(this.position - 5 + 1);//右上
                moveList.add(this.position + 1);//右
                //moveList.add(this.position + 5 + 1);//右下
                moveList.add(this.position + 5);//下
                //moveList.add(this.position + 5 - 1);//左下
                moveList.add(this.position - 1);//左
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
     * ディープコピー
     */
    @Override
    public Hu clone(){
        Hu cloned = (Hu)super.clone();
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
                setPieceClass(10);
            }else{
                setPieceClass(20);
            }
        }else{
            if (this.owner){
                setPieceClass(6);
            }else{
                setPieceClass(16);
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