package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;

/**
 * 王の単体テスト
 */
public class OuTest 
{
    /*
     * 駒の動ける場所をテスト
     */
    @Test
    public void testMove() {
        Board board = new Board();
        board.setPiece(null, 21);
        board.setPiece(null, 15);
        //board.setPiece(new Kin(16,false), 16);
        System.out.println(board.getPiece(0).getClass());
        ArrayList<Integer> expected = board.getPiece(20).move(board);
        for(int i = 0;i < expected.size();i++){
            System.out.println(expected.get(i));
        }

    }
    /*
     * 駒が成るかテスト
     */
    @Test
    public void testPromotedPiece() {
        Ou ou = new Ou(0, true);
        assertFalse(ou.getPromote());

        ou.setPromote(true);
        assertTrue(ou.getPromote());
        
        ou.setPromote(false);
        assertFalse(ou.getPromote());
    }
}
