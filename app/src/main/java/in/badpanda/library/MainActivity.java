package in.badpanda.library;

import android.content.Loader;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<List<BookObj>>> {

    private String REQUEST_URL_FICTION = "https://books.googleapis.com/books/v1/volumes?q=subject:fiction&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private String REQUEST_URL_SELF_DEVELOPMENT = "https://books.googleapis.com/books/v1/volumes?q=subject:Self%20Devlopment&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private String REQUEST_URL_COMPUTER_SCIENCE = "https://books.googleapis.com/books/v1/volumes?q=Computer%20Science&maxResults=40&orderBy=relevance&langRestrict=en&projection=lite&download=epub&key=AIzaSyDV2uhGjjzL-2nmt0H7eX1e2U1Nj94nIcU";

    private int BOOK_LOADER_ID = 1;

    private RecyclerView recyclerView, recyclerView2, recyclerView3;
//    private RecyclerViewAdapter recyclerViewAdapter;
//    private RecyclerViewAdapter recyclerViewAdapter2;
//    private RecyclerViewAdapter recyclerViewAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView2 = findViewById(R.id.RecyclerView2);
        recyclerView3 = findViewById(R.id.RecyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

//        recyclerViewAdapter2 = new RecyclerViewAdapter(this, newBooks);
//        recyclerViewAdapter3 = new RecyclerViewAdapter(this, newBooks);

//        recyclerView2.setAdapter(recyclerViewAdapter);
//        recyclerView3.setAdapter(recyclerViewAdapter);

    }


    @Override
    public Loader<ArrayList<List<BookObj>>> onCreateLoader(int id, Bundle args) {
        return new BooksAsyncTaskLoader(this,REQUEST_URL_FICTION,REQUEST_URL_SELF_DEVELOPMENT,REQUEST_URL_COMPUTER_SCIENCE);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<List<BookObj>>> loader, ArrayList<List<BookObj>> data) {
        if(!data.isEmpty()) {

            recyclerView.setAdapter(new RecyclerViewAdapter(this, data.get(0)));
            recyclerView2.setAdapter(new RecyclerViewAdapter(this, data.get(1)));
            recyclerView3.setAdapter(new RecyclerViewAdapter(this, data.get(2)));
            Log.d("TEST","Recycler View Populated");
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<List<BookObj>>> loader) {

    }
}