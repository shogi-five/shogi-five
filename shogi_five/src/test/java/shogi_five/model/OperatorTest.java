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
     * availableMoveのテスト
     * 持ち駒から移動可能な位置のリストが取得できれば良い
     */
    @Test
    public void testAvailableMoveMochigomaKin(){
        //正解データ
        ArrayList<Integer> kincorrect = new ArrayList<>();
        for (int i=0;i<25;i++){
            if (i == 5 || i == 13){
                continue;
            }
            kincorrect.add(i);
        }

        //何もないboardをつくる
        Board board = new Board();
        for (int j = 0; j < 25;j++){
            board.setPiece(null, j);
        }

        //邪魔駒を設定
        board.setPiece(new Hu(13, true), 13);
        board.setPiece(new Hu(5, true), 5);


        //持ち駒を設定
        int position = 37;
        board.setPiece(new Kin(position, true), position);

        ArrayList<Integer> ans = Operator.availableMove(board, position);

        //正解と比較
        assertEquals(kincorrect, ans);
    }

    /*
     * availableMoveのテスト
     * 持ち駒から移動可能な位置のリストが取得できれば良い
     */
    @Test
    public void testAvailableMoveMochigomaHu(){
        //正解データ
        ArrayList<Integer> kincorrect = new ArrayList<>();
        for (int i=5;i<25;i++){
            if (i == 5 || i == 13){
                continue;
            }
            kincorrect.add(i);
        }

        //何もないboardをつくる
        Board board = new Board();
        for (int j = 0; j < 25;j++){
            board.setPiece(null, j);
        }

        //邪魔駒を設定
        board.setPiece(new Hu(13, true), 13);
        board.setPiece(new Hu(5, true), 5);


        //持ち駒を設定
        int position = 37;
        board.setPiece(new Hu(position, true), position);

        ArrayList<Integer> ans = Operator.availableMove(board, position);

        //正解と比較
        assertEquals(kincorrect, ans);
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

    /*
     * 評価関数h1についてテスト
     */
    @Test
    public void h1Test(){
        Board board = new Board();
        for (int i=0;i<25;i++){//空のBoardを作成
            board.setPiece(null, i);
        }

        //配列の用意
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Chooseable> humanAvailblePiece = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();
        ArrayList<Chooseable> aiAvailblePiece = new ArrayList<>();

        //ai所有の駒の準備
        Hu aiHu0 = new Hu(0, false);board.setPiece(aiHu0, 0);aiPieces.add(aiHu0);
        Kin aiKin1 = new Kin(1, false);board.setPiece(aiKin1, 1);aiPieces.add(aiKin1);
        Gin aiGin2 = new Gin(2, false);board.setPiece(aiGin2, 2);aiPieces.add(aiGin2);
        Hisha aiHisha3 = new Hisha(3, false);board.setPiece(aiHisha3, 3);aiPieces.add(aiHisha3);

        //human所有の駒の準備
        Hu huHu0 = new Hu(20, true);board.setPiece(huHu0, 20);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);board.setPiece(huGin1, 21);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);board.setPiece(huKaku2, 22);humanPieces.add(huKaku2);

        //Statusの用意
        Human human = new Human(humanPieces, humanAvailblePiece);
        AI ai = new AI(aiPieces, aiAvailblePiece);
        Status status = new Status(board, human, ai);

        //Statusの評価値を計算
        int evaluation = Operator.h1(status);

        //正解と比較
        assertEquals(1, evaluation);
    }

    /*
     * 評価関数h2についてテスト
     */
    @Test
    public void h2Test(){
        Board board = new Board();
        for (int i=0;i<25;i++){//空のBoardを作成
            board.setPiece(null, i);
        }

        //配列の用意
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Chooseable> humanAvailblePiece = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();
        ArrayList<Chooseable> aiAvailblePiece = new ArrayList<>();

        //ai所有の駒の準備
        Hu aiHu0 = new Hu(0, false);board.setPiece(aiHu0, 0);aiPieces.add(aiHu0);
        Kin aiKin1 = new Kin(1, false);board.setPiece(aiKin1, 1);aiPieces.add(aiKin1);
        Gin aiGin2 = new Gin(2, false);board.setPiece(aiGin2, 2);aiPieces.add(aiGin2);
        Hisha aiHisha3 = new Hisha(3, false);board.setPiece(aiHisha3, 3);aiPieces.add(aiHisha3);

        //human所有の駒の準備
        Hu huHu0 = new Hu(20, true);board.setPiece(huHu0, 20);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);board.setPiece(huGin1, 21);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);board.setPiece(huKaku2, 22);humanPieces.add(huKaku2);

        //Statusの用意
        Human human = new Human(humanPieces, humanAvailblePiece);
        AI ai = new AI(aiPieces, aiAvailblePiece);
        Status status = new Status(board, human, ai);

        //Statusの評価値を計算
        int evaluation = Operator.h2(status);

        //正解と比較
        assertEquals(6, evaluation);
    }
    
    /*
     * 評価関数h3についてテスト
     */
    @Test
    public void h3Test(){
        Board board = new Board();
        for (int i=0;i<25;i++){//空のBoardを作成
            board.setPiece(null, i);
        }

        //配列の用意
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Chooseable> humanAvailblePiece = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();
        ArrayList<Chooseable> aiAvailblePiece = new ArrayList<>();

        //ai所有の駒の準備
        Hu aiHu0 = new Hu(0, false);aiHu0.setPromote(true);board.setPiece(aiHu0, 0);aiPieces.add(aiHu0);
        Kin aiKin1 = new Kin(1, false);board.setPiece(aiKin1, 1);aiPieces.add(aiKin1);
        Gin aiGin2 = new Gin(2, false);board.setPiece(aiGin2, 2);aiPieces.add(aiGin2);
        Hisha aiHisha3 = new Hisha(3, false);board.setPiece(aiHisha3, 3);aiPieces.add(aiHisha3);

        //human所有の駒の準備
        Hu huHu0 = new Hu(20, true);board.setPiece(huHu0, 20);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);board.setPiece(huGin1, 21);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);board.setPiece(huKaku2, 22);humanPieces.add(huKaku2);

        //Statusの用意
        Human human = new Human(humanPieces, humanAvailblePiece);
        AI ai = new AI(aiPieces, aiAvailblePiece);
        Status status = new Status(board, human, ai);

        //Statusの評価値を計算
        int evaluation = Operator.h3(status);

        //正解と比較
        assertEquals(1, evaluation);
    }

    /*
     * 評価関数h4についてテスト
     */
    @Test
    public void h4Test(){
        Board board = new Board();
        for (int i=0;i<25;i++){//空のBoardを作成
            board.setPiece(null, i);
        }

        //配列の用意
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Chooseable> humanAvailblePiece = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();
        ArrayList<Chooseable> aiAvailblePiece = new ArrayList<>();

        //ai所有の駒の準備
        Hu aiHu0 = new Hu(0, false);aiHu0.setPromote(true);board.setPiece(aiHu0, 0);aiPieces.add(aiHu0);
        Kin aiKin1 = new Kin(1, false);board.setPiece(aiKin1, 1);aiPieces.add(aiKin1);
        Gin aiGin2 = new Gin(2, false);board.setPiece(aiGin2, 2);aiPieces.add(aiGin2);
        Hisha aiHisha3 = new Hisha(3, false);board.setPiece(aiHisha3, 3);aiPieces.add(aiHisha3);
        Ou aiOu4 = new Ou(4, false);board.setPiece(aiOu4, 4);aiPieces.add(aiOu4);


        //human所有の駒の準備
        Hu huHu0 = new Hu(20, true);board.setPiece(huHu0, 20);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);board.setPiece(huGin1, 21);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);board.setPiece(huKaku2, 22);humanPieces.add(huKaku2);
        Ou huOu3 = new Ou(23, true);board.setPiece(huOu3, 23);humanPieces.add(huOu3);


        //Statusの用意
        Human human = new Human(humanPieces, humanAvailblePiece);
        AI ai = new AI(aiPieces, aiAvailblePiece);
        Status status = new Status(board, human, ai);

        //Statusの評価値を計算
        int evaluation = Operator.h4(status);

        //正解と比較
        assertEquals(-2, evaluation);
    }

    /*
     * 評価関数h5についてテスト
     */
    @Test
    public void h5Test(){
        Board board = new Board();
        for (int i=0;i<25;i++){//空のBoardを作成
            board.setPiece(null, i);
        }

        //配列の用意
        ArrayList<Piece> humanPieces = new ArrayList<>();
        ArrayList<Chooseable> humanAvailblePiece = new ArrayList<>();
        ArrayList<Piece> aiPieces = new ArrayList<>();
        ArrayList<Chooseable> aiAvailblePiece = new ArrayList<>();

        //ai所有の駒の準備
        Hu aiHu0 = new Hu(0, false);aiHu0.setPromote(true);board.setPiece(aiHu0, 0);aiPieces.add(aiHu0);
        Kin aiKin1 = new Kin(1, false);board.setPiece(aiKin1, 1);aiPieces.add(aiKin1);
        Gin aiGin2 = new Gin(2, false);board.setPiece(aiGin2, 2);aiPieces.add(aiGin2);
        Hisha aiHisha3 = new Hisha(3, false);board.setPiece(aiHisha3, 3);aiPieces.add(aiHisha3);
        Ou aiOu4 = new Ou(4, false);board.setPiece(aiOu4, 4);aiPieces.add(aiOu4);


        //human所有の駒の準備
        Hu huHu0 = new Hu(20, true);board.setPiece(huHu0, 20);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);board.setPiece(huGin1, 21);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);board.setPiece(huKaku2, 22);humanPieces.add(huKaku2);
        Ou huOu3 = new Ou(23, true);board.setPiece(huOu3, 23);humanPieces.add(huOu3);


        //Statusの用意
        Human human = new Human(humanPieces, humanAvailblePiece);
        AI ai = new AI(aiPieces, aiAvailblePiece);
        Status status = new Status(board, human, ai);

        //Statusの評価値を計算
        int evaluation = Operator.h5(status);

        //正解と比較
        assertEquals(39, evaluation);
    }

    /*
     * 駒が移動した際のhumanとaiの更新のテスト
     */
    @Test
    public void humanAiChangeTest(){
        //盤面を用意する
        Board board = new Board();

        ArrayList<Chooseable> availavelePieceAI = new ArrayList<>();
        ArrayList<Piece> havePieceAI = new ArrayList<>();
        
        havePieceAI.add(board.getPiece(0));
        havePieceAI.add(board.getPiece(1));
        havePieceAI.add(board.getPiece(2));
        havePieceAI.add(board.getPiece(3));
        havePieceAI.add(board.getPiece(4));
        havePieceAI.add(board.getPiece(9));
        

        ArrayList<Chooseable> availavelePieceHuman = new ArrayList<>();
        ArrayList<Piece> havePieceHuman = new ArrayList<>();

        havePieceHuman.add(board.getPiece(24));
        havePieceHuman.add(board.getPiece(23));
        havePieceHuman.add(board.getPiece(22));
        havePieceHuman.add(board.getPiece(21));
        havePieceHuman.add(board.getPiece(20));
        havePieceHuman.add(board.getPiece(15));

        AI ai = new AI(havePieceAI, availavelePieceAI);
        Human human = new Human(havePieceHuman,availavelePieceHuman);
        Status status = new Status(board, human, ai);

        //動かした盤面から計算してみる
        Operator.operator(status, 0, 5);
        Operator.operator(status, 24, 9);

        for(int i=0;i<45;i++){
            Piece piece = status.getBoard().getPiece(i);//盤面から駒を取得
            if (piece == null){//nullの場合
                continue;
            }else if(piece.getOwner()){//humanが所有の場合
                for(Piece p:human.getHavePiece()){
                    if (piece.getPosition() == p.getPosition()){
                        assertEquals(p.getOwner(), piece.getOwner());
                    }
                }
            }else{//aiが所有の場合
                for(Piece p:human.getHavePiece()){
                    if (piece.getClass() == p.getClass() && p.getOwner() == piece.getOwner()){
                        assertEquals(p.getOwner(), piece.getOwner());
                    }
                }
            }
        }
    }
}
