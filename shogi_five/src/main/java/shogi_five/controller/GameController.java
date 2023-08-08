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
    private boolean isHumanturn;
    


    /**
     * ゲームをコントロールする
     */
    public GameController() {
        this.status = new Status(new Board(), new Human(new ArrayList<Piece>(),new ArrayList<Chooseable>()), new AI(new ArrayList<Piece>(), new ArrayList<Chooseable>()));
        this.view = new View(this);
        this.operator = new Operator();
        this.previousBoard = new ArrayList<Status>();
        this.isHumanturn = true;
    }

    /*
     * ゲームのメインループ
     */
    public void game() {
        this.init();//初期化
        this.setView();//盤面を表示

        

        while (!this.checkVictory(false) && ! this.checkVictory(true)) {
            if (this.isHumanturn) {
                continue;
            }
            Status AINextStatus = getNextAIStatus();//AIの探索
            this.updateStatus(AINextStatus);//Statusの更新
            this.setView();//更新を反映

            this.isHumanturn = true;

        }
        this.isHumanturn = false;
        System.out.println("決着がつきました");
        

    }

    /* 
     * ゲームの初期化
    */
    public void init(){       
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();

        for (int i=0;i<25;i++){
            Piece piece = this.status.getBoard().getPiece(i);
            if (piece == null) {
                continue;
            }
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
    public void setView(){
        this.view.printBoard(this.status.getBoard());
    }


    /*
     * 盤面に移動可能な範囲を表示
     */
    public void setAvailableMoveView(ArrayList<Integer> moveList){}



    /*
     * 現在移動可能なリストを取得
     */
    public void getChooseableList(){}

    /*
     * Playerが選択した駒の移動可能なリストをセット
     */
    public void setChooseableList(){}

    /*
     * 人間の手から次の盤面の生成
     */
    public Status getNextHumanStatus(int selectPiece, int selectPosition){
        return this.operator.operator(this.status, selectPiece, selectPosition);
    }

    /*
     * AIの手から次の盤面の生成
     */
    public Status getNextAIStatus(){
        return this.status.getAI().inference(this.status);
    }

    /*
     * 勝利判定
     * 王が二つ含まれていたら勝利とする．
     * @parame boolean owner trueならHuman，FalseならAIについて勝利判定を行う
     * @return boolean 勝利していたらtrue
     */
    public boolean checkVictory(boolean owner){
        boolean checkVictory = false;

        ArrayList<Piece> pieces = new ArrayList<>();

        if (owner){
            pieces = this.status.getHuman().getHavePiece();//Humanの駒のリストを取得
        }else{
            pieces = this.status.getAI().getHavePiece();//AIの駒のリストを取得
        }

        int OuCounter = 0;//王の個数を数える

        for(Piece piece:pieces){
            if(piece instanceof Ou){
                OuCounter++;
            }
        }
        
        if (OuCounter >= 2){
            checkVictory = true;
        }

        return checkVictory;
    }

    /*
     * Statusの更新
     */
    public void updateStatus(Status nextStatus){
        this.previousBoard.add(this.status);
        this.status = nextStatus;
    }

    /**
     * 割り込み
     * @param src
     * @param des
     * @return おけるかどうか
     */
    public boolean putPieceEvent(int src, int des) {

        if (!this.isHumanturn) {
            return false;
        }

        ArrayList<Integer> moveList = this.operator.availableMove(this.status.getBoard(), src);//移動できる範囲
        
        // 移動可能かどうか判定
        boolean isContain = false;

        for (int d: moveList) {
            isContain = isContain | (d==des);
        }
        
        // 移動できなかった.
        if (!isContain) {
            return false;
        }

        Status nextStatus = getNextHumanStatus(src, des);//盤面を変更
        this.updateStatus(nextStatus);//Statusの更新

        this.view.printBoard(this.status.getBoard());   
        this.isHumanturn = false;
        return true;
    }

}
