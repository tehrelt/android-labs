package ru.evteev.sportapp.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseDescription {
    public static final String AUTHORITY = "ru.evteev.sportapp.database";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static String DATABASE_NAME = "sport_db";
    public static int DATABASE_VERSION = 1;

    public static final class Sport implements BaseColumns {
        public static final String TABLE_NAME = "sport";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";

        public static Uri buildSportUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
