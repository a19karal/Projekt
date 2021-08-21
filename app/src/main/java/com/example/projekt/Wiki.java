package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Wiki extends AppCompatActivity
{
    ArrayAdapter<String> adapter;
    private ArrayList<String> charname=new ArrayList<String>();
    private  ArrayList<String>charloc=new ArrayList<String>();
    private  ArrayList<String> charcomp=new ArrayList<String>();
    private  ArrayList<Characters> charactersArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        adapter=new ArrayAdapter<String>(Wiki.this,R.layout.list_item_textview,R.id.list_item_textview_xml,charname);
        Button close = findViewById(R.id.Back);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}