from src.core.base_test import BaseTest


class TestABTesting(BaseTest):
    # @pytest.fixture(autouse=True)
    # def setup(self, page, config, request):
    #     self.logger.debug("Setting up test environment for TestABTesting")
    #     self.setup_test(page, config, request)
    #     self.page_ab_testing = PageABTesting(page)

    def test_sample_page(self, page):
        page.goto("https://www.google.com", wait_until="domcontentloaded")
        assert "Google" in page.title()
