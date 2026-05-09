import { BasePage } from "../core/base-page.js";

export class PageFrameNested extends BasePage {
  PAGE_PATH = "/nested_frames";
  TOP_FRAME = "frame[name='frame-top']";
  TOP_LEFT_FRAME = "frame[name='frame-left']";
  TOP_MIDDLE_FRAME = "frame[name='frame-middle']";
  TOP_RIGHT_FRAME = "frame[name='frame-right']";
  BOTTOM_FRAME = "frame[name='frame-bottom']";

  async open(baseUrl) { await this.navigate(`${baseUrl}${this.PAGE_PATH}`); }
  async getFrameContent(frame) {
    if (frame === "left") return await this.getNestedFrameText(this.TOP_FRAME, this.TOP_LEFT_FRAME);
    if (frame === "middle") return await this.getNestedFrameText(this.TOP_FRAME, this.TOP_MIDDLE_FRAME);
    if (frame === "right") return await this.getNestedFrameText(this.TOP_FRAME, this.TOP_RIGHT_FRAME);
    if (frame === "bottom") return await this.getFrameText(this.BOTTOM_FRAME);
    throw new Error("Frame not found");
  }
}
