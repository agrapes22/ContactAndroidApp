/* Contact base class:
 * Creates basic contact with standard information. 
 * Includes methods to output a contact as one string and
 * set individual fields
 */

package com.example.contactapp;

import java.util.ArrayList;
import java.util.Comparator;

public class Contact implements Comparable<Contact>
{
	private Integer number;
	private String name;
	private String phone;
	private ArrayList<Photo> listOfPhotos;
	private Location location;
	
	public Contact()
	{
		
	}
	
	public Contact(int number, String name, String phone, ArrayList<Photo> listOfPhotos,
			Location location)
	{
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.listOfPhotos = listOfPhotos;
		this.location = location;
	}
	
	public String toString()
	{
		String photoList = "";
		for (Photo photo : listOfPhotos)
		{
			photoList += photo.toString() + " - ";
		}
		return number + "," + name + "," + phone + "," + photoList + ","
				+ location.toString();
	}

	public String getName()
	{
		return name;
	}

	//Compare given contact by number
	@Override
	public int compareTo(Contact contact)
	{
		int test = this.number.compareTo(contact.getNumber());
		if (test == 0)
		{
			return this.name.compareTo(contact.name);
		}
		else
		{
			return test;
		}
	}
	
	//Check if given string is in any field
	public boolean equals(String property)
	{
		if (this.name.contains(property))
		{
			return true;
		}
		else
		{
			if (this.location.equals(property))
			{
				return true;
			}
			else
			{
				if (("" + this.number).equals(property))
				{
					return true;
				}
				else
				{
					if (this.phone.equals(property))
					{
						return true;
					}
					else
					{
						if (this.listOfPhotos.toString().contains(property))
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
	}

	public static Comparator<Contact> ContactNameAZComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact p1, Contact p2)
		{
			return p1.getName().compareTo(p2.getName());
		}
	};

	public static Comparator<Contact> ContactNameZAComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact p1, Contact p2)
		{
			return p2.getName().compareTo(p1.getName());
		}
	};

	public static Comparator<Contact> ContactIdAscComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact p1, Contact p2)
		{
			return p1.getNumber() - p2.getNumber();
		}
	};

	public static Comparator<Contact> ContactIdDescComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact p1, Contact p2)
		{
			return p2.getNumber() - p1.getNumber();
		}
	};

	public int getNumber()
	{
		return number;
	}

	public String getPhone()
	{
		return phone;
	}

	public ArrayList<Photo> getListOfPhotos()
	{
		return listOfPhotos;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setListOfPhotos(ArrayList<Photo> listOfPhotos)
	{
		this.listOfPhotos = listOfPhotos;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	
}
