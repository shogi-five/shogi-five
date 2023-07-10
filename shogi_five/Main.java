package app;

import contoller.GameController;
import model.board.Board;
import model.board.Human;
import model.board.Player;
import view.View;

public class App{
    public static void main(String[] args){
        //コントローラの初期化
        GameController gameController = new GameController();
        //ゲーム開始
        gameController.game();
    }
}