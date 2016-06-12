package com.hongoctuan.admin.ungdungxemphim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;

import java.util.ArrayList;

/**
 * Created by admin on 5/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "ungdungxemphim";
    public static String TABLE_LICHSUSEARCH = "lichsusearch";
    public static String TABLE_PHIM = "phim";
    public static String TABLE_BINHLUAN = "binhluan";
    public static String TABLE_VUNG = "vung";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_RAP = "rap";
    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public ArrayList<MovieDTO> getTypeMovie(String theloai){
        ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_PHIM +" where theloai = '" + theloai +"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            MovieDTO phim = new MovieDTO();
            phim.setMovieId(cursor.getString(0));
            phim.setMovieName(cursor.getString(1));
            phim.setDirectorName(cursor.getString(2));
            phim.setActor(cursor.getString(3));
            phim.setRateString(cursor.getString(4));
            phim.setMovieSumary(cursor.getString(5));
            phim.setCategory(cursor.getString(6));
            phim.setMovieUrl(cursor.getString(7));
            list.add(phim);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE binhluan (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,maphim TEXT, nguoibinhluan TEXT, noidung TEXT)";
        db.execSQL(sql);
        sql="CREATE TABLE lichsusearch (keyword TEXT PRIMARY KEY)";
        db.execSQL(sql);
        sql="CREATE TABLE phim (id TEXT primary key,tenphim TEXT,daodien TEXT,dienvien TEXT,danhgia TEXT,tomtat TEXT,theloai TEXT,url TEXT)";
        db.execSQL(sql);
        sql="CREATE TABLE rap (tenrap TEXT PRIMARY KEY,vung TEXT)";
        db.execSQL(sql);
        sql="CREATE TABLE vung (tenvung TEXT PRIMARY KEY)";
        db.execSQL(sql);

//        Data dt = new Data();
//        sql = dt.createDataComment1();
//        db.execSQL(sql);
//        sql = dt.createDataComment2();
//        db.execSQL(sql);
//        sql = dt.createDataComment3();
//        db.execSQL(sql);
//        sql = dt.createDataComment4();
//        db.execSQL(sql);
//        sql = dt.createDataComment5();
//        db.execSQL(sql);
//        sql = dt.createDataComment6();
//        db.execSQL(sql);
//        sql = dt.createDataComment7();
//        db.execSQL(sql);
//        sql = dt.createDataComment8();
//        db.execSQL(sql);
//        sql = dt.createDataComment9();
//        db.execSQL(sql);
//        sql = dt.createDataComment10();
//        db.execSQL(sql);
//        sql = dt.createDataComment11();
//        db.execSQL(sql);
//        sql = dt.createDataComment12();
//        db.execSQL(sql);
//        sql = dt.createDataComment13();
//        db.execSQL(sql);
//        sql = dt.createDataComment14();
//        db.execSQL(sql);
//        sql = dt.createDataComment15();
//        db.execSQL(sql);
//        sql = dt.createDataComment16();
//        db.execSQL(sql);
//        sql = dt.createDataComment17();
//        db.execSQL(sql);
//        sql = dt.createDataComment18();
//        db.execSQL(sql);
//        sql = dt.createDataComment19();
//        db.execSQL(sql);
//        sql = dt.createDataComment20();
//        db.execSQL(sql);
//        sql = dt.createDataComment21();
//        db.execSQL(sql);
//        sql = dt.createDataComment22();
//        db.execSQL(sql);
//        sql = dt.createDataComment23();
//        db.execSQL(sql);
//        sql = dt.createDataComment24();
//        db.execSQL(sql);
//        sql = dt.createDataComment25();
//        db.execSQL(sql);
//        sql = dt.createDataComment26();
//        db.execSQL(sql);
//
//        sql = dt.createDataMovie1();
//        db.execSQL(sql);
//        sql = dt.createDataMovie2();
//        db.execSQL(sql);
//        sql = dt.createDataMovie3();
//        db.execSQL(sql);
//        sql = dt.createDataMovie4();
//        db.execSQL(sql);
//        sql = dt.createDataMovie5();
//        db.execSQL(sql);
//        sql = dt.createDataMovie6();
//        db.execSQL(sql);
//        sql = dt.createDataMovie7();
//        db.execSQL(sql);
//        sql = dt.createDataMovie8();
//        db.execSQL(sql);
//        sql = dt.createDataMovie9();
//        db.execSQL(sql);
//        sql = dt.createDataMovie10();
//        db.execSQL(sql);
//        sql = dt.createDataMovie11();
//        db.execSQL(sql);
//        sql = dt.createDataMovie12();
//        db.execSQL(sql);
//        sql = dt.createDataMovie13();
//        db.execSQL(sql);
//        sql = dt.createDataMovie14();
//        db.execSQL(sql);
//        sql = dt.createDataMovie15();
//        db.execSQL(sql);
//        sql = dt.createDataMovie16();
//        db.execSQL(sql);
//        sql = dt.createDataMovie17();
//        db.execSQL(sql);
//        sql = dt.createDataMovie18();
//        db.execSQL(sql);
//        sql = dt.createDataMovie19();
//        db.execSQL(sql);
//        sql = dt.createDataMovie20();
//        db.execSQL(sql);
//        sql = dt.createDataMovie21();
//        db.execSQL(sql);
//        sql = dt.createDataMovie22();
//        db.execSQL(sql);
//        sql = dt.createDataMovie23();
//        db.execSQL(sql);
//        sql = dt.createDataMovie24();
//        db.execSQL(sql);
//        sql = dt.createDataMovie25();
//        db.execSQL(sql);
//        sql = dt.createDataMovie26();
//        db.execSQL(sql);
//        sql = dt.createDataMovie27();
//        db.execSQL(sql);
//        sql = dt.createDataMovie28();
//        db.execSQL(sql);
//        sql = dt.createDataMovie29();
//        db.execSQL(sql);
//        sql = dt.createDataMovie30();
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_PHIM);
        db.execSQL("drop table if exists" + TABLE_BINHLUAN);
        db.execSQL("drop table if exists" + TABLE_RAP);
        db.execSQL("drop table if exists" + TABLE_VUNG);
        onCreate(db);
    }

    public ArrayList<String> getListRap(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_RAP;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public ArrayList<String> getRap(String vung){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_RAP + " where vung = '"+vung+"'  ORDER BY rowid DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public void insertRap(String tenrap, String vung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenrap", tenrap);
        values.put("vung", vung);
        if(db.insert(TABLE_RAP, null, values)!= -1){
        }else{
        }
        db.close();
    }

    public void insertVung(String tenvung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenvung", tenvung);
        if(db.insert(TABLE_VUNG, null, values)!= -1){
        }else{
        }
        db.close();
    }

    public ArrayList<String> getVung(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_VUNG ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
}
