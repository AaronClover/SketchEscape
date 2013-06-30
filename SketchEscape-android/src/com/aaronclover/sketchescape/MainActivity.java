package com.aaronclover.sketchescape;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.aaronclover.sketchescape.SketchEscape;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
import com.google.analytics.tracking.android.EasyTracker;
import com.aaronclover.sketchescape.IActivityRequestHandler;

public class MainActivity extends AndroidApplication implements
		IActivityRequestHandler {
	protected static final int SHOW_ADS = 1;
	protected static final int HIDE_ADS = 0;
	protected static final int LOAD_ADS = 2;
	// protected AdView adview;
	private InterstitialAd intAd;

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_ADS: {
				// adview.setVisibility(View.VISIBLE);
				intAd.show();
				break;
			}
			case HIDE_ADS: {
				// adview.setVisibility(View.GONE);
				break;
			}
			case LOAD_ADS: {
				intAd.loadAd(new AdRequest());
				break;
			}
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		RelativeLayout layout = new RelativeLayout(this);
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		// if (android.os.Build.VERSION.SDK_INT >= 14) {
		// getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		// }
		EasyTracker.getInstance().setContext(this);
		cfg.hideStatusBar = true;
		cfg.useWakelock = true;
		cfg.useGL20 = true;
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// SketchEscape se = new SketchEscape();
		// adview = new AdView(this, AdSize.IAB_BANNER, "a151bbb5785fa88");
		intAd = new InterstitialAd(this, "a151bbb5785fa88");
		View gameView = initializeForView(new SketchEscape(this), cfg);
		// initialize(se, cfg);

		// adview.loadAd(new AdRequest());
		intAd.loadAd(new AdRequest());

		// RelativeLayout.LayoutParams adParams =
		// new
		// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		// adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		layout.addView(gameView);
		// layout.addView(adview, adParams);

		setContentView(layout);
		IntAd intAd = new IntAd();

	}

	// Analytics
	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}

	// This is the callback that posts a message for the handler
	@Override
	public void controlAds(int control) {
		switch (control) {
		case 0: {
			handler.sendEmptyMessage(HIDE_ADS);
			break;
		}
		case 1: {
			handler.sendEmptyMessage(SHOW_ADS);
			break;
		}
		case 2: {
			handler.sendEmptyMessage(LOAD_ADS);
		}
		}
	}

}
