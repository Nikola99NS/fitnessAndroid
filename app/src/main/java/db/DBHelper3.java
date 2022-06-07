package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper3 extends SQLiteOpenHelper {

    public static final String DBNAME ="komentari.db";

    public DBHelper3(@Nullable Context context) {
        super(context, "komentari.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table komentari(id int primary key,username TEXT , gym TEXT,komentar TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists komentari");
    }

    public Boolean insertData(String username, String gym, String komentar){
        int min = 0;
        int max = 2147483647;
        int id = (int)Math.floor(Math.random()*(max-min+1)+min);

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("username",username);
        contentValues.put("gym", gym);
        contentValues.put("komentar",komentar);
        long result = MyDB.insert("komentari",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<String> vratiKomentareZaOvuTeretanu(String gym){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from komentari where gym =?", new String[] {gym});
        int n = cursor.getCount();
        ArrayList<String> comments=new ArrayList<String>();
        int i=0;
        while(cursor.moveToNext()){
            i++;
            @SuppressLint("Range") String kom = cursor.getString(cursor.getColumnIndex("komentar"));
            @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex("username"));
            String komUser = user + " " + kom;
            comments.add(komUser);
        }
        return comments;
    }
}
