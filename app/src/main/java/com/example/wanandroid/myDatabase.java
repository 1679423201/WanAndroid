package com.example.wanandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库的创建
 */
public class myDatabase extends SQLiteOpenHelper{

    private Context mContext;

    /**
     * 定义一个字符串存储建表语句
     * 建立一个readText存储已读文章标题
     */
    private static final String CREATE_BOOK = "create table myTable("
            + "id integer primary key autoincrement,"
            + "readText text)";

    public myDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
