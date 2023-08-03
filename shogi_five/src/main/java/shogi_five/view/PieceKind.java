package shogi_five.view;


import java.awt.Image;
import java.awt.Toolkit;

import shogi_five.model.piece.Piece;
import shogi_five.model.piece.Ou;
import shogi_five.model.piece.Gin;
import shogi_five.model.piece.Hisha;
import shogi_five.model.piece.Hu;
import shogi_five.model.piece.Kaku;
import shogi_five.model.piece.Kin;

enum PieceKind {
    EMPTY       (null),
    OU          ("assets\\img\\piece_ou.png"),          // 王
    GYOKU       ("assets\\img\\piece_gyoku.png"),       // 玉
    KIN         ("assets\\img\\piece_kin.png"),         // 金
    GIN         ("assets\\img\\piece_gin.png"),         // 銀
    NARI_GIN    ("assets\\img\\piece_nari_gin.png"),    // 成銀
    KAKU        ("assets\\img\\piece_kaku.png"),        // 角
    NARI_KAKU   ("assets\\img\\piece_nari_kaku.png"),   // 馬
    HISHA       ("assets\\img\\piece_hisha.png"),       // 飛車
    NARI_HISHA  ("assets\\img\\piece_nari_hisha.png"),  // 竜
    FU          ("assets\\img\\piece_fu.png"),          // 歩
    NARI_FU     ("assets\\img\\piece_nari_fu.png");     // 成金

    
    private final Image img;

    private PieceKind(String path) {
        
        this.img = path == null ? null : Toolkit.getDefaultToolkit().getImage(path);
    }

    public Image getImage() {
        return this.img;
    }

    public boolean isEmpty() {
        return this == PieceKind.EMPTY;
    }

    public PieceKind reversed() {
        switch (this) {

            case GIN: return PieceKind.NARI_GIN;
            case NARI_GIN: return PieceKind.GIN;

            case KAKU: return PieceKind.NARI_KAKU;
            case NARI_KAKU: return PieceKind.KAKU;

            case HISHA: return PieceKind.NARI_HISHA;
            case NARI_HISHA: return PieceKind.HISHA;

            case FU: return PieceKind.NARI_FU;
            case NARI_FU: return PieceKind.FU;

            default : return this;
        }   
    }

        public PieceKind front() {
        switch (this) {

            case NARI_GIN: return PieceKind.GIN;
            case NARI_KAKU: return PieceKind.KAKU;
            case NARI_HISHA: return PieceKind.HISHA;
            case NARI_FU: return PieceKind.FU;
            
            default : return this;
        }   
    }

    public PieceKind back() {
        switch (this) {
            case GIN: return PieceKind.NARI_GIN;
            case KAKU: return PieceKind.NARI_KAKU;
            case HISHA: return PieceKind.NARI_HISHA;
            case FU: return PieceKind.NARI_FU;
            default : return this;
        }   

    }

    public static PieceKind from(Piece piece) {
        if (piece instanceof Gin && !piece.getPromote()) {
            return PieceKind.GIN;
        }
        if (piece instanceof Gin && piece.getPromote()) {
            return PieceKind.NARI_GIN;
        }

        if (piece instanceof Hisha && !piece.getPromote()) {
            return PieceKind.HISHA;
        }
        if (piece instanceof Hisha && piece.getPromote()) {
            return PieceKind.NARI_HISHA;
        }

        if (piece instanceof Hu && !piece.getPromote()) {
            return PieceKind.FU;
        }
        if (piece instanceof Hu && piece.getPromote()) {
            return PieceKind.NARI_FU;
        }

        if (piece instanceof Kaku && !piece.getPromote()) {
            return PieceKind.KAKU;
        }
        if (piece instanceof Kaku && piece.getPromote()) {
            return PieceKind.NARI_KAKU;
        }

        if (piece instanceof Kin) {
            return PieceKind.KIN;
        }
        if (piece instanceof Ou && !piece.getPromote()) {
            return PieceKind.OU;
        }
        if (piece instanceof Ou && piece.getPromote()) {
            return PieceKind.GYOKU;
        }
        
        return EMPTY;

    }

    public static Piece into(Box box) {
        if (box == null) {
            return null;
        }
        Piece piece = null;
        int position = box.getPosition();
        boolean isDownward = box.isDownward();

        switch (box.getPiece()) {
            case GIN: {
                piece = new Gin(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case NARI_GIN :{
                piece = new Gin(position, !isDownward);
                piece.setPromote(true);
                break;
            }
            case HISHA: {
                piece = new Hisha(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case NARI_HISHA:{
                piece = new Hisha(position, !isDownward);
                piece.setPromote(true);
                break;
            }
            case FU: {
                piece = new Hu(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case NARI_FU:{
                piece = new Hu(position, !isDownward);
                piece.setPromote(true);
                break;
            }
            case KAKU: {
                piece = new Kaku(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case NARI_KAKU:{
                piece = new Kaku(position, !isDownward);
                piece.setPromote(true);
                break;
            }
            case KIN: {
                piece = new Kin(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case OU: {
                piece = new Ou(position, !isDownward);
                piece.setPromote(false);
                break;
            }
            case GYOKU: {
                piece = new Ou(position, isDownward);
                piece.setPromote(false);
                break;
            }


            default: return null;
        }
        return piece;
    }
}