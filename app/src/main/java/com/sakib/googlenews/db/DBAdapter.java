package com.sakib.googlenews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx)
    {
        this.c=ctx;
        helper=new DBHelper(c);
    }

    //OPEN DB
    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE
    public void close()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    //INSERT DATA TO DB
    public long add(String title, String description, String img, String url)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Contants.TITLE,title);
            cv.put(Contants.DESCRIPTION, description);
            cv.put(Contants.IMAGE, img);
            cv.put(Contants.URL, url);
            return db.insert(Contants.TB_NAME,Contants.ROW_ID,cv);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    public void clearDatabase() {
        String clearDBQuery = "DELETE FROM "+Contants.TB_NAME;
        db.execSQL(clearDBQuery);
    }
    //RETRIEVE ALL
    public Cursor getAll()
    {
        String[] columns={Contants.ROW_ID,Contants.TITLE,Contants.DESCRIPTION,Contants.IMAGE,Contants.URL};

        return db.query(Contants.TB_NAME,columns,null,null,null,null,null);
    }

    //UPDATE
    public long UPDATE(int id, String name, String pos, String img, String url)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Contants.TITLE,name);
            cv.put(Contants.DESCRIPTION, pos);
            cv.put(Contants.IMAGE, img);
            cv.put(Contants.URL, url);

            return db.update(Contants.TB_NAME,cv,Contants.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //DELETE
    public long Delete(int id)
    {
        try
        {

            return db.delete(Contants.TB_NAME,Contants.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

}
