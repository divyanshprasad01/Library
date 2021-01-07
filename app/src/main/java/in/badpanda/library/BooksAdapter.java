package in.badpanda.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<BookObj> {
    public BooksAdapter(@NonNull Context context, ArrayList<BookObj> newBook) {
        super(context,0, newBook);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.books_grid_palatte, parent, false);
        }

        BookObj newBook = getItem(position);

        TextView title = (TextView) view.findViewById(R.id.gridTitle);
        title.setText(newBook.getTitle());

        TextView author = (TextView) view.findViewById(R.id.gridAuthor);
        author.setText(newBook.getAuthor());

        ImageView thumbnail = (ImageView) view.findViewById(R.id.gridThumbnail);
        thumbnail.setImageResource(R.drawable.ic_launcher_background);
        return view;
    }
}
