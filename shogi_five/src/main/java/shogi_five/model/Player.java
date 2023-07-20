package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;


//プレイヤーを表すクラス
//自分が所有している駒,移動可能な駒と行き先を持つ

public abstract class Player{

    //フィールド変数
    private Piece[] havePiece;  //所有している駒
    private Chooseable[] availblePiece; //移動可能なコマとその行先


    //コンストラクター
    public Player(Piece[] havePiece, Chooseable[] availblePiece){
        this.havePiece = havePiece.clone();              // 所有しているコマをディープコピー
        this.availblePiece = availblePiece.clone();      //移動可能なコマとその行先をディープコピー

    }



}
