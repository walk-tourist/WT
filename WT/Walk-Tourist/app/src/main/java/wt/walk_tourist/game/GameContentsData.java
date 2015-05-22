package wt.walk_tourist.game;

/**
 * Created by User on 2015/05/09.
 */
public class GameContentsData {
    /** ゲームコンテンツID */
    private int contentsId;

    /** ゲームコンテンツ名 */
    private String contentsName;

    /** ゲーム画像 */
    private int contentsImage;

    // コンストラクタ
    GameContentsData(String contentsName, int contentsImage) {
        this.contentsName = contentsName;
        this.contentsImage = contentsImage;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    public String getContentsName() {
        return contentsName;
    }

    public int getContentsImage() {
        return contentsImage;
    }
}
