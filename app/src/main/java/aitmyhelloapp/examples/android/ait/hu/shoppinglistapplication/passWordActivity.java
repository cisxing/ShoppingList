package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.R;
import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item;

public class passWordActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        final EditText password = (EditText) findViewById(R.id.setPassword);
        Button btn = (Button) findViewById(R.id.btnNewPassword);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                Intent intentResult = new Intent();
                intentResult.putExtra("ANSWER",pass);
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pass_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
