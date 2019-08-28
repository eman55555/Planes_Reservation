package com.example.amir.planes_reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class travel extends ActionBarActivity {

      Spinner spinner1,spinner2;
    String s1,s2,type;

    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        type = getIntent().getExtras().getString("Type");
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter = ArrayAdapter.createFromResource(this,R.array.leaving,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"is selected",Toast.LENGTH_LONG).show();

                //Toast.makeText(getBaseContext(), adapter.getItem(position) + "is selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = ArrayAdapter.createFromResource(this,R.array.destination,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"is selected",Toast.LENGTH_LONG).show();

               // Toast.makeText(getBaseContext(), adapter.getItem(position) + "is selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel, menu);
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

    public void OK(View view) {
        //to get the leaving city
        s1=spinner1.getSelectedItem().toString();
        //to get the destination
        s2=spinner2.getSelectedItem().toString();

        if(s1.equals("From")||s1.equals("To")||s2.equals("From")||s2.equals("To"))
            Toast.makeText(getBaseContext(),"Review your choices",Toast.LENGTH_LONG).show();
        else
        {
            DoTask task = new DoTask();
            task.execute("http://productapi.890m.com/phpproj/JSON_planes.php");
            if (task.json_string == null) {
                Toast.makeText(getApplicationContext(), "Test Your Internet connection ", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, ListOfPlanes.class);
                Bundle b = new Bundle();
                b.putString("data", task.json_string);
                b.putString("TypeOfPlane", type);
                b.putString("AirPort", s1);
                b.putString("Direction", s2);
                intent.putExtras(b);
                startActivity(intent);

            }
        }


    }
}
