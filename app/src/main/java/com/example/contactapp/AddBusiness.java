package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddBusiness extends AppCompatActivity
{
    Button bt_busOk, bt_busCancel;
    EditText et_name, et_phone, et_streetNum, et_streetName, et_city, et_state, et_hours, et_URL;
    EditText et_picNum, et_picURL, et_picDate, et_picDesc;
    TextView tv_idNum;
    int id;

    AddressBook contactList;
    MyApplication myApplication = (MyApplication) this.getApplication();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        contactList = myApplication.getContactList();

        bt_busOk = (Button) findViewById(R.id.bt_busOk);
        bt_busCancel = (Button) findViewById(R.id.bt_busCancel);

        et_name = (EditText) findViewById(R.id.et_busName);
        et_phone = (EditText) findViewById(R.id.et_busPhone);
        et_streetNum = (EditText) findViewById(R.id.et_busLocNum);
        et_streetName = (EditText) findViewById(R.id.et_busLocSt);
        et_city = (EditText) findViewById(R.id.et_busLocCity);
        et_state = (EditText) findViewById(R.id.et_busLocSt);
        et_hours = (EditText) findViewById(R.id.et_busHours);
        et_URL = (EditText) findViewById(R.id.et_busURL);

        et_picNum = (EditText)findViewById(R.id.et_busPicNum);
        et_picURL = (EditText)findViewById(R.id.et_busPicURL);
        et_picDate = (EditText)findViewById(R.id.et_busPicDate);
        et_picDesc = (EditText)findViewById(R.id.et_busPicDesc);

        tv_idNum = (TextView) findViewById(R.id.tv_idNum);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        Contact contact = null;

        if (id >= 0)
        {
            if (contactList.byIDNum(id) != null)
            {
                contact = contactList.byIDNum(id);
            }
            tv_idNum.setText(String.valueOf(id));
            et_name.setText(contact.getName());
            et_phone.setText((contact.getPhone()));
            et_URL.setText(((BusinessContact) contact).getWebURL());
            et_hours.setText(((BusinessContact)contact).getBusinessHours());
            et_streetNum.setText(String.valueOf(((BusinessContact) contact).getLocation().getLocIDNum()));
            et_streetName.setText(((BusinessContact) contact).getLocation().getStreet());
            et_state.setText(((BusinessContact) contact).getLocation().getState());
            et_city.setText(((BusinessContact) contact).getLocation().getCity());
            et_picNum.setText(String.valueOf(((BusinessContact) contact).getListOfPhotos().get(0).getPhotoIDNum()));
            et_picDesc.setText(((BusinessContact) contact).getListOfPhotos().get(0).getDescription());
            et_picURL.setText(((BusinessContact) contact).getListOfPhotos().get(0).getFileName());
            et_picDate.setText(((BusinessContact) contact).getListOfPhotos().get(0).getDateOfPhoto());

            //add loc and pic info to text
        }
        else
        {

        }

        bt_busOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (id >= 0)
                {
                    //edit current contact
                    String name = et_name.getText().toString();
                    String number = et_phone.getText().toString();
                    //String loc = et_loc.getText().toString(); //break up to create loc item
                    String URL = et_URL.getText().toString();
                    String hours = et_hours.getText().toString();

                    Location loc = new Location(Integer.parseInt(et_streetNum.getText().toString()), et_streetName.getText().toString(), et_city.getText().toString(), et_state.getText().toString());

                    Photo pic = new Photo(Integer.parseInt(et_picNum.getText().toString()), et_picURL.getText().toString(), et_picDate.getText().toString(), et_picDesc.getText().toString());

                    ArrayList<Photo> listOfPics = new ArrayList<Photo>();

                    listOfPics.add(pic);

                    BusinessContact updateBusiness = new BusinessContact(id, name, number, listOfPics, loc, URL, hours);
                    contactList.set(id, updateBusiness);
                }
                else
                {
                    //create new person
                    int nextId = myApplication.getNextId();
                    String name = et_name.getText().toString();
                    String number = et_phone.getText().toString();
                    //String loc = et_loc.getText().toString(); //break up to create loc item
                    String URL = et_URL.getText().toString();
                    String hours = et_hours.getText().toString();

                    Location loc = new Location(Integer.parseInt(et_streetNum.getText().toString()), et_streetName.getText().toString(), et_city.getText().toString(), et_state.getText().toString());

                    Photo pic = new Photo(Integer.parseInt(et_picNum.getText().toString()), et_picURL.getText().toString(), et_picDate.getText().toString(), et_picDesc.getText().toString());

                    ArrayList<Photo> listOfPics = new ArrayList<Photo>();

                    listOfPics.add(pic);

                    BusinessContact newBusiness = new BusinessContact(id, name, number, listOfPics, loc, URL, hours);

                    contactList.addContact(newBusiness);
                    myApplication.setNextId(nextId++);
                }

                Intent intent = new Intent(AddBusiness.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_busCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddBusiness.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
