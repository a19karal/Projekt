package com.example.projekt;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Wiki);
        Button button2 = findViewById(R.id.About);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Wiki.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view)
            {
                Intent intent2 = new Intent(MainActivity.this, About.class);
                startActivity(intent2);

            }
        });
    }
}