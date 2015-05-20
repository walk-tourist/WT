package wt.walk_tourist.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wt.walk_tourist.R;

/**
 * Created by User on 2015/05/09.
 */
public class TouristSpotListAdapter extends ArrayAdapter<TouristSpotData> {
    private LayoutInflater layoutInflater;

    public TouristSpotListAdapter(Context context, int textViewResourceId, List<TouristSpotData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TouristSpotData item = (TouristSpotData)getItem(position);

        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.settings_tourist_spot_list_item, null);
        }

        // 観光地名
        ((TextView)convertView.findViewById(R.id.tourist_spot_list_item_spot_name)).setText(item.getSpotName());
        // 観光地数
        ((TextView)convertView.findViewById(R.id.tourist_spot_list_item_spot_count)).setText(item.getSpotCount());

        return convertView;
    }


}
