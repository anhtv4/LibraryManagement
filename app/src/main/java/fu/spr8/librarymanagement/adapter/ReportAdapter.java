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
import fu.spr8.librarymanagement.activity.ReportDetailActivity;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.db.ReportDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;
import fu.spr8.librarymanagement.model.Report;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private List<Report> reports;
    private ReportDatabase reportDatabase;
    RecyclerView recyclerView;
    public ReportAdapter(List<Report> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.report_recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report report = reports.get(position);

        ReaderDatabase readerDatabase = new ReaderDatabase(holder.view.getContext());
        ReaderM readerM = readerDatabase.getReaderByID(report.getReaderId());

        BookDatabase bookDatabase = new BookDatabase(holder.view.getContext());
        Book book = bookDatabase.getBookByID(report.getBookId());

        holder.id.setText(String.valueOf(report.getId()));
        holder.reader.setText(readerM.getName());
        holder.book.setText(book.getTitle());
        holder.quantity.setText(String.valueOf(report.getQuantity()));
        holder.status.setText(report.getStatus() == 0 ? "Lending" : "Returned");
    }

    @Override
    public int getItemCount() {
        if (reports != null) {
            return reports.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView id;
        public final TextView reader;
        public final TextView book;
        public final TextView quantity;
        public final TextView status;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            id = view.findViewById(R.id.tvId);
            reader = view.findViewById(R.id.textView1);
            book = view.findViewById(R.id.textView2);
            quantity = view.findViewById(R.id.textView3);
            status = view.findViewById(R.id.textView12);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext() , ReportDetailActivity.class);
                    intent.putExtra("ReportID", id.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}


