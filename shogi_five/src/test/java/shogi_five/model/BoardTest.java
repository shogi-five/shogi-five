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

}
