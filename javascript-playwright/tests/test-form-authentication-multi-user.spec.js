import fs from "node:fs";
import { BaseTest, test } from "../src/core/base-test.js";
import { loadDataFile } from "../src/utilities/path-loader.js";
import { PageFormAuthentication } from "../src/pages/page-form-authentication-multi-user.js";

const loginData = JSON.parse(fs.readFileSync(loadDataFile("login_users.json"), "utf-8"));

for (const [index, testData] of loginData.entries()) {
  test(`test_multiple_user_login ${index + 1} ${testData.username}`, async ({ page, config }, testInfo) => {
    const base = new BaseTest();
    base.setupTest(page, config, testInfo);
    const formAuth = new PageFormAuthentication(page);
    await formAuth.open(base.baseUrl);
    await formAuth.login(testData.username, testData.password);
    base.assertTextMatching(testData.expected_message.toLowerCase(), (await formAuth.getMessage()).toLowerCase(), "Login message should match.");
  });
}
