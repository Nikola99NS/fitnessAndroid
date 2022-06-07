package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper5 extends SQLiteOpenHelper {

    public static final String DBNAME ="rezervacije.db";


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table rezervacije(trener TEXT primary key, username TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists rezervacije");

    }

    public Boolean insertData(String username,String trener){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trener", trener);
        contentValues.put("username",username);

        long result = MyDB.insert("rezervacije",null,contentValues);
        if(result==-1) return false;
        else{
            return true;
        }
    }

    public DBHelper5(@Nullable Context context) {
        super(context, "rezervacije.db", null, 1);
    }


    public int vratiBrojSlobodnihMesta(String trener){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from rezervacije where trener =?", new String[] {trener});
        int n = cursor.getCount();
        return 5-n;

    }

    public boolean checkUser(String username,String trener){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from rezervacije where username =? and trener =?", new String[] {username, trener});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public String vratiImeIPrezime(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from kalorije where username =?", new String[] {username});
        int n = cursor.getCount();

        if(n>0){
            String vrati = "";
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String ime = cursor.getString(cursor.getColumnIndex("ime"));
                @SuppressLint("Range") String prezime = cursor.getString(cursor.getColumnIndex("prezime"));
                vrati = ime + " " + prezime;
                Log.i("vrati",vrati);
            }
            Log.i("vrati2",vrati);
            return vrati;
        }
        return "null";

    }
}
