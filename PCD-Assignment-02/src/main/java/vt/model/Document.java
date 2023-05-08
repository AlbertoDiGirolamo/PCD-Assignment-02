package vt.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Document {

	private int numLines;
    private File file;
    
    public Document(File file) {
        this.file = file;
    }

    public int countNumLines() {
        BufferedReader reader;
        try {
        	reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();
            while (line != null) {
                numLines++;
                line = reader.readLine();
            }
            return numLines;
        } catch (Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
    public static Document fromFile(File file) throws IOException {
        return new Document(file);
    }

    public String getPath(){
        return this.file.getPath();
    }
}
