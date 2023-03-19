package fu.spr8.librarymanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.model.Admin;

public class AdminDatabase {
    static final String TABLE_NAME = "Admin";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "dob";
    private static final String COL_4 = "address";
    private static final String COL_5 = "phone";
    private static final String COL_6 = "email";
    private static final String COL_7 = "username";
    private static final String COL_8 = "password";
    public static SQLiteDatabase db;
    private static DatabaseHelper dbHelper;
    private Context context;
    static final String TABLE_CREATE = String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT )",
            TABLE_NAME, COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8);

    public AdminDatabase(Context context1) {
        context = context1;
        dbHelper = new DatabaseHelper(context);
    }

    public void addAdmin(Admin admin) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, admin.getId());
        values.put(COL_2, admin.getName());
        values.put(COL_3, admin.getDob());
        values.put(COL_4, admin.getAddress());
        values.put(COL_5, admin.getPhone());
        values.put(COL_6, admin.getEmail());
        values.put(COL_7, admin.getUsername());
        values.put(COL_8, admin.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Add Admin Successfully!");

    }

    public Admin getAdminAccount(String username, String password) {
        db = dbHelper.getReadableDatabase();

        Admin admin = null;
        String where = "username=? AND password=?";
        String[] args = {username, password};
        Cursor cursor = db.query(TABLE_NAME, null, where, args,null, null, null);
        if(cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            admin = new Admin(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        }
        return admin;
    }

    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Admin admin = new Admin(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            admins.add(admin);
            cursor.moveToNext();
        }
        return admins;
    }

    public void updateAdmin(Admin admin) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(admin.getName() != null)values.put(COL_2, admin.getName());
        if(admin.getDob() != null)values.put(COL_3, admin.getDob());
        if(admin.getAddress() != null)values.put(COL_4, admin.getAddress());
        if(admin.getPhone() != null)values.put(COL_5, admin.getPhone());
        if(admin.getEmail() != null)values.put(COL_6, admin.getEmail());

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { String.valueOf(admin.getId()) });
        db.close();
        Utils.showToast(context.getApplicationContext(), "Admin update successfully!");
    }

    public void deleteAdmin(String id) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[] {id});
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Deleted userID /" + id + "/ successfully!");
    }

    public void changePassword(String id, String password){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_8, password);

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { id });
        db.close();
        Utils.showToast(context.getApplicationContext(), "Change password successfully!");
    }

}
