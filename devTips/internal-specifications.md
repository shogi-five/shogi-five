# ゲームの推移
## 概略
1. 将棋盤の初期化
2. 将棋盤を表示
3. プレイヤーが動かしたい駒を選択
4. 駒が動ける位置を示す
5. プレイヤーが位置を選択する
6. 駒を動かし、次の将棋盤の状態を得る
7. ゲーム終了か判定
8. 続くなら2に戻る

## 詳細
MVCアーキテクチャで実装を目指す。
それぞれ
M : Bord
V : View
C : GameControler

1. GameControlerが盤面を初期化
2. GameControlerがOperatorに盤面と所有している駒達を渡す
3. OperatorがSelectionリストを返す
4. GameControlerがViewに盤面を渡す
5. プレイヤーが駒を選択
6. 移動可能な駒であればViewが駒をGameControlerに渡す、でなければ5に戻る
7. GameControlerは選択された駒の移動可能な範囲をViewに渡す
8. ユーザが移動可能な範囲から選択する
9. Viewが移動可能な範囲であれば位置をGameControlerに渡す、でなければ8に戻る
10. GameControlerがOperatorにBordと駒の移動先を渡す
11. Operatorは次の盤面をGameControlerに返す
12. GameControlerがViewに次の盤面を渡す
13. GameControlerがゲーム終了か判定する
14. 指し手を交代し、2に戻る

---

# クラス
## App
### 説明
アプリケーションの立ち上げ。
### フィールド
- View gameView：盤面
- GameControler gameContoler：コントローラ
- Operator operator：オペレータ

### メソッド
- void run()：ゲーム開始

---

## GameControler
### 説明
ゲームのコントロールをする。

### フィールド
- View gameView：盤面を表示
- Bord bord：盤面
- Operator operator：盤面を遷移させる
- Player[] playerlist：プレイヤーのリスト
- Bord[] bordLog：過去の盤面

### メソッド
- void game()：ゲームのループ
- void init()：盤面の初期化
- void setView(Bord：盤面)： 盤面の表示
- void setSelectionView()：Selection[]をViewにセット
- Selection[] getSelectionList(Bord：盤面, Piece[]：自分の駒)：現在移動可能なリストを取得
- void setSelectionList(Selection[])：Selection[]をPlayerにセット
- bool checkSelectionList(Selection)：SelectionがSelection[]にあるか判定
- int getSelectionPosition()：駒の移動先の位置を取得
- Bord getNextBord(Bord：盤面, Piece：駒, int：位置)：次の盤面の生成
- void checkVictory()：勝敗の判定
- void changePlayer()：指し手の交代
- void setPlayer(Piece)：所有している駒をセット
- void getPlayer(Piece)：所有している駒をゲット

---

## View
### 説明
将棋盤、駒、駒の移動可能範囲を表示する。
### フィールド
- Bord bord：盤面
- GameControler gameControler：コントローラ

### メソッド
- void PrintBord(Bord：盤面)：盤面を表示
- int getMovePiece()：移動させたい駒の位置を取得
- int getMovePoisition()：移動させたい駒の遷移先の位置を取得

---
## Operator
### 説明
盤面を更新したり、駒が移動可能な位置を返す。

### フィールド

### メソッド
- Bord operator(Bord：盤面, Piece：駒, int：位置)：駒を移動させ、次の盤面を返す
- Selection[] availablePiece(Bord：盤面, Piece[]：駒のリスト)：駒のリストがそれぞれ移動可能なSelectionのリストを返す
- void piceMove(Bord：盤面, Piece：駒, int：移動先)：駒を移動先に移動させる

---

## Bord
#### 説明
将棋盤を表す。

#### フィールド
- Piece[] bord：将棋盤を表す一次配列

#### メソッド
- void setPiece(Piece：駒, int：位置)：駒を位置にセット
- Piece getPiece(int：位置)：位置にセットされている駒をゲット

---
## Piece
### 説明
駒を表す。

### フィールド
- int pieceClass：駒の種類
- int position：現在地
- boolean promote：成りかどうか

### メソッド
- boolean move(Bord：盤面, int：移動先)：移動できるか判定

---
## Selection
#### 説明
駒の種類と、移動可能な番地の組み合わせ

#### フィールド
- Piece 駒の種類
- int 遷移先の番地

---
## Player
### 説明
プレイヤーを表す。

### フィールド
- Piece[] havePiece：所有している駒
- Selection[] availablePiece：移動可能な駒と行先

### メソッド

---

## Human extend Player
### 説明
人間のプレイヤーを表す。

---

## AI extend Player
### 説明
AIプレイヤーを表す。
