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
    private static int DATABASE_VERSION = 1;

    public static String TABLE_PHIM = "phim";
    public static String TABLE_BINHLUAN = "binhluan";
    public static String TABLE_LICHSUSEARCH = "lichsusearch";
    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //insertData();
    }

    public MovieDTO getPhim(String maphim){
        MovieDTO phim = new MovieDTO();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_PHIM +" where id = '"+maphim+"'";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        phim.setMovieId(cursor.getString(0));
        phim.setMovieName(cursor.getString(1));
        phim.setDirectorName(cursor.getString(2));
        phim.setActor(cursor.getString(3));
        phim.setRateString(cursor.getString(4));
        phim.setMovieSumary(cursor.getString(5));
        phim.setCategory(cursor.getString(6));
        phim.setMovieUrl(cursor.getString(7));
        db.close();
        return phim;
    }
    public ArrayList<MovieDTO> getListPhimGoiY(String theloai, String maphim){
        ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_PHIM +" where id != '"+maphim+"' and theloai = '" + theloai +"'";
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

    public ArrayList<MovieDTO> getSearchPhim (String tenphim){
        ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT * FROM  phim where tenphim like '%"+tenphim+"%' ORDER BY rowid DESC";
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

    public ArrayList<String> getDanhSachPhim (){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT * FROM  phim ORDER BY rowid DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
    public ArrayList<CommentDTO> getBinhluan(String maphim){
        ArrayList<CommentDTO> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_BINHLUAN +" where maphim = '"+maphim+"'  ORDER BY rowid DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            CommentDTO binhluan = new CommentDTO();
            binhluan.setCommentId(cursor.getString(0));
            binhluan.setCommenter(cursor.getString(2));
            binhluan.setContent(cursor.getString(3));
            list.add(binhluan);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
    public void insertLichSu(String keyword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("keyword", keyword);
        db.insert(TABLE_LICHSUSEARCH, null, values);
        db.close();
    }

    public ArrayList<String> getLichSuSearch(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_LICHSUSEARCH +" ORDER BY rowid DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    public void insertComment(String MovieId,String UserComment, String Content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maphim", MovieId);
        values.put("nguoibinhluan", UserComment);
        values.put("noidung", Content);
        if(db.insert(TABLE_BINHLUAN, null, values)!= -1){
        }else{
            Toast.makeText(context, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", "hd01");
        values.put("tenphim", "Biệt đội đánh thuê");
        values.put("daodien", "Sylvester Stallone");
        values.put("dienvien", "Lý Liên Kiệt, Sylvester Stallone, Jason Statham, Jet Li, Dolph Lundgren");
        values.put("danhgia", "4");
        values.put("tomtat", "Bộ phim là cuộc chiến của một nhóm lính đánh thuê, nhằm vào Nam Mỹ để hoàn thành nhiệm vụ " +
                "là lật đổ một nhà độc tài tại đây. Nhà độc tài là tướng quân Garza. Dù là tướng quân nhưng ông lại bị một " +
                "cưu điệp viên CIA  thao túng và ép phải ủng hộ cho việc sản xuất thuốc phiện.Khi nhóm đánh thuê do Barney Ross " +
                "chỉ huy được chính CIA nhờ tiêu diệt cựu điệp viên xấu Paine thì Ross đã biết nhiệm vụ lần này vô cùng nguy hiểm. " +
                "Anh đã có thể dừng lại nhưng sự ám ảnh về cô gái có bức vẽ tuyệt vời Sandra đã đẩy anh và nhóm đến một cuộc chiến " +
                "đầy cam go.");
        values.put("theloai", "hd");
        values.put("url", "https://www.youtube.com/watch?v=0qjxbz7cBmU");


        if(db.insert(TABLE_PHIM, null, values)!= -1){
            Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
        db.close();
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("maphim", "hd01");
        values2.put("nguoibinhluan", "User A");
        values2.put("noidung", "phim rất hay!!!");
        if(db2.insert(TABLE_BINHLUAN, null, values2)!= -1){
            Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
        db2.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE binhluan (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,maphim TEXT, nguoibinhluan TEXT, noidung TEXT)";
        db.execSQL(sql);
        sql="CREATE TABLE lichsusearch (keyword TEXT PRIMARY KEY)";
        db.execSQL(sql);
        sql="CREATE TABLE phim (id TEXT primary key,tenphim TEXT,daodien TEXT,dienvien TEXT,danhgia TEXT,tomtat TEXT,theloai TEXT,url TEXT)";
        db.execSQL(sql);

        Data dt = new Data();
        sql = dt.createDataComment1();
        db.execSQL(sql);
        sql = dt.createDataComment2();
        db.execSQL(sql);
        sql = dt.createDataComment3();
        db.execSQL(sql);
        sql = dt.createDataComment4();
        db.execSQL(sql);
        sql = dt.createDataComment5();
        db.execSQL(sql);
        sql = dt.createDataComment6();
        db.execSQL(sql);
        sql = dt.createDataComment7();
        db.execSQL(sql);
        sql = dt.createDataComment8();
        db.execSQL(sql);
        sql = dt.createDataComment9();
        db.execSQL(sql);
        sql = dt.createDataComment10();
        db.execSQL(sql);
        sql = dt.createDataComment11();
        db.execSQL(sql);
        sql = dt.createDataComment12();
        db.execSQL(sql);
        sql = dt.createDataComment13();
        db.execSQL(sql);
        sql = dt.createDataComment14();
        db.execSQL(sql);
        sql = dt.createDataComment15();
        db.execSQL(sql);
        sql = dt.createDataComment16();
        db.execSQL(sql);
        sql = dt.createDataComment17();
        db.execSQL(sql);
        sql = dt.createDataComment18();
        db.execSQL(sql);
        sql = dt.createDataComment19();
        db.execSQL(sql);
        sql = dt.createDataComment20();
        db.execSQL(sql);
        sql = dt.createDataComment21();
        db.execSQL(sql);
        sql = dt.createDataComment22();
        db.execSQL(sql);
        sql = dt.createDataComment23();
        db.execSQL(sql);
        sql = dt.createDataComment24();
        db.execSQL(sql);
        sql = dt.createDataComment25();
        db.execSQL(sql);
        sql = dt.createDataComment26();
        db.execSQL(sql);

        sql = dt.createDataMovie1();
        db.execSQL(sql);
        sql = dt.createDataMovie2();
        db.execSQL(sql);
        sql = dt.createDataMovie3();
        db.execSQL(sql);
        sql = dt.createDataMovie4();
        db.execSQL(sql);
        sql = dt.createDataMovie5();
        db.execSQL(sql);
        sql = dt.createDataMovie6();
        db.execSQL(sql);
        sql = dt.createDataMovie7();
        db.execSQL(sql);
        sql = dt.createDataMovie8();
        db.execSQL(sql);
        sql = dt.createDataMovie9();
        db.execSQL(sql);
        sql = dt.createDataMovie10();
        db.execSQL(sql);
        sql = dt.createDataMovie11();
        db.execSQL(sql);
        sql = dt.createDataMovie12();
        db.execSQL(sql);
        sql = dt.createDataMovie13();
        db.execSQL(sql);
        sql = dt.createDataMovie14();
        db.execSQL(sql);
        sql = dt.createDataMovie15();
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_PHIM);
        db.execSQL("drop table if exists" + TABLE_BINHLUAN);
        onCreate(db);
    }
}
