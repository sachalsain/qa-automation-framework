import { BaseTest, test } from "../src/core/base-test.js";
import { PageAddRemoveElements } from "../src/pages/page-add-remove-elements.js";

test("test_add_remove_visiblity", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const addRemove = new PageAddRemoveElements(page);
  await addRemove.open(base.baseUrl);
  await base.assertVisible(addRemove.getAddButton(), "Add Element button should be visible by default.");
  await base.assertHidden(addRemove.getDeleteButton(), "Delete Element button should be hidden by default.");
  await addRemove.clickAddButton();
  await base.assertVisible(addRemove.getDeleteButton(), "Delete Element button should be visible after clicking Add button.");
  await addRemove.clickDeleteButton();
  await base.assertHidden(addRemove.getDeleteButton(), "Delete Element button should be hidden after clicking Delete button.");
});
