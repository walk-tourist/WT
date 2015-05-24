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


public class MDF_TouristSpot_ForPref extends MDF_TouristSpot {

    // 選択した観光地（県）
    private int selectedSpotNo;

    @Override
    public void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.tourist_spot_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.tourist_spot_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンの名称をセットする
        ((TextView)v.findViewById(R.id.tourist_spot_back_button)).setText(getResources().getString(R.string.tourist_spot_back_button_home));

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.tourist_spot_back_button).setOnClickListener(this);

    }

    @Override
    public void showList(View v) {
        // 観光地Listのデータを作成
        List<D_TouristSpot> touristSpotDatas = new ArrayList<D_TouristSpot>();

        touristSpotDatas.add(new D_TouristSpot("北海道２", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("青森県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("岩手県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("宮城県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("秋田県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("山形県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("福島県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("茨城県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("栃木県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("群馬県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("埼玉県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("千葉県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("東京都", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("神奈川県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("新潟県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("富山県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("石川県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("福井県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("山梨県", "50/100",R.drawable.building,  false));;
        touristSpotDatas.add(new D_TouristSpot("長野県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("岐阜県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("静岡県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("愛知県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("三重県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("滋賀県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("京都府", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("大阪府", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("兵庫県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("奈良県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("和歌山県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("鳥取県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("島根県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("岡山県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("広島県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("山口県","50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("徳島県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("香川県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("愛媛県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("高知県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("福岡県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("佐賀県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("長崎県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("熊本県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("大分県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("宮崎県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("鹿児島県", "50/100",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("沖縄県", "50/100",R.drawable.building,  false));

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

            MDF_TouristSpot_ForBor touristSpotForBorFragment = new MDF_TouristSpot_ForBor();

            fragmentTransaction.add(R.id.main_fragment, touristSpotForBorFragment, "touristSpotForBorFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }
}

