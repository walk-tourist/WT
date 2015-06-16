package wt.walk_tourist.wt_fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.ArrayList;

import wt.walk_tourist.MainActivity;
import wt.walk_tourist.R;
import wt.walk_tourist.sound.SE_SoundPool;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivityで使用するFragmentを管理するクラス
 */
public abstract class WT_MainDisplayFragment extends WT_Fragment {

    // 子Fragment
    protected ArrayList<WT_PartsDisplayFragment> m_PartsDisplayFragmentList = new ArrayList<WT_PartsDisplayFragment>();

    protected SE_SoundPool mSoundPool;

    public WT_MainDisplayFragment() {

        if ( Build.VERSION.SDK_INT >= 21 ) {
            // SoundPoolはAPI21以降、インスタンスをBuilderから取得する。
            SoundPool.Builder mSoundPoolBuilder = new SoundPool.Builder();
            mSoundPoolBuilder.setMaxStreams(5);
            mSoundPoolBuilder.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build());
            mSoundPool = (SE_SoundPool)mSoundPoolBuilder.build();
        }else{
            // 過去API用
            mSoundPool = new SE_SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
    }

    public enum MDF_NAME{
        MDF_START,
        MDF_BASE,
        MDF_GAME,
        MDF_SPOT,
        MDF_MAP,
        MDF_HELP,
        MDF_POINT,
        MDF_DFF
    }

    public interface MainFragmentListener
    {
        void changeMDF(MDF_NAME name);
        void changeMDF(MDF_NAME name, int transaction);
    }

    protected MainFragmentListener mListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mListener = (MainFragmentListener)activity;
        MainActivity main = (MainActivity)activity;
        m_LayoutHeight = main.m_LayoutHeight;
        m_LayoutWidth = main.m_LayoutWidth;

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mSoundPool.release();
    }

    public void addPartsDisplayFragment(int containerViewId, WT_PartsDisplayFragment partsDisplayFragment, String tag )
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(containerViewId, partsDisplayFragment, tag);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        fragmentTransaction.commit();

        m_PartsDisplayFragmentList.add(partsDisplayFragment);
    }

    public WT_PartsDisplayFragment getPartsDisplayFragment(String tag)
    {
        for (int i = 0 ; i < m_PartsDisplayFragmentList.size() ; i++){
            WT_PartsDisplayFragment pdf = m_PartsDisplayFragmentList.get(i);
            if ( pdf.getTag() == tag )
            {
                return pdf;
            }
        }
        return null;
    }


    // 画面の向きを取得する
    public int getScreenOrientation()
    {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    // 画面での再生曲を取得する
    //public abstract int getBGMResId();
    public int getBGMResId()
    {
        return R.raw.bgm02;
    }

    // 本フラグメント(MDF)破棄時に破棄する必要がある子フラグメント(PDF)を破棄する
    public void removeChildFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // PDF_Remove処理
        for (int i = 0 ; i < m_PartsDisplayFragmentList.size() ; i++){
            WT_PartsDisplayFragment pdf = m_PartsDisplayFragmentList.get(i);
            pdf.releaseParts();
            fragmentTransaction.remove(pdf);
        }

        fragmentTransaction.commit();
    }

}