package draxnol.files;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileManager {
	/*Check if db exists*/
	/*Look for config file*/
	/*config file for now will be a text documents with n columns, where the last value is total*/
	static final String DATABASENAME = "invoiceDatabase.db";
	static final String CONFIGFILENAME = "config.txt";
	static final URL DATABASEPATH = FileManager.class.getResource(DATABASENAME);
	static final URL CONFIGPATH = FileManager.class.getResource(CONFIGFILENAME);
	File configFile;
	File dataBaseFile;
	
	File testFile = new File("test.txt");
	
	
	
	
	
	public FileManager() {
		configFile = new File(CONFIGFILENAME);
		dataBaseFile = new File(DATABASENAME);
		
		
	}
	
	public String getDatabaseName() {
		return DATABASENAME;
	}
	
	public String getConfigFileName() {
		return CONFIGFILENAME;
	}
	
	public boolean checkConfigFileExists() {
		return configFile.exists();
	}
	
	public boolean checkDBfileExists() {
		return dataBaseFile.exists();
	}
	
	public void createDBFile() {
		try {
			dataBaseFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createConfigFile() {
		try {
			configFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
