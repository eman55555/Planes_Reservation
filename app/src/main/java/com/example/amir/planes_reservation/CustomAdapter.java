package com.example.amir.planes_reservation;

/**
 * Created by amir on 08/08/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amir on 25/07/16.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> day = new ArrayList<String>();
    ArrayList<String> month = new ArrayList<String>();
    ArrayList<String> year = new ArrayList<String>();
    ArrayList<String> hours = new ArrayList<String>();
    ArrayList<String> minute = new ArrayList<String>();
    ArrayList<String> second = new ArrayList<String>();
    TextView t1,t2,t3,t4,t5,t6,t7;
    Context con;
    private static LayoutInflater inflater=null;
    public CustomAdapter(ListOfPlanes mainActivity, ArrayList<String> id, ArrayList<String> day, ArrayList<String> month,
                         ArrayList<String> year, ArrayList<String> hours, ArrayList<String> minute, ArrayList<String> second )
    {

        this.id=id;
        this.day=day;
        this.month=month;
        this.year=year;
        this.hours=hours;
        this.minute=minute;
        this.second=second;
        con = mainActivity;
        inflater = ( LayoutInflater )con.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return id.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View listaView;
        listaView = inflater.inflate(R.layout.list_planes, null);
        t1=(TextView) listaView.findViewById(R.id.textType);
        t2=(TextView) listaView.findViewById(R.id.textDay);
        t3=(TextView) listaView.findViewById(R.id.textMonth);
        t4=(TextView) listaView.findViewById(R.id.textYear);
        t5=(TextView) listaView.findViewById(R.id.textHours);
        t6=(TextView) listaView.findViewById(R.id.textMunite);
        t7=(TextView) listaView.findViewById(R.id.textsecond);
        t1.setText("plane No."+id.get(i));
        t2.setText("Date >>> "+day.get(i)+" / ");
        t3.setText(month.get(i)+" / ");
        t4.setText(year.get(i));
        t5.setText("Time >>> "+hours.get(i)+" : ");
        t6.setText(minute.get(i)+" : ");
        t7.setText(second.get(i));
        return listaView;
    }
}

