package com.example.contactapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>
{
    AddressBook contactList;
    Context context;

    public RecycleViewAdapter(AddressBook contactList, Context context)
    {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_line, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Log.d("LOGSAVEINFO", contactList.getList().get(position).getName());
        holder.tv_name.setText(contactList.getList().get(position).getName());
        holder.tv_phone.setText(contactList.getList().get(position).getPhone());
        holder.tv_loc.setText(contactList.getList().get(position).getLocation().toString());
        Glide.with(this.context).load(contactList.getList().get(position).getListOfPhotos().get(0).getFileName()).into(holder.iv_pic);

        holder.parentLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (contactList.getList().get(position).getClass() == PersonContact.class)
                {
                    Intent intent = new Intent(context, AddPerson.class);
                    intent.putExtra("id", contactList.getList().get(position).getNumber());
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, AddBusiness.class);
                    intent.putExtra("id", contactList.getList().get(position).getNumber());
                    context.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return contactList.getList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_pic;
        TextView tv_name, tv_phone, tv_loc;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_picture);
            tv_name = itemView.findViewById(R.id.tv_nameOneLine);
            tv_phone = itemView.findViewById(R.id.tv_phoneOneLine);
            tv_loc = itemView.findViewById(R.id.tv_locationOneLine);
            parentLayout = itemView.findViewById(R.id.oneContactLineLayout);
        }
    }
}
