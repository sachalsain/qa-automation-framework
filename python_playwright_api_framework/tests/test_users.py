import allure
import pytest

from utilities.logger import get_logger

logger = get_logger(__name__)

@allure.feature("FETCH All Users")
@allure.story("Fetch All Users")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.users
def test_fetch_all_users(reqres_api):
    logger.info("Fetching all users from API.")
    response = reqres_api.get_users(1)
    logger.debug("Verifying if SUCCESS response received.")
    assert response.status == 200
    logger.debug("Converting the response to JSON.")
    response_body = response.json()
    logger.debug("Verifying if requested page is 1.")
    assert response_body["page"] == 1
    logger.debug("Verifying if Records per page is greater than 0.")
    assert response_body["per_page"] > 0
    logger.debug("Verifying if Total Records are greater than 0.")
    assert response_body["total"] > 0
    logger.debug("Verifying if Total Pages are greater than 0.")
    assert response_body["total_pages"] > 0
    logger.info(response.body())
    logger.debug("Extracting the data to verify users.")
    users = response_body["data"]
    
    logger.debug("Verifying if extracted users are a list.")
    assert isinstance(users, list)
    logger.debug("Verifying if length of extraced users is equal to per page value of records.")
    assert len(users) == response_body["per_page"]

    logger.debug("Iterating through the list of users.")
    for user in users:
        logger.debug("Verifying if user has an ID.")
        assert user["id"]
        logger.debug("Verifying if user has an email.")
        assert user["email"]
        logger.debug("Verifying if user email has '@' part.")
        assert "@" in user["email"]
        logger.debug("Verifying if user has a first name.")
        assert user["first_name"]
        logger.debug("Verifying if user has a last name.")
        assert user["last_name"]
        logger.debug("Verifying if user has an avatar.")
        assert user["avatar"]
        logger.debug("Verifying if that avatar begins with secure HTTP protocol.")
        assert user["avatar"].startswith("https://")

@allure.feature("CREATE User")
@allure.story("Create new user")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.users
def test_create_user(reqres_api, config):
    logger.info("Creating user.")
    response = reqres_api.create_user(config.get("email"), config.get("password"))
    logger.debug(response.body())

    logger.debug("Verifying if SUCCESS response received.")
    assert response.status == 201
    logger.debug("Converting the response to JSON.")
    response_body = response.json()
    logger.debug("Verifying if email is as per provided.")
    assert response_body["email"] == config.get("email")
    logger.debug("Verifying if password is as per provided.")
    assert response_body["password"] == config.get("password")
    logger.debug("Verifying if body contain ID.")
    assert response_body["id"]
    logger.debug("Verifying if user has an email.")
    assert response_body["email"]
    logger.debug("Verifying if user email has '@' part.")
    assert "@" in response_body["email"]


@allure.feature("FETCH User")
@allure.story("Fetch single user")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.users
def test_fetch_user(reqres_api, config):
    logger.info("Fetching single user from API using ID.")
    response = reqres_api.get_single_user(1)
    logger.debug("Verifying if SUCCESS response received.")
    assert response.status == 200
    logger.debug("Converting the response to JSON.")
    response_body = response.json()
    user = response_body["data"]
    logger.debug("Verifying if user has an ID.")
    assert user["id"]
    logger.debug("Verifying if user has an email.")
    assert user["email"]
    logger.debug("Verifying if user email has '@' part.")
    assert "@" in user["email"]
    logger.debug("Verifying if user has a first name.")
    assert user["first_name"]
    logger.debug("Verifying if user has a last name.")
    assert user["last_name"]
    logger.debug("Verifying if user has an avatar.")
    assert user["avatar"]
    logger.debug("Verifying if that avatar begins with secure HTTP protocol.")
    assert user["avatar"].startswith("https://")

@allure.feature("UPDATE User")
@allure.story("Update user")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.users
def test_update_user(reqres_api, config):
    logger.info("Updating user using ID.")
    response = reqres_api.update_user(1, config.get("name"), config.get("job"))
    response_body = response.json()
    logger.debug("Verifying if user name has been updated to required.")
    assert response_body["name"] == config.get("name")
    logger.debug("Verifying if user job has been updated to required.")
    assert response_body["job"] == config.get("job")

@allure.feature("DELETE User")
@allure.story("Delete new created user")
@allure.severity(allure.severity_level.CRITICAL)
@pytest.mark.smoke
@pytest.mark.users
def test_delete_user(reqres_api, config):
    logger.info("Deleting user using ID.")
    response = reqres_api.delete_user(1)
    logger.debug("Verifying if SUCCESS response received.")
    assert response.status == 204
