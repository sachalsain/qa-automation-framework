import allure
from playwright.sync_api import expect

from utils.logger import get_logger


class DashboardPage:
    def __init__(self, page):
        self.page = page
        self.logger = get_logger(self.__class__.__name__)

        self.dashboard_header = page.locator(".oxd-text--h6")
        self.user_dropdown = page.locator(".oxd-userdropdown-tab")
        self.logout_link = page.get_by_role("menuitem", name="Logout")

    @allure.step("Verify dashboard page is loaded")
    def verify_dashboard_loaded(self):
        self.logger.info("Verifying dashboard page is loaded")

        expect(self.dashboard_header).to_be_visible()
        expect(self.dashboard_header).to_have_text("Dashboard")

    @allure.step("Get dashboard header text")
    def get_dashboard_header_text(self):
        self.logger.info("Getting dashboard header text")

        return self.dashboard_header.inner_text()

    @allure.step("Open user dropdown menu")
    def open_user_menu(self):
        self.logger.info("Opening user dropdown menu")

        self.user_dropdown.click()

    @allure.step("Logout from application")
    def logout(self):
        self.logger.info("Logging out from application")

        self.open_user_menu()
        self.logout_link.click()
