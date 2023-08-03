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
    public void testMove() {
        Board board = new Board();

        ArrayList<Chooseable> availavelePieceAI = new ArrayList<>();
        ArrayList<Piece> havePieceAI = new ArrayList<>();
        
        //havePieceAI.add(board.getPiece(0));
        //havePieceAI.add(board.getPiece(1));
        havePieceAI.add(board.getPiece(2));
        havePieceAI.add(board.getPiece(3));
        havePieceAI.add(board.getPiece(4));
        havePieceAI.add(board.getPiece(9));
        

        ArrayList<Chooseable> availavelePieceHuman = new ArrayList<>();
        ArrayList<Piece> havePieceHuman = new ArrayList<>();

        //havePieceAI.add(board.getPiece(24));
        //havePieceAI.add(board.getPiece(23));
        havePieceAI.add(board.getPiece(22));
        havePieceAI.add(board.getPiece(21));
        havePieceAI.add(board.getPiece(20));
        havePieceAI.add(board.getPiece(15));

        AI ai = new AI(havePieceAI, availavelePieceAI);
        Human human = new Human(havePieceHuman,availavelePieceHuman);
        Status status = new Status(board, human, ai);
        Node node = new Node(status,0,0);

        Node ans = Tree.miniMax(node, 3);
        Board ansBoard = ans.getStatus().getBoard();
        for(int i = 0;i < 5;i++){
            for(int j = 0;j<5;j++){
                //System.out.print(ansBoard.getPiece(i*j)+" ");
            }
            //System.out.println("");
        }

        //assertEquals(board.getPiece(8), null);
    }

}
