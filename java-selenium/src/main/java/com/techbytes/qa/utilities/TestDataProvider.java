package com.techbytes.qa.utilities;

import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class TestDataProvider {

    private static final Logger logger = LoggerFactory.getLogger(TestDataProvider.class);
    private static final String LOG_PREFIX = "[TestDataProvider]";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        logger.info("[{}] -> Providing login credentials test data", LOG_PREFIX);

        List<Map<String, String>> data = JsonUtils.readJson(ConfigurationLoader.get("jsonDataPath"));

        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }
}
