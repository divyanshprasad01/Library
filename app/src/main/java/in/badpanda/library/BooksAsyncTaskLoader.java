package in.badpanda.library;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class BooksAsyncTaskLoader extends AsyncTaskLoader<List<BookObj>> {
    String mURL;

    public BooksAsyncTaskLoader(@NonNull Context context,String mURL) {
        super(context);
        this.mURL = mURL;
    }


    protected void onStartLoading() {
        onForceLoad();
    }

    @Nullable
    @Override
    public List<BookObj> loadInBackground() {
        List<BookObj> result = getOnlineContent.fetch(mURL);
        return result;
    }
}
