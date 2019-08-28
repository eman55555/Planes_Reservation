package com.example.amir.planes_reservation;

import android.app.*;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cancel extends AppCompatActivity {
EditText editText ;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        editText = (EditText)findViewById(R.id.editTxt);
        context = this;
    }

    public void enter(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        myDialog dialog =new myDialog();
        Bundle bundle =new Bundle();
        bundle.putString("data",editText.getText().toString());
        dialog.setArguments(bundle);


        dialog.show(fragmentManager,"MyDialog");


    }
}
