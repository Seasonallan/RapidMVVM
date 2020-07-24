package com.season.mvp.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.season.lib.BaseContext;
import com.season.lib.support.bitmap.BitmapUtil;
import com.season.lib.ui.view.StartPageView;

public abstract class PageTurningActivity extends BaseTLEActivity {

    private StartPageView startPageView;

    /**
     * 是否默认全屏
     * @return
     */
    protected boolean isFullScreen(){
        return true;
    }

    /**
     * 是否允许翻页
     * @return
     */
    protected boolean enablePager(){
        return true;
    }

    /**
     * 加载耗时操作
     */
    protected void onPagerEnded(){
        isAnimationEnded = true;
    }

    protected boolean isAnimationEnded = false;

    private static Bitmap sCacheBitmap;
    public static void putCacheBitmap(Bitmap bitmap){
        sCacheBitmap = bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseContext.init(getApplicationContext());
        if (isFullScreen()){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        if (enablePager()){
            Bitmap bitmap = null;
            if (BitmapUtil.isBitmapAvaliable(sCacheBitmap)){
                bitmap = Bitmap.createBitmap(sCacheBitmap.getWidth(), sCacheBitmap.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawBitmap(sCacheBitmap, 0,0, null);
            }
            if (bitmap != null){
                RelativeLayout relativeLayout = new RelativeLayout(this);
                setContentView(relativeLayout);
                startPageView = new StartPageView(this){
                    @Override
                    public void onLoaded() {
                        super.onLoaded();
                        onPagerEnded();
                    }
                };
                startPageView.setBitmap(bitmap);
                startPageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                });
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                View contentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
                relativeLayout.addView(contentView, params);
                relativeLayout.addView(startPageView, params);
                overridePendingTransition(0, 0);
                startPageView.post(new Runnable() {
                    @Override
                    public void run() {
                        startPageView.start();
                    }
                });
                return;
            }
        }
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    protected void onPagerFinish(){
        if (startPageView != null){
            startPageView.finish();
            return;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (startPageView != null){
            startPageView.finish();
            return;
        }
        super.onBackPressed();
    }
}
