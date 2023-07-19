package shogi_five.model.piece;

import java.util.ArrayList;

/**
 * 将棋の駒を表す抽象クラス
 */

public abstract class Piece {
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者
}
