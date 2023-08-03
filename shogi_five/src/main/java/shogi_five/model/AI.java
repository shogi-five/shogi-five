package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

import shogi_five.model.Tree;

import java.util.ArrayList;

//プレイヤーを表すクラス
//自分が所有している駒,移動可能な駒と行き先を持つ

public class AI extends Player{

    //フィールド変数
    private ArrayList<Piece> havePiece;  //所有している駒
    private ArrayList<Chooseable> availblePiece; //移動可能なコマとその行先


    //コンストラクター
    public AI(ArrayList<Piece> havePiece, ArrayList<Chooseable> availblePiece){
        super(havePiece, availblePiece);
        this.havePiece = (ArrayList<Piece>)havePiece.clone();              // 所有しているコマをディープコピー
        this.availblePiece = (ArrayList<Chooseable>)availblePiece.clone();      //移動可能なコマとその行先をディープコピー
    }

    /*
     * ディープコピー
     */
    public AI clone() throws CloneNotSupportedException{
        try{
            AI cloned = (AI)super.clone();
            return cloned;
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("クローンに失敗しました",e);
        }
    }

    /*
     * 探索を行う
     * @ return Status nextStatus 探索の結果得られたStatusを返す
     */
    public Status inference(Status status){
        Tree search = new Tree();
        Status nextStatus = search.miniMax().getStatus();
        return nextStatus;
    }

    /*
     * havePieceに駒を追加
     */
    public void addHavePiece(Piece piece){
        this.havePiece.add(piece);
    }

    /*
     * havePieceから駒を削除
     */
    public void removeHavePiece(Piece piece){
        this.havePiece.remove(piece);
    }

    /*
     * havePieceのセッター
     */
    public void setHavePiece(ArrayList<Piece> pieces){
        this.havePiece = pieces;
    }
    /*
     * havePieceのゲッター
     */
    public ArrayList<Piece> getHavePiece(){
        return this.havePiece;
    }
}
