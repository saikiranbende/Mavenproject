package wcpConnect.plugin;
 
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
     
@Mojo( name = "modWCPConnectProp")
public class propertyFile extends AbstractMojo
{

Scanner scan1;
Scanner scan2;
BufferedWriter bw;
FileWriter fw;
public void appendPropertyValues() {
	try {
		String propFileName = "ABSOLUTEFILEPATH/connection.properties";
		File propertyFile = new File(propFileName);
	
		if (propFileName != null) {
			scan1 = new Scanner(propertyFile);
		} 

		else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found.");
		}
	
		scan1.nextLine();
		scan1.nextLine();
		//starts iterating through URL connections
		while (scan1.hasNextLine()) {
			scan2 = new Scanner(propertyFile);
			String line = scan1.nextLine();
		
			if (line.substring(0,1).equals("w")) {
				//Stops from reading the entire property file, ends at last URL connection
				break;
			}
		
			else {
				String array[] = line.split("\\.",3);
				scan2.nextLine();
				scan2.nextLine();
				int count = 0;
				
				while (scan2.hasNextLine()) {
					String line2 = scan2.nextLine();
					String array2[] = line2.split("\\.",3);
					
					if (line2.substring(0,1).equals("#")) {
						//Prevents out of bounds array exception from stopping code
						continue;
					}

					else if (array[1].equals(array2[1])) {
					count++;
					}
				}

			if (count < 2) {
				//appending the file
				fw = new FileWriter(propertyFile, true);
				bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				pw.println("urlConnection."+array[1]+".authenticationType=none");
				pw.println("urlConnection."+array[1]+".connectionClassName=oracle.adf.model.connection.url.HttpURLConnection");
				pw.println("urlConnection."+array[1]+".realm=");
				pw.close();
			}
		}
		}
	
	}
	
	catch (Exception e) {
		System.out.println("Exception: " + e);
	} 

	finally {
		scan1.close();
		scan2.close();
	}
   }

	public void execute() throws MojoExecutionException {
       		propertyFile props = new propertyFile();
		props.appendPropertyValues();
	
    	}
}
