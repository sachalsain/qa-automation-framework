import { test } from "../fixtures.js";
import { DashboardPage } from "../pages/dashboard_page.js";
import { LoginPage } from "../pages/login_page.js";
import { loadJson } from "../utils/data_reader.js";

const usersData = loadJson("test_data/users.json");

test.describe("Login", () => {
  test("valid login @smoke @regression @login", async ({ page }) => {
    const loginPage = new LoginPage(page);
    const dashboardPage = new DashboardPage(page);
    const validUser = usersData.valid_user;

    await loginPage.open();
    await loginPage.verifyLoginPageLoaded();
    await loginPage.login(validUser.username, validUser.password);

    await dashboardPage.verifyDashboardLoaded();
  });

  for (const userData of usersData.invalid_users) {
    test(`invalid login - ${userData.scenario} @regression @login`, async ({ page }) => {
      const loginPage = new LoginPage(page);

      await loginPage.open();
      await loginPage.verifyLoginPageLoaded();
      await loginPage.login(userData.username, userData.password);

      if (userData.username === "" || userData.password === "") {
        await loginPage.verifyRequiredMessage(userData.expected_error);
      } else {
        await loginPage.verifyInvalidCredentialsMessage(userData.expected_error);
      }
    });
  }

  test("logout @smoke @regression @login", async ({ page }) => {
    const loginPage = new LoginPage(page);
    const dashboardPage = new DashboardPage(page);
    const validUser = usersData.valid_user;

    await loginPage.open();
    await loginPage.login(validUser.username, validUser.password);
    await dashboardPage.verifyDashboardLoaded();

    await dashboardPage.logout();
    await loginPage.verifyLoginPageLoaded();
  });
});
