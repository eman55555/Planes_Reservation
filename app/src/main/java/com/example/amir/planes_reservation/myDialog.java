package com.example.amir.planes_reservation;

/**
 * Created by amir on 09/08/16.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amir on 09/08/16.
 */
public class myDialog extends DialogFragment {
 String Data;
    RequestQueue queue;
    ProgressDialog progressDialog;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Data = getArguments().getString("data");

        queue = Volley.newRequestQueue(Cancel.context);
        progressDialog = new ProgressDialog(Cancel.context);
        progressDialog.setCancelable(false);


        builder.setMessage("Are You Sure ? ").setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                progressDialog.setMessage("Connect To Server");
                progressDialog.show();

                StringRequest RecStr = new StringRequest(Request.Method.POST,
                        "http://productapi.890m.com/phpproj/delete_client.php", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        Toast.makeText(Cancel.context,"Item Deleted",Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(Cancel.context, volleyError.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }){

                    @Override
                    protected Map<String, String> getParams(){

                        Map<String,String>params = new HashMap<String,String>();
                        params.put("id",Data);

                        return  params;
                    }
                };

                queue.add(RecStr);

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });

        return builder.create();
    }


}
