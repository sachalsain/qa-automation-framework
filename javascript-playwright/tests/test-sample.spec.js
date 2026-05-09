import { test, expect } from "../src/core/base-test.js";

test("test_sample_page", async ({ page }) => {
  await page.goto("https://www.google.com", { waitUntil: "domcontentloaded" });
  await expect(page).toHaveTitle(/Google/);
});
