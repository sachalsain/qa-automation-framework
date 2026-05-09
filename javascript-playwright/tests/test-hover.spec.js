import { BaseTest, test } from "../src/core/base-test.js";
import { PageHover } from "../src/pages/page-hover.js";

test("test_hover_over_picture", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const hover = new PageHover(page);
  await hover.open(base.baseUrl);
  base.assertTextMatching("user1", (await hover.getDisplayedProfileName(1)).toLowerCase(), "First profile should be user1.");
  base.assertTextMatching("user2", (await hover.getDisplayedProfileName(2)).toLowerCase(), "Second profile should be user2.");
  base.assertTextMatching("user3", (await hover.getDisplayedProfileName(3)).toLowerCase(), "Third profile should be user3.");
});
