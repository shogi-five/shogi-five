# 説明
今回はubuntu:22をベースに、JDK17をインストールするDockerfileを共有、コンテナ作成までを示す。

以下の説明はこの名前で設定する。
- イメージ名：shogi-five-ubuntu
- コンテナ名：shogi-dev

# WSL2
Windows for subsystem Linuxの場合、
```
sudo apt install docker
```

# windows
1. [[https://docs.docker.com/desktop/install/windows-install/ | 公式]]の「Docker Desktop for Windows」で、インストーラをダウンロード
2. インストーラを起動
3. Docker Desktopを起動
4. Accept
5. アカウント作成を求められるので適宜対応
6. Dockerfileを接地したディレクトリでPowerShellを開く
```bash
# dockerfileからイメージ作成
docker build ./ -t shogi-five-image
# イメージからコンテナ作成
docker create -it --name shogi-five shogi-five-image

# 立ち上げ
docker start shogi-five

# 入る
docker exec -it shogi-five /bin/bash

# バージョン確認
java --version
# コンテナを抜ける
exit
```

# 今後使うとき
```bash
# イメージの確認
docker images

# コンテナの確認
docker ps -a

# コンテナの起動
docker start shogi-dev

# 入る
docker exec -it shogi-five /bin/bash

# コンテナ停止
docker stop shogi-dev


# コンテナ削除
# コンテナを停止してから
docker rm shogi-dev

# イメージ削除
# コンテナを削除してから
docker rmi shogi-five-ubuntu
```

実際に作業を行うのはコンテナの中。そしてコンテナはイメージを元に作成される。

dockerのコンテナを使う際は
- イメージからコンテナを作る
- コンテナを起動する
- コンテナに入る

コンテナを削除する際は
- コンテナを停止する
- コンテナを削除