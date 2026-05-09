import { expect } from "@playwright/test";
import { getLogger } from "../utils/logger.js";

export class DashboardPage {
  constructor(page) {
    this.page = page;
    this.logger = getLogger(this.constructor.name);

    this.dashboardHeader = page.locator(".oxd-text--h6");
    this.userDropdown = page.locator(".oxd-userdropdown-tab");
    this.logoutLink = page.getByRole("menuitem", { name: "Logout" });
  }

  async verifyDashboardLoaded() {
    this.logger.info("Verifying dashboard page is loaded");
    await expect(this.dashboardHeader).toBeVisible();
    await expect(this.dashboardHeader).toHaveText("Dashboard");
  }

  async getDashboardHeaderText() {
    this.logger.info("Getting dashboard header text");
    return this.dashboardHeader.innerText();
  }

  async openUserMenu() {
    this.logger.info("Opening user dropdown menu");
    await this.userDropdown.click();
  }

  async logout() {
    this.logger.info("Logging out from application");
    await this.openUserMenu();
    await this.logoutLink.click();
  }
}
