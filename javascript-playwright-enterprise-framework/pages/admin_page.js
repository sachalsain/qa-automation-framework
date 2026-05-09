import { expect } from "@playwright/test";
import { getLogger } from "../utils/logger.js";

export class AdminPage {
  constructor(page) {
    this.page = page;
    this.logger = getLogger(this.constructor.name);

    this.adminMenu = page.getByRole("link", { name: "Admin" });
    this.pageHeader = page.locator("h6").filter({ hasText: "Admin" });
    this.usernameInput = page.locator(".oxd-form-row input.oxd-input").first();
    this.userRoleDropdown = page.locator(".oxd-select-text").first();
    this.searchButton = page.getByRole("button", { name: "Search" });
    this.resetButton = page.getByRole("button", { name: "Reset" });
    this.recordsFoundLabel = page.locator(".orangehrm-horizontal-padding span");
    this.resultRows = page.locator(".oxd-table-card");
    this.noRecordsLabel = page.getByText("No Records Found");
  }

  async navigateToAdmin() {
    this.logger.info("Navigating to Admin module");
    await this.adminMenu.click();
    await expect(this.pageHeader).toHaveText("Admin");
  }

  async enterUsername(username) {
    this.logger.info("Entering admin username search value");
    await this.usernameInput.fill(username);
  }

  async selectUserRole(role) {
    this.logger.info("Selecting user role: %s", role);
    await this.userRoleDropdown.click();
    await this.page.getByRole("option", { name: role }).click();
  }

  async clickSearch() {
    this.logger.info("Clicking Admin search button");
    await this.searchButton.click();
  }

  async clickReset() {
    this.logger.info("Clicking Admin reset button");
    await this.resetButton.click();
  }

  async searchUserByUsername(username) {
    await this.enterUsername(username);
    await this.clickSearch();
  }

  async searchUsersByRole(role) {
    await this.selectUserRole(role);
    await this.clickSearch();
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

  async verifyNoRecordsFound() {
    await expect(this.noRecordsLabel).toBeVisible();
  }
}
