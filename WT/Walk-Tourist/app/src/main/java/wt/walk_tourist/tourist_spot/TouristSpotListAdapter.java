package wt.walk_tourist.tourist_spot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;

import wt.walk_tourist.R;

/**
 * Created by User on 2015/05/09.
 */
public class TouristSpotListAdapter extends BaseAdapter {
    private final Context context;
    private final int layoutId;
    private final List<TouristSpotData> list;

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
    public TouristSpotListAdapter(Context context, int layoutId, List<TouristSpotData> list) {
        super();
        this.context =context;
        this.layoutId = layoutId;
        this.list = list;
    }

    private static class ViewHolder {
        TextView spotName;
        TextView spotCount;
        ImageView spotImage;
        ImageView cameraImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        TouristSpotData item = (TouristSpotData)getItem(position);

        if (null == convertView) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId, null);

            final ViewHolder holder = new ViewHolder();

            holder.spotName = (TextView)view.findViewById(R.id.tourist_spot_list_item_spot_name);
            holder.spotCount = (TextView)view.findViewById(R.id.tourist_spot_list_item_spot_count);
            holder.spotImage = (ImageView)view.findViewById(R.id.tourist_spot_list_item_spot_image);
            holder.cameraImage = (ImageView)view.findViewById(R.id.tourist_spot_list_item_spot_camera);

            holder.spotImage.setTag(list.get(position));
            holder.cameraImage.setTag(list.get(position));

            view.setTag(holder);
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).spotImage.setTag(list.get(position));
            ((ViewHolder) view.getTag()).cameraImage.setTag(list.get(position));
        }

        ViewHolder viewholder = (ViewHolder) view.getTag();

        // 観光地名
        viewholder.spotName.setText(item.getSpotName());
        // 観光地数
        viewholder.spotCount.setText(item.getSpotCount());
        // 観光地イメージ画像
        viewholder.spotImage.setImageResource(item.getSpotImage());

        // 撮影ポイントであれば、カメラアイコンを表示する
        if (item.isCameraImageFlag()) {
            viewholder.cameraImage.setImageResource(R.drawable.camera);
            viewholder.cameraImage.setVisibility(ImageView.VISIBLE);
        } else {
            viewholder.cameraImage.setVisibility(ImageView.GONE);
        }

        return view;
    }
}
