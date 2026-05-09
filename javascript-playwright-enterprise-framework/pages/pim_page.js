import { expect } from "@playwright/test";
import { getLogger } from "../utils/logger.js";

export class PimPage {
  constructor(page) {
    this.page = page;
    this.logger = getLogger(this.constructor.name);

    this.pimMenu = page.getByRole("link", { name: "PIM" });
    this.pageHeader = page.locator("h6");
    this.pageHeading = page.locator("h6.orangehrm-main-title");

    this.addButton = page.getByRole("button", { name: "Add" });
    this.firstNameInput = page.locator("input[name='firstName']");
    this.middleNameInput = page.locator("input[name='middleName']");
    this.lastNameInput = page.locator("input[name='lastName']");
    this.employeeIdInput = page.locator(".oxd-grid-2 input.oxd-input").first();
    this.saveButton = page.getByRole("button", { name: "Save" });

    this.employeeNameInput = page.locator("div.oxd-grid-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");
    this.searchButton = page.getByRole("button", { name: "Search" });
    this.resetButton = page.getByRole("button", { name: "Reset" });

    this.recordsFoundLabel = page.locator(".orangehrm-horizontal-padding span");
    this.resultRows = page.locator(".oxd-table-card");
    this.employeeDetailsHeader = page.locator("h6").filter({ hasText: "Personal Details" });
  }

  async navigateToPim() {
    this.logger.info("Navigating to PIM module");
    await this.pimMenu.click();
    await expect(this.pageHeader).toHaveText("PIM");
  }

  async clickAddEmployee() {
    this.logger.info("Clicking Add Employee button");
    await this.addButton.click();
    await expect(this.pageHeading).toHaveText("Add Employee");
  }

  async enterFirstName(firstName) {
    this.logger.info("Entering employee first name");
    await this.firstNameInput.fill(firstName);
  }

  async enterMiddleName(middleName) {
    this.logger.info("Entering employee middle name");
    await this.middleNameInput.fill(middleName);
  }

  async enterLastName(lastName) {
    this.logger.info("Entering employee last name");
    await this.lastNameInput.fill(lastName);
  }

  async enterEmployeeId(employeeId) {
    this.logger.info("Entering employee ID");
    await this.employeeIdInput.fill(employeeId);
  }

  async clickSave() {
    this.logger.info("Saving employee");
    await this.saveButton.click();
  }

  async addEmployee(firstName, middleName, lastName, employeeId) {
    await this.clickAddEmployee();
    await this.enterFirstName(firstName);
    await this.enterMiddleName(middleName);
    await this.enterLastName(lastName);
    await this.enterEmployeeId(employeeId);
    await this.clickSave();
  }

  async verifyEmployeeDetailsLoaded() {
    this.logger.info("Verifying employee details page is loaded");
    await this.employeeDetailsHeader.waitFor({ state: "visible", timeout: 20000 });
    await expect(this.employeeDetailsHeader).toBeVisible();
  }

  async searchEmployeeByName(employeeName) {
    this.logger.info("Searching employee by name: %s", employeeName);
    await this.employeeNameInput.fill(employeeName);
    await this.page.keyboard.press("ArrowDown");
    await this.page.keyboard.press("Enter");
    await this.searchButton.click();
  }

  async verifyResultsDisplayed() {
    await expect(this.recordsFoundLabel).toBeVisible();
    const recordsText = await this.recordsFoundLabel.innerText();
    expect(recordsText.includes("Record Found") || recordsText.includes("Records Found")).toBeTruthy();
  }

  async verifyTableContainsText(expectedText) {
    await expect(this.resultRows.first()).toBeVisible();
    const tableText = await this.resultRows.first().innerText();
    expect(tableText).toContain(expectedText);
  }

  async clickReset() {
    this.logger.info("Clicking PIM reset button");
    await this.resetButton.click();
  }
}
