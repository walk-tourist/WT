package wt.walk_tourist.parts_fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import wt.walk_tourist.R;
import wt.walk_tourist.animation.A_Character;
import wt.walk_tourist.define.Define;

/**
 * Created by taguchi on 2015/06/08.
 * 背景が流れるViewとそれに乗っかるキャラクター
 */
public class WalkAnimation_TextureView extends TextureView implements TextureView.SurfaceTextureListener, Runnable{

    private A_Character mCharacter;

    private Thread mLoop;
    private boolean mRunning = false;
    private Bitmap mHaikei;
    private int mDispWidth;
    private int mImageWidth;
    private int mX;

    private Define.DIRECTION_DEF mDirection;

    public WalkAnimation_TextureView(Context context)
    {
        super(context);
        setSurfaceTextureListener(this);
    }

    public WalkAnimation_TextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSurfaceTextureListener(this);
    }

    public WalkAnimation_TextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSurfaceTextureListener(this);
    }

    //サーフェイステクスチャ有効化時に呼ばれる
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface,int width,int height) {

        mHaikei = BitmapFactory.decodeResource(getResources(), R.drawable.haikei);

        mImageWidth = mHaikei.getWidth();

        mDispWidth = getWidth();

        mDirection = Define.DIRECTION_DEF.RIGHT;
        mCharacter = new A_Character(getResources().openRawResource(R.raw.c01_32),32,500,1000,3);

        mX = 0;
        //スレッドの開始
        mLoop = new Thread(this);
        mRunning = true;
        mLoop.start();
    }

    //サーフェイステクスチャサイズ変更時に呼ばれる
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface,int width,int height) {
    }

    //サーフェイステクスチャ更新時に呼ばれる
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        // TODO 描画内容更新処理
        switch(mDirection)
        {
            case DOWN:
                mCharacter.updateYPos(1);
                break;

            case LEFT:
                mX = mX +1;
                break;

            case RIGHT:
                mX = mX -1;
                break;

            case UP:
                mCharacter.updateYPos(-1);
                break;
        }
    }

    //サーフェイステクスチャ破棄時に呼ばれる
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

        setBitmapRelease();

        return true;
    }

    public void setBitmapRelease()
    {
        // TODO 加工したBITMAPのリサイクル処理

    }

    public void stopThread()
    {
        if( null != mLoop )
        {
            mRunning = false;
            try {
                mLoop.join(0);
            } catch (InterruptedException e) {
                Log.e("stopThread","join処理失敗!!");
            }

            mLoop = null;
        }
    }


    @Override
    public void run() {
        while( mRunning ){
            //Canvasの取得(マルチスレッド環境対応のためLock)
            Canvas canvas = lockCanvas();

            if (null != canvas) {
                canvas.drawColor(0xFF000000);

                // 画像を左に流し切った場合
                if ( 0 == ( mImageWidth + mX )  )
                {
                    // 初期位置に戻す
                    mX = 0;
                }
                // 画像を右に流しきった場合
                else if ( mImageWidth == mX )
                {
                    // 初期位置に戻す
                    mX = mImageWidth - mDispWidth;
                }
                // 1枚目の画像描画
                canvas.drawBitmap(mHaikei, mX, 0, null);

                // 1枚目の画の右が見切れている場合
                if ( ( mImageWidth + mX ) < mDispWidth )
                {
                    // 2枚目の画像描画
                    canvas.drawBitmap(mHaikei, mImageWidth + mX, 0, null);
                }
                // 1枚目の画の左が見切れている場合
                else if ( mX > 0 )
                {
                    // 2枚目の画像描画
                    canvas.drawBitmap(mHaikei, - mImageWidth + mX, 0, null);
                }
                // キャラクター描画
                canvas.drawBitmap(mCharacter.getAnimationBitmap(),mCharacter.getXPos(),mCharacter.getYPos(),null);

                //LockしたCanvasを解放、ほかの描画処理スレッドがあればそちらに。
                unlockCanvasAndPost(canvas);
            }
        }
    }

    void changeDirection(Define.DIRECTION_DEF direction)
    {
        mDirection = direction;
        mCharacter.changeDirection(direction);
    }
}