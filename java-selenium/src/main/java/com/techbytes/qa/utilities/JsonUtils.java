package com.techbytes.qa.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    //  To create a data structure from json file.
    public static List<Map<String, String>> readJson(String path) {
        logger.info(" -> Reading json file.");
        try (InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream(path);) {
            logger.info(" -> Creating ObjectMapper instance.");
            ObjectMapper mapper = new ObjectMapper();
            logger.info(" -> Creating data structure from inputStream.");
            return mapper.readValue(is, new TypeReference<>() {
            });
        } catch (IOException e) {
            logger.error(" -> Failed to read JSON: " + path, e);
            throw new RuntimeException("Failed to read JSON: " + path, e);
        }
    }
}
