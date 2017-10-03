package com.rai;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.rai.jeteater.AdService;
import com.rai.jeteater.jeteater;


public class AndroidLauncher extends AndroidApplication implements AdService {

	private InterstitialAd mInterstitialAd;

	//handler
	private Handler handler= new Handler();
	AdView adview;
	LinearLayout game;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.generic);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View v= initializeForView(new jeteater(this), config);


		adview=(AdView)findViewById(R.id.adView);
		game= (LinearLayout)findViewById(R.id.game);

		game.addView(v);

		MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

		adview.loadAd(new AdRequest.Builder().build());

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

		mInterstitialAd.loadAd(new AdRequest.Builder().build());

		mInterstitialAd.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				mInterstitialAd.loadAd(new AdRequest.Builder().build());

			}
		});
	}

	@Override
	public void ShowInterstetial() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if(mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				}
			}
		});
	}

	@Override
	public void toggleBanner() {

		final int vis=adview.getVisibility();
		handler.post(new Runnable() {
			@Override
			public void run() {
				switch (vis){
					case View.VISIBLE:
						adview.setVisibility(View.INVISIBLE);
					case View.INVISIBLE:
						adview.setVisibility(View.VISIBLE);

				}
			}
		});


	}
}
