package shogi_five.model;

import shogi_five.model.Node;

import java.util.ArrayList;

public class Tree{
    private ArrayList<Node> node;

    /*
     * コンストラクタ
     */
    public Tree(){
        this.node = new ArrayList<>();
    }

    /*
     * minimax法で探索を行う．決定したNodeを返す．
     */
    public Node miniMax(ArrayList<Node> node,int depth){}
}