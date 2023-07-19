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
        this.pieceClass = 6;
        this.position = position;
        this.promote = false;
        this.owner = owner;
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
}