# Created on : Apr 4, 2026, 11:58:02 AM
# Author     : Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}

import pytest

from conftest import load_json_data
from src.pages.page_form_authentication_multi_user import PageFormAuthentication
from src.core.base_test import BaseTest
from src.utilities.logger import get_logger
from src.utilities.path_loader import load_data_dir


class TestFormAuthentication(BaseTest):
    logger = get_logger(__name__)
    LOGIN_DATA = load_json_data(load_data_dir("login_users.json"))

    @pytest.fixture(autouse=True)
    def setup(self, page, config, request):
        self.logger.info("Setting up test environment for TestFormAuthentication.")
        self.setup_test(page, config, request)
        self.logger.debug("Creating PageFormAuthentication instance.")
        self.page_form_authentication = PageFormAuthentication(page)

    @pytest.mark.parametrize(
        "test_data",
        LOGIN_DATA,
        ids=[f"{item['username'] or 'empty'}-login" for item in LOGIN_DATA],
    )
    def test_multiple_user_login(self, test_data):
        self.logger.info("Testing Form Authentication with multiple user credentials.")
        self.logger.debug("Open Form Authentication page.")
        self.page_form_authentication.open(self.base_url)

        username = test_data["username"]
        password = test_data["password"]
        expected_message = test_data["expected_message"].lower()

        self.log_step(
            f"Attempting login with username: {username} and password: {password}"
        )
        self.page_form_authentication.login(username, password)

        self.log_step("Getting the message displayed after login attempt.")
        actual_message = self.page_form_authentication.get_message().lower()

        self.log_step("Verifying the login message.")
        self.assert_text_matching(
            expected_message,
            actual_message,
            f"Expected message to contain '{expected_message}' but got '{actual_message}'",
        )
        self.logger.debug("Verified that the login message is as expected.")
