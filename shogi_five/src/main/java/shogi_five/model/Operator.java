package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.piece.*;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthStyle;

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
        Board board = status.getBoard();
        Human human = status.getHuman();
        AI ai = status.getAI();

        Piece piece = board.getPiece(now);//駒を取得
        Piece playerpiece=null;
        if(piece.getOwner()){//humanの場合
            for(Piece p:human.getHavePiece()){if(p.getPosition() == now){playerpiece = p;}}
        }else{
            for(Piece p:ai.getHavePiece()){if(p.getPosition() == now){playerpiece = p;}}
        }

        Status next_status = new Status(board, human, ai);

        if (board.getPiece(next) == null){ //移動先に駒がない場合
            //成る場合
            if ((next < 5 && piece.getOwner() == true) || (next > 19 && piece.getOwner() == false)){
                piece.setPromote(true);
            }
            //駒を移動
            piece.setPosition(next);
            playerpiece.setPosition(next);
            board.setPiece(null, now);
            board.setPiece(piece, next);

        }else{//移動先に駒がある場合
            //成る場合
            if ((next < 5 && piece.getOwner() == true) || (next > 19 && piece.getOwner() == false)){
                piece.setPromote(true);playerpiece.setPromote(true);;
            }

            Piece captured_piece = board.getPiece(next);//移動先の駒を取得
            captured_piece.setPromote(false);//成りを解除

            //駒を移動
            if (piece.getOwner() == true){//もし人間が取得したら

                //駒の所有権を移動
                captured_piece.setOwner(true);//駒自身に対して
                human.addHavePiece(captured_piece);//人間に追加
                ai.removeHavePiece(captured_piece);//aiから削除

                int pieceClass = captured_piece.getPieceClass();//駒の種類

                //相手の駒を持駒に移動
                switch (pieceClass){
                    case 2://人間の飛車
                        captured_piece.setPosition(35);
                        board.setPiece(captured_piece, 35);
                        break;
                    case 3://人間の角
                        captured_piece.setPosition(36);
                        board.setPiece(captured_piece, 36);
                        break;                    
                    case 4://人間の金
                        captured_piece.setPosition(37);
                        board.setPiece(captured_piece, 37);
                        break;                    
                    case 5://人間の銀
                        captured_piece.setPosition(38);
                        board.setPiece(captured_piece, 38);
                        break;                    
                    case 6://人間の歩
                        captured_piece.setPosition(39);
                        board.setPiece(captured_piece, 39);
                        break;                    
                    case 12://AIの飛車
                        captured_piece.setPosition(40);
                        board.setPiece(captured_piece, 40);
                        break;                    
                    case 13://AIの角
                        captured_piece.setPosition(41);
                        board.setPiece(captured_piece, 41);
                        break;                    
                    case 14://AIの金
                        captured_piece.setPosition(42);
                        board.setPiece(captured_piece, 42);
                        break;                    
                    case 15://AIの銀
                        captured_piece.setPosition(43);
                        board.setPiece(captured_piece, 43);
                        break;                    
                    case 16://AIの歩
                        captured_piece.setPosition(44);
                        board.setPiece(captured_piece, 44);
                        break;
                }

                //自分の駒を相手の位置に移動
                piece.setPosition(next);playerpiece.setPosition(next);
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
                    case 2://人間の飛車
                        board.setPiece(captured_piece, 25);
                        break;
                    case 3://人間の角
                        board.setPiece(captured_piece, 26);
                        break;                    
                    case 4://人間の金
                        board.setPiece(captured_piece, 27);
                        break;                    
                    case 5://人間の銀
                        board.setPiece(captured_piece, 28);
                        break;                    
                    case 6://人間の歩
                        board.setPiece(captured_piece, 29);
                        break;                    
                    case 12://AIの飛車
                        board.setPiece(captured_piece, 30);
                        break;                    
                    case 13://AIの角
                        board.setPiece(captured_piece, 31);
                        break;                    
                    case 14://AIの金
                        board.setPiece(captured_piece, 32);
                        break;                    
                    case 15://AIの銀
                        board.setPiece(captured_piece, 33);
                        break;                    
                    case 16://AIの歩
                        board.setPiece(captured_piece, 34);
                        break;                    
                }

                //自分の駒を相手の位置に移動
                piece.setPosition(next);playerpiece.setPosition(next);
                board.setPiece(null, now);
                board.setPiece(piece, next);
            }
        }

        //Statusにセット
        next_status.setAI(ai);
        next_status.setBoard(board);
        next_status.setHuman(human);

        return next_status;
    }

    /*
     * 駒が移動可能なリストを返す
     * @param board 現在の盤面
     * @param position 駒の位置
     * @return ArrayList<Integer> 駒が移動可能な位置のリスト
     */
    public static ArrayList<Integer> availableMove(Board board, int position){
        //駒が移動できるリストを取得
        ArrayList<Integer> positionList = new ArrayList<>();

        if (position > 24){//持ち駒からの移動の場合
            //駒を取得
            Piece piece = board.getPiece(position);

            

            if (piece instanceof Hu){//もし歩なら
                if (piece.getOwner()){//人間側なら
                    for(int i=5;i<25;i++){
                        if (board.getPiece(i) == null){
                            positionList.add(i);
                        }
                    }
                }else{
                    for(int i=0;i<20;i++){
                        if (board.getPiece(i) == null){
                            positionList.add(i);
                        }
                    }
                }
            }else{
                for (int i=0; i<25 ; i++){
                    if (board.getPiece(i) == null){
                        positionList.add(i);
                    }
                }
            }
        }else{
            //駒を取得
            Piece piece = board.getPiece(position);
            
            
            //駒が移動できるリストを取得
            if (piece != null) {
                positionList = piece.move(board); 
            }
            
        }
        return positionList;
    }

    /*
     * Statusの評価値を返す
     */
    public static int calEval(Status status){
        return h1(status);
    }

    /*
     * 駒の数で評価を行う。
     * AIが多く所有する方が高く評価する
     * @return 評価値
     */
    public static int h1(Status status){
        int humanSumOfPieces = status.getHuman().getHavePiece().size();//人間の所有している駒の個数の合計
        int aiSumOfPieces = status.getAI().getHavePiece().size();//AIの所有している駒の個数の合計

        return aiSumOfPieces - humanSumOfPieces;//AIの個数 - 人間の個数
    }

    /*
     * 駒が前に進むほど高く評価する
     * @return 評価値
     */
    public static int h2(Status status){
        ArrayList<Piece> aiHavePieces = status.getAI().getHavePiece();//AIの所有している駒のリスト

        int aiSumOfPiecesPosition = 0;
        for(Piece piece:aiHavePieces){
            aiSumOfPiecesPosition += piece.getPosition();
        }

        return aiSumOfPiecesPosition;
    }

    /*
     * 駒の成りを評価する
     * AIが所有している駒の内
     * 成の駒を1、そうでない駒を0として評価
     * @return 評価値
     */
    public static int h3(Status status){
        ArrayList<Piece> aiHavePieces = status.getAI().getHavePiece();//AIの所有している駒のリスト

        int aiSumOfPiecesPromote = 0;
        for(Piece piece:aiHavePieces){
            if (piece.getPromote()){
                aiSumOfPiecesPromote++;
            }
        }

        return aiSumOfPiecesPromote;
    }

    /*
     * 王の動きに着目
     * AIの王の動きを大きく、人間の王の動きを小さくする
     * @return 評価値
     */
    public static int h4(Status status){
        ArrayList<Piece> aiHavePieces = status.getAI().getHavePiece();//AIの所有している駒のリスト

        int aiOuMoveNum=0;//AIの王が動ける場所の数
        for(Piece piece:aiHavePieces){
            if (piece instanceof Ou){//王なら                
                aiOuMoveNum = availableMove(status.getBoard(), piece.getPosition()).size();//AIの王が動ける場所の数
                break;
            }
        }

        ArrayList<Piece> humanHavePieces = status.getHuman().getHavePiece();//人間の所有している駒のリスト

        int humanOuMoveNum=0;//人間の王が動ける場所の数
        for(Piece piece:humanHavePieces){
            if (piece instanceof Ou){//王なら
                humanOuMoveNum = availableMove(status.getBoard(), piece.getPosition()).size();//人間の王が動ける場所の数
                break;
            }
        }

        return aiOuMoveNum - humanOuMoveNum;
    }

    /*
     * 各駒に評価値をつける
     * AIの所有している駒の評価値の合計-人間の所有している駒の評価値の合計
     * 王＝無限大(1000000)
     * 歩＝1
     * 金＝15
     * 銀＝5
     * 飛＝20
     * 角＝10
     * 龍王＝40
     * 龍馬＝30
     * @return 評価値
     */
    public static int h5(Status status){
        ArrayList<Piece> aiHavePieces = status.getAI().getHavePiece();//AIの所有している駒のリスト

        int aiHavePiecesValue=0;//AIの所有している駒の評価値の合計
        for(Piece piece:aiHavePieces){
            int pieceClass = piece.getPieceClass();
            if (pieceClass%10 == 1){//王なら
                aiHavePiecesValue += 1000000;
            }else if(pieceClass%10 == 2){//飛車
                aiHavePiecesValue += 20;
            }else if(pieceClass%10 == 3){//角
                aiHavePiecesValue += 10;
            }else if(pieceClass%10 == 4 || pieceClass%10 == 9 || pieceClass%10 == 0){//金、成銀、と金
                aiHavePiecesValue += 15;
            }else if(pieceClass%10 == 5){//銀
                aiHavePiecesValue += 5;
            }else if(pieceClass%10 == 6){//歩
                aiHavePiecesValue += 1;
            }else if(pieceClass%10 == 7){//龍王
                aiHavePiecesValue += 40;
            }else if(pieceClass%10 == 8){//龍馬
                aiHavePiecesValue += 30;
            }
            System.out.println("piece name:" + pieceClass + ", value:"+aiHavePiecesValue);
        }

        ArrayList<Piece> humanHavePieces = status.getHuman().getHavePiece();//人間の所有している駒のリスト

        int humanHavePiecesValue=0;//人間の所有している駒の評価値の合計
        for(Piece piece:humanHavePieces){
            int pieceClass = piece.getPieceClass();
            if (pieceClass%10 == 1){//王なら
                humanHavePiecesValue += 1000000;
            }else if(pieceClass%10 == 2){//飛車
                humanHavePiecesValue += 20;
            }else if(pieceClass%10 == 3){//角
                humanHavePiecesValue += 10;
            }else if(pieceClass%10 == 4 || pieceClass%10 == 9 || pieceClass%10 == 0){//金、成銀、と金
                humanHavePiecesValue += 15;
            }else if(pieceClass%10 == 5){//銀
                humanHavePiecesValue += 5;
            }else if(pieceClass%10 == 6){//歩
                humanHavePiecesValue += 1;
            }else if(pieceClass%10 == 7){//龍王
                humanHavePiecesValue += 40;
            }else if(pieceClass%10 == 8){//龍馬
                humanHavePiecesValue += 30;
            }            
        }

        System.out.println("ai:"+aiHavePiecesValue + ",human:" + humanHavePiecesValue);

        return aiHavePiecesValue - humanHavePiecesValue;
    } 
}