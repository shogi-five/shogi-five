package shogi_five.model.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;
import shogi_five.model.Board;



/*
 * 角のテスト
 */
public class KakuTest {
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

        //角を置く
        int pos = 23;
        board.setPiece(new Kaku(pos, true), pos);

        //動く位置に駒を置く
        board.setPiece(new Gin(11,false), 11);

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
        Kaku kaku = new Kaku(0, true);
        assertFalse(kaku.getPromote());

        kaku.setPromote(true);
        assertTrue(kaku.getPromote());
        
        kaku.setPromote(false);
        assertFalse(kaku.getPromote());
    }
}