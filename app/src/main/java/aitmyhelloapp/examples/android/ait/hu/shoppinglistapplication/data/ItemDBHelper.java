package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ItemTable.db";
    private static final int DATABASE_VERSION = 1;

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        ItemTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        ItemTable.onUpgrade(database, oldVersion, newVersion);
    }
}
