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
        //何もないboardをつくる
        Board board = new Board();
        for (int j = 0; j < 25;j++){
            board.setPiece(null, j);
        }

        //銀を置く
        int pos = 5;
        board.setPiece(new Hu(pos, true), pos);

        //動く位置に駒を置く
        //board.setPiece(new Gin(23,true), 23);

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
        Hu hu = new Hu(0, true);
        assertFalse(hu.getPromote());

        hu.setPromote(true);
        assertTrue(hu.getPromote());
        
        hu.setPromote(false);
        assertFalse(hu.getPromote());
    }
}
