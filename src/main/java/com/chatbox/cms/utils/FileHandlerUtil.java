/**
 * 
 * Description : This class used for handling file operation functionality.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 06, 2016
 */

package com.chatbox.cms.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Bhagawantha Parasuraman Uploaded File utility to storing file.
 */
@Component
public class FileHandlerUtil {

	/**
	 * LOGGER used in the class.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileHandlerUtil.class);

	/**
	 * @param attachments
	 * @param fileLocation
	 * @param rootPath
	 */
	public List<String> storeDocuments(List<MultipartFile> attachments,
			List<String> fileLocation, String rootPath) {
		File storageDir = new File(rootPath + File.separator + "resources");
		if (!storageDir.exists())
			storageDir.mkdirs();

		if (null != attachments && attachments.size() > 0) {
			String fileStorageLoc = "";
			for (MultipartFile attachment : attachments) {

				String fileName = attachment.getOriginalFilename();
				if (null != fileName && !fileName.isEmpty()) {
					try {
						fileStorageLoc = storageDir.getAbsolutePath()
								+ File.separator + fileName;
						attachment.transferTo(new File(fileStorageLoc));
					} catch (IllegalStateException e) {
						LOGGER.error(
								":: storeDocuments : IllegalStateException : ",
								e);
					} catch (IOException e) {
						LOGGER.error(":: storeDocuments : IOException : ", e);
					}
					fileLocation.add(fileName);
				}
			}

		}
		return fileLocation;
	}
}