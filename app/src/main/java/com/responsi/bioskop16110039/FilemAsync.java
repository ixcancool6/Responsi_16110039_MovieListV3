package com.responsi.bioskop16110039;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JamesAndrew on 10/28/2018.
 */

public class FilemAsync extends AsyncTaskLoader<ArrayList<Filem>> {

    private String URL;
    private String filems = null;

    public FilemAsync(Context context, String URL) {
        super(context);
        this.URL = URL;
    }

    @Override
    public ArrayList<Filem> loadInBackground() {
        final ArrayList<Filem> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try {
            Response response = client.newCall(request).execute();

            filems = response.body().string();

            try{
                JSONObject objData = new JSONObject(filems);
                final JSONArray arrayResults = objData.getJSONArray("results");
                if(arrayResults != null) {
                    for (int i = 0; i < arrayResults.length(); i++) {
                        JSONObject objMovie = new JSONObject(arrayResults.get(i).toString());
                        String title = objMovie.getString("title");
                        String overview = objMovie.getString("overview");
                        String releaseDate = objMovie.getString("release_date");
                        String imgPoster = "http://image.tmdb.org/t/p/w185" + objMovie.getString("poster_path");
                        list.add(new Filem(title, overview, releaseDate, imgPoster));
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
