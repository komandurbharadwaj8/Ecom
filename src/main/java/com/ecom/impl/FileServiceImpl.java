package com.ecom.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadFile(String path, MultipartFile file) throws Exception {
		
		
		 //get original file name
        String originalFileName = file.getOriginalFilename();

        // abc.png

        int indexOfLastDot = originalFileName.lastIndexOf(".");
        String fileExtensionWithDot = originalFileName.substring(indexOfLastDot);
        if (fileExtensionWithDot.equalsIgnoreCase(".png") || fileExtensionWithDot.equalsIgnoreCase(".jpg")) {
            //upload continue

            File file1 = new File(path);

            if (!file1.exists()) {
                file1.mkdir();
            }

            String randomName = UUID.randomUUID().toString();


            String fullPath = path + File.separator + randomName + fileExtensionWithDot;

            Files.copy(file.getInputStream(), Paths.get(fullPath));

            System.out.println("File is uploaded");

            return randomName + fileExtensionWithDot;

        } else {
            throw new Exception("InvalidFile");
        }


	}

	@Override
	public InputStream getData(String path, String filename) throws FileNotFoundException {
		
		 String fullPath=path+File.separator+filename;
	        File file=new File(fullPath);
	        InputStream is=new FileInputStream(file);
	        return is;
	}

	@Override
	public boolean deleteFileIfExists(String filePath) {
	
		return false;
	}

}
