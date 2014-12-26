package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by xinyunxing on 10/17/2014.
 */
public class ItemTable {
    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMNAME = "name";
    public static final String COLUMN_CATEGORIES_VALUE = "value";
    //public static final String COLUMN_CATEGORIES_ICONID= "id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_STATUS = "status";

    private static final String DATABASE_CREATE = "create table " + TABLE_ITEM
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ITEMNAME + " text not null, " +
            COLUMN_CATEGORIES_VALUE+" int, "+COLUMN_PRICE+" text not null, "+COLUMN_DESC
            + " text not null, " +COLUMN_STATUS+" bool"+ ");";



    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(ItemTable.class.getName(), "Upgradingfrom version " + oldVersion
                + " to " + newVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(database);
    }

}
