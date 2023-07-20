package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

import java.util.ArrayList;

public class Operator {
    /*
     * 駒の移動、所有を遷移させ次の場面を返す
     * @param board 現在の盤面
     * @param human 人の駒の所有リスト
     * @param ai aiの駒の所有リスト
     * @param now 動かす駒の位置
     * @param next 駒の行先
     * @return Status 駒を移動させたStatus
     */
    public static Status operator(Status status, int now, int next){
        Board board = (Board)status.getBoard();
        Human human = status.getHuman();
        AI ai = status.getAI();

        Piece piece = board.getPiece(now);//駒を取得

        Status next_Status = new Status(board, human, ai);

        if (board.getPiece(next) == null){ //移動先に駒がない場合
            //成る場合
            if ((next < 5 && piece.getOwner() == true) || (next > 19 && piece.getOwner() == false)){
                piece.setPromote(true);
            }
            //駒を移動
            piece.setPosition(next);
            board.setPiece(null, now);
            board.setPiece(piece, next);

        }else{//移動先に駒がある場合
            //***********************todo*********************** */
            //成る場合
            if ((next < 5 && piece.getOwner() == true) || (next > 19 && piece.getOwner() == false)){
                piece.setPromote(true);
            }

            Piece captured_piece = board.getPiece(next);//移動先の駒を取得
            captured_piece.setPromote(false);//成りを解除

            //駒を移動
            if (piece.getOwner()){//もし人間が取得したら

                //駒の所有権を移動
                captured_piece.setOwner(true);//駒自身に対して
                human.addHavePiece(captured_piece);//人間に追加
                ai.removeHavePiece(captured_piece);//aiから削除

                int pieceClass = captured_piece.getPieceClass();//駒の種類

                //相手の駒を持駒に移動
                switch (pieceClass){
                    case 2:
                        board.setPiece(captured_piece, 35);
                        break;
                    case 3:
                        board.setPiece(captured_piece, 36);
                        break;                    
                    case 4:
                        board.setPiece(captured_piece, 37);
                        break;                    
                    case 5:
                        board.setPiece(captured_piece, 38);
                        break;                    
                    case 6:
                        board.setPiece(captured_piece, 39);
                        break;                    
                    case 12:
                        board.setPiece(captured_piece, 40);
                        break;                    
                    case 13:
                        board.setPiece(captured_piece, 41);
                        break;                    
                    case 14:
                        board.setPiece(captured_piece, 42);
                        break;                    
                    case 15:
                        board.setPiece(captured_piece, 43);
                        break;                    
                    case 16:
                        board.setPiece(captured_piece, 44);
                        break;                    
                }

                //自分の駒を相手の位置に移動
                piece.setPosition(next);
                board.setPiece(null, now);
                board.setPiece(piece, next);
                
            }else{//AIが駒を取った場合

                //駒の所有権を移動
                captured_piece.setOwner(false);//駒自身に対して
                human.removeHavePiece(piece);//人間から削除
                ai.addHavePiece(captured_piece);//aiに追加

                int pieceClass = captured_piece.getPieceClass();//駒の種類

                //相手の駒を持駒に移動
                switch (pieceClass){
                    case 2:
                        board.setPiece(captured_piece, 25);
                        break;
                    case 3:
                        board.setPiece(captured_piece, 26);
                        break;                    
                    case 4:
                        board.setPiece(captured_piece, 27);
                        break;                    
                    case 5:
                        board.setPiece(captured_piece, 28);
                        break;                    
                    case 6:
                        board.setPiece(captured_piece, 29);
                        break;                    
                    case 12:
                        board.setPiece(captured_piece, 30);
                        break;                    
                    case 13:
                        board.setPiece(captured_piece, 31);
                        break;                    
                    case 14:
                        board.setPiece(captured_piece, 32);
                        break;                    
                    case 15:
                        board.setPiece(captured_piece, 33);
                        break;                    
                    case 16:
                        board.setPiece(captured_piece, 34);
                        break;                    
                }

                //自分の駒を相手の位置に移動
                piece.setPosition(next);
                board.setPiece(null, now);
                board.setPiece(piece, next);
            }

            //新たなStateを作成
            Status next_status = new Status(board, human, ai);
            return next_Status;
        }

        //Statusにセット
        next_Status.setAI(ai);
        next_Status.setBoard(board);
        next_Status.setHuman(human);

        return next_Status;
    }

    /*
     * 駒が移動可能なリストを返す
     * @param board 現在の盤面
     * @param position 駒の位置
     * @return ArrayList<Integer> 駒が移動可能な位置のリスト
     */
    public static ArrayList<Integer> availableMove(Board board, int position){
        //駒を取得
        Piece piece = board.getPiece(position);        
        //駒が移動できるリストを取得
        ArrayList<Integer> positionList = piece.move(board);

        return positionList;
    }
}