package controller;

import model.board.Board;
import model.player.Player;
import model.operator.Operator;
import view.View;

public class GameController {
    private Board board;
    private Operator operator;
    private Player player1;
    private Player player2;
    private View view;


    /**
     * ゲームをコントロールする
     * @param board
     * @param player1
     * @param player2
     */
    public GameController() {
        this.board = new Board();
        this.player1 = new Player();
        this.player2 = new Player();
        this.view = new View();
        this.operator = new Operator();
    }

    /*
     * ゲームのメインループ
     */
    public void game() {}

    /* 
     * ゲームの初期化
    */
    public init(){}

    /*
     * 盤面の表示
     */
    public setView(){}

    /*
     * 盤面に移動可能な範囲を表示
     */
    public setSlectionView(){}

    /*
     * 現在移動可能なリストを取得
     */
    public getChooseableList(){}

    /*
     * Playerが選択した駒の移動可能なリストをセット
     */
    public setChooseableList(){}

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

}
