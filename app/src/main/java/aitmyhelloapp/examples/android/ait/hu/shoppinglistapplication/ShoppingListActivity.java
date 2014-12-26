package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.adapter.ItemAdapter;
import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.ItemDataSource;
import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item;


public class ShoppingListActivity extends ListActivity {

    public static final int REQUEST_NEW_ITEM_CODE = 100;
    public static final int CONTEXT_ACTION_DELETE = 10;
    private ItemDataSource datasource;
    //private DatabaseHelper databaseHelper = null;
    //private Dao<item, Integer> itemDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        List<item> itemsToBuy = new ArrayList<item>();
        datasource = new ItemDataSource(this);
        datasource.open();

        itemsToBuy = datasource.getAllComments();

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), itemsToBuy);
        setListAdapter(adapter);

        registerForContextMenu(getListView());
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {

        ArrayList<item> newDelete = (ArrayList<item>) datasource.getAllComments();
        while(!newDelete.isEmpty())
        {
            datasource.deleteComment(newDelete.get(0));
            newDelete.remove(0);
        }
        Log.d("datasource","hahahh nothing is in the datasource");
        for (int i=0; i<getListAdapter().getCount();i++)
        {
            item Item = (item) getListAdapter().getItem(i);
            datasource.createItem(Item.getItemName(),Item.getCategory().getValue(),Item.getEstimatedPrice(),Item.getDescription(),Item.getifBought());
            Log.d("datasource","this is done with everything");
        }
        datasource.close();
        super.onStop();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //need to find the id and change it, probably a button that is touched
        if (id == R.id.action_new_item) {
            Intent i = new Intent();
            i.setClass(this, CreateItemActivity.class);
            startActivityForResult(i, REQUEST_NEW_ITEM_CODE);

            return true;
        }
        if(id==R.id.action_new_deleteAll)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            while(!getListAdapter().isEmpty()) {
                item Item = (aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item) getListAdapter().getItem(0);
                ((ItemAdapter) getListAdapter()).removeItem(0);
                datasource.deleteComment(Item);
            }
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                //what does this serializable do? Very confusing
                item Item = (item) data.getSerializableExtra("KEY_PLACE");
                ((ItemAdapter) getListAdapter()).addItem((item) data.getSerializableExtra("KEY_PLACE"));
                datasource.createItem(Item.getItemName(), Item.getCategory().getValue(), Item.getEstimatedPrice(),
                        Item.getDescription(), Item.getifBought());
                ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
                Toast.makeText(this, "Item added to the shopping list!", Toast.LENGTH_LONG).show();
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                break;
        }
    }
   @Override
    public boolean onContextItemSelected(MenuItem item) {
       Log.d("datasource","I do not think this is ever called");
        if (item.getItemId() == CONTEXT_ACTION_DELETE) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            item x = (aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item) getListAdapter().getItem(info.position);
            ((ItemAdapter) getListAdapter()).removeItem(info.position);
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
        } else {
            return false;
        }
        return true;
    }


}
