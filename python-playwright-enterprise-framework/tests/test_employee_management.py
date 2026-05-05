from datetime import datetime

import allure
import pytest

from pages.dashboard_page import DashboardPage
from pages.login_page import LoginPage
from pages.pim_page import PimPage
from utils.data_reader import load_json


users_data = load_json("test_data/users.json")
employees_data = load_json("test_data/employees.json")


@pytest.fixture
def logged_in_page(page):
    login_page = LoginPage(page)
    dashboard_page = DashboardPage(page)

    valid_user = users_data["valid_user"]

    login_page.open()
    login_page.login(valid_user["username"], valid_user["password"])
    dashboard_page.verify_dashboard_loaded()

    return page


def generate_employee_id(prefix):
    timestamp = datetime.now().strftime("%H%M%S")
    return f"{prefix}{timestamp}"


@allure.feature("PIM")
@allure.story("Add employee")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.regression
@pytest.mark.pim
def test_add_employee(logged_in_page):
    pim_page = PimPage(logged_in_page)

    employee = employees_data["employee"]
    employee_id = generate_employee_id(employee["employee_id_prefix"])

    pim_page.navigate_to_pim()
    pim_page.add_employee(
        employee["first_name"],
        employee["middle_name"],
        employee["last_name"],
        employee_id,
    )
    pim_page.verify_employee_details_loaded()


@allure.feature("PIM")
@allure.story("Search employee")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.regression
@pytest.mark.pim
def test_search_employee(logged_in_page):
    pim_page = PimPage(logged_in_page)

    employee_name = employees_data["search"]["employee_name"]

    pim_page.navigate_to_pim()
    pim_page.search_employee_by_name(employee_name)
    pim_page.verify_results_displayed()


@allure.feature("PIM")
@allure.story("Reset employee search")
@allure.severity(allure.severity_level.NORMAL)
@pytest.mark.regression
@pytest.mark.pim
def test_reset_employee_search(logged_in_page):
    pim_page = PimPage(logged_in_page)

    employee_name = employees_data["search"]["employee_name"]

    pim_page.navigate_to_pim()
    pim_page.search_employee_by_name(employee_name)
    pim_page.verify_results_displayed()

    pim_page.click_reset()
    pim_page.verify_results_displayed()
