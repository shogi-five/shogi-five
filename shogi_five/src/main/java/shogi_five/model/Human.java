package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;

import java.util.ArrayList;

//プレイヤーを表すクラス
//自分が所有している駒,移動可能な駒と行き先を持つ

public class Human extends Player{

    //フィールド変数
    private ArrayList<Piece> havePiece;  //所有している駒
    private ArrayList<Chooseable> availblePiece; //移動可能なコマとその行先


    //コンストラクター
    public Human(ArrayList<Piece> havePiece, ArrayList<Chooseable> availblePiece){
        super(havePiece, availblePiece);
        this.havePiece = (ArrayList<Piece>)havePiece.clone();              // 所有しているコマをディープコピー
        this.availblePiece = (ArrayList<Chooseable>)availblePiece.clone();      //移動可能なコマとその行先をディープコピー
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
