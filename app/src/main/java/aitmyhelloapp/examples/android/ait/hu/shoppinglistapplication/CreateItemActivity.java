package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item;


public class CreateItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        //setContentView(R.layout.activity_create_place_to_visit);

        final Spinner spinnerPlaceType = (Spinner) findViewById(R.id.spinnerItemType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemtypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlaceType.setAdapter(adapter);

        final EditText etItemName = (EditText) findViewById(R.id.etItemName);
        final EditText etItemDesc = (EditText) findViewById(R.id.etDesc);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra("KEY_PLACE",
                        new item(item.Category.fromInt(spinnerPlaceType.getSelectedItemPosition())
                                ,etItemName.getText().toString(),etItemDesc.getText().toString(),
                                etPrice.getText().toString()));
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
    }



}
