package com.reportingtool.scheduler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoaderUtilities {

	public static boolean maskFile(String file, String prefix, String sufix) {

		return file.startsWith(prefix) && file.endsWith(sufix);
	}

	public static boolean maskFile(String file, String mask) {

		String[] parts = mask.split("[*]");
		return maskFile(file, parts[0], parts[1]);
	}

	public static List<String> loadFileInList(File input)
			throws IOException {

		FileInputStream fstream = new FileInputStream(input);
		return loadFileInList(fstream);
	}
	
	public static List<String> loadFileInList(InputStream fstream) throws IOException {
		
		List<String> result = new ArrayList<String>();
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			result.add(strLine);
		}
		br.close();

		return result;
	}

	public static List<String> loadFileInList(String input) throws FileNotFoundException, IOException {

		File file = new File(input);
		return loadFileInList(file);
	}
}
