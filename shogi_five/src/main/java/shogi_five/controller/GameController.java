package shogi_five.controller;

import shogi_five.model.*;
import shogi_five.model.piece.*;
import shogi_five.view.View;

import java.util.ArrayList;

public class GameController {
    private Operator operator;
    private Status status;
    private View view;
    private ArrayList<Status> previousBoard;


    /**
     * ゲームをコントロールする
     */
    public GameController() {
        this.status = new Status(new Board(), new Human(null,null), new AI(null, null));
        this.view = new View();
        this.operator = new Operator();
        this.previousBoard = new ArrayList<Status>();
    }

    /*
     * ゲームのメインループ
     */
    public void game() {
        this.init();//初期化
        this.setView();//盤面を表示

        do{
            //***人間の操作

            int selectPiece = this.getSelectPiece();//駒を選択

            ArrayList<Integer> moveList = this.operator.availableMove(this.status.getBoard(), selectPiece);//移動できる範囲

            this.setAvailableMoveView(moveList);//移動可能範囲を表示

            int selectPosition = this.getSelectPosition();//移動させる場所を取得

            Status nextStatus = this.operator.operator(this.status, selectPiece, selectPosition);//盤面を変更

            this.updateStatus(nextStatus);//Statusの更新

            this.setView();//更新を反映

            if (this.checkVictory()){//勝利判定
                break;
            }

            //*** AIの操作

            ArrayList<Integer> inferenceResults = this.status.getAI().inference(this.status);//AIの探索で[駒の位置，動かす場所]を返す

            Status AINextStatus = this.operator.operator(this.status, inferenceResults.get(0), inferenceResults.get(1));//盤面を更新

            this.updateStatus(AINextStatus);//Statusの更新

            this.setView();//更新を反映

            if (this.checkVictory()){//勝利判定
                break;
            }
            
        }while(true);

    }

    /* 
     * ゲームの初期化
    */
    public void init(){       
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();

        for (int i=0;i<25;i++){
            Piece piece = this.status.getBoard().getPiece(i);
            if(piece.getOwner()){//プレイヤーが所持する駒なら
                humanPieces.add(piece);
            }else{
                aiPieces.add(piece);
            }
        }

        this.status.getHuman().setHavePiece(humanPieces);
        this.status.getAI().setHavePiece(aiPieces);

    }

    /*
     * 盤面の表示
     */
    public void setView(){}

    /*
     * 駒を選択
     */
    public int getSelectPiece(){}

    /*
     * 盤面に移動可能な範囲を表示
     */
    public void setAvailableMoveView(ArrayList<Integer> moveList){}

    /*
     * 移動させる場所を取得
     */
    public int getSelectPosition(){}

    /*
     * 現在移動可能なリストを取得
     */
    public void getChooseableList(){}

    /*
     * Playerが選択した駒の移動可能なリストをセット
     */
    public void setChooseableList(){}

    /*
     * 次の盤面の生成
     */
    public Board getNextBoard(){}

    /*
     * 勝敗の判定
     */
    public boolean checkVictory(){}

    /*
     * 指し手の交代
     */
    public void changePlayer(){}

    /*
     * プレイヤーの所有している駒をセット
     */
    public void setHavePice(Piece pice){}

    /*
     * プレイヤーの所有している駒をゲット
     */
    public Piece getHavePice(){}

    /*
     * Statusの更新
     */
    public void updateStatus(Status nextStatus){
        this.previousBoard.add(this.status);
        this.status = nextStatus;
    }

}
