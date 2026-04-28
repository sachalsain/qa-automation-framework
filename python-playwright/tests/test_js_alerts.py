# Created on : Apr 6, 2026, 9:01:03 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from src.core.base_test import BaseTest
from src.pages.page_js_alerts import PageJSAlerts
from src.utilities.logger import get_logger


class TestJSAlerts(BaseTest):
    logger = get_logger(__name__)

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestJSAlerts.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageJSAlerts instance.")
        self.page_js_alerts = PageJSAlerts(page)

    def test_page_js_alerts(self):
        self.logger.info("Testing JS Alerts page.")
        self.logger.debug("Open JS Alerts page.")
        self.page_js_alerts.open(self.base_url)

        self.logger.debug("Clicking Alert Button to test.")
        message = str(self.page_js_alerts.get_message_of_alert())

        self.log_step("Verify that proper Alert Message is shown.")
        self.logger.debug("Verifying if proper Alert Message is shown.")
        self.assert_text_matching(
            "I am a JS Alert".lower(),
            message.lower(),
            "The message must display 'I am a JS Alert'",
        )
        self.logger.debug("Verified that proper Alert Message is shown.")

        self.log_step("Verify that proper Alert Result is shown.")
        self.logger.debug("Verifying if proper Alert Result is shown.")
        self.assert_text_matching(
            "You successfully clicked an alert".lower(),
            self.page_js_alerts.get_result().lower(),
            "You successfully clicked an alert",
        )
        self.logger.debug("Verified that proper Alert Result is shown.")

        self.log_step("Clicking Confirm Button to test OK.")
        message = str(self.page_js_alerts.get_ok_message_of_confirm())

        self.log_step("Verify that proper Confirm Message is shown for OK.")
        self.logger.debug("Verifying if proper Confirm Message is shown for OK.")
        self.assert_text_matching(
            "I am a JS Confirm".lower(),
            message.lower(),
            "The message must display 'I am a JS Confirm'",
        )
        self.logger.debug("Verified that proper Confirm Message is shown for OK.")

        self.log_step("Verify that proper Confirm Result is shown for OK.")
        self.logger.debug("Verifying if proper Confirm Result is shown for OK.")
        self.assert_text_matching(
            "You clicked: Ok".lower(),
            self.page_js_alerts.get_result().lower(),
            "The message must display 'You clicked: Ok'",
        )
        self.logger.debug("Verified that proper Confirm Message is shown for OK.")

        self.log_step("Clicking Confirm Button to test Cancel.")
        message = str(self.page_js_alerts.get_cancel_message_of_confirm())

        self.log_step("Verify that proper Confirm Result is shown for Cancel.")
        self.logger.debug("Verifying if proper Confirm Result is shown for Cancel.")
        self.assert_text_matching(
            "You clicked: Cancel".lower(),
            self.page_js_alerts.get_result().lower(),
            "The message must display 'You clicked: Cancel'",
        )
        self.logger.debug("Verified that proper Confirm Message is shown for Cancel.")

        self.log_step("Clicking Prompt Button to test OK.")
        message = str(self.page_js_alerts.get_ok_message_of_prompt())

        self.log_step("Verify that proper Prompt Result is shown for OK.")
        self.logger.debug("Verifying if proper Prompt Result is shown for OK.")
        self.assert_text_matching(
            "You entered: Playwright Java".lower(),
            self.page_js_alerts.get_result().lower(),
            "The message must display 'You entered: Playwright Java'",
        )
        self.logger.debug("Verified that proper Prompt Message is shown for OK.")

        self.log_step("Clicking Prompt Button to test Cancel.")
        message = str(self.page_js_alerts.get_cancel_message_of_prompt())

        self.log_step("Verify that proper Prompt Result is shown for Cancel.")
        self.logger.debug("Verifying if proper Prompt Result is shown for Cancel.")
        self.assert_text_matching(
            "You entered: null".lower(),
            self.page_js_alerts.get_result().lower(),
            "The message must display 'You entered: null'",
        )
        self.logger.debug("Verified that proper Prompt Message is shown for Cancel.")
