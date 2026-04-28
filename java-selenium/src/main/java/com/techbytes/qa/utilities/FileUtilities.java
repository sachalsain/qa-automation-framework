package com.techbytes.qa.utilities;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FileUtilities {

    private static final Logger logger = LoggerFactory.getLogger(FileUtilities.class);
    
    public FileUtilities(){}

    public void cleanupDownloadFolder(String dirPath, String fileExtension) {
        logger.info(" -> cleaning up the Downloads folder.");
        logger.debug(" -> Creating path for system's Downloads folder.");
        File dir = new File(dirPath);
        if (!dir.exists()) {
            logger.error(" -> Directory not found. Creating directory.");
            dir.mkdirs();
        }

        File[] existingFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(fileExtension));
        if (existingFiles != null) {
            for (File file : existingFiles) {
                if (file.delete()) {
                    logger.info(" -> Cleaned up old file: " + file.getName());
                }
            }
        }
    }

}
