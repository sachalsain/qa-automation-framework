import { BaseTest, test } from "../src/core/base-test.js";
import { PageGeolocation } from "../src/pages/page-geolocation.js";

test("test_geolocation", async ({ geolocationPage, config }, testInfo) => {
  const base = new BaseTest();
  base.setupTest(geolocationPage, config, testInfo);
  const geolocation = new PageGeolocation(geolocationPage);
  await geolocation.open(base.baseUrl);
  await geolocation.clickLocationBtn();
  const [latitude, longitude] = await geolocation.getGeolocation();
  base.assertFloatMatching(config.geolocation_latitude, latitude, "Latitude should match configured value.");
  base.assertFloatMatching(config.geolocation_longitude, longitude, "Longitude should match configured value.");
});
