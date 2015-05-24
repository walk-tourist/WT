package wt.walk_tourist.tourist_spot;

/**
 * Created by User on 2015/05/09.
 */
public class D_TouristSpot {
    /** 観光地ID */
    private int spotId;

    /** 観光地名 */
    private String spotName;

    /** 観光地数 */
    private String spotCount;

    /** 観光地イメージ画像 */
    private int spotImage;

    /** カメラアイコン表示フラグ */
    private boolean cameraImageFlag;

    /**
     * コンストラクタ
     * @param spotName
     * @param spotCount
     * @param spotImage
     * @param cameraImageFlag
     */
    D_TouristSpot(String spotName, String spotCount, int spotImage, boolean cameraImageFlag) {
        this.spotName = spotName;
        this.spotCount = spotCount;
        this.spotImage = spotImage;
        this.cameraImageFlag = cameraImageFlag;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public String getSpotCount() {
        return spotCount;
    }

    public int getSpotImage() {
        return spotImage;
    }

    public boolean isCameraImageFlag() {
        return cameraImageFlag;
    }
}
