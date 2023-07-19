package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.piece.*;

/**
 * Boardの単体テスト
 */
public class ChooseableTest{
    /*
     * 駒の動きをテスト
     */
    @Test
    public void testMove() {
        Hu hu = new Hu(5, true);

        Chooseable cho1 = new Chooseable(hu, 10);
        
        assertEquals(cho1.getMoveablePosition(), 10);

        assertEquals(cho1.getPiceClass(), hu);
    }

}
