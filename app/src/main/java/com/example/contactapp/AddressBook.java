/*Address book class
 * Stores contacts in an ArrayList
 * Includes methods to add, remove, sort, search, and display contacts
 * Method to get list included for file reading
 */

package com.example.contactapp;

import java.util.*;

public class AddressBook {

	private List<Contact> contacts = new ArrayList<Contact>();
	
	public AddressBook()
	{
		
	}
	
	public AddressBook(Contact contact)
	{
		
	}
	
	public void addContact(Contact contact)
	{
		if(!contacts.contains(contact))
		{
			contacts.add(contact);
			//System.out.println("Contact has been added to address book.");
		}
		else
		{
			//System.out.println("Contact was already added");
		}
	}
	
	public void removeContact(Contact contact)
	{
		contacts.remove(contact);
		//System.out.println("Contact has been removed from the address book.");
	}
	
	public void displayContact(Contact contact)
	{
		int value = contacts.indexOf(contact);

		//System.out.println(contacts.get(value));
	}

	public void set(int id, Contact contact)
	{
		removeContact(byIDNum(id));
		addContact(contact);
	}
	
	public void sort()
	{
		Collections.sort(contacts);
		for (Contact contact : contacts)
		{
			System.out.println(contact.toString());
		}
	}
	
	public void searchContacts(String property)
	{
		System.out.println("Results:");
		for (Contact contact : contacts)
		{
			if (contact.equals(property))
			{
				//System.out.println(contact.toString());
			}
			else
			{
				
			}
		}
	}
	
	public Contact byIDNum(int property)
	{
		for (Contact contact : contacts)
		{
			if (contact.getNumber() == property)
			{
				return contact;
			}
			else
			{
				
			}
		}
		
		return null;
	}
	
	public Contact byName(String name)
	{
		for (Contact contact : contacts)
		{
			if (contact.equals(name))
			{
				return contact;
			}
			else
			{
				
			}
		}
		
		return null;
	}
	

	public List<Contact> getList()
	{
		return contacts;
	}
}
