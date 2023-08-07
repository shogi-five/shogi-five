package shogi_five.model;

import shogi_five.model.Status;
import shogi_five.model.Node;
import shogi_five.model.Operator;
import shogi_five.model.Board;
import shogi_five.model.Player;
import shogi_five.model.Chooseable;
import shogi_five.model.piece.*;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class Tree{
    /*
     * フィールド
     */
    /*
     * コンストラクタ
     */

    /*
     * minimax法で探索を行う．決定したNodeを返す．
     */
    public static Node miniMax(Node node,int depth){
        if(depth % 2 == 0){
            depth += 1;
        }
        return evalHuman(node,depth);
    }

    /*
     *AIの評価値を計算する 
     */
    public static Node evalAI(Node node,int depth){
        //System.out.println("depth = "+depth);//log
        if(depth == 0){
            int eval = Operator.calEval(node.getStatus());
            node.setEvaluation(eval);
            //System.out.println("eval = "+eval);
            return node;
        }

        Status status = node.getStatus();
        Board board = status.getBoard();
        int minEval = Integer.MAX_VALUE;//評価値の最小
        Node minNode = new Node();//評価値が最小のノード
        //Pieceのリストを取得
        ArrayList<Piece> haveAIPiece = status.getAI().getHavePiece(); 
        /* 
        ArrayList<Piece> haveAIPiece = new ArrayList<>();
        for(int k = 0;k<45;k++){
            if(node.getStatus().getBoard().getPiece(k) != null){
                if(node.getStatus().getBoard().getPiece(k).getOwner() == true){
                    haveAIPiece.add(node.getStatus().getBoard().getPiece(k));
                }
            }
        }
        */

        int lenHavePiece = haveAIPiece.size();
        /*  //log
        for(int cnt = 0;cnt < lenHavePiece;cnt++){
            System.out.println(haveAIPiece.get(cnt).getPosition());
        }
        */
        

        for1: for(int i = 0; i < lenHavePiece ; i++){
            //動く場所のリストを取得
            ArrayList<Integer> availableMoveList = new ArrayList<>();
            try{
            availableMoveList = Operator.availableMove(board, haveAIPiece.get(i).getPosition());            
            }catch(NullPointerException e){
                //System.out.println("NullPointerExceptionError\n評価値を強制的に計算します");
                int eval = Operator.calEval(node.getStatus());
                node.setEvaluation(eval);
                return node;
            }

            //それぞれの評価値を計算し、最も高い評価値を取得する
            
            for2: for(int j = 0; j < availableMoveList.size() ; j++){
                //駒を動かした新しいノードを用意する
                Node nextNode = node.clone();
                Operator.operator(nextNode.getStatus(),haveAIPiece.get(i).getPosition(),availableMoveList.get(j));
                
                ArrayList<Piece> pieces = nextNode.getStatus().getAI().getHavePiece();
                if (i >= pieces.size()) {
                    break for1;
                }
                pieces.get(i).setPosition(availableMoveList.get(j));
                
                //盤面を評価する
                int eval = evalHuman(nextNode,depth - 1).getEvaluation();
                //評価値の最小値より小さければ代入
                if(minEval > eval){
                    minEval = eval;
                    nextNode.setEvaluation(eval);
                    minNode = nextNode;

                    //System.out.println("depth = " + depth + "\neval = " +maxEval + "\n");//debug
                }
            }
        }
        return minNode;

    }

    /*
     * 人間の階層の評価値を計算する
     */
    public static Node evalHuman(Node node,int depth){
        //System.out.println("depth = "+depth);//log

        Status status = node.getStatus();
        Board board = status.getBoard();
        int maxEval = Integer.MIN_VALUE;//評価値の最大
        Node maxNode = new Node();//評価値が最大のノード

        //Pieceのリストを取得
        ArrayList<Piece> haveHumanPiece = status.getHuman().getHavePiece();
        /* 
        ArrayList<Piece> haveHumanPiece = new ArrayList<>();
        for(int k = 0;k<45;k++){
            if(node.getStatus().getBoard().getPiece(k) != null){
                if(node.getStatus().getBoard().getPiece(k).getOwner() == false){
                    haveHumanPiece.add(node.getStatus().getBoard().getPiece(k));
                }
            }
        }
        */

        int lenHavePiece = haveHumanPiece.size();

        /*log 
        for(int cnt1 = 0;cnt1 < 45;cnt1++){
            if(board.getPiece(cnt1) != null){
            System.out.println(" position = "+(cnt1)+":"+board.getPiece(cnt1).getOwner());
            }
        }
        for(int cnt = 0;cnt < lenHavePiece;cnt++){
            System.out.println("have human piece:"+haveHumanPiece.get(cnt).getPosition());
        }
        */

        for(int i = 0; i < lenHavePiece ; i++){
            //動く場所のリストを取得
           ArrayList<Integer> availableMoveList = new ArrayList<>();
            try{
            availableMoveList = Operator.availableMove(board, haveHumanPiece.get(i).getPosition());            
            }catch(NullPointerException e){
                System.out.println("NullPointerExceptionError\n評価値を強制的に計算します");
                int eval = Operator.calEval(node.getStatus());
                node.setEvaluation(eval);
                return node;
            }
            //それぞれの動かし方の評価値を計算し、最も低い評価値を取得する
            int size = availableMoveList.size();
            for(int j = 0; j < size ; j++){

                //駒を動かした新しいノードを用意する
                Node nextNode = node.clone();
                Operator.operator(nextNode.getStatus(),haveHumanPiece.get(i).getPosition(),availableMoveList.get(j));

                //盤面を評価する
                int eval = evalAI(nextNode,depth - 1).getEvaluation();
                //評価値の最大値より大きければ代入
                if(maxEval < eval){
                    maxEval = eval;
                    nextNode.setEvaluation(eval);
                    maxNode = nextNode;
                }
            }

        }
        return maxNode;
    }
}