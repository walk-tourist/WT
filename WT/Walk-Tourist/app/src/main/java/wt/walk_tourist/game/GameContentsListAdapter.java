package wt.walk_tourist.game;

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
public class GameContentsListAdapter extends ArrayAdapter<GameContentsData> {
    private LayoutInflater layoutInflater;

    public GameContentsListAdapter(Context context, int textViewResourceId, List<GameContentsData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GameContentsData item = (GameContentsData)getItem(position);

        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.game_contents_list_item, null);
        }

        // ゲームコンテンツ名
        ((TextView)convertView.findViewById(R.id.game_contents_list_item_game_name)).setText(item.getContentsName());

        return convertView;
    }


}
