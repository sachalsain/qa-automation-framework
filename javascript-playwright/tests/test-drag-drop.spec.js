import { BaseTest, test } from "../src/core/base-test.js";
import { PageDragDrop } from "../src/pages/page-drag-drop.js";

test("test_page_drag_drop", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const dragDrop = new PageDragDrop(page);
  await dragDrop.open(base.baseUrl);
  await dragDrop.performDragDrop();
  base.assertTextMatching("B", await dragDrop.getTextOfBoxA(), "Box A should contain B after drag and drop.");
  base.assertTextMatching("A", await dragDrop.getTextOfBoxB(), "Box B should contain A after drag and drop.");
});
