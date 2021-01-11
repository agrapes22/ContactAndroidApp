/*Console App class
 * Run all commands from this class
 */

package com.example.contactapp;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp
{
	AddressBook book = new AddressBook();
	
	public static void Test(String[] args) throws IOException, CsvValidationException {
		File file = new File("datadictionary.csv");
		//for (String fileNames : file.list()) System.out.println(fileNames);
		
		int menuOption = 0;
		Scanner input = new Scanner(System.in);
		
		AddressBook book = new AddressBook();
		FileAccessService service = new FileAccessService();
		
		//service.readAllContacts(book, file,);
		
		while (menuOption == 0)
		{
			int menuChoice;
			System.out.println("Welcome to the contact app!");
			System.out.println("What would you like to do?");
			System.out.println("1. Add new Contact\n2. Show all\n3. Find by ID Num"
					+ "\n4. Edit contact (by ID num)\n5. Search"
					+ "\n6. Exit");
			menuChoice = input.nextInt();
			
			switch (menuChoice) {
			case 1: //add new
				System.out.println("Add new contact: ");
				System.out.println("Person or business?\n1. Person\n2. Business");
				int choice = input.nextInt();
				if (choice == 1)
				{
					createNewPerson(book);
					break;
				}
				else if(choice == 2)
				{
					createNewBusiness(book);
					break;
				}
				else
				{
					System.out.println("Invalid, please try again...");
				}
				break;
			case 2: //show all
				System.out.println("Contacts: ");
				book.sort();
				System.out.println("END LIST");
				break;
			case 3: //show one by ID
				System.out.println("Please enter ID number: ");
				int idNum = input.nextInt();
				if(book.byIDNum(idNum) != null)
				{
					System.out.println(book.byIDNum(idNum));
					System.out.println("Edit contact? 1. Yes 2. No: ");
					int pick = input.nextInt();
					if (pick == 1)
					{
						editContact(book, idNum);
					}
					else
					{
						
					}
				}
				else
				{
					System.out.println("Contact does not exist");
				}
				break;
			case 4: //edit contact by ID
				System.out.println("Please enter contact ID number: ");
				idNum = input.nextInt();
				if(book.byIDNum(idNum) != null)
				{
					editContact(book, idNum);
				}
				else
				{
					System.out.println("Contact does not exist");
				}
				break;
			case 5: //search
				input.nextLine();
				System.out.println("Search: Enter any contact attribute: ");
				String search = input.nextLine();
				book.searchContacts(search);
				break;
			case 6: //exit
				System.out.println("Exiting, thanks!");
				menuOption = 1;
				break;
			default:
				System.out.println("Invalid, please enter a valid menu option");
				break;
			}
		}
		
		//service.saveAllContacts(book);
		
	}
	
	private static void createNewBusiness(AddressBook book)
	{
		ArrayList<Photo> photoList = new ArrayList();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Business Selected. Please enter the following information: ");
		System.out.println("Photo ID: ");
		int photoID = input.nextInt();
		input.nextLine();
		System.out.println("File name: ");
		String photoName = input.nextLine();
		System.out.println("Date of photo: (mm/dd/yyyy)");
		String dateOfPic = input.nextLine();
		System.out.println("Description of photo: ");
		String descPic = input.nextLine();
		photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
		
		System.out.println("Add another photo? 1 for yes, 2 for no");
		int pick = input.nextInt();
		
		while (pick != 0)
		{
			switch(pick)
			{
				case 1: 
					System.out.println("Photo ID: ");
					photoID = input.nextInt();
					System.out.println("File name: ");
					photoName = input.next();
					System.out.println("Date of photo: (mm/dd/yyyy)");
					dateOfPic = input.nextLine();
					System.out.println("Description of photo: ");
					descPic = input.nextLine();
					photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
					
					System.out.println("Add another photo? 1 for yes, 2 for no");
					pick = input.nextInt();
					break;
				
				case 2:
					System.out.println("Please enter person information now: ");
					pick = 0;
					break;
				default: 
					System.out.println("Invalid, please try again");
					System.out.println("Add another photo? 1 for yes, 2 for no");
					pick = input.nextInt();
					break;
			}
		}
		
		//Get person contact info
		System.out.println("Business ID number: ");
		int busID = input.nextInt();
		input.nextLine();
		System.out.println("Name: ");
		String name = input.nextLine();
		System.out.println("Phone number (xxx-xxx-xxxx): ");
		String num = input.next();
		System.out.println("Location ID: ");
		int locID = input.nextInt();
		input.nextLine();
		System.out.println("Street: ");
		String street = input.nextLine();
		System.out.println("City: ");
		String city = input.next();
		System.out.println("State: ");
		String state = input.next();
		
		Location loc = new Location(locID, street, city, state);
		
		input.nextLine();
		System.out.println("Business hours: ");
		String hours = input.nextLine();
		System.out.println("URL: ");
		String url = input.next();
		
		book.addContact(new BusinessContact(busID, name, num, photoList, loc, hours, url));
	}

	private static void createNewPerson(AddressBook book)
	{
		ArrayList<Photo> photoList = new ArrayList();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Person Selected. Please enter the following information: ");
		System.out.println("Photo ID: ");
		int photoID = input.nextInt();
		input.nextLine();
		System.out.println("File name: ");
		String photoName = input.nextLine();
		System.out.println("Date of photo: (mm/dd/yyyy)");
		String dateOfPic = input.nextLine();
		System.out.println("Description of photo: ");
		String descPic = input.nextLine();
		photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
		
		
		System.out.println("Add another photo? 1 for yes, 2 for no");
		int pick = input.nextInt();
		
		while (pick != 0)
		{
			switch(pick)
			{
				case 1: 
					System.out.println("Photo ID: ");
					photoID = input.nextInt();
					input.nextLine();
					System.out.println("File name: ");
					photoName = input.nextLine();
					System.out.println("Date of photo: (mm/dd/yyyy)");
					dateOfPic = input.nextLine();
					System.out.println("Description of photo: ");
					descPic = input.nextLine();
					photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
					
					System.out.println("Add another photo? 1 for yes, 2 for no");
					pick = input.nextInt();
					break;
				
				case 2:
					System.out.println("Please enter person information now: ");
					pick = 0;
					break;
				default: 
					System.out.println("Invalid, please try again");
					System.out.println("Add another photo? 1 for yes, 2 for no");
					pick = input.nextInt();
					break;
			}
		}
		
		//Get person contact info
		System.out.println("Person ID number: ");
		int persID = input.nextInt();
		input.nextLine();
		System.out.println("Name: ");
		String name = input.nextLine();
		System.out.println("Phone number (xxx-xxx-xxxx): ");
		String num = input.next();
		System.out.println("Location ID: ");
		int locID = input.nextInt();
		input.nextLine();
		System.out.println("Street: ");
		String street = input.nextLine();
		System.out.println("City: ");
		String city = input.next();
		System.out.println("State: ");
		String state = input.next();
		
		Location loc = new Location(locID, street, city, state);
		
		input.nextLine();
		System.out.println("Date of birth (mm/dd/yyyy): ");
		String DOB = input.nextLine();
		System.out.println("Description of person: ");
		String descPers = input.nextLine();
		
		ArrayList<Contact> relatives = new ArrayList();
		
		System.out.println("Relatives contact ID numbers: ");
		int relative = input.nextInt();
		if (book.byIDNum(relative) != null)
		{
			relatives.add(book.byIDNum(relative));
			System.out.println("Added to list of relatives!");
		}
		else
		{
			System.out.println("Contact not in address book");
		}
		
		book.addContact(new PersonContact(persID, name, num, photoList, loc, DOB, descPers, relatives));
	}

	private static void editContact(AddressBook book, int idNum)
	{
		Scanner input = new Scanner(System.in);
		Contact contact = book.byIDNum(idNum);
		book.displayContact(contact);
		System.out.println("What would you like to edit?");
		System.out.println("1. ID Num\n2. Name\n3. Phone Number\n4. Photos\n5. Location"
				+ "\nFor Person Contact:\n6. Date of Birth\n7. Relatives\n8. Description\nFor Business Contact:\n9. Business Hours"
				+ "\n10. URL");
		int choice = input.nextInt();
		input.nextLine();
		
		while (choice != 0)
		{
			switch (choice)
			{
			case 1: //Id num
				System.out.println("Please enter a new ID num: ");
				int num = input.nextInt();
				contact.setNumber(num);
				System.out.println("ID Num has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
				
			case 2: //name
				System.out.println("Please enter a new name: ");
				String name = input.nextLine();
				contact.setName(name);
				System.out.println("Name has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
				
			case 3: //phone number
				System.out.println("Please enter a new phone number: ");
				String phone = input.nextLine();
				contact.setPhone(phone);
				System.out.println("Phone has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
				
			case 4: //photos
				ArrayList<Photo> photoList = new ArrayList();
				System.out.println("Update photos: ");
				System.out.println("Photo ID: ");
				int photoID = input.nextInt();
				System.out.println("File name: ");
				String photoName = input.next();
				System.out.println("Date of photo: (mm/dd/yyyy)");
				String dateOfPic = input.next();
				System.out.println("Description of photo: ");
				String descPic = input.next();
				photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
				input.nextLine();
				
				System.out.println("Add another photo? 1 for yes, 2 for no");
				int pick = input.nextInt();
				
				while (pick != 0)
				{
					switch(pick)
					{
						case 1: 
							System.out.println("Photo ID: ");
							photoID = input.nextInt();
							System.out.println("File name: ");
							photoName = input.next();
							System.out.println("Date of photo: (mm/dd/yyyy)");
							dateOfPic = input.next();
							System.out.println("Description of photo: ");
							descPic = input.nextLine();
							photoList.add(new Photo(photoID, photoName, dateOfPic, descPic));
							input.nextLine();
							System.out.println("Add another photo? 1 for yes, 2 for no");
							pick = input.nextInt();
							break;
						
						case 2:
							System.out.println("Please enter person information now: ");
							pick = 0;
							break;
						default: 
							System.out.println("Invalid, please try again");
							System.out.println("Add another photo? 1 for yes, 2 for no");
							pick = input.nextInt();
							break;
					}
				}
				contact.setListOfPhotos(photoList);
				choice = 0;
				break;
				
			case 5: //location
				System.out.println("Update location: ");
				System.out.println("Location ID: ");
				int locID = input.nextInt();
				input.nextLine();
				System.out.println("Street: ");
				String street = input.nextLine();
				System.out.println("City: ");
				String city = input.next();
				System.out.println("State: ");
				String state = input.next();
				Location loc = new Location(locID, street, city, state);
				contact.setLocation(loc);
				choice = 0;
				break;
				
			case 6: //date of birth
				System.out.println("Please enter a new date of birth: ");
				String DOB = input.nextLine();
				((PersonContact) contact).setDateOfBirth(DOB);
				System.out.println("Phone has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
				
			case 7: //relatives
				System.out.println("Please enter new realtives: ");
				ArrayList<Contact> relatives = new ArrayList();
				System.out.println("Relatives contact ID numbers: ");
				int relative = input.nextInt();
				if (book.byIDNum(relative) != null)
				{
					relatives.add(book.byIDNum(relative));
					((PersonContact) contact).setListOfRelatives(relatives);
					System.out.println("Added to list of relatives!");
				}
				else
				{
					System.out.println("Contact not in address book");
				}
				
				System.out.println("Relatives has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
			case 8: //description
				
				System.out.println("Please enter new description: ");
				String description = input.nextLine();
				((PersonContact) contact).setDescription(description);
				System.out.println("Description has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
			case 9: //business hours
				System.out.println("Please enter new business hours: ");
				String hours = input.nextLine();
				((BusinessContact) contact).setBusinessHours(hours);
				System.out.println("Business hours have been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
			case 10: //url
				System.out.println("Please enter URL: ");
				String url = input.next();
				((BusinessContact) contact).setWebURL(url);
				System.out.println("URL has been updated: ");
				book.displayContact(contact);
				choice = 0;
				break;
			default: 
				System.out.println("Invalid selection");
				choice = 0;
			break;
			}
		}
	}
	
}
