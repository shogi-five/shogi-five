package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;
import shogi_five.model.piece.*;
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
     * 駒の所有権が移らない駒の移動のテスト
     */
    @Test
    public void testOperator1(){

        //人間の所有駒
        Hu human_hu = new Hu(15,true);
        ArrayList<Piece> human_piece = new ArrayList<>();
        ArrayList<Chooseable> human_chooseable = new ArrayList<>();
        human_piece.add(human_hu);

        //AIの所有駒
        ArrayList<Piece> ai_piece = new ArrayList<>();
        ArrayList<Chooseable> ai_chooseable = new ArrayList<>();    

        //ボードを用意
        Board board = new Board();
        //人間
        Human human = new Human(human_piece, human_chooseable);
        //AI
        AI ai = new AI(ai_piece, ai_chooseable);

        Status current_status = new Status(board, human, ai);

        //駒を配置
        int position = 15;
        int next_position = 10;

        //駒の移動
        Status next_status = Operator.operator(current_status, position, next_position);

        //正解と比較
        assertEquals(next_status.getBoard().getPiece(10).getClass(), human_hu.getClass());
    }

    /*
     * 駒の所有権が移る駒の移動のテスト
     * 6に自分の歩を配置し、1に移動させる
     * 駒の所有権の移動と、成りをテスト
     */
    @Test
    public void testOperator2(){
        //ボードを用意
        Board board = new Board();

        //人間の所有駒
        Hu human_hu = new Hu(6,true);
        ArrayList<Piece> human_piece = new ArrayList<>();
        ArrayList<Chooseable> human_chooseable = new ArrayList<>();
        human_piece.add(human_hu);
        board.setPiece(human_hu, 6);
        //人間
        Human human = new Human(human_piece, human_chooseable);

        //AIの所有駒
        ArrayList<Piece> ai_piece = new ArrayList<>();
        ArrayList<Chooseable> ai_chooseable = new ArrayList<>();
        Piece kaku = board.getPiece(1);
        ai_piece.add(kaku);
        //AI
        AI ai = new AI(ai_piece, ai_chooseable);

        Status current_status = new Status(board, human, ai);

        //駒を配置
        int position = 6;
        int next_position = 1;

        //駒の移動
        Status next_status = Operator.operator(current_status, position, next_position);

        //正解データ
        ArrayList<Piece> correct_human_piece = new ArrayList<>();
        ArrayList<Piece> correct_ai_piece = new ArrayList<>();
        correct_human_piece.add(human_hu);
        correct_human_piece.add(kaku);

        //正解と比較
        assertEquals(next_status.getBoard().getPiece(next_position), human_hu);//歩が正しい位置に移動しているか
        assertEquals(next_status.getBoard().getPiece(next_position).getPromote(), true);//歩が成っているか
        assertEquals(next_status.getBoard().getPiece(41).getClass(), kaku.getClass());//角が持ち駒に移動しているか
        assertEquals(correct_ai_piece, next_status.getAI().getHavePiece());//aiの持ち駒リストが変更されているか
        assertEquals(correct_human_piece, next_status.getHuman().getHavePiece());//人間の持ち駒リストが変更されているか
    }    
}
