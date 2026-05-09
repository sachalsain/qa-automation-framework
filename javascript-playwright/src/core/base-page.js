import path from "node:path";
import { expect } from "@playwright/test";
import { getConfigValue } from "../configurations/configuration-loader.js";
import { loadDownloadsDir } from "../utilities/path-loader.js";
import { getLogger } from "../utilities/logger.js";

export class BasePage {
  constructor(page) {
    this.page = page;
    this.logger = getLogger(this.constructor.name);
  }

  async navigate(url) {
    this.logger.info(`Navigating to URL: ${url}`);
    await this.page.goto(url, { waitUntil: "domcontentloaded" });
  }

  async reloadPage() {
    await this.page.reload();
  }

  getUrl() {
    return this.page.url();
  }

  getElement(selector) {
    return this.page.locator(selector);
  }

  async getElements(selector) {
    const count = await this.page.locator(selector).count();
    return Array.from({ length: count }, (_, index) => this.page.locator(selector).nth(index));
  }

  async getElementCount(selector) {
    return await this.getElement(selector).count();
  }

  async getElementText(selector) {
    return await this.getElement(selector).textContent();
  }

  async getWindowElementText(window, selector) {
    return await window.locator(selector).textContent();
  }

  async createNewWindow(selector) {
    const [newPage] = await Promise.all([
      this.page.context().waitForEvent("page"),
      this.clickElement(selector)
    ]);
    await newPage.waitForLoadState();
    return newPage;
  }

  async clickElement(selector, button = "left") {
    await this.getElement(selector).click({ button });
  }

  async pressKeyOnKeyboard(key) {
    await this.page.keyboard.press(key);
  }

  async dragDrop(sourceSelector, targetSelector) {
    await this.getElement(sourceSelector).dragTo(this.getElement(targetSelector));
  }

  async selectDropdown(selector, value) {
    await this.getElement(selector).selectOption(value);
  }

  async hoverOn(selector) {
    await this.getElement(selector).hover();
  }

  async getTextSelectedDropdown(selector) {
    return await this.getElement(`${selector} option:checked`).textContent();
  }

  async fillValueInput(selector, text) {
    await this.getElement(selector).fill(String(text));
  }

  async getValueInput(selector) {
    return await this.getElement(selector).inputValue();
  }

  async getApiResponse(url) {
    return await this.page.request.head(url);
  }

  async postApiResponse(url, formData) {
    return await this.page.request.post(url, { form: formData });
  }

  async executeScript(locator, script, ...args) {
    return await locator.evaluate(script, ...args);
  }

  async waitForVisibilityChange(selector, state = "visible", timeout = 30000) {
    await this.getElement(selector).first().waitFor({ state, timeout });
  }

  async waitForEnabledChange(selector, state = "enabled", timeout = 30000) {
    if (state === "enabled") {
      await expect(this.getElement(selector).first()).toBeEnabled({ timeout });
      return;
    }
    if (state === "disabled") {
      await expect(this.getElement(selector).first()).toBeDisabled({ timeout });
      return;
    }
    throw new Error("Unsupported state. Use one of: enabled, disabled.");
  }

  async getOkDialogMessage(triggerAction) {
    const dialogPromise = new Promise((resolve) => {
      this.page.once("dialog", async (dialog) => {
        const message = dialog.message();

        if (dialog.type() === "prompt") {
          await dialog.accept(getConfigValue("promptText", "Playwright Java"));
        } else {
          await dialog.accept();
        }

        resolve(message);
      });
    });

    await triggerAction();
    return await dialogPromise;
  }

  async getCancelDialogMessage(triggerAction) {
    const dialogPromise = new Promise((resolve) => {
      this.page.once("dialog", async (dialog) => {
        const message = dialog.message();
        await dialog.dismiss();
        resolve(message);
      });
    });

    await triggerAction();
    return await dialogPromise;
  }

  async getFrameText(frameLocator) {
    const text = await this.page.frameLocator(frameLocator).locator("body").textContent();
    return text.trim();
  }

  async getNestedFrameText(parentFrameLocator, childFrameLocator) {
    const text = await this.page
      .frameLocator(parentFrameLocator)
      .frameLocator(childFrameLocator)
      .locator("body")
      .textContent();
    return text.trim();
  }

  async clickAndWaitForDownload(selector) {
    const [download] = await Promise.all([
      this.page.waitForEvent("download"),
      this.clickElement(selector)
    ]);
    const destination = path.join(loadDownloadsDir(), download.suggestedFilename());
    await download.saveAs(destination);
    return destination;
  }

  async waitForFunction(fn, ...args) {
    await this.page.waitForFunction(fn, ...args);
  }

  async uploadFile(selector, filePath) {
    await this.page.setInputFiles(selector, filePath);
  }
}
