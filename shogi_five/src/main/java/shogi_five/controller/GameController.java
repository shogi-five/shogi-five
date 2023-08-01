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

            int selectPiece = getSelectPiece();//駒を選択

            ArrayList<Integer> moveList = this.operator.availableMove(this.status.getBoard(), selectPiece);//移動できる範囲

            setAvailableMoveView(moveList);//移動可能範囲を表示

            int selectPosition = getSelectPosition();//移動させる場所を取得

            Status nextStatus = getNextHumanStatus(selectPiece, selectPosition);//盤面を変更

            this.updateStatus(nextStatus);//Statusの更新

            this.setView();//更新を反映

            if (this.checkVictory(true)){//勝利判定
                break;
            }

            //*** AIの操作

            Status AINextStatus = getNextAIStatus();//AIの探索

            this.updateStatus(AINextStatus);//Statusの更新

            this.setView();//更新を反映

            if (this.checkVictory(false)){//勝利判定
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
     * @return int 移動させたい駒の位置
     */
    public int getSelectPiece(){
        return this.view.getMovePice();
    }

    /*
     * 盤面に移動可能な範囲を表示
     */
    public void setAvailableMoveView(ArrayList<Integer> moveList){}

    /*
     * 移動させる場所を取得
     * @return int 駒の移動先
     */
    public int getSelectPosition(){
        return this.view.getMovePosition();
    }

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
            pieces = this.status.getHuman().getHavePiece();//AIの駒のリストを取得
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

}
