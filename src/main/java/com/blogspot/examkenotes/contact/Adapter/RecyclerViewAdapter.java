package com.blogspot.examkenotes.contact.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.examkenotes.contact.R;
import com.blogspot.examkenotes.contact.displayContact;
import com.blogspot.examkenotes.contact.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static Context context;
    private static List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    // Where to get the single card as viewholder Object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumbers());

    }

    // How many items?
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.textView2);
            phoneNumber = itemView.findViewById(R.id.textView3);
            iconButton = itemView.findViewById(R.id.imageView);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Contact contact = contactList.get(position);
            String name = contact.getName();
            String phone = contact.getPhoneNumbers();
            Integer id= contact.getId();
            Toast.makeText(context, "The position is "+String.valueOf(position) + " Name-" + name + " Phone-"+phone, Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(context, displayContact.class);
            myIntent.putExtra("Rname", name); //Optional parameters
            myIntent.putExtra("Rphone", phone); //Optional parameters
            myIntent.putExtra("Rid",id);
            context.startActivity(myIntent);
        }
    }
}