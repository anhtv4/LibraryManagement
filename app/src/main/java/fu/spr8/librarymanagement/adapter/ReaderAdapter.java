package fu.spr8.librarymanagement.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.activity.BookDetailActivity;
import fu.spr8.librarymanagement.activity.ReaderDetailActivity;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ViewHolder> {
    private List<ReaderM> readers;
    private ReaderDatabase readerDatabase;
    RecyclerView recyclerView;
    public ReaderAdapter(List<ReaderM> readers) {
        this.readers = readers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.reader_recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReaderM readerM = readers.get(position);

        holder.id.setText(String.valueOf(readerM.getId()));
        holder.name.setText(readerM.getName());
        holder.email.setText(readerM.getEmail());
        holder.address.setText(readerM.getAddress());
    }

    @Override
    public int getItemCount() {
        if (readers != null) {
            return readers.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView id;
        public final TextView name;
        public final TextView email;
        public final TextView address;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            id = view.findViewById(R.id.tvId);
            name = view.findViewById(R.id.textView1);
            email = view.findViewById(R.id.textView2);
            address = view.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext() , ReaderDetailActivity.class);
                    intent.putExtra("ReaderID", id.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}


