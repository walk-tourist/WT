package wt.walk_tourist.MDF.tourist_spot;

import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;


public class MDF_TouristSpot_ForSpot extends MDF_TouristSpot {

    // 選択した観光地（場所）
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
        ((TextView)v.findViewById(R.id.tourist_spot_area)).setText("京都府京都市");
    }

    @Override
    public void showList(View v) {
        // 観光地Listのデータを作成
        List<D_TouristSpot> touristSpotDatas = new ArrayList<D_TouristSpot>();

        // TODO カメラ画像が差し込めるようにリストの項目を修正しよう！！
        touristSpotDatas.add(new D_TouristSpot("金閣寺", "　",R.drawable.jiin,  false));
        touristSpotDatas.add(new D_TouristSpot("比叡山", "　",R.drawable.mountain,  false));
        touristSpotDatas.add(new D_TouristSpot("銀閣寺", "　",R.drawable.jiin,  false));
        touristSpotDatas.add(new D_TouristSpot("祇園四条", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("東寺", "　",R.drawable.jiin,  true));
        touristSpotDatas.add(new D_TouristSpot("円山公園", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("八坂神社", "　",R.drawable.jinja,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
        touristSpotDatas.add(new D_TouristSpot("先斗町", "　",R.drawable.building,  false));
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
            // この画面を閉じる
            mListener.changeMDF(MDF_NAME.MDF_SPOT_FOR_BOR, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        } else if (view.getId() == R.id.listView1) {
            // TODO タッチした観光地の詳細へ
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか

        }
    }

}
