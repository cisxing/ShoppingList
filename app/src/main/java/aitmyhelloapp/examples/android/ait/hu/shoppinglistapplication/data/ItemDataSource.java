package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinyunxing on 10/17/2014.
 */
public class ItemDataSource {
    // Database fields
    private SQLiteDatabase database;
    private ItemDBHelper dbHelper;
    private String[] allColumns = { ItemTable.COLUMN_ID,
            ItemTable.COLUMN_ITEMNAME, ItemTable.COLUMN_CATEGORIES_VALUE,
            ItemTable.COLUMN_PRICE,
            ItemTable.COLUMN_DESC,ItemTable.COLUMN_STATUS };


    public ItemDataSource(Context context) {
        dbHelper = new ItemDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public item createItem(String aName, int aValue, String aPrice,String aDesc
    ,Boolean astatus) {
        Log.d("datasource","name is"+aName+"value is "+aValue+"price is"+aPrice+ "description is"+aDesc
        +"status is "+astatus);
        ContentValues values = new ContentValues();
        values.put(ItemTable.COLUMN_ITEMNAME, aName);
        values.put(ItemTable.COLUMN_CATEGORIES_VALUE, aValue);
        //values.put(ItemTable.COLUMN_CATEGORIES_ICONID, aIconId);
        values.put(ItemTable.COLUMN_PRICE, aPrice);
        values.put(ItemTable.COLUMN_DESC, aDesc);
        values.put(ItemTable.COLUMN_STATUS, astatus);
        open();
        long insertId = database.insert(ItemTable.TABLE_ITEM, null,
                values);
        Log.d("datasource","the id number is"+insertId);
        Cursor cursor = database.query(ItemTable.TABLE_ITEM,
                allColumns, ItemTable.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        item newItem = cursorToItem(cursor);
       Log.d("datasource",""+"the new item's id"+newItem.getId());
        cursor.close();
        return newItem;
    }

    private item cursorToItem(Cursor cursor) {
        item Item = new item(item.Category.fromInt(cursor.getInt(2)),cursor.getString(1),
                cursor.getString(4),cursor.getString(3));
        Item.setifBought(cursor.getInt(5)>0);
        Item.setId(cursor.getLong(0));
        return Item;
    }

    public void deleteComment(item Item) {
        long id = Item.getId();
        //Todo: fix this column id thing
        database.delete(ItemTable.TABLE_ITEM, ItemTable.COLUMN_ID
                + " = " + id, null);
    }

    public List<item> getAllComments() {
        open();
        List<item> items = new ArrayList<item>();

        Cursor cursor = database.query(ItemTable.TABLE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item Item = cursorToItem(cursor);
            items.add(Item);
            cursor.moveToNext();
        }
        // Ne felejtsük bezárni!
        cursor.close();
        return items;
    }
}
