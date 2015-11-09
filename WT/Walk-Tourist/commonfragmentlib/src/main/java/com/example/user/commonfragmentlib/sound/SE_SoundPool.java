package com.example.user.commonfragmentlib.sound;

import android.media.SoundPool;

/**
 * Created by taguchi on 2015/06/16.
 * 効果音再生用
 */
public class SE_SoundPool extends SoundPool {

    public SE_SoundPool(int maxStreams, int streamType, int srcQuality) {
        super(maxStreams, streamType, srcQuality);
    }

    public void defPlay(int soundID)
    {
        super.play(soundID,1.0f,1.0f,0,0,1.0f);
    }

}