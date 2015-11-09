package wt.walk_tourist.MDF.tourist_spot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.commonfragmentlib.MainDisplayFragment;

import wt.walk_tourist.R;


public class MDF_TouristSpot extends MainDisplayFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tourist_spot_fragment, container, false);

        // 画面表示項目をセット
        setViewItems(v);

        // リスト表示処理
        showList(v);

        return v;
    }

    /**
     * 画面表示項目をセット
     * @param v
     */
    public void setViewItems(View v) {
        // TODO 継承先で実装
    }

    /**
     * リスト表示処理
     * @param v
     */
    protected void showList(View v) {
        // TODO 継承先で実装
    }


    @Override
    public void onClick(View view) {
        // TODO 継承先で実装
    }

    public int getBGMResId()
    {
        return R.raw.bgm02;
    }
}