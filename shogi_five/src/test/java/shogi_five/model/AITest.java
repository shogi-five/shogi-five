package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.piece.Gin;
import shogi_five.model.piece.Hu;
import shogi_five.model.piece.Kaku;
import shogi_five.model.piece.Piece;

public class AITest {
    /*
     * ディープコピーのテスト
     */
    @Test
    public void testclone(){

        //人間の所有駒
        ArrayList<Piece> AIPieces  = new ArrayList<>();        
        Hu huHu0 = new Hu(20, true);AIPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);AIPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);AIPieces.add(huKaku2);

        ArrayList<Chooseable> human_chooseable = new ArrayList<>();

        //人間
        AI ai = new AI(AIPieces, human_chooseable);

        AI next = ai.clone();

        //同じ参照でないか
        assertNotSame(ai, next);
        System.out.println(ai + "," + next);

        //同じ内容か
        for(int i=0;i<AIPieces.size();i++){
            System.out.println(ai.getHavePiece().get(i) + "," + next.getHavePiece().get(i));
            assertNotSame(ai.getHavePiece().get(i), next.getHavePiece().get(i));
        }
    }
}