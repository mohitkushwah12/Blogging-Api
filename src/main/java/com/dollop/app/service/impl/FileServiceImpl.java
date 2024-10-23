package com.dollop.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {

	private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		String originalFileName = file.getOriginalFilename();
		logger.info("File Name : ()", originalFileName);
		String fileName = UUID.randomUUID().toString();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileNameWithExtension = fileName + extension;
		String fullPathWithFileName = path + fileNameWithExtension;
		logger.info("Full Image Path : ()", fullPathWithFileName);
		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
		{
			logger.info("Extension is : ()", extension);
			File folder = new File(path);
			if(!folder.exists())
			{
				folder.mkdirs();
			}
			Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
		}
		else
		{
			throw new ResourceNotFoundException("File With this "+ extension + "not allowed");
		}
		return fileNameWithExtension;
	}
	
//	public List<String> uploadFiles(MultipartFile[] files, String path) throws IOException {
//        List<String> fileNames = new ArrayList<>();
//        for (MultipartFile file : files) {
//            String originalFileName = file.getOriginalFilename();
//            String fileName = UUID.randomUUID().toString();
//            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
//            String fileNameWithExtension = fileName + extension;
//            String fullPathWithFileName = path + fileNameWithExtension;
//            if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
//                File folder = new File(path);
//                if (!folder.exists()) {
//                    folder.mkdirs();
//                }
//                Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
//                fileNames.add(fileNameWithExtension);
//            } else {
//                throw new BadApirequestException("File with extension " + extension + " not allowed");
//            }
//        }
//        return fileNames;
//    }

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException 
	{
		String fullPath = path + File.separator+name;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

//	@Override
//	public InputStream getResource(String path, List<String> list) throws FileNotFoundException {
//		String fullPath = path + File.separator+list;
//		InputStream inputStream = new FileInputStream(fullPath);
//		return inputStream;
//	}

}
