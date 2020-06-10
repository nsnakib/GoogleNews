package com.sakib.googlenews.db;

public class Contants {
    //COLUMNS
    static final String ROW_ID="id";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String IMAGE= "image";
    static final String URL= "url";

    //DB PROPERTIES
    static final String DB_NAME="NEWS_DB";
    static final String TB_NAME="NEWS_TB";
    static final int DB_VERSION='1';

   //CREATE TABLE STATEMENTS
    static final String CREATE_TB="CREATE TABLE NEWS_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT NOT NULL,description TEXT NOT NULL,image TEXT NOT NULL,url TEXT NOT NULL);";
}

