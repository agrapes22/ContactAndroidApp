package com.example.contactapp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application
{
    //private static AddressBook contactList = new AddressBook();
    private static int nextId = 9;

    private static AddressBook book = new AddressBook();
    private static FileAccessService service = new FileAccessService();
    //private static File file = new File("datadictionary.csv");

    private static String fileName = "datadictionary.csv";
    private static File file = new File(fileName);

    public MyApplication() throws IOException, CsvValidationException
    {
        //load(getApplicationContext());
        fillList(getApplicationContext());
    }

    private void fillList(Context context) throws IOException, CsvValidationException {
        service.readAllContacts(book, file, context);
    }

    public static AddressBook getContactList ()  {
        return book;
    }

    public static void setContactList(AddressBook contactList)
    {
        MyApplication.book = contactList;
    }

    public static int getNextId()
    {
        return nextId;
    }

    public static void setNextId(int nextId)
    {
        MyApplication.nextId = nextId;
    }

    public static void load(Context context) throws IOException, CsvValidationException
    {
        //service.testFile(context);
        service.readAllContacts(book, file, context);
    }

    public static void save(Context context) throws IOException
    {
        service.saveAllContacts(book, context);
    }

}

