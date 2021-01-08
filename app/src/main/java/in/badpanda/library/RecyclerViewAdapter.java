package in.badpanda.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.coustemAdapter> {
    Context context;
    private List<BookObj> bookObj;

    public RecyclerViewAdapter(Context context, List<BookObj> bookObj) {
        this.context = context;
        this.bookObj = bookObj;
    }

    @NonNull
    @Override
    public coustemAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_grid_palatte,parent,false);
        return new coustemAdapter(layout);
    }
    public static class coustemAdapter extends RecyclerView.ViewHolder{
        TextView title ;
        TextView author;
        ImageView thumbnail;

        public coustemAdapter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.gridTitle);
            author = itemView.findViewById(R.id.gridAuthor);
            thumbnail = (ImageView) itemView.findViewById(R.id.gridThumbnail);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull coustemAdapter holder, int position) {
        BookObj book = bookObj.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.thumbnail.setImageBitmap(book.getThumbnail());

    }


    @Override
    public int getItemCount() {
        return bookObj.size();
    }


}
