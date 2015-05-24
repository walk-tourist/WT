package wt.walk_tourist.tourist_spot;

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
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;


public class MDF_TouristSpot_ForBor extends MDF_TouristSpot {

    // 選択した観光地（市町村）
    private int selectedSpotNo;

    @Override
    public void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.tourist_spot_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.tourist_spot_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンの名称をセットする
        ((TextView)v.findViewById(R.id.tourist_spot_back_button)).setText(getResources().getString(R.string.tourist_spot_back_button_before));

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.tourist_spot_back_button).setOnClickListener(this);

        // 観光地エリアをセットする
        ((TextView)v.findViewById(R.id.tourist_spot_area)).setText("京都府");
    }

    @Override
    public void showList(View v) {
        // 観光地Listのデータを作成
        List<D_TouristSpot> touristSpotDatas = new ArrayList<D_TouristSpot>();

        touristSpotDatas.add(new D_TouristSpot("京都市","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("福知山市","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("永谷園市","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("尼崎市","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("福寿円","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("八坂","50/101",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("五右衛門","50/101",R.drawable.building,  false));

        LA_TouristSpot touristSpotListAdapter = new LA_TouristSpot(getActivity(), R.layout.tourist_spot_list_item, touristSpotDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)v.findViewById(R.id.listView1);
        prefectureListView.setAdapter(touristSpotListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView)parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((D_TouristSpot)listView.getItemAtPosition(position)).getSpotId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((D_TouristSpot)listView.getItemAtPosition(position)).getSpotName(), Toast.LENGTH_SHORT).show();

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
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか
            SettingsUtilty.outputOperationLog("観光地を選択しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            MDF_TouristSpot_ForSpot touristSpotForSpotFragment = new MDF_TouristSpot_ForSpot();

            fragmentTransaction.add(R.id.main_fragment, touristSpotForSpotFragment, "touristSpotForSpotFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }

}
