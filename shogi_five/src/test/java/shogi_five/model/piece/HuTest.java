package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.Board;

/*
 * 歩の単体テスト
 */
public class HuTest {
    /*
     * 駒の動ける場所をテスト
     */
    @Test
    public void testMove() {
        Board board = new Board();
        ArrayList<Integer> expected = board.getPiece(15).move(board);
        for(int i = 0;i < expected.size();i++){
            System.out.println(expected.get(i));
        }

    }
    /*
     * 駒の動ける場所をテスト(動く場所に自分の駒があるとき)
     */
    @Test
    public void testCannotMove() {
        Board board = new Board();
        board.setPiece(new Kin(10, true), 10);//金を歩の前に置く
        //System.out.println(board.getPiece(10).getClass());
        ArrayList<Integer> expected = board.getPiece(15).move(board);
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
