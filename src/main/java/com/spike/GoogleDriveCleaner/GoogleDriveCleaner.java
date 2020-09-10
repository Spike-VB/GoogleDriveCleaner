package com.spike.GoogleDriveCleaner;

import java.io.*;
import java.util.*;

public class GoogleDriveCleaner {
	
	public static void main(String[] args) {
		GoogleDriveCleaner app = new GoogleDriveCleaner();
		app.start();
	}
	
	private void start() {
		String[] excludeDir = {
				//"D:\\GoogleDrive\\eclipse",
				//"D:\\GoogleDrive\\Java",
				//"D:\\GoogleDrive\\Java\\eclipse"
				};
		List<String> excludeDirList = Arrays.asList(excludeDir);
		excludeDir = null;
		
		LinkedList<File> files = this.findFiles(new File("D:\\GoogleDrive"), "(1)", excludeDirList);
		LinkedList<File> dirs = this.findDirs(new File("D:\\GoogleDrive"), "(1)", excludeDirList);
		//this.printFindResults(files);
		this.printFindResults(dirs);
		//this.delete(files);
	}
	
	public LinkedList<File> findFiles(File startDir, String namePart, List<String> excludeDir) {
		
		LinkedList<File> files = new LinkedList<File>();
		
		LinkedList<File> dirQueue = new LinkedList<File>();
		dirQueue.add(startDir);
		
		while(!dirQueue.isEmpty()) {
			File currentDir = dirQueue.poll();
			List<File> dirList = this.getSubDir(currentDir);
			
			for(File f : dirList) {
				if(!excludeDir.contains(f.getPath())) {
					dirQueue.add(f);
				}
			}
			
			List<File> fileList = this.getFiles(currentDir);
			
			for(File f : fileList) {
				if(f.getPath().contains(namePart)) {
					files.add(f);
				}
			}
		}
		
		return files;
	}
	
	public LinkedList<File> findDirs(File startDir, String namePart, List<String> excludeDir) {
		
		LinkedList<File> dirs = new LinkedList<File>();
		
		LinkedList<File> dirQueue = new LinkedList<File>();
		dirQueue.add(startDir);
		
		while(!dirQueue.isEmpty()) {
			File currentDir = dirQueue.poll();
			List<File> dirList = this.getSubDir(currentDir);
			
			for(File d : dirList) {
				if(!excludeDir.contains(d.getPath())) {
					if(!d.getPath().contains(namePart)) {
						dirQueue.add(d);
					}
					else {
						dirs.add(d);
					}
				}
			}
		}
		
		return dirs;
	}
	
	public void delete(LinkedList<File> files) {
		LinkedList<File> deleted = new LinkedList<File>();
		LinkedList<File> notDeleted = new LinkedList<File>();
		
		for(File f : files) {
			if(f.delete()) {
				deleted.add(f);
			}
			else {
				notDeleted.add(f);
			}
		}
		
		System.out.println("Deleted:");
		
		for(File f : deleted) {
			System.out.println(f.getPath());
		}
		
		System.out.println("");
		System.out.println("Not deleted:");
		
		for(File f : notDeleted) {
			System.out.println(f.getPath());
		}
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
	
	private void printFindResults (LinkedList<File> files) {
		
		for(File f : files) {
			System.out.println(f.getPath());
		}
	}
}
