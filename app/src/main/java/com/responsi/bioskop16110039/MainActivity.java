package com.responsi.bioskop16110039;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Filem>>{
    private RecyclerView recyclerView;
    private ArrayList<Filem> list;
    private Button btnMoveActivity;

    private String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=b0d1b7f14b9fa185823ff44f5706c76b&region=US";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);
        getSupportLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<ArrayList<Filem>>) this).forceLoad();

    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewFilemAdapter CardViewFilemAdapter = new CardViewFilemAdapter(this);
        CardViewFilemAdapter.setFilems(list);
        recyclerView.setAdapter(CardViewFilemAdapter);
    }

    @Override
    public Loader<ArrayList<Filem>> onCreateLoader(int i, Bundle bundle) {
        return new FilemAsync(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Filem>> loader, ArrayList<Filem> filems) {
        this.list = filems;
        Log.d("LIST : ", String.valueOf(this.list.size()));
        if (this.list != null) {
            showRecyclerCardView();
        } else {
            setContentView(R.layout.detail_filem);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Filem>> loader) {
        this.list = null;
    }

    public void Pindah(View view) {
        Intent intent = new Intent(MainActivity.this, DetailFilem.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.bahasa:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

