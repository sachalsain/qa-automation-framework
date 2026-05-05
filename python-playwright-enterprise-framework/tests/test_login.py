import allure
import pytest

from pages.dashboard_page import DashboardPage
from pages.login_page import LoginPage
from utils.data_reader import load_json


users_data = load_json("test_data/users.json")

@allure.feature("Login")
@allure.story("Valid login")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.regression
@pytest.mark.login
def test_valid_login(page):
    login_page = LoginPage(page)
    dashboard_page = DashboardPage(page)

    valid_user = users_data["valid_user"]

    login_page.open()
    login_page.verify_login_page_loaded()
    login_page.login(valid_user["username"], valid_user["password"])

    dashboard_page.verify_dashboard_loaded()

@allure.feature("Login")
@allure.story("Invalid login")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.regression
@pytest.mark.login
@pytest.mark.parametrize("user_data", users_data["invalid_users"])
def test_invalid_login(page, user_data):
    login_page = LoginPage(page)

    login_page.open()
    login_page.verify_login_page_loaded()
    login_page.login(user_data["username"], user_data["password"])

    if user_data["username"] == "" or user_data["password"] == "":
        login_page.verify_required_message(user_data["expected_error"])
    else:
        login_page.verify_invalid_credentials_message(user_data["expected_error"])


@allure.feature("Login")
@allure.story("Logout")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.regression
@pytest.mark.login
def test_logout(page):
    login_page = LoginPage(page)
    dashboard_page = DashboardPage(page)

    valid_user = users_data["valid_user"]

    login_page.open()
    login_page.login(valid_user["username"], valid_user["password"])
    dashboard_page.verify_dashboard_loaded()

    dashboard_page.logout()
    login_page.verify_login_page_loaded()
