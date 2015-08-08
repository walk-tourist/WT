package wt.walk_tourist.MDF.point_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wt.walk_tourist.MDF.game.D_Game_Contents;
import wt.walk_tourist.R;

/**
 * Created by User on 2015/05/09.
 */
public class LA_Item extends BaseAdapter {
    private final Context context;
    private final int layoutId;
    private final List<D_Item> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * コンストラクタ
     * @param context
     * @param layoutId
     * @param list
     */
    public LA_Item(Context context, int layoutId, List<D_Item> list) {
        super();
        this.context =context;
        this.layoutId = layoutId;
        this.list = list;
    }

    private static class ViewHolder {
        TextView itemName;
        TextView itemDescription;
        TextView itemPrice;
        ImageView itemImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        D_Item item = (D_Item)getItem(position);

        if (null == convertView) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId, null);

            final ViewHolder holder = new ViewHolder();

            holder.itemName = (TextView)view.findViewById(R.id.list_item_name);
            holder.itemDescription = (TextView)view.findViewById(R.id.list_item_description);
            holder.itemPrice = (TextView)view.findViewById(R.id.list_item_price);
            holder.itemImage = (ImageView)view.findViewById(R.id.list_item_image);

            holder.itemImage.setTag(list.get(position));

            view.setTag(holder);
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).itemImage.setTag(list.get(position));
        }

        ViewHolder viewholder = (ViewHolder) view.getTag();

        // アイテム名
        viewholder.itemName.setText(item.getItemName());
        // アイテムの説明文
        viewholder.itemDescription.setText(item.getItemDescription());
        // アイテムの価格
        viewholder.itemPrice.setText(Integer.toString(item.getItemPrice()) + "pt");
        // アイテムイメージ画像
        viewholder.itemImage.setImageResource(item.getItemImage());

        return view;
    }
}
