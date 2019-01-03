package com.aps.testing.commons;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.aps.testing.helper.FileUploadHelper;
import com.aps.testing.util.ValidationConstants;

import cucumber.api.java.en.Given;

public class FileUploadCommons {

	@Autowired
	FileUploadHelper fileUploadHelper;
	
	private static final Logger LOGGER = Logger.getLogger(FileUploadCommons.class.getName());


	@Given("^I \"([^\"]*)\" a file \"([^\"]*)\" into \"([^\"]*)\" folder in NIFI$")
	public void uploadAFile(String command, String fileName, String nifiFolder) throws Throwable {
		if (command.equalsIgnoreCase("upload")) {
			String result = fileUploadHelper.fileUpload(command, fileName, nifiFolder);
			Assert.isTrue("File Transfered".equals(result), ValidationConstants.EQUALEXPRESSION.toString());

		} else if (command.equals("dont upload")) {
			LOGGER.info("File already uploaded");

		}
	}

}
