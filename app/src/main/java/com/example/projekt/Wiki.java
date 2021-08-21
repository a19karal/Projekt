package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ArrayList;

public class Wiki extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private ArrayList<String> charname = new ArrayList<String>();
    private ArrayList<String> charloc = new ArrayList<String>();
    private ArrayList<String> charcomp = new ArrayList<String>();
    private ArrayList<Characters> charactersArrayList = new ArrayList<>();

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        adapter = new ArrayAdapter<String>(Wiki.this, R.layout.list_item_textview, R.id.item_textview_xml, charname);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        Button close = findViewById(R.id.Back);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), charname.get(i) + " lives in " + charloc.get(i) + " and works for the  " + charcomp.get(i) + " clan.", Toast.LENGTH_LONG).show();
            }
        });

        textView = findViewById(R.id.textView);
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19karal");
    }

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19karal");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                JSONArray jsonAr = new JSONArray(json);
                for (int i = 0; i<jsonAr.length(); i++){
                    JSONObject jsonObj = jsonAr.getJSONObject(i);
                    String name = jsonObj.getString("name");
                    String location = jsonObj.getString("location");
                    String company = jsonObj.getString("company");
                    charname.add(name);
                    charloc.add(location);
                    charcomp.add(company);
                }
                adapter.notifyDataSetChanged();
            }
            catch (JSONException e){
                Log.d("a19karal",e.getLocalizedMessage());
            }
        }
    }
}
