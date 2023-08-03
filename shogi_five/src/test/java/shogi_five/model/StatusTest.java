package shogi_five.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import org.junit.Test;

import shogi_five.model.piece.Hu;
import shogi_five.model.piece.Piece;

public class StatusTest {
    /*
     * ディープコピーのテスト
     */
    @Test
    public void testclone(){
        Board board = new Board();
        Human human = new Human(new ArrayList<Piece>(), new ArrayList<Chooseable>());
        AI ai = new AI(new ArrayList<Piece>(), new ArrayList<Chooseable>());

        Status status = new Status(board, human, ai);

        Status next = status.clone();

        assertNotSame(status, next);
    }
}
