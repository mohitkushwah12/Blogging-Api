package com.dollop.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService 
{
	String uploadFile(MultipartFile file, String path) throws IOException;
	//InputStream getResource(String path, List<String> list) throws FileNotFoundException;
	InputStream getResource(String path, String name) throws FileNotFoundException;
	//public List<String> uploadFiles(MultipartFile[] files, String path) throws IOException;
}
