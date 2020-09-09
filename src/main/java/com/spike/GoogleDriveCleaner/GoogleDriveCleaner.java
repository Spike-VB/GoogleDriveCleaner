package com.spike.GoogleDriveCleaner;

import java.io.*;
import java.util.*;

public class GoogleDriveCleaner {
	
	public static void main(String[] args) {
		GoogleDriveCleaner app = new GoogleDriveCleaner();
		app.start();
	}
	
	public void start() {
		ArrayList<File> files = this.find(new File("D:\\GoogleDrive"), "(1)");
		
		for(File f : files) {
			System.out.println(f.getPath());
		}
	}
	
	private ArrayList<File> find(File dir, String namePart) {
		
		ArrayList<File> files = new ArrayList<File>();
		
		LinkedList<File> dirQueue = new LinkedList<File>();
		dirQueue.add(dir);
		
		while(!dirQueue.isEmpty()) {
			File currentDir = dirQueue.poll();
			List<File> dirList = this.getSubDir(currentDir);
			
			for(File f : dirList) {
				dirQueue.add(f);
			}
			
			List<File> fileList = this.getFiles(currentDir);
			
			for(File f : fileList) {
				f.getPath().contains(namePart);
				files.add(f);
			}
		}
		
		return files;
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
