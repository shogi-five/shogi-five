package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;
import shogi_five.model.piece.Piece;
import shogi_five.model.Operator;

/*
 * Operatorのテスト
 */
public class OperatorTest {
    /*
     * availableMoveのテスト
     * 駒の位置から移動可能な位置のリストが取得できれば良い
     */
    @Test
    public void testAvailableMove(){
        //正解データ
        ArrayList<Integer> correct = new ArrayList<>();
        correct.add(10);

        //ボードを用意
        Board board = new Board();

        //動かしたい駒の位置
        int position = 15;
        //駒が移動可能な位置のリストを取得
        ArrayList<Integer> ans = Operator.availableMove(board, position);
        //正解と比較
        assertEquals(correct, ans);
    }

    /*
     * 駒の所有権が映らない駒の移動のテスト
     */
    @Test
    public void testOperator1(){
        //ボードを用意
        Board board = new Board();

        //動かしたい駒の位置
        int position = 15;
        //動かしたい駒を取得
        Piece hu = board.getPiece(position);
        //駒が移動可能な位置のリストを取得
        ArrayList<Integer> availableList = Operator.availableMove(board, position);

        //駒の移動
        Status next_status = Operator.operator(board, null, null, position, availableList.get(0));

        //正解と比較
        assertEquals(next_status.getBoard().getPiece(10), hu);
    }
}
