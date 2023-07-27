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
3. OperatorがChooseableリストを返す
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

## 駒の所有権が移る場合
1. プレイヤーが駒を選択
2. Viewが選択された位置をGameControlerに渡す
3. GameControlerが位置から駒を推定
4. プレイヤーが所有している駒でなければ、元に戻る
5. GameControlerがOperatorに盤面と駒と位置を渡す
6. Operatorは駒が移動可能な位置のリスト(Chooseable)を返す
7. GameControlerは移動可能なリストをViewに渡す
8. プレイヤーが移動先を決定
9. Viewが選択された位置をGameControlerに渡す
10. GameControlerが盤面と駒と移動先、各プレイヤーの所有している駒のリストをOperator渡す
11. Operatorは駒を動かし、盤面、駒のリストも更新し返す
12. GameControlerがViewに渡す

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
- void main()：ゲーム開始

---

## GameControler
### 説明
ゲームのコントロールをする。

### フィールド
- Operator operator：盤面を遷移させる
- Status status：盤面や駒の所有などを表す
- View view：盤面を表示する
- ArrayList<Status> previousBoard：過去の盤面

### メソッド
- void game()：ゲームのループ
- void init()：盤面の初期化
- void setView(Bord：盤面)： 盤面の表示
- int getSelectPiece()：選択された駒の番号を取得
- void setAvailableMoveView()：移動可能な範囲を表示
- int getSelectPosition()：選択された駒の移動先を取得
- int getChooseablePosition()：駒の移動先の位置を取得
- void checkVictory()：勝敗の判定
- Status getNextHumanStatus()：人間の手から次の状態を取得
- Status getNextAIStatus()：AIの手から次の状態を取得
- updateStaus(Stattus)：Statusの更新

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
- Status operator(Staus：状況, Piece：駒, int：位置)：駒を移動させ、次の盤面を返す
- int[] availablePiece(Bord：盤面, Piece：駒)：駒が移動可能なリストを返す

---
# Status
## 説明
盤面、駒の所有を示し、その場面の状況を表す。

## フィールド
- Board board：盤面を表す
- Player[] playerPiece：プレイヤーが所有する駒を表す

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
- int[]：移動可能なリスト

### メソッド
- int[] move(Bord：盤面)：現在地から移動可能なリスト

---
## Chooseable
#### 説明
駒と、移動可能な番地の組み合わせ

#### フィールド
- Piece 駒の種類
- int 遷移先の番地

---
## Player
### 説明
プレイヤーを表す。

### フィールド
- Piece[] havePiece：所有している駒
- Chooseable[] availablePiece：移動可能な駒と行先

### メソッド

---

## Human extend Player
### 説明
人間のプレイヤーを表す。

---

## AI extend Player
### 説明
AIプレイヤーを表す。
