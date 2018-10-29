package com.responsi.bioskop16110039;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by JamesAndrew on 10/28/2018.
 */

public class DetailFilem extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtOverview;
    private TextView txtRelease;
    private ImageView imgPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_filem);

        Filem f = (Filem) getIntent().getSerializableExtra("F");
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(f.getTxtTitle());
        txtOverview = (TextView) findViewById(R.id.txtDescription);
        txtOverview.setText(f.getTxtOverview());
        txtRelease = (TextView) findViewById(R.id.txtDate);
        txtRelease.setText(f.getReleaseDate());
        imgPoster = (ImageView) findViewById(R.id.posterMovie);
        Glide.with(getApplicationContext())
                .load(f.getImgPoster())
                .override(350, 350)
                .into(imgPoster);
    }
}
