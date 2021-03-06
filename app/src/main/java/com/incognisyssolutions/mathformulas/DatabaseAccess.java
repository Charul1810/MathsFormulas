package com.incognisyssolutions.mathformulas;

/**
 * Created by CHARUL on 09-01-2018.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHandler(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getCategories() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM CategoriesTbl", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //list.add(Integer.parseInt(cursor.getString(0)));
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getSubCategories(int id) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from CategoriesTbl c LEFT JOIN FormulasTbl f ON c.cat_id=f.cat_id where c.cat_id="+id+"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //list.add(Integer.parseInt(cursor.getString(0)));
            //list.add(cursor.getString(0));
            list.add(cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getTopics() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM FormulasTbl", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //list.add(Integer.parseInt(cursor.getString(0)));
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getFormula(String s) {

        String query = "select * from FormulasTbl where f_title='" + s + "'";
        Cursor cursor = database.rawQuery(query, null);
        String formula = null;
        if (cursor != null && cursor.moveToFirst())
        {
            formula = cursor.getString(cursor.getColumnIndex("f_url"));
            //cursor.close();
        }
        //cursor.moveToFirst();
        // cursor.moveToNext();
        //String formula =cursor.getString( cursor.getColumnIndex("f_url"));
        cursor.close();
        return formula;

    }

    // Getting contacts Count
//    public int getCount() {
//        String countQuery = "SELECT * FROM CategoriesTbl";
//        Cursor cursor = database.rawQuery(countQuery, null);
//
//        // return count
//        return cursor.getCount();
//    }
}
