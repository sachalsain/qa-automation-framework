package com.techbytes.qa.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * Reads Excel (.xlsx) file from resources and returns data as
     * List<Map<String, String>>
     * @param path
     * @param sheetName
     * @return 
     */
    public static List<Map<String, String>> readExcel(String path, String sheetName) {
        logger.info("Data from Excel File requested");
        
        logger.debug("Reading Excel file from path: {}", path);
        try (
                InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(path);
                Workbook workbook = WorkbookFactory.create(is)
        ) {
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();

            List<Map<String, String>> data = new ArrayList<>();

            // Header row
            Row headerRow = rows.next();
            List<String> headers = new ArrayList<>();
            headerRow.forEach(cell -> headers.add(cell.getStringCellValue()));

            // Data rows
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = currentRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(i), cell.toString());
                }

                data.add(rowData);
            }

            logger.debug("Excel rows loaded: {}", data.size());
            return data;

        } catch (Exception e) {
            logger.error("Could not read Excel File. Reason: {}", e.getMessage());
            throw new RuntimeException("Failed to read Excel file from path: " + path, e);
        }
    }

}
