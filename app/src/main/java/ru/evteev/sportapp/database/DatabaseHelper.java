package ru.evteev.sportapp.database;

import static ru.evteev.sportapp.database.DatabaseDescription.Sport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DatabaseDescription.DATABASE_NAME, null, DatabaseDescription.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_SPORT_TABLE = "CREATE TABLE " + Sport.TABLE_NAME + " (" +
                Sport._ID + " integer primary key, " +
                Sport.COLUMN_NAME + " TEXT); ";

        db.execSQL(CREATE_SPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }
}
