package com.example.amir.planes_reservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class saving extends ActionBarActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    String id,day,month,year,hours,minute,second;
    String f_class,s_class,no_class,number;
    String type,Airp,Dir,ticket_type,s;
    static boolean ok=false,ok1=false,ok2=false;
    EditText name;
    ProgressDialog progressDialog;
    RequestQueue queue,requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        name=(EditText)findViewById(R.id.editText2);
        id = getIntent().getExtras().getString("id");
        day = getIntent().getExtras().getString("day");
        month = getIntent().getExtras().getString("month");
        year = getIntent().getExtras().getString("year");
        hours = getIntent().getExtras().getString("hours");
        minute = getIntent().getExtras().getString("minute");
        second = getIntent().getExtras().getString("second");
        f_class = getIntent().getExtras().getString("first_class");
        s_class = getIntent().getExtras().getString("second_class");
        type = getIntent().getExtras().getString("type");
        Airp = getIntent().getExtras().getString("AirPrt");
        Dir = getIntent().getExtras().getString("Direct");
        number = getIntent().getExtras().getString("num_tickt");
        no_class = getIntent().getExtras().getString("no_class");
        ticket_type = getIntent().getExtras().getString("Ticket_type");
        queue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saving, menu);
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

    public void Save(View view) {
        if(!ok)
        queue.add(update_plane());
        if(!ok1)
        requestQueue.add(Save_client());
        if(ok1)
          s = return_id();

        if(ok&&ok1&&s!=null)
            Toast.makeText(getBaseContext(), "you Reserve and your id is "+s, Toast.LENGTH_LONG).show();
        else
        Toast.makeText(getBaseContext(), "Test your Internet Connection", Toast.LENGTH_LONG).show();




    }
    public StringRequest update_plane()
    {

        StringRequest RecStr = new StringRequest(Request.Method.POST,
                "http://productapi.890m.com/phpproj/update_planes.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();

                ok=true;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                /*Toast.makeText(getApplicationContext(), volleyError.getMessage(),Toast.LENGTH_LONG).show();*/
                ok=false;

            }
        }){

            @Override
            protected Map<String, String> getParams(){

                Map<String,String>params = new HashMap<String,String>();
                params.put("id",id);
                params.put("type",type);
                params.put("day",day);
                params.put("month",month);
                params.put("year",year);
                params.put("hours",hours);
                params.put("minute",minute);
                params.put("second",second);
                params.put("first_class",f_class);
                params.put("second_class",s_class);
                params.put("AirPort",Airp);
                params.put("Direction",Dir);


                return  params;
            }
        };

        return RecStr;

    }
    public StringRequest Save_client()
    {



        StringRequest RecSt = new StringRequest(Request.Method.POST,
                "http://productapi.890m.com/phpproj/sent_client.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();

                ok1=true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();

//                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();

                ok1=false;
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("name", name.getText().toString());
                params.put("ticket_type", ticket_type);
                params.put("NumberOfTicket",number);
                params.put("Plane_ID", id);
                params.put("class", no_class);
                return params;
            }
        };

        return RecSt;
    }
    public String return_id()
    {
        String _id=null;
        DoTask task=new DoTask();
        task.execute("http://productapi.890m.com/phpproj/JSON_client.php");
        if (task.json_string==null)
        {
            Toast.makeText(getApplicationContext(),"Test Your Internet connection ",Toast.LENGTH_LONG).show();
            ok2=false;
        }
        else
        {
            try {
                jsonObject = new JSONObject(task.json_string);

                jsonArray =jsonObject.getJSONArray("Client");

                JSONObject jo = jsonArray.getJSONObject(0);

                _id = jo.getString("id");

            }catch (Exception e)
            {
                e.getStackTrace();
            }

            ok2=true;
        }

        return _id;
    }
}
