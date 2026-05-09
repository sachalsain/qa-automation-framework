import { test } from "../fixtures.js";
import { DashboardPage } from "../pages/dashboard_page.js";
import { LoginPage } from "../pages/login_page.js";
import { PimPage } from "../pages/pim_page.js";
import { loadJson } from "../utils/data_reader.js";

const usersData = loadJson("test_data/users.json");
const employeesData = loadJson("test_data/employees.json");

async function loggedInPage(page) {
  const loginPage = new LoginPage(page);
  const dashboardPage = new DashboardPage(page);
  const validUser = usersData.valid_user;

  await loginPage.open();
  await loginPage.login(validUser.username, validUser.password);
  await dashboardPage.verifyDashboardLoaded();

  return page;
}

function generateEmployeeId(prefix) {
  const now = new Date();
  const timestamp = [now.getHours(), now.getMinutes(), now.getSeconds()]
    .map((value) => String(value).padStart(2, "0"))
    .join("");
  return `${prefix}${timestamp}`;
}

test.describe("PIM", () => {
  test("add employee @smoke @regression @pim", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const pimPage = new PimPage(pageUnderTest);
    const employee = employeesData.employee;
    const employeeId = generateEmployeeId(employee.employee_id_prefix);

    await pimPage.navigateToPim();
    await pimPage.addEmployee(
      employee.first_name,
      employee.middle_name,
      employee.last_name,
      employeeId
    );
    await pimPage.verifyEmployeeDetailsLoaded();
  });

  test("search employee @regression @pim", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const pimPage = new PimPage(pageUnderTest);
    const employeeName = employeesData.search.employee_name;

    await pimPage.navigateToPim();
    await pimPage.searchEmployeeByName(employeeName);
    await pimPage.verifyResultsDisplayed();
  });

  test("reset employee search @regression @pim", async ({ page }) => {
    const pageUnderTest = await loggedInPage(page);
    const pimPage = new PimPage(pageUnderTest);
    const employeeName = employeesData.search.employee_name;

    await pimPage.navigateToPim();
    await pimPage.searchEmployeeByName(employeeName);
    await pimPage.verifyResultsDisplayed();

    await pimPage.clickReset();
    await pimPage.verifyResultsDisplayed();
  });
});
