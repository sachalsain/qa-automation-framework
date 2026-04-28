package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class StatusCodesPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(StatusCodesPage.class);

    public StatusCodesPage(Page page) {
        logger.info("Setting up the Shifting Content Menu Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Status Codes Page URL.");
        page.navigate(ConfigurationLoader.get("statusCodesUrl"));
    }

    public int getStatusCode(int val) {
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(String.valueOf(val)).setExact(true)).click();
        return page.navigate(ConfigurationLoader.get("statusCodesUrl") + "/" + String.valueOf(val)).status();
    }

}
