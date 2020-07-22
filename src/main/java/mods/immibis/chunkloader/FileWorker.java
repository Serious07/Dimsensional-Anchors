package mods.immibis.chunkloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;

public class FileWorker {
	private static String dataPath = "DimensionalAnchors/LastLogOutTime/";
	
	public static void SaveCurrentTime(String playerId){
		WriteFile(playerId, DateTime.now().toString());
	}
	
	public static DateTime LoadLastTime(String playerId){
		return DateTime.parse(ReadFile(playerId));
	}
	
	public static void WriteFile(String playerId, String content){
	    String str = "Hello";
	    
	    if(isFolderExists(dataPath) == false)
	    	CreateFolder(dataPath);
	    
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(dataPath + playerId + ".txt"));
			writer.write(content);
		    
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String ReadFile(String playerId){
		String result = "error";
		
		if(isFolderExists(dataPath) == false)
	    	CreateFolder(dataPath);
		
		try {
			if(isFileExists(dataPath + playerId + ".txt")) {
				File myObj = new File(dataPath + playerId + ".txt");
				Scanner myReader = new Scanner(myObj);
				  
				String data = "";
				  
				while (myReader.hasNextLine()) {
					data += myReader.nextLine();
				}
				
				result = data;
				myReader.close();
			} else {
				result = "Error, file not exists";
			}
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
		
		return result;
	}
	
	public static void CreateFolder(String folderPath){
		new File(folderPath).mkdirs();
	}
	
	public static void DeleteFile(String playerId){
		if(isFolderExists(dataPath) && isFileExists(dataPath + playerId + ".txt")) {
			File file = new File(dataPath + playerId + ".txt");
			file.delete();
		}
	}
	
	public static boolean isFileExists(String filePath){
		File f = new File(filePath);
		
		return f.exists() && !f.isDirectory();
	}
	
	public static boolean isFolderExists(String folderPath){
		File f = new File(folderPath);
		
		return f.exists() && f.isDirectory();
	}
	
	public static ArrayList<String> getUsersList(){
		File folder = new File(dataPath);
		ArrayList<String> result = new ArrayList<String>();
		
		if(isFolderExists(dataPath) == false)
	    	CreateFolder(dataPath);
		
		for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	result.add(fileEntry.getName().replace(".txt", ""));
	        }
	    }
		
		return result;
	}
}