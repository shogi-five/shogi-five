package shogi_five;

import shogi_five.contoller.GameController;
import shogi_five.model.Board;
import shogi_five.model.Human;
import shogi_five.model.Player;
import shogi_five.view.View;

public class App{
    public static void main(String[] args){
        //コントローラの初期化
        GameController gameController = new GameController();
        //ゲーム開始
        gameController.game();
    }
}