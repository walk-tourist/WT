package wt.walk_tourist.point_management;

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
import wt.walk_tourist.game.GameContentsData;
import wt.walk_tourist.game.GameContentsListAdapter;

public class PointManagementFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.point_management_fragment, container, false);

        // 画面表示項目をセット
        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.point_managemen_fragment_layout).setOnClickListener(this);

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.point_management_back_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.point_management_back_button) {
            // TODO この画面を閉じる
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

        }
    }
}
