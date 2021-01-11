/*File access service class
 * Includes file and methods to write and read from file
 */
package com.example.contactapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class FileAccessService implements DataAccessService
{
	private static String fileName = "datadictionary.csv";
	//private static Context context;
	//private static String fname = context.getFilesDir().getPath().toString() + "/datadictionary.csv";
	private File thefile = new File(fileName);

	public FileAccessService()
	{
		
	}

	@Override
	public void readAllContacts(AddressBook book, File file, Context context) throws IOException, CsvValidationException {
		//File f = new File("datadictionary.csv");
		File thefile = new File(String.valueOf(R.raw.datadictionary));

		InputStream inStream = context.getResources().openRawResource(R.raw.datadictionary);

		InputStreamReader read = new InputStreamReader(context.getAssets().open("datadictionary.csv"));

		CSVReader reader = new CSVReader(read);

		Log.d("FILESAVE", "Opened the file sort of");

		String[] items = null;
		while ((items = reader.readNext()) != null)
		{
			//items = reader.readNext();
			//String[] items = line.split(",");

			int id = Integer.valueOf(items[0]);//id number
			String name = items[1];
			String phoneNum = items[2];
			String photos = items[3];
			String loc = items[4];
			String dob = items[5];
			String desc = items[6];
			String relatives = items[7];

			//break loc info out
			String[] locs = loc.split("\\|");
			int locID = Integer.valueOf(locs[0]);
			String street = locs[1];
			String city = locs[2];
			String state = locs[3];

			Location loc1 = new Location(locID, street, city, state);

			ArrayList<Photo> photoList = new ArrayList<Photo>();

			//break photo list out to seperate photos
			String[] photo = photos.split(" - ");
			String[] photoProps;
			for (int i = 0; i < photo.length; i++)
			{
				photoProps = photo[i].split("\\|");
				int photoID = Integer.valueOf(photoProps[0]);
				String photoName = photoProps[1];
				String dateOfPic = photoProps[2];
				String descPic = photoProps[3];
				photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
			}

			ArrayList<Contact> relativeList = new ArrayList();
			//relativeList.add(book.byName(relatives));

			book.addContact(new PersonContact(id, name, phoneNum, photoList, loc1, dob, desc, relativeList));

			//Log.d("FILESAVE", book.toString());

		}

		relatives(book, thefile, context);

		reader.close();
		read.close();

		//Log.d("FILESAVE", "Book: " + book.byIDNum(1).getLocation().toString());

		Log.d("FILESAVE", "Loaded");

		//book.sort();

		//in.close();

		//System.out.println("Contacts have been loaded from file");
		
	}

	@Override
	public void saveAllContacts(AddressBook book, Context context) throws IOException
	{
		//OutputStream outStream = context.getResources().openRawResource(R.raw.datadictionary);

		//OutputStreamWriter write = new OutputStreamWriter(context.getAssets().("datadictionary.csv"));

		//CSVWriter writer = new CSVWriter(read);

		try
		{
			File f = new File("datadictionary.csv");
			//FileWriter fw = new FileWriter(f, true);
			//BufferedWriter pw = new BufferedWriter(fw);

			if (thefile.exists())
			{

			}
			else
			{
				thefile.canRead();
				thefile.canWrite();
				thefile.createNewFile();
				thefile.mkdir();
			}

			CSVWriter writer = new CSVWriter(new FileWriter(thefile));

			for(Contact contact : book.getList())
			{
				String c = contact.toString();
				String[] eachContact = c.split("|");

				writer.writeNext(eachContact);
			}

			writer.close();
			//pw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Log.d("FILESAVE", e.getMessage());
			//System.out.println("Error: saveToFile");
		}
	}
	
	public void relatives(AddressBook book, File file, Context context) throws IOException, CsvValidationException {

		InputStream inStream = context.getResources().openRawResource(R.raw.datadictionary);

		InputStreamReader read = new InputStreamReader(context.getAssets().open("datadictionary.csv"));

		CSVReader reader = new CSVReader(read);

		//Scanner in = new Scanner(file);

		//in.useDelimiter(",");
		String[] items = null;
		while ((items = reader.readNext()) != null)
		{
			int id = Integer.valueOf(items[0]);
			String relatives = items[7];
			
			ArrayList<Contact> relativeList = new ArrayList();
			
			String[] rel = relatives.split("\\|");
			for (int i = 0; i < rel.length; i++)
			{
				relativeList.add(book.byName(rel[i]));
				//relativeList.add(book.byName(relatives));
			}
			
			//System.out.println(book.byIDNum(id));
			//System.out.println(relativeList.toString());
			
			((PersonContact) book.byIDNum(id)).setListOfRelatives(relativeList);
		}
	}

	public void testFile(Context context) throws IOException, CsvValidationException
	{
		//this works don't delete
		//String fName = context.getFilesDir().getPath().toString() + "/newfile.csv";
		//File f = new File(fName);

		InputStream inStream = context.getResources().openRawResource(R.raw.datadictionary);

		InputStreamReader reader = new InputStreamReader(context.getAssets().open("datadictionary.csv"));

		//Log.d("FILESAVE", "Exists: " + f.exists());

		CSVReader read1 = new CSVReader(reader);
		String [] nextLine;
		while ((nextLine = read1.readNext()) != null)
		{
			Log.d("FILESAVE", nextLine[0] + " " + nextLine[1] + " " + nextLine[2]);
			//System.out.println(nextLine[0] + nextLine[1] + nextLine[2]);
		}
		Log.d("FILESAVE", "Read from file");
	 }

}

