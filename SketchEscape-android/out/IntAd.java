//package com.aaronclover.sketchescape;
//
//import com.google.ads.Ad;
//import com.google.ads.AdListener;
//import com.google.ads.AdRequest;
//import com.google.ads.AdRequest.ErrorCode;
//import com.google.ads.InterstitialAd;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Toast;
//
///**
// * An {@link Activity} that requests and can display an InterstitialAd.
// */
//public class IntAd extends Activity implements AdListener,
//    OnClickListener {
//  /** The log tag. */
//  private static final String LOG_TAG = "InterstitialSample";
//
//  /** The interstitial ad. */
//  private InterstitialAd interstitialAd;
//
//  /** The button to reload the interstitial. */
//  private Button loadButton;
//
//  /** The button to show the interstitial. */
//  private Button showButton;
//
//  /** Called when the activity is first created. */
//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.main);
//
//    // Create an ad.
//    interstitialAd = new InterstitialAd(this, "def7937fe1cf4ca8");
//
//    // Set the AdListener.
//    interstitialAd.setAdListener(this);
//
//    // Get a reference to the reload button. This button will load an
//    // interstitial ad.
//    loadButton = (Button) findViewById(R.id.loadButton);
//
//    // Get a reference to the show button. The button will start disabled, but
//    // will be enabled when the interstitial ad is loaded. After it is shown,
//    // it will be disabled again.
//    showButton = (Button) findViewById(R.id.showButton);
//    showButton.setText("Interstitial Not Ready");
//    showButton.setEnabled(false);
//
//    // Set the onClickListener for the buttons.
//    loadButton.setOnClickListener(this);
//    showButton.setOnClickListener(this);
//  }
//
//  /** Called when a view has been clicked. */
//  @Override
//  public void onClick(View view) {
//    // If the load button was clicked, load an interstitial ad. Otherwise, if
//    // the show button was clicked, show the interstitial.
//    if (view == loadButton) {
//      // Disable the show button until the new ad is loaded.
//      showButton.setText("Loading Interstitial...");
//      showButton.setEnabled(false);
//
//      // Load the interstitial ad. Check logcat output for the hashed device ID
//      // to get test ads on a physical device.
//      AdRequest adRequest = new AdRequest();
//      adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
//      interstitialAd.loadAd(adRequest);
//    } else if (view == showButton) {
//      // Disable the show button until another interstitial is loaded.
//      showButton.setText("Interstitial Not Ready");
//      showButton.setEnabled(false);
//
//      // Show the interstitial if it's loaded.
//      if (interstitialAd.isReady()) {
//        interstitialAd.show();
//      } else {
//        Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
//      }
//    }
//  }
//
//  /** Called when an ad was not received. */
//  @Override
//  public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode error) {
//    String message = "onFailedToReceiveAd (" + error + ")";
//    Log.d(LOG_TAG, message);
//    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//
//    // Change the button text and disable the button.
//    if (ad == interstitialAd) {
//      showButton.setText("Failed to Receive Ad");
//      showButton.setEnabled(false);
//    }
//  }
//
//  /**
//   * Called when an ad is clicked and going to start a new Activity that will
//   * leave the application (e.g. breaking out to the Browser or Maps
//   * application).
//   */
//  @Override
//  public void onLeaveApplication(Ad ad) {
//    Log.d(LOG_TAG, "onLeaveApplication");
//    Toast.makeText(this, "onLeaveApplication", Toast.LENGTH_SHORT).show();
//  }
//
//  /**
//   * Called when an Activity is created in front of the app (e.g. an
//   * interstitial is shown, or an ad is clicked and launches a new Activity).
//   */
//  @Override
//  public void onPresentScreen(Ad ad) {
//    Log.d(LOG_TAG, "onPresentScreen");
//    Toast.makeText(this, "onPresentScreen", Toast.LENGTH_SHORT).show();
//  }
//
//    // Change the button text and enable the button.
//    if (ad == interstitialAd) {
//      showButton.setText("Show Interstitial");
//      showButton.setEnabled(true);
//    }
//  }
//
//@Override
//public void onDismissScreen(Ad arg0) {
//	Log.d(LOG_TAG, "onDismissScreen");
//    Toast.makeText(this, "onDismissScreen", Toast.LENGTH_SHORT).show();
//}
//
//@Override
//public void onFailedToReceiveAd(com.google.ads.Ad arg0, ErrorCode arg1) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void onLeaveApplication(com.google.ads.Ad arg0) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void onPresentScreen(com.google.ads.Ad arg0) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void onReceiveAd(com.google.ads.Ad arg0) {
//	Log.d(LOG_TAG, "onReceiveAd");
//    Toast.makeText(this, "onReceiveAd", Toast.LENGTH_SHORT).show();
//}
//}
