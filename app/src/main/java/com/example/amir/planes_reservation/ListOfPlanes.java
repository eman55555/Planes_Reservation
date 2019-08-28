package com.example.amir.planes_reservation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListOfPlanes extends AppCompatActivity {

    ListView lv ;

    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> day = new ArrayList<String>();
    ArrayList<String> month = new ArrayList<String>();
    ArrayList<String> year = new ArrayList<String>();
    ArrayList<String> hours = new ArrayList<String>();
    ArrayList<String> minute = new ArrayList<String>();
    ArrayList<String> second = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> first_class = new ArrayList<String>();
    ArrayList<String> second_class = new ArrayList<String>();
    ArrayList<String> AirPrt = new ArrayList<String>();
    ArrayList<String> Direct = new ArrayList<String>();
    JSONObject jsonObject;
    JSONArray jsonArray;
    String Type_plane,AirPort,Direction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_planes);

        //Request data from api use http url connection
        String JSON_String= getIntent().getExtras().getString("data");
         Type_plane = getIntent().getExtras().getString("TypeOfPlane");
         AirPort = getIntent().getExtras().getString("AirPort");
         Direction = getIntent().getExtras().getString("Direction");


            try {

                jsonObject = new JSONObject(JSON_String);

                jsonArray =jsonObject.getJSONArray("Planes");

                int count = 0;


                while (count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);

                    if ((jo.getString("type")).equals(Type_plane)&&(jo.getString("AirPort")).equals(AirPort)&&(jo.getString("Direction")).equals(Direction)
                            &&(((jo.getInt("first_class"))!=0 )||((jo.getInt("second_class"))!=0 ))) {
                        id.add(jo.getString("id"));
                        day.add(jo.getString("day"));
                        month.add(jo.getString("month"));
                        year.add(jo.getString("year"));
                        hours.add(jo.getString("hours"));
                        minute.add(jo.getString("minute"));
                        second.add(jo.getString("second"));
                        type.add(jo.getString("type"));
                        first_class.add(jo.getString("first_class"));
                        second_class.add(jo.getString("second_class"));
                        AirPrt.add(jo.getString("AirPort"));
                        Direct.add(jo.getString("Direction"));

                    }

                    count++;
                }

                CustomAdapter adapter = new CustomAdapter(this,id,day,month,year,hours,minute,second);

                lv = (ListView) findViewById(R.id.listView);
                lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idd)      {


                Intent i =new Intent(ListOfPlanes.this,go.class);

                Bundle b =new Bundle();
                b.putString("id",id.get(position));
                b.putString("day",day.get(position));
                b.putString("month",month.get(position));
                b.putString("year",year.get(position));
                b.putString("hours",hours.get(position));
                b.putString("minute",minute.get(position));
                b.putString("second",second.get(position));
                b.putString("type",type.get(position));
                b.putString("first_class",first_class.get(position));
                b.putString("second_class",second_class.get(position));
                b.putString("AirPrt",AirPrt.get(position));
                b.putString("Direct",Direct.get(position));


                i.putExtras(b);

                startActivity(i);

            }

        });

            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
