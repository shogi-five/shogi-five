from PIL import Image

def make_blue_transparent(image_path, output_path):
    # 画像を開く
    img = Image.open(image_path)
    # RGBAモードに変換
    img = img.convert("RGBA")
    
    # 画像のピクセルデータを取得
    data = img.getdata()
    
    # 新しいピクセルデータを生成
    new_data = []
    for item in data:
        # 青色（RGB値が(0, 0, 255)）の場合、アルファチャンネルを0にする（透明にする）
        if item[2] >= 128:
            new_data.append((0, 0, 0, 0))
        else:
            new_data.append(item)
    
    # 新しいピクセルデータを設定
    img.putdata(new_data)
    
    # 画像を保存
    img.save(output_path, "PNG")

if __name__ == "__main__":
    # 入力画像のパスと出力画像のパスを指定して実行
    input_image_path = "piece.png"
    output_image_path = "output_image.png"
    make_blue_transparent(input_image_path, output_image_path)
