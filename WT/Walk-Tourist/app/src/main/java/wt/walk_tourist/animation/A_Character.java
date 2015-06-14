package wt.walk_tourist.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import wt.walk_tourist.R;

/**
 * Created by user on 2015/06/14.
 */
public class A_Character {

    private InputStream mInputStream;
    private int mCharacterSize;
    private int mChangeCount;

    private int mAnimationCount;
    private int mDirection;

    // 左歩き
    Bitmap lw_1;
    Bitmap lw_2;
    Bitmap lw_3;

    // 右歩き
    Bitmap rw_1;
    Bitmap rw_2;
    Bitmap rw_3;

    // 上歩き
    Bitmap tw_1;
    Bitmap tw_2;
    Bitmap tw_3;

    // 下歩き
    Bitmap bw_1;
    Bitmap bw_2;
    Bitmap bw_3;

    public A_Character(InputStream inputStream)
    {
        mInputStream = inputStream;
        mCharacterSize = 32; // TODO 今は決めうちの32 後々はファイル名から取得したい
        mChangeCount = 20; // TODO 20回描画で次に切り替わり

        mAnimationCount = 0;
        mDirection = 2; // 0:下 1:左  2:右  3:上 TODO 後々は定義化

        createImage();
    }

    private void createBitmap(BitmapRegionDecoder decoder )
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false; // 実際に画像を読み込む(false)
        Rect rect = new Rect(0,0,mCharacterSize,mCharacterSize);
        bw_1 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize,0,mCharacterSize*2,mCharacterSize);
        bw_2 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize*2,0,mCharacterSize*3,mCharacterSize);
        bw_3 = decoder.decodeRegion(rect, options);

        rect.set(0,mCharacterSize,mCharacterSize,mCharacterSize*2);
        lw_1 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize,mCharacterSize,mCharacterSize*2,mCharacterSize*2);
        lw_2 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize*2,mCharacterSize,mCharacterSize*3,mCharacterSize*2);
        lw_3 = decoder.decodeRegion(rect, options);

        rect.set(0, mCharacterSize*2, mCharacterSize, mCharacterSize*3);
        rw_1 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize,mCharacterSize*2,mCharacterSize*2,mCharacterSize*3);
        rw_2 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize*2,mCharacterSize*2,mCharacterSize*3,mCharacterSize*3);
        rw_3 = decoder.decodeRegion(rect, options);

        rect.set(0, mCharacterSize*3, mCharacterSize, mCharacterSize*4);
        tw_1 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize,mCharacterSize*3,mCharacterSize*2,mCharacterSize*4);
        tw_2 = decoder.decodeRegion(rect, options);

        rect.set(mCharacterSize*2,mCharacterSize*3,mCharacterSize*3,mCharacterSize*4);
        tw_3 = decoder.decodeRegion(rect, options);

    }


    private void createImage()
    {
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(mInputStream, true);

            createBitmap(regionDecoder);

            //R.drawable.c01_32
        }catch (IOException e)
        {

        }finally{
            if(mInputStream != null)
            {
                try{
                    mInputStream.close();
                }
                catch(IOException e)
                {

                }
            }
        }


    }

    public void changeDirection(int direction)
    {
        mDirection = direction;
    }

    private int getPattern()
    {
        mAnimationCount++;

        if( mChangeCount*4 == mAnimationCount)
        {
            mAnimationCount = 0;
        }

        if( mAnimationCount < mChangeCount )
        {
            return 0;
        }
        else if ( mAnimationCount < mChangeCount*2 )
        {
            return 1;
        }
        else if ( mAnimationCount < mChangeCount*3 )
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    public Bitmap getAnimationBitmap()
    {
        Bitmap rBitmap = null;
        int pattern = getPattern();

        switch ( mDirection )
        {
            case 0:
                if( 0 == pattern ) {
                    rBitmap = bw_1;
                }else if( 1 == pattern ) {
                    rBitmap = bw_2;
                }else{
                    rBitmap = bw_3;
                }
                break;
            case 1:
                if( 0 == pattern ) {
                    rBitmap = lw_1;
                }else if( 1 == pattern ) {
                    rBitmap = lw_2;
                }else{
                    rBitmap = lw_3;
                }
                break;
            case 2:
                if( 0 == pattern ) {
                    rBitmap = rw_1;
                }else if( 1 == pattern ) {
                    rBitmap = rw_2;
                }else{
                    rBitmap = rw_3;
                }
                break;
            case 3:
                if( 0 == pattern ) {
                    rBitmap = tw_1;
                }else if( 1 == pattern ) {
                    rBitmap = tw_2;
                }else{
                    rBitmap = tw_3;
                }
                break;
        }
        if(rBitmap == null )
        {
            Log.e("","");

        }

        return rBitmap;
    }

}
