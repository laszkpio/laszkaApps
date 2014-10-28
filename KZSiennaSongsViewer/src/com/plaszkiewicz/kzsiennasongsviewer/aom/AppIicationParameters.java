package com.plaszkiewicz.kzsiennasongsviewer.aom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.ibm.mobile.services.core.IBMBluemix;
import com.ibm.mobile.services.data.IBMData;
import com.plaszkiewicz.kzsiennasongsviewer.R;

public class AppIicationParameters {
	
	private static boolean initialized = false;
	private static AppIicationParameters appInitializer = null;
	
	private static final String CLASS_NAME = AppIicationParameters.class.getSimpleName();
	
	private static final String APP_ID = "applicationID";
	private static final String APP_SECRET = "applicationSecret";
	private static final String APP_ROUTE = "applicationRoute";
	private static final String PROPS_FILE = "kzsiennasongsviewer.properties";

	private AppIicationParameters() {}
	
	public final static AppIicationParameters getInstance(){
		if (appInitializer == null){
			appInitializer = new AppIicationParameters();
		}	
		return appInitializer;
	}
	
	public final boolean initialize(Context context){
		if (initialized){
			return false;
		}
		initialized = true;
		
		Properties props = new java.util.Properties();
		
		try {
			AssetManager assetManager = context.getAssets();
			props.load(assetManager.open(PROPS_FILE));
			Log.i(CLASS_NAME, "Found configuration file: " + PROPS_FILE);
			
			String appId = props.getProperty(APP_ID);
			String appSecret = props.getProperty(APP_SECRET);
			String appRoute = props.getProperty(APP_ROUTE);
			
			// initialize the IBM core backend-as-a-service
			IBMBluemix.initialize(context, appId , appSecret, appRoute);
			// initialize the IBM Data Service
			IBMData.initializeService();
			
			// register the SongCloudData Specialization
			SongCloudData.registerSpecialization(SongCloudData.class);
			
		} catch (FileNotFoundException e) {
			Log.e(CLASS_NAME, "The "+PROPS_FILE+" file was not found.", e);
		} catch (IOException e) {
			Log.e(CLASS_NAME, "The "+PROPS_FILE+" file could not be read properly.", e);
		}
		
		InputStream songsInputStream = context.getResources().openRawResource(R.raw.songs);

		SongsImporter songsImporter = new SongsImporter();
		try {
			songsImporter.importSongs(songsInputStream);
		}
		catch (Exception e){
			System.err.println("Error when loading songs!");
		}
		
		return true;
	}

}
