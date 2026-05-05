import allure
import pytest

from pages.admin_page import AdminPage
from pages.dashboard_page import DashboardPage
from pages.login_page import LoginPage
from utils.data_reader import load_json


users_data = load_json("test_data/users.json")


@pytest.fixture
def logged_in_page(page):
    login_page = LoginPage(page)
    dashboard_page = DashboardPage(page)

    valid_user = users_data["valid_user"]

    login_page.open()
    login_page.login(valid_user["username"], valid_user["password"])
    dashboard_page.verify_dashboard_loaded()

    return page

@allure.feature("Admin")
@allure.story("Search user by username")
@allure.severity(allure.severity_level.NORMAL)
@pytest.mark.smoke
@pytest.mark.regression
@pytest.mark.admin
def test_search_user_by_username(logged_in_page):
    admin_page = AdminPage(logged_in_page)

    admin_page.navigate_to_admin()
    admin_page.search_user_by_username(users_data["admin_search"]["username"])
    admin_page.verify_results_displayed()
    admin_page.verify_table_contains_text(users_data["admin_search"]["username"])


@allure.feature("Admin")
@allure.story("Search user by role")
@allure.severity(allure.severity_level.NORMAL)
@pytest.mark.regression
@pytest.mark.admin
def test_search_users_by_role(logged_in_page):
    admin_page = AdminPage(logged_in_page)

    admin_page.navigate_to_admin()
    admin_page.search_users_by_role(users_data["admin_search"]["role"])
    admin_page.verify_results_displayed()
    admin_page.verify_table_contains_text(users_data["admin_search"]["role"])


@allure.feature("Admin")
@allure.story("Reset search")
@allure.severity(allure.severity_level.NORMAL)
@pytest.mark.regression
@pytest.mark.admin
def test_reset_admin_search(logged_in_page):
    admin_page = AdminPage(logged_in_page)

    admin_page.navigate_to_admin()
    admin_page.search_user_by_username(users_data["admin_search"]["username"])
    admin_page.verify_results_displayed()

    admin_page.click_reset()
    admin_page.verify_results_displayed()
