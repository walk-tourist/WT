package wt.walk_tourist.wt_fragment;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivity内で使用するパーツを管理するクラス
 */
public abstract class WT_PartsDisplayFragment extends WT_Fragment {

    public WT_PartsDisplayFragment() {}

    public abstract void releaseParts();

    public int getsize(){return 0;};
}