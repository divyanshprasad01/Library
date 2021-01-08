package in.badpanda.library;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class BooksAsyncTaskLoader extends AsyncTaskLoader<ArrayList<List<BookObj>>> {

    String FURL,SURL,CSURL;

    public BooksAsyncTaskLoader(@NonNull Context context,String mURL,String SURL, String CSURL) {
        super(context);
        this.FURL = mURL;
        this.SURL = SURL;
        this.CSURL = CSURL;
    }


    protected void onStartLoading() {
        onForceLoad();
    }

    @Nullable
    @Override
    public ArrayList<List<BookObj>> loadInBackground() {
        ArrayList<List<BookObj>> result = new ArrayList<>();
        result.add(0,getOnlineContent.fetch(FURL));
        result.add(1,getOnlineContent.fetch(SURL));
        result.add(2,getOnlineContent.fetch(CSURL));

        return result;
    }
}
