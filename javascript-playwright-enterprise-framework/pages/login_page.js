import { expect } from "@playwright/test";
import { getConfigValue } from "../utils/config_reader.js";
import { getLogger } from "../utils/logger.js";

export class LoginPage {
  constructor(page) {
    this.page = page;
    this.logger = getLogger(this.constructor.name);

    this.usernameInput = page.locator("input[name='username']");
    this.passwordInput = page.locator("input[name='password']");
    this.loginButton = page.locator("button[type='submit']");
    this.errorMessage = page.locator(".oxd-alert-content-text");
    this.requiredMessages = page.locator(".oxd-input-field-error-message");
    this.loginLogo = page.locator(".orangehrm-login-branding");
  }

  async open() {
    const baseUrl = getConfigValue("app", "base_url", "https://opensource-demo.orangehrmlive.com/");
    this.logger.info("Opening login page: %s", baseUrl);
    await this.page.goto(baseUrl, { waitUntil: "domcontentloaded" });
  }

  async enterUsername(username) {
    this.logger.info("Entering username");
    await this.usernameInput.fill(username);
  }

  async enterPassword(password) {
    this.logger.info("Entering password");
    await this.passwordInput.fill(password);
  }

  async clickLogin() {
    this.logger.info("Clicking login button");
    await this.loginButton.click();
  }

  async login(username, password) {
    await this.enterUsername(username);
    await this.enterPassword(password);
    await this.clickLogin();
  }

  async verifyLoginPageLoaded() {
    await expect(this.usernameInput).toBeVisible();
    await expect(this.passwordInput).toBeVisible();
    await expect(this.loginButton).toBeVisible();
  }

  async verifyInvalidCredentialsMessage(expectedMessage) {
    await expect(this.errorMessage).toBeVisible();
    await expect(this.errorMessage).toHaveText(expectedMessage);
  }

  async verifyRequiredMessage(expectedMessage = "Required") {
    await expect(this.requiredMessages.first()).toBeVisible();
    await expect(this.requiredMessages.first()).toHaveText(expectedMessage);
  }
}
