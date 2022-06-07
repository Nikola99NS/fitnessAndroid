package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper4 extends SQLiteOpenHelper {

    public static final String DBNAME ="kalorije.db";


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table kalorije(username TEXT primary key, kcal int, ime String, prezime String, godine String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists kalorije");

    }

    public Boolean insertData(String username, int kcal, String ime, String prezime, String godine){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("kcal", kcal);
        contentValues.put("ime",ime);
        contentValues.put("prezime", prezime);
        contentValues.put("godine",godine);
        long result = MyDB.insert("kalorije",null,contentValues);
        if(result==-1) return false;
        else{
            return true;
        }
    }

    public DBHelper4(@Nullable Context context) {
        super(context, "kalorije.db", null, 1);
    }


    public int vratiKcalZaUsera(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from kalorije where username =?", new String[] {username});
        int n = cursor.getCount();
        Log.i("kursor", String.valueOf(n));
        if(n>0){
            int kcal2=0;
            while(cursor.moveToNext()) {
                @SuppressLint("Range") int kcal = cursor.getInt(cursor.getColumnIndex("kcal"));
                kcal2=kcal;
            }
            return kcal2;

        }else{
            return -1;
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
