package wt.walk_tourist.PDF.WalkAnimation_Texture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import wt.walk_tourist.R;
import wt.walk_tourist.animation.A_BackGround;
import wt.walk_tourist.animation.A_Character;
import wt.walk_tourist.define.Define;

/**
 * Created by taguchi on 2015/06/08.
 * 背景が流れるViewとそれに乗っかるキャラクター
 */
public class WalkAnimation_TextureView extends TextureView implements TextureView.SurfaceTextureListener, Runnable{

    private A_Character mCharacter;
    private A_BackGround mSky;
    private A_BackGround mBuilding;

    private Thread mLoop;
    private boolean mRunning = false;
    private int mViewWidth;
    private int mViewHeight;

    private long mTime;

    private int mAnimationCount;

    private Paint mPaint;


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

        mSky = new A_BackGround(getResources(), R.drawable.haikei2, mViewWidth, mViewHeight, mViewWidth*2, (int)(mViewHeight*0.75f));
        mBuilding = new A_BackGround(getResources(), R.drawable.machi, mViewWidth, mViewHeight, mViewWidth*2, (int)(mViewHeight*0.8f));

        mDirection = Define.DIRECTION_DEF.RIGHT;
        mCharacter = new A_Character(getResources().openRawResource(R.raw.c01_32),32,(int)(mViewWidth*0.2f),(int)(mViewHeight*0.15f),mViewWidth/2,(int)(mViewHeight*0.7f),3,mDirection);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);

        mSky.setPosX(0);
        mSky.setPosY(0);

        mBuilding.setPosX(0);
        mBuilding.setPosY((int)(mViewHeight*0.05f));

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
                mCharacter.updatePosY(mAnimationCount);
                break;

            case LEFT:
                mSky.addPosX(mAnimationCount);
                mBuilding.addPosX(mAnimationCount*3);
                mCharacter.updatePosX(-mAnimationCount);
                break;

            case RIGHT:
                mSky.addPosX(-mAnimationCount);
                mBuilding.addPosX(-mAnimationCount*3);
                mCharacter.updatePosX(mAnimationCount);
                break;

            case UP:
                mCharacter.updatePosY(-mAnimationCount);
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
        mCharacter.BitmapRelease();

        mSky.BitmapRelease();
        mBuilding.BitmapRelease();

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


    private void drawBackGround( Canvas canvas, A_BackGround backGround )
    {
        // 画像を左に流し切った場合
        if ( 0 > ( backGround.getWidth() + backGround.getPosX() ) )
        {
            // 初期位置に戻す
            backGround.setPosX( backGround.getWidth() + backGround.getPosX() );
        }
        // 画像を右に流しきった場合
        else if ( mViewWidth < backGround.getPosX() )
        {
            // 初期位置に戻す
            backGround.setPosX( -( backGround.getWidth() - mViewWidth ) + ( backGround.getPosX() - mViewWidth ));
        }
        // 1枚目の画像描画
        canvas.drawBitmap(backGround.getBitmap(), backGround.getPosX(), backGround.getPosY(), null);

        // 1枚目の画の右が見切れている場合
        if ( ( backGround.getWidth() + backGround.getPosX() ) < mViewWidth )
        {
            // 2枚目の画像描画
            canvas.drawBitmap(backGround.getBitmap(), backGround.getWidth() + backGround.getPosX(), backGround.getPosY(), null);
        }
        // 1枚目の画の左が見切れている場合
        else if ( backGround.getPosX() > 0 )
        {
            // 2枚目の画像描画
            canvas.drawBitmap(backGround.getBitmap(), - backGround.getWidth() + backGround.getPosX(), backGround.getPosY(), null);
        }
    }

    @Override
    public void run() {
        while( mRunning ){

            long delta = System.currentTimeMillis() - mTime;
            mTime = System.currentTimeMillis();
            String fps;

            if(delta < Define.FPS60)
            {
                fps = "60Fps";
                mAnimationCount = 1;
                try{
                    Thread.sleep( Define.FPS60 - delta );
                }catch (InterruptedException e){
                    Log.e("run","InterruptedException!!");
                }

            }
            else
            {
                fps = 1000/delta +"Fps";
                mAnimationCount = (int)(delta / Define.FPS60);
                mTime = mTime - ( delta % Define.FPS60 );
            }

            //Canvasの取得(マルチスレッド環境対応のためLock)
            Canvas canvas = lockCanvas();

            if (null != canvas) {
                canvas.drawColor(0xFFFFFFFF);

                drawBackGround(canvas, mSky);
                drawBackGround(canvas, mBuilding);

                // キャラクター描画
                canvas.drawBitmap(mCharacter.getAnimationBitmap(),mCharacter.getXPos(),mCharacter.getYPos(),null);

                canvas.drawText(fps,100,200,mPaint);

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

    public void setViewSize(int width, int height)
    {
        mViewWidth = width;
        mViewHeight = height;
    }
}