package fu.spr8.librarymanagement.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.activity.AddBookActivity;
import fu.spr8.librarymanagement.activity.BookDetailActivity;
import fu.spr8.librarymanagement.activity.BookManagement;
import fu.spr8.librarymanagement.activity.LoginActivity;
import fu.spr8.librarymanagement.activity.MainActivity;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;
    private BookDatabase bookDatabase;
    RecyclerView recyclerView;
    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
//        bookDatabase = new BookDatabase(parent.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);

        holder.id.setText(String.valueOf(book.getId()));
        holder.isbn.setText(book.getIsbn());
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        if (books != null) {
            return books.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView id;
        public final TextView isbn;
        public final TextView title;
        public final TextView author;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            id = view.findViewById(R.id.tvId);
            isbn = view.findViewById(R.id.textView1);
            title = view.findViewById(R.id.textView2);
            author = view.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext() , BookDetailActivity.class);
                    intent.putExtra("BookID", id.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}


