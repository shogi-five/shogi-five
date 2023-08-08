package shogi_five.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import shogi_five.view.PieceKind;

/**
 * 将棋の駒を表示し, クリックされているかどうかを監視する.
 */
public class Box extends JPanel implements MouseListener {
    private View view;
    private int position;           // 位置
    private PieceKind pieceKind;    // 駒の種類
    private boolean isDownward;     // 駒が下向きならば `true`
    private boolean isMouseEntered; // box上にカーソルがあるかどうか
    private boolean isPicked;      // box が押されているか





    /**
     *  コンストラクタ
     * @param view ビュー
     * @param position 位置
     * @param size box の大きさ
     */
    Box(View view, int position, int size) {

        // フィールとの初期化
        this.view = view;
        this.position = position;
        this.pieceKind = PieceKind.EMPTY;
        this.isDownward = false;
        this.isMouseEntered = false;
        this.isPicked = false;
        
        // マウスイベントの処理
        this.addMouseListener(this);

        // サイズを指定
        this.setPreferredSize(new Dimension(size, size));
        
        // 背景を透明に
        this.setBackground(new Color(0, 0, 0, 0));
    }





    /*****  IMPLIMENT FOR  `MouseListener` *****/

    // クリックされたとき (mousePressed, Released で処理を行う)
    public void mouseClicked(MouseEvent e) {}

    // 押されたとき
    public void mousePressed(MouseEvent e) {
        this.isPicked = true;
        this.view.getCursor().pressed(this);
    }

    // 離したとき
    public void mouseReleased(MouseEvent e) {
        this.isPicked = false;
        this.view.getCursor().released(this);
    }

    //　カーソルがこの `box` 上に侵入したとき
    public void mouseEntered(MouseEvent e) {
        this.isMouseEntered = true;
        this.view.getCursor().entered(this);
        this.view.repaint();
    }

    //　カーソルがこの `box` 上から出たとき
    public void mouseExited(MouseEvent e) {
        this.isMouseEntered = false;
        this.view.getCursor().exited(this);
        this.view.repaint();
        
    }

    /*****  END IMPLIMENT FOR  `MouseListener` *****/





    /***** Override *****/

    /**
     * 再描画処理
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // 縦横のサイズ
        int width = this.getWidth();
        int height = this.getHeight();
        
        // 背景を透明に
        graphics.setColor(new Color(0, 0, 0, 0));
        graphics.fillRect(0, 0, width, height);

        // 空の場合 終了
        if (this.getPiece().isEmpty()) { return; }

        // 駒の画像を取得
        Image img = this.getPiece().getImage();
        Graphics2D graphics2D = (Graphics2D)graphics;

        // 下向きのとき画像を 180度回転させる
        if (this.isDownward()) {
            graphics2D.rotate(Math.toRadians(180), img.getWidth(this)/2, img.getHeight(this)/2);
        }
        
        Cursor cursor = this.view.getCursor();

        if ( !this.isDownward() && (
                cursor.getPickedBox() == this || 
                (cursor.getPickedBox() == null && cursor.getEnteredBox() == this))) {
            
            graphics2D.drawImage(img, 0, -(int)(this.getHeight()*0.1), this);
            return;
        }
        graphics2D.drawImage(img, 0, 0, this);
        


    }

    /***** END Override *****/





    /**
     * この `Box` 上にカーソルがあるかどうかを返します。
     * 
     * @return カーソルがあるとき {@code true} そうでないとき {@code false}
     */
    public boolean isMouseEntered() {
        return this.isMouseEntered;
    }





    /**
     * この `Box` が押されているかどうかを返します。
     * 押されたあと, カーソルが この `Box` 上から出たあとも,
     * ボタンが離されるまで true を返します.
     * 
     * @return `Box` が押されているかどうか
     */
    public boolean isPicked() {
        return this.isPicked;
    }





    /**
     * この `Box`　が表示している駒の種類を返します 
     * 
     * @return　駒の種類
     */
    public PieceKind getPiece() {
        return this.pieceKind;
    }





    /**
     * この `Box` が表示している駒の向きを返します。
     * 下向きの場合 `true` を返します。
     * 
     * @return 駒の向き
     */
    public boolean isDownward() {
        return this.isDownward;
    }





    /**
     * この `Box`　の位置を返します。
     * 
     * @return　位置
     */
    public int getPosition() {
        return this.position;
    }





    /**
     * 表示する駒の種類と向きをセットします。
     * 
     * @param pieceKind 駒の種類
     * @param isDownward 駒の向き
     */
    public void setPiece(PieceKind pieceKind, boolean isDownward) {
        this.pieceKind = pieceKind;
        this.isDownward = isDownward;
    }
}
