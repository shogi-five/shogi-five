package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;

/*
 * 飛車のテスト
 */
public class HishaTest {
    /*
     * 駒の動ける場所をテスト
     */

    @Test
    public void testMove() {
        //何もないboardをつくる
        Board board = new Board();
        for (int j = 0; j < 25;j++){
            board.setPiece(null, j);
        }

        //飛車を置く
        int pos = 0;
        board.setPiece(new Hisha(pos, true), pos);

        //動く位置に駒を置く
        board.setPiece(new Gin(1,true), 1);
        board.getPiece(pos).setPromote(true);

        //表示
        ArrayList<Integer> expected = board.getPiece(pos).move(board);
        for(int i = 0;i < expected.size();i++){
            System.out.println(expected.get(i));
        }

    }
        /*
     * 駒が成るかテスト
     */


  
    @Test
    public void testPromotedPiece() {
        Hisha hisha = new Hisha(0, true);
        assertFalse(hisha.getPromote());

        hisha.setPromote(true);
        assertTrue(hisha.getPromote());
        
        hisha.setPromote(false);
        assertFalse(hisha.getPromote());
    }
    
    
}