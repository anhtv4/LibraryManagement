package fu.spr8.librarymanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LibraryManagement";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AdminDatabase.TABLE_CREATE);
        sqLiteDatabase.execSQL(BookDatabase.TABLE_CREATE);
        sqLiteDatabase.execSQL(ReaderDatabase.TABLE_CREATE);
        sqLiteDatabase.execSQL(ReportDatabase.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AdminDatabase.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BookDatabase.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReaderDatabase.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReportDatabase.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
