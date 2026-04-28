package com.techbytes.qa.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * Reads CSV file from resources and returns data as
     * List<Map<String, String>>
     * @param path
     * @return 
     */
    public static List<Map<String, String>> readCsv(String path) {

        logger.info(" -> Reading CSV file: {}", path);
//  Creating try-with-resource block to autoclose InputStream, InputStreamReader, CsvParser even if an exception occurs
        try (
                InputStream is = CsvUtils.class.getClassLoader().getResourceAsStream(path);
                InputStreamReader reader = new InputStreamReader(is);
                CSVParser parser = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .get()
                .parse(reader);
            ) {
            List<Map<String, String>> data = new ArrayList<>();

            for (CSVRecord record : parser) {
                Map<String, String> row = new HashMap<>();
                parser.getHeaderMap().keySet().forEach(header -> row.put(header, record.get(header)));
                data.add(row);
            }
            logger.info(" -> CSV records loaded: {}", data.size());
            return data;
        } catch (IOException e) {
            logger.error(" -> Failed to read CSV: " + path, e);
            throw new RuntimeException("Failed to read CSV: " + path, e);
        }
    }
}
