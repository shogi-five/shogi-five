package shogi_five.model;

import shogi_five.model.piece.*;

public class Board implements Cloneable{
    /*
     * フィールド
     * 盤面の座標
     */
    private Piece[] board;

    /*
     * コンストラクタ：初期化
     */
    public Board() {
        /* 
        * 座標:0~24
        * ----------------
        * | 0, 1, 2, 3, 4|
        * | 5, 6, 7, 8, 9|
        * |10,11,12,13,14|
        * |15,16,17,18,19|
        * |20,21,22,23,24|
        * ----------------
        * 先手の持ち駒:25~34
        * 後手の持ち駒:35~44
        */
        this.board = new Piece[45];
        //Piece(int 駒の種類,Chooseable[] 動く選択肢のリスト)<-駒の種類の番号がわからない、AvailablePieceはどこ
        //後手の駒の初期化
        this.board[0] = new Hisha(0,false);    //飛車
        this.board[1] = new Kaku(1,false);    //角
        this.board[2] = new Gin(2,false);    //銀
        this.board[3] = new Kin(3,false);    //金
        this.board[4] = new Ou(4,false);    //玉
        this.board[9] = new Hu(9,false);    //歩
        //先手の駒の初期化
        this.board[24] = new Hisha(24,true);    //飛車
        this.board[23] = new Kaku(23,true);    //角
        this.board[22] = new Gin(22,true);    //銀
        this.board[21] = new Kin(21,true);    //金
        this.board[20] = new Ou(20,true);    //玉
        this.board[15] = new Hu(15,true);    //歩
    }

    /*
     * 駒を位置にセット
     */
    public void setPiece(Piece piece, int position){
        this.board[position] = piece;
    }

    /*
     * 位置にセットされている駒をゲット
     */
    public Piece getPiece(int position){
        return this.board[position];
    }

    /*
     * ディープコピー
     */
    @Override
    public Board clone(){
        try{
            Board cloned = (Board)super.clone();
            cloned.board = new Piece[board.length];
            for(int i=0;i<board.length;i++){
                if(board[i] != null){
                    cloned.board[i] = board[i].clone();
                }else{
                    cloned.board[i] = null;
                }
            }
            return cloned;
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("クローンに失敗しました",e);
        }
    }
}