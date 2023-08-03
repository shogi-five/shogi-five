package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.piece.Gin;
import shogi_five.model.piece.Hu;
import shogi_five.model.piece.Kaku;
import shogi_five.model.piece.Piece;

public class HumanTest {
    /*
     * ディープコピーのテスト
     */
    @Test
    public void testclone(){

        //人間の所有駒
        ArrayList<Piece> humanPieces  = new ArrayList<>();        
        Hu huHu0 = new Hu(20, true);humanPieces.add(huHu0);
        Gin huGin1 = new Gin(21, true);humanPieces.add(huGin1);
        Kaku huKaku2 = new Kaku(22, true);humanPieces.add(huKaku2);

        ArrayList<Chooseable> human_chooseable = new ArrayList<>();

        //人間
        Human human = new Human(humanPieces, human_chooseable);

        Human next = human.clone();

        //同じ参照でないか
        assertNotSame(human, next);
        System.out.println(human + "," + next);

        //同じ内容か
        for(int i=0;i<humanPieces.size();i++){
            System.out.println(human.getHavePiece().get(i) + "," + next.getHavePiece().get(i));
            assertNotSame(human.getHavePiece().get(i), next.getHavePiece().get(i));
        }

    }
}
