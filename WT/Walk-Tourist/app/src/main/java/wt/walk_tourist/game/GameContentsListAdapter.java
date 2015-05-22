package wt.walk_tourist.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wt.walk_tourist.R;

/**
 * Created by User on 2015/05/09.
 */
public class GameContentsListAdapter extends BaseAdapter {
    private final Context context;
    private final int layoutId;
    private final List<GameContentsData> list;

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
    public GameContentsListAdapter(Context context, int layoutId, List<GameContentsData> list) {
        super();
        this.context =context;
        this.layoutId = layoutId;
        this.list = list;
    }

    private static class ViewHolder {
        TextView contentsName;
        ImageView contentsImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        GameContentsData item = (GameContentsData)getItem(position);

        if (null == convertView) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId, null);

            final ViewHolder holder = new ViewHolder();

            holder.contentsName = (TextView)view.findViewById(R.id.game_contents_list_item_game_name);
            holder.contentsImage = (ImageView)view.findViewById(R.id.game_contents_list_item_game_image);

            holder.contentsImage.setTag(list.get(position));

            view.setTag(holder);
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).contentsImage.setTag(list.get(position));
        }

        ViewHolder viewholder = (ViewHolder) view.getTag();

        // ゲームコンテンツ名
        viewholder.contentsName.setText(item.getContentsName());
        // ゲームイメージ画像
        viewholder.contentsImage.setImageResource(item.getContentsImage());

        return view;
    }
}
