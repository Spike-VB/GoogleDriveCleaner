package com.spike.GoogleDriveCleaner;

import java.io.*;
import java.util.*;

public class GoogleDriveCleaner {
	
	public static void main(String[] args) {
		GoogleDriveCleaner app = new GoogleDriveCleaner();
		app.start();
	}
	
	public void start() {
		/*
		List<File> files = getSubDir(new File("D:\\GoogleDrive"));
		for(File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		*/
		ArrayList<File> files = this.find(new File("D:\\GoogleDrive"), "(1)");
	}
	
	private ArrayList<File> find(File dir, String namePart) {
		
		ArrayList<File> files = new ArrayList<File>();
		
		
		
		return new ArrayList<File>();
	}
	
	private List<File> getFiles(File directory) {
		
		File[] files = directory.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return new File(dir, name).isFile();
			}
		});
		
		List<File> filesList = Arrays.asList(files);

		return filesList;
	}
	
	private List<File> getSubDir(File directory) {
		
		File[] files = directory.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		
		List<File> dirList = Arrays.asList(files);
		
		return dirList;
	}
}
