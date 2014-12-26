package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    final int ANSWER = 1;
    final String NEW_PASSWORD = "NEW_PASSWORD";
    final String PASS_WORD="PASSWORD";
    //final String passWord = "";
    private int NUMTRY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText password = (EditText) findViewById(R.id.password);
        //sp.getString(passWord,"");
        SharedPreferences sp = getSharedPreferences(NEW_PASSWORD,MODE_PRIVATE);
        Button logIn = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, passWordActivity.class);
                startActivityForResult(i, ANSWER);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(NEW_PASSWORD,MODE_PRIVATE);
                if (!sp.getString(PASS_WORD,"").equals("")) {
                    if (NUMTRY > 0) {
                        //if the username and password matches
                        if (password.getText().toString().equals(sp.getString(PASS_WORD,""))) {
                            Intent i = new Intent(LoginActivity.this, ShoppingListActivity.class);
                            startActivity(i);
                            finish();
                            //if they do not, then one less chance and give instructions
                        } else {
                            Toast.makeText(getBaseContext(), "Password is wrong, you have " + NUMTRY + " chances left", Toast.LENGTH_LONG).show();
                            NUMTRY--;
                        }
                    }
                    //password is typed wrongly for three times
                    else {
                        //finish the activity
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Password is not set up yet, please register",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(resultCode ==RESULT_OK) {
                SharedPreferences sp = getSharedPreferences(NEW_PASSWORD, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(PASS_WORD,data.getExtras().getString("ANSWER", ""));

                editor.commit();
            }
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
