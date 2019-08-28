package com.example.amir.planes_reservation;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class go extends AppCompatActivity {

    String id;
    String day;
    String month;
    String year;
    String hours;
    String minute;
    String second;
    String f_class,s_class,no_class;
    String type,Airp,Dir;
    RadioGroup r1,r2;
    RadioButton rb1,rb2;
    String g;
    EditText numberOfTicket;
    int isselected1,isSelected2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        numberOfTicket = (EditText)findViewById(R.id.txtadd);
        id = getIntent().getExtras().getString("id");
        day = getIntent().getExtras().getString("day");
        month = getIntent().getExtras().getString("month");
        year = getIntent().getExtras().getString("year");
        hours = getIntent().getExtras().getString("hours");
        minute = getIntent().getExtras().getString("minute");
        second = getIntent().getExtras().getString("second");
        f_class =  getIntent().getExtras().getString("first_class");
        s_class =  getIntent().getExtras().getString("second_class");
        type = getIntent().getExtras().getString("type");
        Airp = getIntent().getExtras().getString("AirPrt");
        Dir = getIntent().getExtras().getString("Direct");
        r1 = (RadioGroup) findViewById(R.id.selectseat);
        r2 = (RadioGroup) findViewById(R.id.selectticket);//2131492952


        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                isselected1 = r1.getCheckedRadioButtonId();
                rb1 =(RadioButton)findViewById(isselected1);
                 g= rb1.getText().toString();

            }
        });
        r2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                isSelected2 = r2.getCheckedRadioButtonId();
                rb2 =(RadioButton)findViewById(isSelected2);

            }
        });

    }

    public void Done(View view)
    {
        int first = Integer.parseInt(f_class);
        int secnd = Integer.parseInt(s_class);


        if(g.equals("First Class"))
        {
            no_class ="1";
            first-=Integer.parseInt(numberOfTicket.getText().toString());
        }
        else
        {
            no_class="2";
            secnd-=Integer.parseInt(numberOfTicket.getText().toString());
        }

        Intent i =new Intent(go.this,saving.class);

        Bundle b =new Bundle();
        b.putString("id",id);
        b.putString("day",day);
        b.putString("month",month);
        b.putString("year",year);
        b.putString("hours",hours);
        b.putString("minute",minute);
        b.putString("second",second);
        b.putString("type",type);
        b.putString("first_class",String.valueOf(first));
        b.putString("second_class",String.valueOf(secnd));
        b.putString("AirPrt",Airp);
        b.putString("Direct",Dir);
        b.putString("num_tickt",numberOfTicket.getText().toString());
        b.putString("Ticket_type",rb2.getText().toString());
        b.putString("no_class",no_class);

        i.putExtras(b);

        startActivity(i);
    }
}
