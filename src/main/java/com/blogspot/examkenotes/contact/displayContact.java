package com.blogspot.examkenotes.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blogspot.examkenotes.contact.Adapter.RecyclerViewAdapter;
import com.blogspot.examkenotes.contact.datahandler.Mydbhandler;
import com.blogspot.examkenotes.contact.model.Contact;

public class displayContact extends AppCompatActivity {

    MainActivity main=new MainActivity();
    Mydbhandler db= new Mydbhandler(main);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        Intent intent = getIntent();
        String rname = intent.getStringExtra("Rname"); //if it's a string you stored.
        String rphone = intent.getStringExtra("Rphone"); //if it's a string you stored.
        Integer rid=   intent.getIntExtra("Rid",0);

        TextView textView=findViewById(R.id.textView4);
        textView.setText(rphone);

        TextView textView1=findViewById(R.id.textView5);
        textView1.setText(rname);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton=findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact= new Contact();
                contact.setId(rid);
                db.deleteContact(contact);
            }
        });
        ImageButton imageButton1=findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(main, updateContact.class);
                main.startActivity(myIntent);


                Intent intent = getIntent();
                String rrname = intent.getStringExtra("rrname"); //if it's a string you stored.
                String rrphone = intent.getStringExtra("rrphone"); //if it's a string you stored.
                Contact contact= new Contact();
                contact.setId(rid);
                contact.setName(rrname);
                contact.setPhoneNumbers(rrphone);
                int affectedRows = db.updateContact(contact);

                Log.d("dbrohit", "No. of  affected rows are: " + affectedRows );
            }
        });
    }
}