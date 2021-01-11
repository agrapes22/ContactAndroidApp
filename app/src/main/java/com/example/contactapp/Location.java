/*Location class
 * Set up a location with street number, street name,
 * city, and state
 * Includes method to return location as one string
 */

package com.example.contactapp;

public class Location
{
	private int locIDNum;
	private String street;
	private String city;
	private String state;
	
	public Location()
	{
	}
	
	public Location(int locIDNum, String street, String city, String state)
	{
		this.locIDNum = locIDNum;
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
	public String toString()
	{
		return locIDNum + "|" + street + "|" + city
				+ "|" + state;
	}
	
	//Check if given string is in any field
	public boolean equals(String property)
	{
		if (("" + this.locIDNum).equals(property))
		{
			return true;
		}
		else
		{
			if (this.street.equals(property))
			{
				return true;
			}
			else
			{
				if (this.city.equals(property))
				{
					return true;
				}
				else
				{
					if (this.state.equals(property))
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

	public int getLocIDNum() {
		return locIDNum;
	}

	public void setLocIDNum(int locIDNum) {
		this.locIDNum = locIDNum;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
