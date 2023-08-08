package shogi_five;

import shogi_five.controller.GameController;
import shogi_five.model.Board;
import shogi_five.model.Human;
import shogi_five.model.Player;
import shogi_five.view.View;
// ((2(5|6|7|8|9))|3\d|4\d)
public class Main{
    public static void main(String[] args){
        //コントローラの初期化
        GameController gameController = new GameController();
        //ゲーム開始
        gameController.game();
    }
}