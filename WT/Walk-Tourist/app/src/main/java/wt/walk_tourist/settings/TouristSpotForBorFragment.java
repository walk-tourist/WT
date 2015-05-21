package wt.walk_tourist.settings;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;
import wt.walk_tourist.settings.utility.SettingsUtilty;


public class TouristSpotForBorFragment extends TouristSpotFragment {

    // 選択した観光地（県）
    private int selectedSpotNo;

    @Override
    public void showList(View v) {
        v.findViewById(R.id.settings_tourist_spot_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンの名称をセットする
        ((TextView)v.findViewById(R.id.tourist_spot_back_button)).setText(getResources().getString(R.string.tourist_spot_back_button_before));

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.tourist_spot_back_button).setOnClickListener(this);

        // 観光地エリアをセットする
        ((TextView)v.findViewById(R.id.tourist_spot_area)).setText("観光地エリア");

        // 観光地Listのデータを作成
        List<TouristSpotData> touristSpotDatas = new ArrayList<TouristSpotData>();

        touristSpotDatas.add(new TouristSpotData("京都府　京都市", "50/100"));
        touristSpotDatas.add(new TouristSpotData("京都府　東京都市","50/101"));

        TouristSpotListAdapter touristSpotListAdapter = new TouristSpotListAdapter(getActivity(), 0, touristSpotDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)v.findViewById(R.id.listView1);
        prefectureListView.setAdapter(touristSpotListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView)parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((TouristSpotData)listView.getItemAtPosition(position)).getSpotId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((TouristSpotData)listView.getItemAtPosition(position)).getSpotName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tourist_spot_back_button) {
            // TODO この画面を閉じる
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

        } else if (view.getId() == R.id.listView1) {
            // TODO タッチした観光地の詳細へ
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか


            // TODO 都道府県 →市町村ときたので次は、場所詳細のリストのフラグメントを呼ぶ　まだフラグメント自体作ってません。

            SettingsUtilty.outputOperationLog("観光地を選択しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TouristSpotForBorFragment touristSpotForBorFragment = new TouristSpotForBorFragment();

            fragmentTransaction.add(R.id.setting_tourist_spot_fragment, touristSpotForBorFragment, "観光地-市町村");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }

}
