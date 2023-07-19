package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 王の単体テスト
 */
public class OuTest 
{
    /*
     * 駒の動きをテスト
     */
    @Test
    public void testMove() {
        Ou ou = new Ou(20, true);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(15);
        expected.add(16);
        expected.add(21);
        
        ArrayList<Integer> actual = ou.move();
        
        assertEquals(expected, actual);
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
