package in.badpanda.library;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<List<BookObj>>> {

    private final String REQUEST_URL_FICTION = "https://books.googleapis.com/books/v1/volumes?q=subject:fiction&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private final String REQUEST_URL_SELF_DEVELOPMENT = "https://books.googleapis.com/books/v1/volumes?q=subject:Self%20Devlopment&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private final String REQUEST_URL_COMPUTER_SCIENCE = "https://books.googleapis.com/books/v1/volumes?q=Computer%20Science&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private final int BOOK_LOADER_ID = 1;
    TextView fiction, selfDevlopment, computerScience;
    View loadingIndicator;
    private RecyclerView recyclerView, recyclerView2, recyclerView3;
//    private boolean whilecallback = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fiction =  findViewById(R.id.fiction);
        selfDevlopment = findViewById(R.id.selfDevelopment);
        computerScience =  findViewById(R.id.computerScience);

        loadingIndicator = findViewById(R.id.loading_indicator);

        View parentView = findViewById(android.R.id.content);


        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView2 = findViewById(R.id.RecyclerView2);
        recyclerView3 = findViewById(R.id.RecyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if (isConnected()) {
            loadInBackground();
        } else {

            loadingIndicator.setVisibility(View.GONE);
//            while(whilecallback){
                Snackbar.make(parentView, "No Internet!!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isConnected()) {
                                    loadInBackground();
//                                    whilecallback = false;
                                    loadingIndicator.setVisibility(View.VISIBLE);

                                }
                            }
                        }).show();
//            }
        }
    }


    @Override
    public Loader<ArrayList<List<BookObj>>> onCreateLoader(int id, Bundle args) {
        return new BooksAsyncTaskLoader(this, REQUEST_URL_FICTION, REQUEST_URL_SELF_DEVELOPMENT, REQUEST_URL_COMPUTER_SCIENCE);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<List<BookObj>>> loader, ArrayList<List<BookObj>> data) {
        if (!data.isEmpty()) {
            fiction.setVisibility(View.VISIBLE);
            selfDevlopment.setVisibility(View.VISIBLE);
            computerScience.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new RecyclerViewAdapter(this, data.get(0)));
            recyclerView2.setAdapter(new RecyclerViewAdapter(this, data.get(1)));
            recyclerView3.setAdapter(new RecyclerViewAdapter(this, data.get(2)));
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView2.setVisibility(View.VISIBLE);
            recyclerView3.setVisibility(View.VISIBLE);
            loadingIndicator.setVisibility(View.GONE);


            Log.d("TEST", "Recycler View Populated");
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<List<BookObj>>> loader) {

    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void loadInBackground() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
    }
}