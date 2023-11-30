package ru.evteev.sportapp.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evteev.la2.R;
import ru.evteev.sportapp.database.DatabaseDescription.Sport;

public class SportContentProvider extends ContentProvider {
    private DatabaseHelper dbHelper;
    private static final int ONE_SPORT = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int SPORTS = 2;

    static {
        uriMatcher.addURI(DatabaseDescription.AUTHORITY, Sport.TABLE_NAME + "/#", ONE_SPORT);
        uriMatcher.addURI(DatabaseDescription.AUTHORITY, Sport.TABLE_NAME, SPORTS);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Sport.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case ONE_SPORT:
                queryBuilder.appendWhere(Sport._ID + "=" + uri.getLastPathSegment());
                break;
            case SPORTS:

                break;
            default:
                throw new UnsupportedOperationException(getContext().getString(R.string.invalid_query_uri) + " " + uri);
        }

        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri newSportUri = null;

        switch (uriMatcher.match(uri)) {
            case SPORTS:
                long rowId = dbHelper.getWritableDatabase().insert(Sport.TABLE_NAME, null, values);
                if(rowId > 0) {
                    newSportUri = Sport.buildSportUri(rowId);
                    getContext().getContentResolver().notifyChange(uri, null);
                } else {
                    throw new SQLException(getContext().getString(R.string.insert_failed) + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException(getContext().getString(R.string.invalid_insert_uri) + uri);
        }

        return newSportUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numberOfRowsDeleted;

        switch (uriMatcher.match(uri)) {
            case ONE_SPORT:
                String id = uri.getLastPathSegment();
                numberOfRowsDeleted = dbHelper.getWritableDatabase().delete(Sport.TABLE_NAME, Sport._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(getContext().getString(R.string.invalid_delete_uri) + uri);
        }

        if (numberOfRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numberOfRowsUpdated;

        switch (uriMatcher.match(uri)) {
            case ONE_SPORT:
                String id = uri.getLastPathSegment();
                numberOfRowsUpdated = dbHelper.getWritableDatabase().update(Sport.TABLE_NAME, values, Sport._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(getContext().getString(R.string.invalid_update_uri) + uri);
        }

        if(numberOfRowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsUpdated;
    }
}
