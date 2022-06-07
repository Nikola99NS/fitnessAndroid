package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {

    public static final String DBNAME ="ratings.db";

    public DBHelper2(@Nullable Context context) {
        super(context, "ratings.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table ratings(uAndg TEXT primary key, gym TEXT,rating FLOAT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists ratings");
    }

    public Boolean insertData(String uAndg, String gym, float rating){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uAndg",uAndg);
        contentValues.put("gym", gym);
        contentValues.put("rating",rating);
        long result = MyDB.insert("ratings",null,contentValues);
        if(result==-1) return false;
        else{
            return true;
        }
    }

    public boolean checkUserAndGym(String userAndGym){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from ratings where uAndg =?", new String[] {userAndGym});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public float vratiProsecnuOcenuTeretane(String gym){
        float prosecna=0;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from ratings where gym =?", new String[] {gym});
        int n = cursor.getCount();
        while(cursor.moveToNext()){
            @SuppressLint("Range") float ocena = cursor.getFloat(cursor.getColumnIndex("rating"));
            prosecna = prosecna + ocena;
        }
        if(n>0) {
            return prosecna / n;
        }else{
            return 0;
        }
    }
}
