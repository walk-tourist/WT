package wt.walk_tourist.animation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *
 * Created by taguchi on 2015/06/22.
 */
public class CreateBitmap {

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // 画像の元サイズ
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }

    public static Bitmap getCreateScaledBitmap( Bitmap bitmap, int width, int height )
    {
        return Bitmap.createScaledBitmap( bitmap, width, height, true );
    }

    public Bitmap decodeSampledBitmapFromFile(String filePath, int viewWidth, int viewHeight, int reqWidth, int reqHeight) {

        // inJustDecodeBounds=true で画像のサイズをチェック
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // inSampleSize を計算
        options.inSampleSize = calculateInSampleSize(options, viewWidth, viewHeight);

        // inSampleSize をセットしてデコード
        options.inJustDecodeBounds = false;

        Bitmap original = BitmapFactory.decodeFile(filePath, options);
        Bitmap reBitmap = Bitmap.createScaledBitmap(original, reqWidth, reqHeight, true);
        original.recycle();


        return reBitmap;
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int id, int viewWidth, int viewHeight, int reqWidth, int reqHeight) {

        // inJustDecodeBounds=true で画像のサイズをチェック
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);

        // inSampleSize を計算
        options.inSampleSize = calculateInSampleSize(options, viewWidth, viewHeight);

        // inSampleSize をセットしてデコード
        options.inJustDecodeBounds = false;

        Bitmap original = BitmapFactory.decodeResource(res, id, options);
        Bitmap reBitmap = Bitmap.createScaledBitmap(original, reqWidth, reqHeight, true );
        original.recycle();

        return reBitmap;
    }

}