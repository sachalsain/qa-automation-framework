import { BaseTest, test } from "../src/core/base-test.js";
import { PageForgotPassword } from "../src/pages/page-forgot-password.js";

test("test_forgot_password", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const forgotPassword = new PageForgotPassword(page);
  await forgotPassword.open(base.baseUrl);
  await forgotPassword.submitEmail();
  base.assertTextMatching("email sent", (await forgotPassword.getMessage()).toLowerCase(), "Forgot password message should match.");
  base.assertTextMatching("/email_sent", forgotPassword.getPageUrl(), "URL should contain email_sent.");
});
