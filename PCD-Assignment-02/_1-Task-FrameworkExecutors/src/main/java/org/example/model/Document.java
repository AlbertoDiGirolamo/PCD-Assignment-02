package org.example.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Document {

	private static long numLines;
    private File file;
    
    public Document(File file) {
        this.file = file;
    }
    public Long getNumLines(){
        return numLines;
    }

    
    public void countNumLines() throws IOException {
        BufferedReader reader;
        try {
        	reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();
            while (line != null) {
                numLines++;
                line = reader.readLine();
            }
        } catch (Exception ex){
        	ex.printStackTrace();
        }
    }
    public static Document fromFile(File file) throws IOException {
        return new Document(file);
    }
}
