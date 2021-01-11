/*Person contact class
 * Inherits contact base class
 * Includes fields for date of birth, description, and a list of relatives
 * Includes methods to convert to one string and set fields
 */

package com.example.contactapp;

import java.util.ArrayList;
import java.util.Optional;

public class PersonContact extends Contact
{
	private String dateOfBirth;
	private String description;
	private ArrayList<Contact> listOfRelatives;
	private String relatives;
	
	public PersonContact(int number, String name, String phone, ArrayList<Photo> listOfPhotos,
			Location location, String dateOfBirth, String description, ArrayList<Contact> listOfRelatives)
	{
		super(number, name, phone, listOfPhotos, location);
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.listOfRelatives = listOfRelatives;
	}
	
	public PersonContact(int number, String name, String phone, ArrayList<Photo> listOfPhotos,
			Location location, String dateOfBirth, String description)
	{
		super(number, name, phone, listOfPhotos, location);
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		//this.listOfRelatives = listOfRelatives;
	}
	
	public String toString()
	{
		return super.toString() + "," + dateOfBirth + "," 
				+ description + "," + this.relativeList();
	}
	
	//check if given string matches any fields
	public boolean equals(String property)
	{
		if (super.equals(property))
		{
			return true;
		}
		else
		{
			if (this.dateOfBirth.equals(property))
			{
				return true;
			}
			else
			{
				if (this.description.equals(property))
				{
					return true;
				}
				else
				{
					if (this.listOfRelatives.toString().equals(property))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
	}
	
	//Make relatives in arraylist one string to output
	public String relativeList()
	{
		relatives = "";
		if (listOfRelatives == null)
		{
			relatives = "";
		}
		else
		{
			for (Contact contact : listOfRelatives)
			{
				relatives = relatives + contact.getName() + "|";
			}
		}
		return relatives;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setListOfRelatives(ArrayList<Contact> listOfRelatives)
	{
		this.listOfRelatives = listOfRelatives;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Contact> getListOfRelatives() {
		return listOfRelatives;
	}

	public String getRelatives() {
		return relatives;
	}
}
