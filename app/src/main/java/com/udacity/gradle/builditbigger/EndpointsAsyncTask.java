package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.example.nimitagg.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokedisplay.JokeActivity;

import java.io.IOException;

/**
 * Created by Nimit Agg on 30-08-2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    public EndpointsAsyncTask(AVLoadingIndicatorView avLoadingIndicatorView) {
        this.avLoadingIndicatorView=avLoadingIndicatorView;
    }

    @Override
    protected String doInBackground(Context... context) {
        if(myApiService == null) {  // Only do this once
     /*       MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://122.161.117.33:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-141916.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        this.context = context[0];


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        avLoadingIndicatorView.setVisibility(View.INVISIBLE);
       // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, JokeActivity.class).putExtra("joke",result));
    }
}