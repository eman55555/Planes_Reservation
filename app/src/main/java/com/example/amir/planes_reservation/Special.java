package com.example.amir.planes_reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;





public class Special extends ActionBarActivity {

    String Type ;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        btn1=(Button)findViewById(R.id.button3);
        btn2=(Button)findViewById(R.id.button4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_special, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Special(View view) {
     Type = "Special";

        Intent i =new Intent(this,travel.class);

        i.putExtra("Type",Type);

        startActivity(i);
    }

    public void Normal(View view) {
        Type = "Normal";

        Intent i =new Intent(this,travel.class);

        i.putExtra("Type",Type);

        startActivity(i);
    }
}
