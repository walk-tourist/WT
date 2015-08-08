package wt.walk_tourist.MDF.point_management;

/**
 * Created by User on 2015/08/01.
 */
public class D_Item {
    /** アイテムID */
    private int itemId;

    /** アイテム名 */
    private String itemName;

    /** アイテムの説明*/
    private String itemDescription;

    /** アイテム価格 */
    private int itemPrice;

    /** アイテム画像 */
    private int itemImage;

    // コンストラクタ
    D_Item(String itemName, String itemDescription, int itemPrice, int itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemImage() {
        return itemImage;
    }
}
