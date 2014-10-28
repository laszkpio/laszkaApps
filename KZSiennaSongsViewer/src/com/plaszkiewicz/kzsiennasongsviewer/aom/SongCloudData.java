package com.plaszkiewicz.kzsiennasongsviewer.aom;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("SongCloudData")
public class SongCloudData extends IBMDataObject {
	
	public static final String CLASS_NAME = "SongCloudData";
	private static final String NAME = "name";
	private static final String USER_ID = "userId";
	private static final String NO_OF_ENTRIES = "numberOfEntries";
	
	public String getName() {
		return (String) getObject(NAME);
	}
	public void setName(String itemName) {
		setObject(NAME, (itemName != null) ? itemName : "");
	}
	
	public String getUserId() {
		return (String) getObject(USER_ID);
	}
	public void setUserId(String userId) {
		setObject(USER_ID, (userId != null) ? userId : "");
	}
	
	public Integer getNumberOfEntries() {
		return (Integer) getObject(NO_OF_ENTRIES);
	}
	public void setNumberOfEntries(Integer numberOfEntries) {
		setObject(NO_OF_ENTRIES, numberOfEntries);
	}
	
	public String toString() {
		String theItemName = "";
		theItemName = getName();
		return theItemName;
	}

}
