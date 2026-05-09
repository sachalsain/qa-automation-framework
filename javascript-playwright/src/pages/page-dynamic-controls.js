import { BasePage } from "../core/base-page.js";

export class PageDynamicControls extends BasePage {
  PAGE_PATH = "/dynamic_controls";
  CHECKBOX = "#checkbox-example input[type='checkbox']";
  BTN_CHECKBOX = "#checkbox-example button[type='button']";
  MSG_CHECKBOX = "#checkbox-example #message";
  LOAD_CHECKBOX = "#checkbox-example #loading";
  TEXTBOX = "#input-example input[type='text']";
  BTN_TEXTBOX = "#input-example button[type='button']";
  MSG_TEXTBOX = "#input-example #message";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async reload() { await this.reloadPage(); }
  getCheckbox() { return this.getElement(this.CHECKBOX); }
  getTextbox() { return this.getElement(this.TEXTBOX); }
  async clickCheckboxBtn(state = "hidden") {
    await this.clickElement(this.BTN_CHECKBOX);
    await this.waitForVisibilityChange(this.LOAD_CHECKBOX, state);
  }
  async getCheckboxMsg() { return (await this.getElementText(this.MSG_CHECKBOX)).trim(); }
  async clickTextboxBtn(button = "left", state = "enabled") {
    await this.clickElement(this.BTN_TEXTBOX, button);
    await this.waitForEnabledChange(this.TEXTBOX, state);
  }
  async getTextboxMsg() { return (await this.getElementText(this.MSG_TEXTBOX)).trim(); }
  async enterValueInTextbox(text) { await this.fillValueInput(this.TEXTBOX, text); }
  async getTextboxValue() { return await this.getValueInput(this.TEXTBOX); }
}
