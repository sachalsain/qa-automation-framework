import { BaseTest, test } from "../src/core/base-test.js";
import { PageShadowDom } from "../src/pages/page-shadow-dom.js";

test("test_shadow_dom", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const shadowDom = new PageShadowDom(page);
  await shadowDom.open(base.baseUrl);
  base.assertTextMatching("some different text!", (await shadowDom.getParagraphText()).toLowerCase(), "Paragraph text should match.");
  base.assertTextMatching("some different text!", (await shadowDom.getListText(1)).toLowerCase(), "List text should match.");
  base.assertTextMatching("a list!", (await shadowDom.getListText(2)).toLowerCase(), "List text should match.");
});
