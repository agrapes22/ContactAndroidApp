/*Business contact class
 * Inherits contact base class
 * Includes fields for business hours and website
 * Includes methods to convert to one string and set fields
 */

package com.example.contactapp;

import java.util.ArrayList;

public class BusinessContact extends Contact
{
	private String businessHours;
	private String webURL;
	
	public BusinessContact()
	{
	}
	
	public BusinessContact(int number, String name, String phone, ArrayList<Photo> listOfPhotos,
			Location location, String businessHours, String webURL)
	{
		super(number, name, phone, listOfPhotos, location);
		this.businessHours = businessHours;
		this.webURL = webURL;
	}
	
	public String toString()
	{
		return super.toString() + " , Business Hours: " + businessHours + ", URL: " + webURL; 
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
			if (this.businessHours.equals(property))
			{
				return true;
			}
			else
			{
				if (this.webURL.equals(property))
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

	public void setBusinessHours(String businessHours)
	{
		this.businessHours = businessHours;
	}

	public void setWebURL(String webURL)
	{
		this.webURL = webURL;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public String getWebURL() {
		return webURL;
	}
}
