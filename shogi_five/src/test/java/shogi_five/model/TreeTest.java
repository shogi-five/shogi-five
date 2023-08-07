package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;
import shogi_five.model.piece.*;

/**
 * Boardの単体テスト
 */
public class TreeTest{
    /*
     * 駒の動きをテスト
     */
    @Test
    public void testMiniMax() {
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
        Node node = new Node(status,0,0);

        //盤面を動かす
        //Operator.operator(status, 0, 5);
        //Operator.operator(status, 24, 9);


        Node ans = Tree.miniMax(node, 5);
        Board ansBoard = ans.getStatus().getBoard();
        for(int i = 0;i < 45;i++){
            System.out.print(" position = "+(i)+":"+ansBoard.getPiece(i)+":");
            if(ansBoard.getPiece(i) == null){
                System.out.println("");
            }else if(ansBoard.getPiece(i).getOwner()){
                System.out.println("true");
            }else{
                System.out.println("false");
            }
        }
        System.out.println("\neval = " + ans.getEvaluation());

        //assertEquals(board.getPiece(8), null);
    }

}
