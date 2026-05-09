import { BaseTest, test } from "../src/core/base-test.js";
import { PageInfiniteScroll } from "../src/pages/page-infinite-scroll.js";

test("test_infinite_scroll", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const infiniteScroll = new PageInfiniteScroll(page);
  await infiniteScroll.open(base.baseUrl);
  const initialCount = await infiniteScroll.getParaCount();
  await infiniteScroll.scrollDown();
  base.assertGreater(initialCount, await infiniteScroll.getParaCount(), "Paragraph count should increase after scrolling.");
});
