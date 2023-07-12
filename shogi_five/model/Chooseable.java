
package model;
import model.Piece;

//コマの移動する種類と移動先を持つクラス
public class Chooseable{

    //フィールド変数
    private Piece pieceClass; //移動するコマの種類
    private int moveablePosition; //移動可能な番号

    //コンストラクタ
    public Chooseable(Piece pieceClass, int moveablePosition){
        this.piceClass = pieceClass;
        this.moveablePosition = moveablePosition;
    }

    //piceClassのgetterで移動するコマの種類の獲得
    public Piece getPiceClass(){
        return this.pieceClass;
    }

    //piceClassのsetterで移動するコマの種類をセット
    public void setPiceClass(Piece piceClass){
        this.pieceClass = piceClass;
    }

    //moveablePositionのgetterで移動可能な番号獲得
    public int getMoveablePosition(){
        return this.moveablePosition;
    }

    //moveablePositionのsetterで移動可能な番号のセット
    public void setMoveablePosition(int moveablePosition){
        this.moveablePosition = moveablePosition;
    }

}
    




