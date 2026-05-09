import { BaseTest, test } from "../src/core/base-test.js";
import { PageDigestAuthentication } from "../src/pages/page-digest-authentication.js";

test("test_digest_authentication", async ({ authenticatedPage, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(authenticatedPage, config, testInfo);
  const digest = new PageDigestAuthentication(authenticatedPage);
  await digest.open(base.baseUrl);
  base.assertTextMatching("digest auth", (await digest.getHeadingText()).toLowerCase(), "Heading must contain Digest Auth");
  base.assertTextMatching("congratulations", (await digest.getParagraphText()).toLowerCase(), "Paragraph must contain Congratulations");
});
