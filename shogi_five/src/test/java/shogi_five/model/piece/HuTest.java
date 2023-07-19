package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/*
 * 歩の単体テスト
 */
public class HuTest {
    /*
     * 駒の動きをテスト
     */
    @Test
    public void testMove() {
        Hu hu = new Hu(15, true);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(10);
    
        ArrayList<Integer> actual = hu.move();
        
        assertEquals(expected, actual);

        hu.setPosition(actual.get(0));

        assertEquals(hu.getPosition(), 10);
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

    /*
     * 駒を成らせて動きをテスト
     */
    @Test
    public void testPromotedAndMove() {
        Hu hu = new Hu(10, true);//生成
        ArrayList<Integer> expected1 = new ArrayList<>();
        expected1.add(5);
        ArrayList<Integer> actual1 = hu.move();//移動先一覧
        assertEquals(expected1, actual1);
        hu.setPosition(actual1.get(0));//移動
        hu.setPromote(true);//成り

        ArrayList<Integer> expected2 = new ArrayList<>();
        expected2.add(0);
        expected2.add(1);
        expected2.add(6);
        expected2.add(10);

        ArrayList<Integer> actual2 = hu.move();//移動先一覧
        
        assertEquals(expected2, actual2);
    }    
}
