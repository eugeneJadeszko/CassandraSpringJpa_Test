package main.by.intexsoft.cassandraJpa.utility.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.by.intexsoft.cassandraJpa.utility.Utility;

public class FileUtilityImpl implements Utility {
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void write(String dirPath, String fileName, String text, boolean append) {
		File file = new File(dirPath, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (FileWriter writer = new FileWriter(file, append)) {
			writer.write(text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String read(String inputFile) {
		File file = new File(inputFile);
		if (!file.exists())
			return "file not found";
		StringBuilder sb = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = in.readLine()) != null) {
				sb.append(s);
				sb.append("\n");
			}
		} catch (IOException e) {
			System.out.println("i/o exception");
		}
		return sb.toString();
	}

	@Override
	public boolean delete(String dirPath, String fileName) {
		File file = new File(dirPath, fileName);
		if (!file.exists())
			return false;
		file.delete();
		return true;
	}

	@Override
	public boolean move(String sourcePath, String fileName, String targetPath) {
		String text = read(sourcePath + "/" + fileName);
		if (!text.equals("file not found")) {
			write(targetPath, fileName, text, false);
		} else {
			System.out.println(text);
			return false;
		}
		if (!delete(sourcePath, fileName)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidJSON(File file) throws IOException {
		try {
			mapper.readTree(file);
		} catch (JsonProcessingException e) {
			return false;
		}
		return true;
	}
}
