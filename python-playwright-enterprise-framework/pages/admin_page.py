import time

import allure
from playwright.sync_api import expect

from utils.logger import get_logger


class AdminPage:
    def __init__(self, page):
        self.page = page
        self.logger = get_logger(self.__class__.__name__)

        self.admin_menu = page.get_by_role("link", name="Admin")
        self.page_header = page.locator("h6").filter(
            has_text="Admin"
        )
        self.username_input = page.locator(".oxd-form-row input.oxd-input").first
        self.user_role_dropdown = page.locator(".oxd-select-text").first
        self.search_button = page.get_by_role("button", name="Search")
        self.reset_button = page.get_by_role("button", name="Reset")
        self.records_found_label = page.locator(".orangehrm-horizontal-padding span")
        self.result_rows = page.locator(".oxd-table-card")
        self.no_records_label = page.get_by_text("No Records Found")

    @allure.step("Navigate to Admin module")
    def navigate_to_admin(self):
        self.logger.info("Navigating to Admin module")
        self.admin_menu.click()
        expect(self.page_header).to_have_text("Admin")

    @allure.step("Enter admin username search value")
    def enter_username(self, username):
        self.logger.info("Entering admin username search value")

        self.username_input.fill(username)

    @allure.step("Select user role")
    def select_user_role(self, role):
        self.logger.info("Selecting user role: %s", role)

        self.user_role_dropdown.click()
        self.page.get_by_role("option", name=role).click()

    @allure.step("Click Admin search button")
    def click_search(self):
        self.logger.info("Clicking Admin search button")

        self.search_button.click()

    @allure.step("Click Admin reset button")
    def click_reset(self):
        self.logger.info("Clicking Admin reset button")

        self.reset_button.click()

    @allure.step("Search user by username")
    def search_user_by_username(self, username):
        self.enter_username(username)
        self.click_search()

    @allure.step("Search users by role")
    def search_users_by_role(self, role):
        self.select_user_role(role)
        self.click_search()

    @allure.step("Verify Admin results are displayed")
    def verify_results_displayed(self):
        expect(self.records_found_label).to_be_visible()

        records_text = self.records_found_label.inner_text()

        assert "Record Found" in records_text or "Records Found" in records_text

    @allure.step("Verify Admin table contains text")
    def verify_table_contains_text(self, expected_text):
        expect(self.result_rows.first).to_be_visible()

        table_text = self.result_rows.first.inner_text()

        assert expected_text in table_text

    @allure.step("Verify no Admin records found")
    def verify_no_records_found(self):
        expect(self.no_records_label).to_be_visible()
