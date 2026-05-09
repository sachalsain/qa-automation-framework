import * as allure from "allure-js-commons";
import { test, expect } from "../fixtures.js";
import { getLogger } from "../utilities/logger.js";

const logger = getLogger("tests.test_users");

test("@smoke @regression @users Fetch all users", async ({ reqresApi }) => {
  await allure.feature("FETCH All Users");
  await allure.story("Fetch All Users");
  await allure.severity("critical");

  logger.info("Fetching all users from API.");
  const response = await reqresApi.getUsers(1);
  logger.debug("Verifying if SUCCESS response received.");
  expect(response.status()).toBe(200);

  logger.debug("Converting the response to JSON.");
  const responseBody = await response.json();
  logger.debug("Verifying if requested page is 1.");
  expect(responseBody.page).toBe(1);
  logger.debug("Verifying if Records per page is greater than 0.");
  expect(responseBody.per_page).toBeGreaterThan(0);
  logger.debug("Verifying if Total Records are greater than 0.");
  expect(responseBody.total).toBeGreaterThan(0);
  logger.debug("Verifying if Total Pages are greater than 0.");
  expect(responseBody.total_pages).toBeGreaterThan(0);
  logger.info(JSON.stringify(responseBody));

  logger.debug("Extracting the data to verify users.");
  const users = responseBody.data;
  logger.debug("Verifying if extracted users are a list.");
  expect(Array.isArray(users)).toBeTruthy();
  logger.debug("Verifying if length of extracted users is equal to per page value of records.");
  expect(users).toHaveLength(responseBody.per_page);

  logger.debug("Iterating through the list of users.");
  for (const user of users) {
    logger.debug("Verifying if user has an ID.");
    expect(user.id).toBeTruthy();
    logger.debug("Verifying if user has an email.");
    expect(user.email).toBeTruthy();
    logger.debug("Verifying if user email has '@' part.");
    expect(user.email).toContain("@");
    logger.debug("Verifying if user has a first name.");
    expect(user.first_name).toBeTruthy();
    logger.debug("Verifying if user has a last name.");
    expect(user.last_name).toBeTruthy();
    logger.debug("Verifying if user has an avatar.");
    expect(user.avatar).toBeTruthy();
    logger.debug("Verifying if that avatar begins with secure HTTP protocol.");
    expect(user.avatar).toMatch(/^https:\/\//);
  }
});

test("@smoke @regression @users Create user", async ({ reqresApi, config }) => {
  await allure.feature("CREATE User");
  await allure.story("Create new user");
  await allure.severity("critical");

  logger.info("Creating user.");
  const response = await reqresApi.createUser(config.email, config.password);
  logger.debug("Verifying if SUCCESS response received.");
  expect(response.status()).toBe(201);

  logger.debug("Converting the response to JSON.");
  const responseBody = await response.json();
  logger.debug(JSON.stringify(responseBody));
  logger.debug("Verifying if email is as per provided.");
  expect(responseBody.email).toBe(config.email);
  logger.debug("Verifying if password is as per provided.");
  expect(responseBody.password).toBe(config.password);
  logger.debug("Verifying if body contain ID.");
  expect(responseBody.id).toBeTruthy();
  logger.debug("Verifying if user has an email.");
  expect(responseBody.email).toBeTruthy();
  logger.debug("Verifying if user email has '@' part.");
  expect(responseBody.email).toContain("@");
});

test("@smoke @regression @users Fetch single user", async ({ reqresApi }) => {
  await allure.feature("FETCH User");
  await allure.story("Fetch single user");
  await allure.severity("critical");

  logger.info("Fetching single user from API using ID.");
  const response = await reqresApi.getSingleUser(1);
  logger.debug("Verifying if SUCCESS response received.");
  expect(response.status()).toBe(200);

  logger.debug("Converting the response to JSON.");
  const responseBody = await response.json();
  const user = responseBody.data;
  logger.debug("Verifying if user has an ID.");
  expect(user.id).toBeTruthy();
  logger.debug("Verifying if user has an email.");
  expect(user.email).toBeTruthy();
  logger.debug("Verifying if user email has '@' part.");
  expect(user.email).toContain("@");
  logger.debug("Verifying if user has a first name.");
  expect(user.first_name).toBeTruthy();
  logger.debug("Verifying if user has a last name.");
  expect(user.last_name).toBeTruthy();
  logger.debug("Verifying if user has an avatar.");
  expect(user.avatar).toBeTruthy();
  logger.debug("Verifying if that avatar begins with secure HTTP protocol.");
  expect(user.avatar).toMatch(/^https:\/\//);
});

test("@smoke @regression @users Update user", async ({ reqresApi, config }) => {
  await allure.feature("UPDATE User");
  await allure.story("Update user");
  await allure.severity("critical");

  logger.info("Updating user using ID.");
  const response = await reqresApi.updateUser(1, config.name, config.job);
  const responseBody = await response.json();
  logger.debug("Verifying if user name has been updated to required.");
  expect(responseBody.name).toBe(config.name);
  logger.debug("Verifying if user job has been updated to required.");
  expect(responseBody.job).toBe(config.job);
});

test("@smoke @regression @users Delete user", async ({ reqresApi }) => {
  await allure.feature("DELETE User");
  await allure.story("Delete new created user");
  await allure.severity("critical");

  logger.info("Deleting user using ID.");
  const response = await reqresApi.deleteUser(1);
  logger.debug("Verifying if SUCCESS response received.");
  expect(response.status()).toBe(204);
});
