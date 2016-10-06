package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeTeller;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.jokedisplay.JokeActivity;


public class MainActivity extends ActionBarActivity {
    InterstitialAd mInterstitialAd;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.progressbar);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                avLoadingIndicatorView.setVisibility(View.VISIBLE);
                new EndpointsAsyncTask(avLoadingIndicatorView).execute(MainActivity.this);
            }
        });

        requestNewInterstitial();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {

            avLoadingIndicatorView.setVisibility(View.VISIBLE);
            new EndpointsAsyncTask(avLoadingIndicatorView).execute(this);
        }


        //Toast.makeText(this, jokeTeller.tellJoke(), Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(com.udacity.gradle.builditbigger.MainActivity.this, JokeActivity.class).putExtra("joke",jokeTeller.tellJoke()));
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D296B08F827BB51CC4843DC6D385D573")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }



}
