package com.example.contactapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddPerson extends AppCompatActivity
{
    Button bt_personOk, bt_personCancel, bt_addPhoto;
    EditText et_name, et_phone, et_streetNum, et_streetName, et_city, et_state, et_DOB, et_description, et_relatives;
    EditText et_picNum, et_picURL, et_picDate, et_picDesc;
    TextView tv_idNum;
    int id;

    Button bt_call, bt_text, bt_email, bt_map;

    AddressBook contactList;
    MyApplication myApplication = (MyApplication) this.getApplication();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_main);

        contactList = myApplication.getContactList();

        bt_personOk = (Button) findViewById(R.id.bt_busOk);
        bt_personCancel = (Button) findViewById(R.id.bt_busCancel);

        et_name = (EditText) findViewById(R.id.et_busName);
        et_phone = (EditText) findViewById(R.id.et_busPhone);
        et_streetNum = (EditText) findViewById(R.id.et_busLocNum);
        et_streetName = (EditText) findViewById(R.id.et_busLocSt);
        et_city = (EditText) findViewById(R.id.et_busLocCity);
        et_state = (EditText) findViewById(R.id.et_busLocSt);
        et_DOB = (EditText) findViewById(R.id.et_busHours);
        et_description = (EditText) findViewById(R.id.et_personDescription);
        et_relatives = (EditText) findViewById(R.id.et_personRelatives);

        et_picNum = (EditText)findViewById(R.id.et_busPicNum);
        et_picURL = (EditText)findViewById(R.id.et_busPicURL);
        et_picDate = (EditText)findViewById(R.id.et_busPicDate);
        et_picDesc = (EditText)findViewById(R.id.et_busPicDesc);

        tv_idNum = (TextView) findViewById(R.id.tv_idNum);

        bt_call = (Button) findViewById(R.id.bt_call);
        bt_text = (Button) findViewById(R.id.bt_text);
        bt_email = (Button) findViewById(R.id.bt_email);
        bt_map = (Button) findViewById(R.id.bt_map);


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
            et_DOB.setText(((PersonContact) contact).getDateOfBirth());
            et_description.setText(((PersonContact)contact).getDescription());
            et_relatives.setText(String.valueOf(((PersonContact)contact).getListOfRelatives().get(0).getNumber()));
            et_streetNum.setText(String.valueOf(((PersonContact) contact).getLocation().getLocIDNum()));
            et_streetName.setText(((PersonContact) contact).getLocation().getStreet());
            et_state.setText(((PersonContact) contact).getLocation().getState());
            et_city.setText(((PersonContact) contact).getLocation().getCity());
            et_picNum.setText(String.valueOf(((PersonContact) contact).getListOfPhotos().get(0).getPhotoIDNum()));
            et_picDesc.setText(((PersonContact) contact).getListOfPhotos().get(0).getDescription());
            et_picURL.setText(((PersonContact) contact).getListOfPhotos().get(0).getFileName());
            et_picDate.setText(((PersonContact) contact).getListOfPhotos().get(0).getDateOfPhoto());

            //add loc and pic info to text
        }
        else
        {

        }

        bt_personOk.setOnClickListener(new View.OnClickListener()
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
                    String DOB = et_DOB.getText().toString();
                    String description = et_description.getText().toString();

                    ArrayList<Contact> relatives = new ArrayList();

                    int relative = Integer.parseInt(et_relatives.getText().toString());
                    if (contactList.byIDNum(relative) != null)
                    {
                        relatives.add(contactList.byIDNum(relative));
                    }
                    else
                    {

                    }

                    Location loc = new Location(Integer.parseInt(et_streetNum.getText().toString()), et_streetName.getText().toString(), et_city.getText().toString(), et_state.getText().toString());

                    Photo pic = new Photo(Integer.parseInt(et_picNum.getText().toString()), et_picURL.getText().toString(), et_picDate.getText().toString(), et_picDesc.getText().toString());

                    ArrayList<Photo> listOfPics = new ArrayList<Photo>();

                    listOfPics.add(pic);

                    PersonContact updateContact = new PersonContact(id, name, number, listOfPics, loc, DOB, description, relatives);

                    contactList.removeContact(contactList.byIDNum(id));
                    contactList.addContact(updateContact);
                    //contactList.set(id, updateContact);
                }
                else
                {
                    //create new person
                    int nextId = myApplication.getNextId();
                    String name = et_name.getText().toString();
                    String number = et_phone.getText().toString();
                    //String loc = et_loc.getText().toString(); //break up to create loc item
                    String DOB = et_DOB.getText().toString();
                    String description = et_description.getText().toString();

                    ArrayList<Contact> relatives = new ArrayList();

                    int relative = Integer.parseInt(et_relatives.getText().toString());
                    if (contactList.byIDNum(relative) != null)
                    {
                        relatives.add(contactList.byIDNum(relative));
                    }
                    else
                    {

                    }

                    Location loc = new Location(Integer.parseInt(et_streetNum.getText().toString()), et_streetName.getText().toString(), et_city.getText().toString(), et_state.getText().toString());

                    Photo pic = new Photo(Integer.parseInt(et_picNum.getText().toString()), et_picURL.getText().toString(), et_picDate.getText().toString(), et_picDesc.getText().toString());

                    ArrayList<Photo> listOfPics = new ArrayList<Photo>();

                    listOfPics.add(pic);

                    PersonContact newPerson = new PersonContact(nextId, name, number, listOfPics, loc, DOB, description, relatives);

                    contactList.addContact(newPerson);
                    myApplication.setNextId(nextId++);
                }

                Intent intent = new Intent(AddPerson.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_personCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddPerson.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(contactList.byIDNum(id).getPhone());

            }
        });

        bt_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(contactList.byIDNum(id).getPhone());

            }
        });

        bt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = "";
                composeEmail(addresses, "Hello from me");
            }
        });

        bt_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:00?q=" + contactList.byIDNum(id).getLocation().toString();
                Uri mapsuri = Uri.parse(mapsQuery);
                showMap(mapsuri);
            }
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", "Hello I would like to chat");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
