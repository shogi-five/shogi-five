package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;
import shogi_five.model.piece.*;

/**
 * Boardの単体テスト
 */
public class BoardTest{
    /*
     * 駒の動きをテスト
     */
    @Test
    public void testMove() {
        Board board = new Board();
        System.out.println(board.getPiece(8));
        //assertEquals(board.getPiece(8), null);
    }

    /*
     * ディープコピーのテスト
     */
    @Test
    public void testclone(){
        Board board = new Board();

        Board next = board.clone();//コピー

        //同じ参照でないか
        assertNotSame(board, next);

        //同じ内容か
        for(int i=0;i<25;i++){
            if (board.getPiece(i) == null){
                assertNull(board.getPiece(i));
                assertNull(next.getPiece(i));
            }else{
                assertEquals(board.getPiece(i).getClass(),next.getPiece(i).getClass());
            }
        }
    }

}
