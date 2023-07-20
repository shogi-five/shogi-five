package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;

import java.util.ArrayList;

//プレイヤーを表すクラス
//自分が所有している駒,移動可能な駒と行き先を持つ

public abstract class Player{

    //フィールド変数
    private ArrayList<Piece> havePiece;  //所有している駒
    private ArrayList<Chooseable> availblePiece; //移動可能なコマとその行先


    //コンストラクター
    public Player(ArrayList<Piece> havePiece, ArrayList<Chooseable> availblePiece){
        this.havePiece = (ArrayList<Piece>)havePiece.clone();              // 所有しているコマをディープコピー
        this.availblePiece = (ArrayList<Chooseable>)availblePiece.clone();      //移動可能なコマとその行先をディープコピー

    }

    public abstract void setHavePiece(ArrayList<Piece> pieces);
    public abstract ArrayList<Piece> getHavePiece();
}
