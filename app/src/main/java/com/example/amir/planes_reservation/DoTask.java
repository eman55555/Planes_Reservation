package com.example.amir.planes_reservation;

/**
 * Created by amir on 08/08/16.
 */
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class DoTask extends AsyncTask<String,Void,String>
{
  static String json_string;
    String jsonResponse;

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url1=new URL(params[0]);
            HttpURLConnection urlConnection= (HttpURLConnection) url1.openConnection();
            InputStream inputStream=urlConnection.getInputStream();
            //buffer reader
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            while ((jsonResponse=bufferedReader.readLine())!=null)
            {

                stringBuilder.append(jsonResponse+"\n");
            }

            bufferedReader.close();
            inputStream.close();
            urlConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //  txtResponse.setText(s);
        json_string=s;
    }
}
