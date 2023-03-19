package fu.spr8.librarymanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.Report;

public class ReportDatabase {
    static final String TABLE_NAME = "Report";
    private static final String COL_1 = "id";
    private static final String COL_2 = "bookId";
    private static final String COL_3 = "readerId";
    private static final String COL_4 = "quantity";
    private static final String COL_5 = "status";
    private static final String COL_6 = "dateBorrow";
    private static final String COL_7 = "dateBack";
    public static SQLiteDatabase db;
    private static DatabaseHelper dbHelper;
    private Context context;
    static final String TABLE_CREATE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT)",
            TABLE_NAME, COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7);

    public ReportDatabase(Context context1) {
        context = context1;
        dbHelper = new DatabaseHelper(context);
    }

    public void addReport(Report report) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, report.getBookId());
        values.put(COL_3, report.getReaderId());
        values.put(COL_4, report.getQuantity());
        values.put(COL_5, report.getStatus());
        values.put(COL_6, report.getDateBorrow());
        values.put(COL_7, report.getDateBack());

        db.insert(TABLE_NAME, null, values);
        db.close();

        BookDatabase bookDatabase = new BookDatabase(context);
        bookDatabase.updateQuantityBook(report.getBookId(), report.getQuantity());

        Utils.showToast(this.context.getApplicationContext(), "Add report successfully!");

    }

    public List<Report> getAllReport() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Report report = new Report(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
            reports.add(report);
            cursor.moveToNext();
        }
        return reports;
    }

    public void updateReport(Report report) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (String.valueOf(report.getBookId()) != null) values.put(COL_2, report.getBookId());
        if (String.valueOf(report.getReaderId()) != null) values.put(COL_3, report.getReaderId());
        if (String.valueOf(report.getQuantity()) != null) values.put(COL_4, report.getQuantity());
        if (String.valueOf(report.getStatus()) != null) values.put(COL_5, report.getStatus());
        if (String.valueOf(report.getDateBorrow()) != null)
            values.put(COL_6, report.getDateBorrow());
        if (String.valueOf(report.getDateBack()) != null) values.put(COL_7, report.getDateBack());

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(report.getId())});
        db.close();
        Utils.showToast(context.getApplicationContext(), "Report update successfully!");
    }

    public void deleteReport(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Deleted report successfully!");
    }

    public Report getReportByID(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, COL_1 + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Report report = new Report(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
        return report;
    }

    public List<Report> searchReportByStatus(int status) {
        List<Report> reports = new ArrayList<>();
        String query = null;
        if(status == 2){
            query = "SELECT * FROM " + TABLE_NAME;
        }else{
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_5 + " = " + status;
        }

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Report report = new Report(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                reports.add(report);
                cursor.moveToNext();
            }
        } else {
            Utils.showToast(this.context.getApplicationContext(), "No matching report found!");
        }

        return reports;
    }

    public void changeStatus(Report report){
        Report report1 = this.getReportByID(report.getId());
        int current_status = report1.getStatus();
        int new_status = current_status == 0 ? 1 : 0;
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5, new_status);
        values.put(COL_7, report.getDateBack());

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(report.getId())});
        db.close();
        Utils.showToast(context.getApplicationContext(), "Report change status successfully!");
    }

}
