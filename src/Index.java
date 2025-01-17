import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
public class Index {
	static HashMap<String, String> blobs = new HashMap<String, String>();
	/*
	public static void main(String[] args) throws IOException{
		Blob git = new Blob();
		git.init();
		git.add("file.txt");
	}
	*/
	public void init() throws IOException {
		
		File ob = new File("objects");
	
		if(ob.mkdir()) {
			System.out.println("directory created");
		} else {
			System.out.println("directory not created");
		}
		Path o = Paths.get("objects");
		File nf = new File(o + "/index.txt");
		nf.createNewFile();
		
	}
	public static void add(String filename)  throws IOException, NoSuchAlgorithmException{
		Blob b = new Blob(filename);
		
		blobs.put(filename, b.getSha());
		//Appends that pair to a list in a file named 'index'
		PrintWriter out = new PrintWriter("./objects/index");
 		for (String file: blobs.keySet()) 
 			out.println(file + " : " + blobs.get(file));
 	 	
 		out.close();
	}
	public static void removeBlob(String filename) throws FileNotFoundException {
		if(blobs.containsKey(filename)) {
			 
			File f = new File( "objects/" + blobs.get(filename));
			if(!f.delete())
				System.out.println("cannot delete file");
			else
				System.out.println("file deleted");
			
			blobs.remove(filename);
			PrintWriter out = new PrintWriter("./objects/index");
	 		for (String file: blobs.keySet()) 
	 			out.println(file + " : " + blobs.get(file));
	 	 	
	 		out.close();
	 		return;
		}
		System.out.println("File does not exist");
		
	}
}
