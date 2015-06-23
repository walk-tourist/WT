package wt.walk_tourist.animation;

import android.content.res.Resources;
import android.graphics.Bitmap;

/**
 *
 * Created by taguchi on 2015/06/23.
 */
public class A_BackGround extends CreateBitmap{


    Bitmap mBackBitmap;
    int mPosX = 0;
    int mPosY = 0;

    public A_BackGround(Resources res, int id, int viewWidth, int viewHeight, int reqWidth, int reqHeight)
    {
        mBackBitmap = decodeSampledBitmapFromResource(res, id, viewWidth, viewHeight, reqWidth,reqHeight );
    }

    public Bitmap getBitmap()
    {
        return mBackBitmap;
    }

    public int getWidth()
    {
        if(null != mBackBitmap) {
            return mBackBitmap.getWidth();
        }
        return 0;
    }

    public int getHeight()
    {
        if(null != mBackBitmap) {
            return mBackBitmap.getHeight();
        }
        return 0;
    }

    public void setPosX(int posX)
    {
        mPosX = posX;
    }

    public void setPosY(int posY)
    {
        mPosY = posY;
    }

    public void addPosX(int posX)
    {
        mPosX = mPosX+posX;
    }

    public void addPosY(int posY)
    {
        mPosY = mPosY+posY;
    }

    public int getPosX()
    {
        return mPosX;
    }

    public int getPosY()
    {
        return mPosY;
    }

    public void BitmapRelease()
    {
        mBackBitmap.recycle();
    }

}