package com.aaronclover.sketchescape;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.ads.*;
import com.google.ads.AdRequest.ErrorCode;

public class IntAd extends Activity implements AdListener {

  private static final String MY_INTERSTITIAL_UNIT_ID = "a151bbb5785fa88";
private InterstitialAd interstitial;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Create the interstitial
    interstitial = new InterstitialAd(this, MY_INTERSTITIAL_UNIT_ID);

    // Create ad request
    AdRequest adRequest = new AdRequest();

    // Begin loading your interstitial
    interstitial.loadAd(adRequest);

    // Set Ad Listener to use the callbacks below
    interstitial.setAdListener(this);
  }

  @Override
  public void onReceiveAd(Ad ad) {
    Log.d("OK", "Received ad");
    if (ad == interstitial) {
      interstitial.show();
    }
  }

@Override
public void onDismissScreen(Ad arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
	// TODO Auto-generated method stub
	
}

@Override
public void onLeaveApplication(Ad arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onPresentScreen(Ad arg0) {
	// TODO Auto-generated method stub
	
}
}