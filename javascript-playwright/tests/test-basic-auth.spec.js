import { BaseTest, test } from "../src/core/base-test.js";
import { PageBasicAuth } from "../src/pages/page-basic-auth.js";

test("test_basic_auth", async ({ authenticatedPage, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(authenticatedPage, config, testInfo);
  const basicAuth = new PageBasicAuth(authenticatedPage);
  await basicAuth.open(base.baseUrl);
  base.assertTextMatching("basic auth", (await basicAuth.getHeadingText()).toLowerCase(), "Heading must contain Basic Auth");
  base.assertTextMatching("congratulations", (await basicAuth.getParagraphText()).toLowerCase(), "Paragraph must contain Congratulations");
});
