package com.jbk.product.utility;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SheetUtility {
	@Autowired
	 ServletContext context;

	public  boolean checkFileFormat(MultipartFile file) {

		if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}

		return false;
	}

	public  String setPathOfFile(MultipartFile file) {
		String path = context.getRealPath("webapp");
		String fileName = file.getOriginalFilename();
		String finalPath = path + File.separator + fileName;
		return finalPath;

	}

}
