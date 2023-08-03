package shogi_five.model;

import shogi_five.model.Status;

public class Node{
    private Status status;
    private int evaluation;
    private int parent;
    private int child;

    /*
     * コンストラクタ
     */
    public Node(Status status, int parent, int child){
        this.status = status;
        this.parent = parent;
        this.child = child;
    }

    /*
     * statuのゲッター
     */
    public Status getStatus(){
        return this.status;
    }

    /*
     * 評価のゲッター
     */
    public int getEvaluation(){
        return this.evaluation;
    }

    /*
     * 評価のセッター
     */
    public void setEvaluation(int evaluation){
        this.evaluation = evaluation;
    }

    /*
     * parentのゲッター
     */
    public int getParent(){
        return this.parent;
    }

    /*
     * childのゲッター
     */
    public int getChild(){
        return this.child;
    }

    /*
     * childのセッター
     */
    public void setChild(int child){
        this.child = child;
    }


    /*
     * ディープコピー
     */
    public Node clone() throws CloneNotSupportedException{
        try{
            Node cloned = (Node)super.clone();
            return cloned;
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("クローンに失敗しました",e);
        }
    }

}
