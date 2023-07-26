package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

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
     * 探索を行う
     * @ return ArrayList<Integer> inferenceResults [駒の位置，動かす場所]
     */
    public ArrayList<Integer> inference(Status status){
        ArrayList<Integer> inferenceResults = new ArrayList<>();
        return inferenceResults;
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
