import { BaseTest, test } from "../src/core/base-test.js";
import { PageRedirection } from "../src/pages/page-redirection.js";

test("test_redirection", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const redirection = new PageRedirection(page);
  await redirection.open(base.baseUrl);
  await redirection.clickLink();
  base.assertTextMatching(redirection.getExpectedUrl(), redirection.getRedirectedUrl(), "Redirected URL should match.");
  base.assertTextMatching("status codes", (await redirection.getRedirectedHeading()).toLowerCase(), "Heading should contain status codes.");
  base.assertIntegerMatching(200, await redirection.getStatusCode("/200"), "Status code should be 200.");
  base.assertIntegerMatching(301, await redirection.getStatusCode("/301"), "Status code should be 301.");
  base.assertIntegerMatching(404, await redirection.getStatusCode("/404"), "Status code should be 404.");
  base.assertIntegerMatching(500, await redirection.getStatusCode("/500"), "Status code should be 500.");
});
