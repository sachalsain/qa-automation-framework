package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ChallengingDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ChallengingDomPage.class);

    public ChallengingDomPage(Page page) {
        logger.info("Setting up the Challenging Dom Page.");
        super(page);
    }
    
    private Locator table = page.locator(("table"));

    public void open() {
        logger.info("Navigating to the ChallengingDom URL.");
        page.navigate(ConfigurationLoader.get("challengingDomUrl"));
    }
    
    public int getTableHeadersCount() {
        logger.info("Getting count of headers.");
        List<Locator> headers = table.locator("thead tr th").all();
        return headers.size();
    }
    
    public int getTableDataRowCount() {
        logger.info("Getting count of data rows.");
        List<Locator> rows = table.locator("tbody tr").all();
        return rows.size();
    }
    
    public String getTableDataValue(int row, int column) {
        logger.info("Getting the value of data cell on row: {} and column: {}.", row, column);
        return table.locator("tbody tr").nth(row).locator("td").nth(column).textContent();
    }
}
