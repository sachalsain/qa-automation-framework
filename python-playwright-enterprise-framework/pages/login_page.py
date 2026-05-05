import allure
from playwright.sync_api import expect

from utils.config_reader import get_config_value
from utils.logger import get_logger


class LoginPage:
    def __init__(self, page):
        self.page = page
        self.logger = get_logger(self.__class__.__name__)

        self.username_input = page.locator("input[name='username']")
        self.password_input = page.locator("input[name='password']")
        self.login_button = page.locator("button[type='submit']")
        self.error_message = page.locator(".oxd-alert-content-text")
        self.required_messages = page.locator(".oxd-input-field-error-message")
        self.login_logo = page.locator(".orangehrm-login-branding")

    @allure.step("Open login page")
    def open(self):
        base_url = get_config_value("app", "base_url")
        self.logger.info("Opening login page: %s", base_url)

        self.page.goto(base_url)

    @allure.step("Enter username")
    def enter_username(self, username):
        self.logger.info("Entering username")

        self.username_input.fill(username)

    @allure.step("Enter password")
    def enter_password(self, password):
        self.logger.info("Entering password")

        self.password_input.fill(password)

    @allure.step("Click login button")
    def click_login(self):
        self.logger.info("Clicking login button")

        self.login_button.click()

    @allure.step("Login with provided credentials")
    def login(self, username, password):
        self.enter_username(username)
        self.enter_password(password)
        self.click_login()

    @allure.step("Verify login page is displayed")
    def verify_login_page_loaded(self):
        expect(self.username_input).to_be_visible()
        expect(self.password_input).to_be_visible()
        expect(self.login_button).to_be_visible()

    @allure.step("Verify invalid credentials error message")
    def verify_invalid_credentials_message(self, expected_message):
        expect(self.error_message).to_be_visible()
        expect(self.error_message).to_have_text(expected_message)

    @allure.step("Verify required field validation message")
    def verify_required_message(self, expected_message="Required"):
        expect(self.required_messages.first).to_be_visible()
        expect(self.required_messages.first).to_have_text(expected_message)
