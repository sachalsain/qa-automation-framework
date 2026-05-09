import { BaseTest, test } from "../src/core/base-test.js";
import { PageBrokenImages } from "../src/pages/page-broken-images.js";

test("test_broken_images", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const brokenImages = new PageBrokenImages(page);
  await brokenImages.open(base.baseUrl);
  await base.assertVisible(brokenImages.getImageContainer(), "Images container should be visible.");
  const images = await brokenImages.getAllImages();
  const failedImages = [];
  for (const img of images) {
    const loaded = await brokenImages.runJsToCheckImgComplete(img, (image) => image.complete && typeof image.naturalWidth === "number" && image.naturalWidth > 0);
    if (!loaded) failedImages.push(await img.getAttribute("src"));
  }
  base.assertSizeOfList(0, failedImages.length, `Images with src '${failedImages}' should be loaded.`);
});

test("test_broken_images_API", async ({ page, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(page, config, testInfo);
  const brokenImages = new PageBrokenImages(page);
  await brokenImages.open(base.baseUrl);
  const images = await brokenImages.getAllImages();
  const failedImages = [];
  for (const img of images) {
    const src = await img.getAttribute("src");
    const response = await brokenImages.getStatusCode(base.baseUrl, src);
    if (response.status() !== 200) failedImages.push(src);
  }
  base.assertSizeOfList(0, failedImages.length, `Images with src '${failedImages}' should return 200 status code.`);
});
