import allure
from playwright.sync_api import expect

from utils.logger import get_logger


class PimPage:
    def __init__(self, page):
        self.page = page
        self.logger = get_logger(self.__class__.__name__)

        self.pim_menu = page.get_by_role("link", name="PIM")
        self.page_header = page.locator("h6")
        self.page_heading = page.locator("h6.orangehrm-main-title")

        self.add_button = page.get_by_role("button", name="Add")
        self.first_name_input = page.locator("input[name='firstName']")
        self.middle_name_input = page.locator("input[name='middleName']")
        self.last_name_input = page.locator("input[name='lastName']")
        self.employee_id_input = page.locator(".oxd-grid-2 input.oxd-input").first
        self.save_button = page.get_by_role("button", name="Save")

        # self.employee_name_input = page.locator(
        #     "label:has-text('Employee Name')"
        # ).locator("xpath=../../following-sibling::div//input")
        self.employee_name_input = page.locator("div.oxd-grid-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)")
        self.search_button = page.get_by_role("button", name="Search")
        self.reset_button = page.get_by_role("button", name="Reset")

        self.records_found_label = page.locator(".orangehrm-horizontal-padding span")
        self.result_rows = page.locator(".oxd-table-card")
        self.employee_details_header = page.locator("h6").filter(
            has_text="Personal Details"
        )

    @allure.step("Navigate to PIM module")
    def navigate_to_pim(self):
        self.logger.info("Navigating to PIM module")

        self.pim_menu.click()
        expect(self.page_header).to_have_text("PIM")

    @allure.step("Click Add Employee button")
    def click_add_employee(self):
        self.logger.info("Clicking Add Employee button")

        self.add_button.click()
        expect(self.page_heading).to_have_text("Add Employee")

    @allure.step("Enter employee first name")
    def enter_first_name(self, first_name):
        self.logger.info("Entering employee first name")

        self.first_name_input.fill(first_name)

    @allure.step("Enter employee middle name")
    def enter_middle_name(self, middle_name):
        self.logger.info("Entering employee middle name")

        self.middle_name_input.fill(middle_name)

    @allure.step("Enter employee last name")
    def enter_last_name(self, last_name):
        self.logger.info("Entering employee last name")

        self.last_name_input.fill(last_name)

    @allure.step("Enter employee ID")
    def enter_employee_id(self, employee_id):
        self.logger.info("Entering employee ID")

        self.employee_id_input.fill(employee_id)

    @allure.step("Save employee")
    def click_save(self):
        self.logger.info("Saving employee")

        self.save_button.click()

    @allure.step("Add new employee")
    def add_employee(self, first_name, middle_name, last_name, employee_id):
        self.click_add_employee()
        self.enter_first_name(first_name)
        self.enter_middle_name(middle_name)
        self.enter_last_name(last_name)
        self.enter_employee_id(employee_id)
        self.click_save()

    @allure.step("Verify employee personal details page is loaded")
    def verify_employee_details_loaded(self):
        self.logger.info("Verifying employee details page is loaded")
        self.employee_details_header.wait_for(state="visible", timeout=20000)
        expect(self.employee_details_header).to_be_visible()

    @allure.step("Search employee by name")
    def search_employee_by_name(self, employee_name):
        self.logger.info("Searching employee by name: %s", employee_name)

        self.employee_name_input.fill(employee_name)
        self.page.keyboard.press("ArrowDown")
        self.page.keyboard.press("Enter")
        self.search_button.click()

    @allure.step("Verify PIM results are displayed")
    def verify_results_displayed(self):
        expect(self.records_found_label).to_be_visible()

        records_text = self.records_found_label.inner_text()
        
        assert "Record Found" in records_text or "Records Found" in records_text

    @allure.step("Verify PIM table contains text")
    def verify_table_contains_text(self, expected_text):
        expect(self.result_rows.first).to_be_visible()

        table_text = self.result_rows.first.inner_text()

        assert expected_text in table_text

    @allure.step("Click PIM reset button")
    def click_reset(self):
        self.logger.info("Clicking PIM reset button")

        self.reset_button.click()
