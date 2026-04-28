# Created on : Mar 28, 2026, 5:45:06 PM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}


from pathlib import Path
from typing import Literal

from playwright.sync_api import Locator, Page, Response, expect

from src.utilities.logger import get_logger
from src.utilities.path_loader import load_downloads_dir
from src.configurations.configuration_loader import ConfigurationLoader


class BasePage:
    logger = get_logger(__name__)

    def __init__(self, page):
        self.logger.info(
            "Initializing BasePage with provided Playwright page instance."
        )
        self.page = page
        
    def on_page_error(self, callback):
        self.logger.info("Registering page error listener.")
        self.page.on("pageerror", callback)

    def navigate(self, url: str):
        self.logger.info(f"Navigating to URL: {url}")
        self.page.goto(url, wait_until="domcontentloaded")

    def reload_page(self):
        self.logger.info("Reloading the current page.")
        self.page.reload()

    def get_url(self) -> str:
        self.logger.info("Returning page URL.")
        return self.page.url

    def create_new_window(self, selector: str):
        self.logger.info(f"Creating a new window by clicking element with selector: {selector}")
        with self.page.context.expect_page() as page_info:
            self.click_element(selector)
        new_page = page_info.value
        self.logger.debug("Waiting for the new page to load.")
        new_page.wait_for_load_state()
        self.logger.debug("Returning the new page instance.")
        return new_page

    def get_element(self, selector: str) -> Locator:
        self.logger.info(f"Getting element: {selector}")
        return self.page.locator(selector)

    def get_elements(self, selector: str) -> list[Locator]:
        self.logger.info(f"Getting all elements with selector: {selector}")
        return self.page.locator(selector).all()

    def get_element_count(self, selector: str) -> int:
        self.logger.info(f"Returning count of elements with selector: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        self.logger.debug("Returning number of elements.")
        return element.count()

    def get_element_text(self, selector: str) -> str | None:
        self.logger.info(f"Returning text from element with selector: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        self.logger.debug(f"Returning text content of selector: {selector}")
        return element.text_content()

    def get_window_element_text(self, window: Page, selector: str) -> str | None:
        self.logger.info(f"Returning text from element of window: {window} with selector: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = window.locator(selector)
        self.logger.debug(f"Returning text content of selector: {selector}")
        return element.text_content()

    def is_element_enabled(self, selector: str) -> bool:
        self.logger.info(f"Checking if element with selector: {selector} is enabled")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        return element.is_enabled()

    def is_element_disabled(self, selector: str) -> bool:
        self.logger.info(f"Checking if element with selector: {selector} is disabled")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        return element.is_disabled()

    def get_ok_dialog_message(self, trigger_action) -> str | None:
        self.logger.info("Waiting for dialog and capturing its OK message.")
        message = {"text": None}

        def handle_dialog(dialog):
            message["text"] = dialog.message
            dialog.accept(
                ConfigurationLoader.get("promptText", "Playwright Java")
            ) if dialog.type == "prompt" else dialog.accept()

        self.page.once("dialog", handle_dialog)
        trigger_action()

        self.logger.debug(f"Returning Captured dialog message: {message['text']}")
        return message["text"]

    def get_cancel_dialog_message(self, trigger_action) -> str | None:
        self.logger.info("Waiting for dialog and capturing its OK message.")
        message = {"text": None}

        def handle_dialog(dialog):
            message["text"] = dialog.message
            dialog.dismiss()

        self.page.once("dialog", handle_dialog)
        trigger_action()

        self.logger.debug(f"Returning Captured dialog message: {message['text']}")
        return message["text"]

    def get_frame_text(self, frame_locator: str):
        self.logger.info(f"Getting frame with locator: {frame_locator}")
        return (
            self.page.frame_locator(frame_locator)
            .locator("body")
            .text_content()
            .strip()
        )

    def get_nested_frame_text(
        self, parent_frame_locator: str, child_frame_locator: str
    ):
        self.logger.info(
            f"Getting nested frame with parent locator: {parent_frame_locator} and child locator: {child_frame_locator}"
        )
        return (
            self.page.frame_locator(parent_frame_locator)
            .frame_locator(child_frame_locator)
            .locator("body")
            .text_content()
            .strip()
        )

    def click_element(
        self, selector: str, button: Literal["left", "middle", "right"] = "left"
    ):
        self.logger.info(f"Clicking on element: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        self.logger.debug(f"Performing {button} Click action on {selector} ")
        element.click(button=button)

    def press_key_on_keyboard(self, key: str):
        self.logger.info(f"Pressing key: {key}")
        self.page.keyboard.press(key)

    def drag_drop(self, source_selector: str, target_selector: str):
        self.logger.info(
            f"Performing drag and drop from {source_selector} to {target_selector}"
        )

        self.logger.debug(f"Requesting element with selector: {source_selector}")
        src_element = self.get_element(source_selector)
        self.logger.debug(f"Requesting element with selector: {target_selector}")
        tagert_element = self.get_element(target_selector)

        self.logger.debug(f"Draging element: {source_selector} to {target_selector}")
        src_element.drag_to(tagert_element)

    def select_dropdown(self, selector: str, value: str):
        self.logger.info(
            f"Selecting option with value: {value} from element with selector: {selector}"
        )
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        self.logger.debug(f"Selecting {value} from {selector}dropdown.")
        element.select_option(value)

    def hover_on(self, selector: str):
        self.logger.info(f"Hovering over element with selector: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        element.hover()

    def get_text_selected_dropdown(self, selector: str) -> str | None:
        self.logger.info(
            f"Getting selected option text from element with selector: {selector}"
        )
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(f"{selector} option:checked")
        self.logger.debug(
            f"Returning text content of slected option from {selector} dropdown."
        )
        return element.text_content()

    def fill_value_input(self, selector: str, text: str):
        self.logger.info(f"Filling element: {selector} with text: {text}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        element.fill(text)

    def get_value_input(self, selector: str) -> str:
        self.logger.info(f"Getting input value from element with selector: {selector}")
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector)
        return element.input_value()

    def get_API_response(self, url) -> Response:
        self.logger.info(f"Getting API response for link: '{url}'")
        return self.page.request.head(url)

    def post_API_response(self, url, form_data: dict) -> Response:
        self.logger.info(f"Getting API response for link: '{url}'")
        return self.page.request.post(url, form=form_data)

    def execute_script(self, locator: Locator, script: str, *args) -> bool:
        self.logger.info(f"Executing script: {script}")
        return locator.evaluate(script, *args)

    def wait_for_visibility_change(
        self,
        selector: str,
        state: Literal["attached", "detached", "hidden", "visible"] = "visible",
        timeout: int = 30000,
    ):
        self.logger.info(
            f"Waiting for visibility state change of element with locator: {selector}"
        )
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector).first
        if state in {"attached", "detached", "visible", "hidden"}:
            element.wait_for(state=state, timeout=timeout)
            return
        raise ValueError(
            "Unsupported state. Use one of: attached, detached, hidden, visible."
        )

    def wait_for_enabled_change(
        self,
        selector: str,
        state: Literal["enabled", "disabled"] = "enabled",
        timeout: int = 30000,
    ):
        self.logger.info(
            f"Waiting for enabled state change of element with locator: {selector}"
        )
        self.logger.debug(f"Requesting element with selector: {selector}")
        element = self.get_element(selector).first
        if state == "enabled":
            expect(element).to_be_enabled(timeout=timeout)
            return
        if state == "disabled":
            expect(element).to_be_disabled(timeout=timeout)
            return
        raise ValueError("Unsupported state. Use one of: enabled, disabled.")

    def click_and_wait_for_download(self, selector: str) -> Path:
        self.logger.info(
            f"Clicking element with selector: {selector} and waiting for download to complete."
        )
        with self.page.expect_download() as download_info:
            self.click_element(selector)
        download = download_info.value
        self.logger.debug("Defining target directory.")
        target_dir = load_downloads_dir()
        self.logger.debug("Ensuring directory exists.")
        target_dir.mkdir(parents=True, exist_ok=True)
        destination = target_dir / download.suggested_filename
        self.logger.debug(f"Saving downloaded file to {destination}.")
        download.save_as(destination)
        self.logger.debug("Returning path to downloaded file.")
        return destination

    def wait_for_function(self, function: str, *args):
        self.logger.info("Waiting for function to return a truthy value.")
        self.page.wait_for_function(function, *args)

    def upload_file(self, selector: str, file_path: Path):
        self.logger.info(f"Uploading file from path: {file_path.as_posix()}")
        self.page.set_input_files(selector, file_path)
