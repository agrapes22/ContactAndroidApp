package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    Button bt_addPerson, bt_addBusiness;
    Menu menu;
    MyApplication myApplication = (MyApplication) this.getApplication();

    AddressBook contacts;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(myApplication, "File loaded correct", Toast.LENGTH_LONG).show();

        try {
            myApplication.load(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        contacts = myApplication.getContactList();


        Log.d("LOGSAVEMAIN", String.valueOf(contacts.getList().size()));

        bt_addPerson = (Button) findViewById(R.id.bt_addPerson);
        bt_addBusiness = (Button) findViewById(R.id.bt_addBusiness);

        bt_addPerson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivity(intent);
            }
        });

        bt_addBusiness.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AddBusiness.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.lv_contactList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(contacts, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_aToz:
                //sort a to z
                Collections.sort((List<Contact>)contacts, Contact.ContactNameAZComparator);
                Toast.makeText(MainActivity.this, "Sort A to Z", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_zToa:
                //sort z to a
                Collections.sort((List<Contact>)contacts, Contact.ContactNameZAComparator);
                Toast.makeText(MainActivity.this, "Sort Z to A", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_idAsc:
                //sort date ascending
                Collections.sort((List<Contact>)contacts, Contact.ContactIdAscComparator);
                Toast.makeText(MainActivity.this, "Sort Date ascending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_idDesc:
                //sort date descending
                Collections.sort((List<Contact>)contacts, Contact.ContactIdDescComparator);
                Toast.makeText(MainActivity.this, "Sort Date descending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        try {
            MyApplication.save(this);
            //Toast.makeText(myApplication, "File saved correct", Toast.LENGTH_LONG).show();
            Log.d("FILESAVEINFO", "Saved correctly");

        } catch (IOException e) {
            //Toast.makeText(myApplication, "Not saved correctly", Toast.LENGTH_LONG).show();
            Log.d("FILESAVEINFO", "This didn't save right...");
        }
    }
}