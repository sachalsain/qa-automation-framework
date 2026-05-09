import { test } from "../fixtures.js";
import { AdminPage } from "../pages/admin_page.js";
import { DashboardPage } from "../pages/dashboard_page.js";
import { LoginPage } from "../pages/login_page.js";
import { loadJson } from "../utils/data_reader.js";

const usersData = loadJson("test_data/users.json");

async function loggedInPage(page) {
  const loginPage = new LoginPage(page);
  const dashboardPage = new DashboardPage(page);
  const validUser = usersData.valid_user;

  await loginPage.open();
  await loginPage.login(validUser.username, validUser.password);
  await dashboardPage.verifyDashboardLoaded();

  return page;
}

test.describe("Admin", () => {
  test("search user by username @smoke @regression @admin", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const adminPage = new AdminPage(pageUnderTest);

    await adminPage.navigateToAdmin();
    await adminPage.searchUserByUsername(usersData.admin_search.username);
    await adminPage.verifyResultsDisplayed();
    await adminPage.verifyTableContainsText(usersData.admin_search.username);
  });

  test("search users by role @regression @admin", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const adminPage = new AdminPage(pageUnderTest);

    await adminPage.navigateToAdmin();
    await adminPage.searchUsersByRole(usersData.admin_search.role);
    await adminPage.verifyResultsDisplayed();
    await adminPage.verifyTableContainsText(usersData.admin_search.role);
  });

  test("reset admin search @regression @admin", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const adminPage = new AdminPage(pageUnderTest);

    await adminPage.navigateToAdmin();
    await adminPage.searchUserByUsername(usersData.admin_search.username);
    await adminPage.verifyResultsDisplayed();

    await adminPage.clickReset();
    await adminPage.verifyResultsDisplayed();
  });
});
