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

/**
 * Created by taguchi on 2015/06/08.
 * 背景が流れるView
 */
public class PDF_WalkAnimation_TextureView extends TextureView implements TextureView.SurfaceTextureListener, Runnable{

    private Thread mLoop;
    private boolean mRunning = false;
    private Bitmap mHaikei;
    private int mDispWidth;
    private int mImageWidth;
    private int mX;

    public PDF_WalkAnimation_TextureView(Context context)
    {
        super(context);
        setSurfaceTextureListener(this);
    }

    public PDF_WalkAnimation_TextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSurfaceTextureListener(this);
    }

    public PDF_WalkAnimation_TextureView(Context context, AttributeSet attrs,  int defStyle) {
        super(context, attrs, defStyle);
        setSurfaceTextureListener(this);
    }

    //サーフェイステクスチャ有効化時に呼ばれる
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface,int width,int height) {

        mHaikei = BitmapFactory.decodeResource(getResources(), R.drawable.haikei);

        mImageWidth = mHaikei.getWidth();

        mDispWidth = getWidth();

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
        mX = mX -1;

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

                // 画像を流し切った場合
                if ( 0 == ( mImageWidth + mX )  )
                {
                    // 初期位置に戻す
                    mX = 0;
                }

                // 1枚目の画像描画
                canvas.drawBitmap(mHaikei, mX, 0, null);

                // 1枚目の画像が見切れている場合
                if ( ( mImageWidth + mX ) < mDispWidth )
                {
                    // 2枚目の画像描画
                    canvas.drawBitmap(mHaikei, mImageWidth + mX, 0, null);
                }
                //LockしたCanvasを解放、ほかの描画処理スレッドがあればそちらに。
                unlockCanvasAndPost(canvas);
            }
        }
    }
}