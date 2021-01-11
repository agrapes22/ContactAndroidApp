/*Photo class
 * Set up photo with ID number, file name, date take, and description
 * Includes method to return one string for all fields
 */

package com.example.contactapp;

public class Photo
{
	private int photoIDNum;
	private String fileName;
	private String dateOfPhoto;
	private String description;
	
	public Photo()
	{
	}
	
	public Photo(int photoIDNum, String fileName, String dateOfPhoto, String description)
	{
		this.photoIDNum = photoIDNum;
		this.fileName = fileName;
		this.dateOfPhoto = dateOfPhoto;
		this.description = description;
	}
	
	public String toString()
	{
		return photoIDNum + "|" + fileName + "|" + dateOfPhoto + "|"
				+ description;
	}
	
	//Check if given string matches any fields
	public boolean equals(String property)
	{
		if (("" + this.photoIDNum).equals(property))
		{
			return true;
		}
		else
		{
			if (fileName.contains(property))
			{
				return true;
			}
			else
			{
				if (dateOfPhoto.equals(property))
				{
					return true;
				}
				else
				{
					if (description.contains(property))
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

	public String getFileName()
	{
		return fileName;
	}

	public int getPhotoIDNum() {
		return photoIDNum;
	}

	public void setPhotoIDNum(int photoIDNum) {
		this.photoIDNum = photoIDNum;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDateOfPhoto() {
		return dateOfPhoto;
	}

	public void setDateOfPhoto(String dateOfPhoto) {
		this.dateOfPhoto = dateOfPhoto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
