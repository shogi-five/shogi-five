package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;


import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.piece.Gin;
import shogi_five.model.piece.Hisha;
import shogi_five.model.piece.Hu;
import shogi_five.model.piece.Kaku;
import shogi_five.model.piece.Kin;
import shogi_five.model.piece.Ou;
import shogi_five.model.piece.Piece;

public class NodeTest {

    /*
     * ディープコピーのテスト
     */
    @Test
    public void testclone(){
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

        Node node = new Node(status, 0, 0);
        Node nextNode = node.clone();

        //同じ参照でないか
        assertNotSame(node, nextNode);

        
        //boardが同じ内容か
        for(int i=0;i<25;i++){
            if (node.getStatus().getBoard().getPiece(i) == null){
                assertNull(node.getStatus().getBoard().getPiece(i));
                assertNull(nextNode.getStatus().getBoard().getPiece(i));
            }else{
                assertEquals(node.getStatus().getBoard().getPiece(i).getClass(), nextNode.getStatus().getBoard().getPiece(i).getClass());
            }
        }

    }
}
