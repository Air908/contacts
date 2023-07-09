package com.blogspot.examkenotes.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.examkenotes.contact.datahandler.Mydbhandler;

public class updateContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        MainActivity mainActivity = new MainActivity();
        Mydbhandler db = new Mydbhandler(mainActivity);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView= findViewById(R.id.editTextText);
                EditText text=findViewById(R.id.editTextPhone);

                String rrphone=text.getText().toString(); ;
                String rrname=textView.getText().toString();

                Intent myIntent = new Intent(mainActivity, displayContact.class);
                myIntent.putExtra("rrname", rrname); //Optional parameters
                myIntent.putExtra("rrphone", rrphone); //Optional parameters
                mainActivity.startActivity(myIntent);


            }
        });

    }
}