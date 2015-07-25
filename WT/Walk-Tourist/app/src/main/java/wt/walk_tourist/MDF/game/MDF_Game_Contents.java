package wt.walk_tourist.MDF.game;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MDF_Game_Contents extends WT_MainDisplayFragment implements View.OnClickListener {

    // 選択したゲームコンテンツ
    private int selectedSpotNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.game_contents_fragment, container, false);


        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.game_contents_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.game_contents_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.game_contents_back_button).setOnClickListener(this);

        showList(v);

    }

    private void showList(View v) {
        // 観光地Listのデータを作成
        List<D_Game_Contents> gameContentsDatas = new ArrayList<D_Game_Contents>();

        gameContentsDatas.add(new D_Game_Contents("黒ひげ危機一髪", R.drawable.doll));
        gameContentsDatas.add(new D_Game_Contents("大人のUNO", R.drawable.game_contents_background));
        gameContentsDatas.add(new D_Game_Contents("実録 オセロ", R.drawable.doll));
        gameContentsDatas.add(new D_Game_Contents("拝啓：僕は元気です。", R.drawable.doll));
        gameContentsDatas.add(new D_Game_Contents("間違いさがし", R.drawable.doll));

        LA_Game_Contents gameContentsListAdapter = new LA_Game_Contents(getActivity(), R.layout.game_contents_list_item, gameContentsDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)v.findViewById(R.id.listView1);
        prefectureListView.setAdapter(gameContentsListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView) parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((D_Game_Contents) listView.getItemAtPosition(position)).getContentsId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((D_Game_Contents) listView.getItemAtPosition(position)).getContentsName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.game_contents_back_button) {
            // TODO この画面を閉じる
            mListener.changeMDF(MDF_NAME.MDF_BASE,FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        } else if (view.getId() == R.id.listView1) {
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか
            // TODO コンテンツ画面へ遷移するようにする
        }
    }
}