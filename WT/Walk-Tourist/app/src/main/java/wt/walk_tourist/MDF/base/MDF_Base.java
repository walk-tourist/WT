package wt.walk_tourist.MDF.base;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.commonfragmentlib.MainDisplayFragment;
import com.example.user.commonfragmentlib.PartsDisplayFragment;

import wt.walk_tourist.R;
import wt.walk_tourist.define.Define;
import wt.walk_tourist.PDF.WalkAnimation_Texture.PDF_WalkAnimation_Texture;

public class MDF_Base extends MainDisplayFragment implements View.OnClickListener {

    // SoundPool用ID
    private int mSoundId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.base_fragment, container, false);

        setViewItems(v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mSoundId = mSoundPool.load(getActivity().getBaseContext(),R.raw.system49,1);

        PartsDisplayFragment walkAnimation_Texture = new PDF_WalkAnimation_Texture();
        Bundle args = new Bundle();
        args.putInt(PartsDisplayFragment.BUNDLE_KEY.BUNDLE_KEY_WIDTH.getKey(), m_LayoutWidth);
        args.putInt(PartsDisplayFragment.BUNDLE_KEY.BUNDLE_KEY_HEIGHT.getKey(), m_LayoutHeight);

        walkAnimation_Texture.setArguments(args);
        addPartsDisplayFragment(R.id.base_texture_fragment, walkAnimation_Texture, "base_texture_fragment");

    }

    private void setViewItems(View v) {

        v.findViewById(R.id.button_tourist_spot).setOnClickListener(this);
        v.findViewById(R.id.button_start_game).setOnClickListener(this);
        v.findViewById(R.id.button_point_management).setOnClickListener(this);
        v.findViewById(R.id.button_help).setOnClickListener(this);
        v.findViewById(R.id.button_display_map).setOnClickListener(this);

        v.findViewById(R.id.button_left).setOnClickListener(this);
        v.findViewById(R.id.button_right).setOnClickListener(this);
        v.findViewById(R.id.button_top).setOnClickListener(this);
        v.findViewById(R.id.button_bottom).setOnClickListener(this);

    }

    private void changeDirection(Define.DIRECTION_DEF Direction)
    {
        PDF_WalkAnimation_Texture fragment = (PDF_WalkAnimation_Texture)getPartsDisplayFragment("base_texture_fragment");
        if( null != fragment ) {
            fragment.changeDirection(Direction);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_tourist_spot:
                mSoundPool.defPlay(mSoundId);
                mListener.changeMDF(MDF_NAME.MDF_SPOT_FOR_PREF, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;
            case R.id.button_start_game:
                mSoundPool.defPlay(mSoundId);
                mListener.changeMDF(MDF_NAME.MDF_GAME, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;
            case R.id.button_point_management:
                mSoundPool.defPlay(mSoundId);
                mListener.changeMDF(MDF_NAME.MDF_POINT, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;
            case R.id.button_help:
                mSoundPool.defPlay(mSoundId);
                mListener.changeMDF(MDF_NAME.MDF_HELP, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;
            case R.id.button_display_map:
                mSoundPool.defPlay(mSoundId);
                mListener.changeMDF(MDF_NAME.MDF_MAP, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;

            case R.id.button_bottom:
                mSoundPool.defPlay(mSoundId);
                changeDirection(Define.DIRECTION_DEF.DOWN);
                break;
            case R.id.button_left:
                mSoundPool.defPlay(mSoundId);
                changeDirection(Define.DIRECTION_DEF.LEFT);
                break;
            case R.id.button_right:
                mSoundPool.defPlay(mSoundId);
                changeDirection(Define.DIRECTION_DEF.RIGHT);
                break;
            case R.id.button_top:
                mSoundPool.defPlay(mSoundId);
                changeDirection(Define.DIRECTION_DEF.UP);
                break;
        }
    }

    public int getBGMResId()
    {
        return R.raw.bgm01;
    }
}