package view;

import controller.GameController;
import model.board.Board;

public class View {
    private GameController gameController;

    public View(GameController gameController) {
        this.gameController = gameController;
    }

    /*
     * 盤面を表示
     */
    public void printBoard(Board board) {}

    /*
     * 選択した駒の位置を取得
     */
    public int getMovePice(){}

    /*
     * 選択した駒の移動先を取得
     */
    public int getMovePosition(){}
}
