package wt.walk_tourist.game;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;

public class GameContentsFragment extends Fragment implements View.OnClickListener {

    // 選択したゲームコンテンツ
    private int selectedSpotNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.game_contents_fragment, container, false);

        // 画面表示項目をセット
        setViewItems(v);

        // リスト表示処理
        showList(v);

        return v;
    }

    private void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.game_contents_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.game_contents_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンの名称をセットする
        ((TextView)v.findViewById(R.id.game_contents_back_button)).setText(getResources().getString(R.string.game_contents_back_button_home));

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.game_contents_back_button).setOnClickListener(this);

    }

    private void showList(View v) {
        // 観光地Listのデータを作成
        List<GameContentsData> gameContentsDatas = new ArrayList<GameContentsData>();

        gameContentsDatas.add(new GameContentsData("黒ひげ危機一髪"));
        gameContentsDatas.add(new GameContentsData("大人のUNO"));
        gameContentsDatas.add(new GameContentsData("実録 オセロ"));
        gameContentsDatas.add(new GameContentsData("拝啓：僕は元気です。"));
        gameContentsDatas.add(new GameContentsData("間違いさがし"));

        GameContentsListAdapter gameContentsListAdapter = new GameContentsListAdapter(getActivity(), 0, gameContentsDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)v.findViewById(R.id.listView1);
        prefectureListView.setAdapter(gameContentsListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView)parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((GameContentsData)listView.getItemAtPosition(position)).getContentsId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((GameContentsData) listView.getItemAtPosition(position)).getContentsName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.game_contents_back_button) {
            // TODO この画面を閉じる
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

        } else if (view.getId() == R.id.listView1) {
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか
            // TODO コンテンツ画面へ遷移するようにする
        }
    }
}
