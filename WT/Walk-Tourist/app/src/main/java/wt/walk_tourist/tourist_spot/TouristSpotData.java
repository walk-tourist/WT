package wt.walk_tourist.tourist_spot;

/**
 * Created by User on 2015/05/09.
 */
public class TouristSpotData {
    /** 観光地ID */
    private int spotId;

    /** 観光地名 */
    private String spotName;

    /** 観光地数 */
    private String spotCount;

    // コンストラクタ
    TouristSpotData(String spotName, String spotCount) {
        this.spotName = spotName;
        this.spotCount = spotCount;
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

}
