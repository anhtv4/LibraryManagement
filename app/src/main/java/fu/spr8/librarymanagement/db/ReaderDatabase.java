package fu.spr8.librarymanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.model.Admin;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;

public class ReaderDatabase {
    static final String TABLE_NAME = "Reader";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "dob";
    private static final String COL_4 = "address";
    private static final String COL_5 = "email";
    public static SQLiteDatabase db;
    private static DatabaseHelper dbHelper;
    private Context context;
    static final String TABLE_CREATE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
            TABLE_NAME, COL_1, COL_2, COL_3, COL_4, COL_5);

    public ReaderDatabase(Context context1) {
        context = context1;
        dbHelper = new DatabaseHelper(context);
    }

    public void addReader(ReaderM reader) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, reader.getName());
        values.put(COL_3, reader.getDob());
        values.put(COL_4, reader.getAddress());
        values.put(COL_5, reader.getEmail());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Add Reader Successfully!");

    }

    public ReaderM getReaderByID(int id) {
        db = dbHelper.getReadableDatabase();

        ReaderM readerM = null;
        String where = "id=?";
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, null, where, args,null, null, null);
        if(cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            readerM = new ReaderM(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        return readerM;
    }

    public List<ReaderM> getAllReader() {
        List<ReaderM> readers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ReaderM readerM = new ReaderM(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            readers.add(readerM);
            cursor.moveToNext();
        }
        return readers;
    }

    public void updateReader(ReaderM readerM) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(readerM.getName() != null)values.put(COL_2, readerM.getName());
        if(readerM.getDob() != null)values.put(COL_3, readerM.getDob());
        if(readerM.getAddress() != null)values.put(COL_4, readerM.getAddress());
        if(readerM.getEmail() != null)values.put(COL_5, readerM.getEmail());

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { String.valueOf(readerM.getId()) });
        db.close();
        Utils.showToast(context.getApplicationContext(), "Reader update successfully!");
    }

    public void deleteReader(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[] {String.valueOf(id)});
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Deleted successfully!");
    }

    public List<ReaderM> searchReaderByName(String name) {
        List<ReaderM> readers = new ArrayList<>();
        String query = null;
        if(name.equals("")){
            query = "SELECT * FROM " + TABLE_NAME;
        }else{
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " LIKE '%" + name + "%'";
        }

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {
                ReaderM readerM = new ReaderM(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                readers.add(readerM);
                cursor.moveToNext();
            }
        }else{
            Utils.showToast(this.context.getApplicationContext(), "No matching reader found!");
        }

        return readers;
    }

}
