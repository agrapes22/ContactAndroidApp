/*
 * Interface for data access and writing
 */

package com.example.contactapp;

import android.content.Context;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataAccessService
{
	public void readAllContacts(AddressBook book, File file, Context context) throws FileNotFoundException, IOException, CsvValidationException;
	public void saveAllContacts(AddressBook book, Context context) throws IOException;
}
