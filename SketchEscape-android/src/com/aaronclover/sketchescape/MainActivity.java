package com.aaronclover.sketchescape;

import android.os.Bundle;


import com.aaronclover.sketchescape.SketchEscape;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends AndroidApplication {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //if (android.os.Build.VERSION.SDK_INT >= 14) {
        //	getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //}
        EasyTracker.getInstance().setContext(this);
        cfg.hideStatusBar = true;
        cfg.useWakelock = true;
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        SketchEscape se = new SketchEscape();
        initialize(se, cfg);
        
        
        
        
        
        
    }
    
    //Analytics
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
  
}

