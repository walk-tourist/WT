package wt.walk_tourist.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import wt.walk_tourist.define.Define;

/**
 * Created by user on 2015/06/14.
 * キャラクタークラス
 */
public class A_Character {

    private InputStream mInputStream;
    private int mCatSize;
    private int mCharacterSizeX;
    private int mCharacterSizeY;

    private int mChangeCount;
    private int mXPos;
    private int mYPos;
    private int mPattern;

    private int mAnimationCount;
    private Define.DIRECTION_DEF mDirection;

    class BitmapPattern
    {
        Bitmap bitmap;
        int pattern;
    }
    // 左歩き
    ArrayList<BitmapPattern> lw = new ArrayList<BitmapPattern>();

    // 右歩き
    ArrayList<BitmapPattern> rw = new ArrayList<BitmapPattern>();

    // 上歩き
    ArrayList<BitmapPattern> tw = new ArrayList<BitmapPattern>();

    // 下歩き
    ArrayList<BitmapPattern> bw = new ArrayList<BitmapPattern>();

    public A_Character(InputStream inputStream, int size, int cXSize, int cYSize, int xPos, int yPos, int pattern, Define.DIRECTION_DEF direction)
    {
        mInputStream = inputStream;
        mCatSize = size;
        mCharacterSizeX = cXSize;
        mCharacterSizeY = cYSize;

        // 縦横同じにする場合
        //  mCharacterSizeX = mCharacterSizeY;

        mXPos = xPos - (cXSize / 2);
        mYPos = yPos - (cYSize / 2);
        mPattern = pattern;

        mAnimationCount = 0;
        mChangeCount = 20;
        mDirection = direction;

        createImage();
    }

    public static Bitmap getCreateScaledBitmap( Bitmap bitmap, int width, int height )
    {
        return Bitmap.createScaledBitmap( bitmap, width, height, true );
    }

    private void createBitmap(BitmapRegionDecoder decoder )
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false; // 実際に画像を読み込む(false)
        Rect rect = new Rect(0,0,0,0);

        for( int i = 0 ; i < mPattern; i++ )
        {
            rect.set( i * mCatSize, 0, mCatSize * ( 1 + i ), mCatSize);
            BitmapPattern bitmapPattern = new BitmapPattern();
            bitmapPattern.bitmap = getCreateScaledBitmap( decoder.decodeRegion(rect, options), mCharacterSizeX, mCharacterSizeY);
            bitmapPattern.pattern = i;
            bw.add(bitmapPattern);
        }

        for( int i = 0 ; i < mPattern; i++ )
        {
            rect.set( i * mCatSize, mCatSize, mCatSize * ( 1 + i ), mCatSize*2);
            BitmapPattern bitmapPattern = new BitmapPattern();
            bitmapPattern.bitmap = getCreateScaledBitmap(decoder.decodeRegion(rect, options), mCharacterSizeX, mCharacterSizeY);
            bitmapPattern.pattern = i;
            lw.add(bitmapPattern);
        }

        for( int i = 0 ; i < mPattern; i++ )
        {
            rect.set( i * mCatSize, mCatSize*2, mCatSize * ( 1 + i ), mCatSize*3);
            BitmapPattern bitmapPattern = new BitmapPattern();
            bitmapPattern.bitmap = getCreateScaledBitmap(decoder.decodeRegion(rect, options), mCharacterSizeX, mCharacterSizeY);
            bitmapPattern.pattern = i;
            rw.add(bitmapPattern);
        }

        for( int i = 0 ; i < mPattern; i++ )
        {
            rect.set( i * mCatSize, mCatSize*3, mCatSize * ( 1 + i ), mCatSize*4);
            BitmapPattern bitmapPattern = new BitmapPattern();
            bitmapPattern.bitmap = getCreateScaledBitmap(decoder.decodeRegion(rect, options), mCharacterSizeX, mCharacterSizeY);
            bitmapPattern.pattern = i;
            tw.add(bitmapPattern);
        }

    }

    public void BitmapRelease()
    {
        if (null != bw) {
            for (int i = 0; i < bw.size(); i++) {
                BitmapPattern bp = bw.get(i);
                bp.bitmap.recycle();
            }
        }
        if (null != lw) {
            for (int i = 0; i < lw.size(); i++) {
                BitmapPattern bp = lw.get(i);
                bp.bitmap.recycle();
            }
        }
        if (null != rw) {
            for (int i = 0; i < rw.size(); i++) {
                BitmapPattern bp = rw.get(i);
                bp.bitmap.recycle();
            }
        }
        if (null != tw) {
            for (int i = 0; i < tw.size(); i++) {
                BitmapPattern bp = tw.get(i);
                bp.bitmap.recycle();
            }
        }
    }

    private void createImage()
    {
        try {
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(mInputStream, true);

            createBitmap(regionDecoder);

        }catch (IOException e)
        {
            Log.e("","");
        }finally{
            if(mInputStream != null)
            {
                try{
                    mInputStream.close();
                }
                catch(IOException e)
                {
                    Log.e("","");
                }
            }
        }
    }

    public void changeDirection( Define.DIRECTION_DEF direction)
    {
        mDirection = direction;
    }

    private int getPattern()
    {
        int r;
        mAnimationCount++;
        if( mChangeCount*(mPattern+(-2+mPattern)) <= mAnimationCount)
        {
            mAnimationCount = 0;
        }

        if ( ( mAnimationCount / mChangeCount ) < mPattern )
        {
            return ( mAnimationCount / mChangeCount ) % mPattern;
        }
        else
        {
            return mPattern - ( 2 + ( ( mAnimationCount / mChangeCount ) % mPattern ));
        }
    }

    public Bitmap getAnimationBitmap() {
        ArrayList<BitmapPattern> bpl = null;
        int pattern = getPattern();

        switch (mDirection) {
            case DOWN:
                bpl = bw;
                break;
            case LEFT:
                bpl = lw;
                break;
            case RIGHT:
                bpl = rw;
                break;
            case UP:
                bpl = tw;
                break;
        }
        if (null != bpl) {
            for (int i = 0; i < bpl.size(); i++) {
                BitmapPattern bp = bpl.get(i);
                if (bp.pattern == pattern) {
                    return bp.bitmap;
                }
            }
        }
        return null;
    }

    public void updateXPos(int x)
    {
        mXPos = mXPos + x;
    }

    public void updateYPos(int y)
    {
        mYPos = mYPos + y;
    }

    public int getXPos()
    {
        return mXPos;
    }

    public int getYPos()
    {
        return mYPos;
    }

}