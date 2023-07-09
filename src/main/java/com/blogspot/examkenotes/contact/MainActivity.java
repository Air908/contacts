package com.blogspot.examkenotes.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.blogspot.examkenotes.contact.Adapter.RecyclerViewAdapter;
import com.blogspot.examkenotes.contact.datahandler.Mydbhandler;
import com.blogspot.examkenotes.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mydbhandler db = new Mydbhandler(MainActivity.this);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, addContact.class);
                startActivity(myIntent);
            }
        });

        Intent intent = getIntent();
        String rrname = intent.getStringExtra("rrname"); //if it's a string you stored.
        String rrphone = intent.getStringExtra("rrphone"); //if it's a string you stored.

        //Adding a contact to the database
        Contact rohit= new Contact();
        rohit.setPhoneNumbers(rrphone);
        rohit.setName(rrname);
        db.addContact(rohit);

        contactArrayList =new ArrayList<>();

        //get all contacts
        List<Contact> allContacts = db.getAllContacts();
        for(Contact contact: allContacts){
            Log.d("dbrohit", "ID:" + contact.getId() + "\n"+
                    "Name: " + contact.getName() + "\n" + "Phone Number: " + contact.getPhoneNumbers() + "\n" );
            contactArrayList.add(contact);
        }

        //Use recyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.d("dbrohit", "onCreate: Bro you have " + db.getCount()+ "countacts in your database");
    }
}