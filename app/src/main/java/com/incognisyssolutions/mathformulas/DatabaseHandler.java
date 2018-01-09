package com.incognisyssolutions.mathformulas;

/**
 * Created by CHARUL on 09-01-2018.
 */
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
public class DatabaseHandler extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "MathFormulasDB.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
