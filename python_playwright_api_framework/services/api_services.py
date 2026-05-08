import json

import allure
from playwright.sync_api import APIRequestContext

from utilities.logger import get_logger


class ReqResApiService:
    def __init__(self, request: APIRequestContext, base_url: str, api_key: str):
        self.logger = get_logger(self.__class__.__name__)
        self.request = request
        self.base_url = base_url.rstrip("/")
        self.headers = {
            "x-api-key": api_key,
            "Content-Type": "application/json",
        }

    def _attach_request(self, method, url, headers=None, payload=None):
        safe_headers = dict(headers or {})
        if "x-api-key" in safe_headers:
            safe_headers["x-api-key"] = "***"

        request_data = {
            "method": method,
            "url": url,
            "headers": safe_headers,
            "payload": payload,
        }
        allure.attach(
            json.dumps(request_data, indent=4),
            name=f"{method} request",
            attachment_type=allure.attachment_type.JSON,
        )

    def _attach_response(self, method, response):
        response_data = {
            "status": response.status,
            "headers": response.headers,
            "body": response.text(),
        }
        allure.attach(
            json.dumps(response_data, indent=4),
            name=f"{method} response",
            attachment_type=allure.attachment_type.JSON,
        )

    def get_users(self, page=2):
        url = f"{self.base_url}/api/users?page={page}"
        self._attach_request("GET", url, headers=self.headers)
        response = self.request.get(
            url,
            headers=self.headers,
        )
        self._attach_response("GET", response)
        return response

    def get_single_user(self, user_id):
        url = f"{self.base_url}/api/users/{user_id}"
        self._attach_request("GET", url, headers=self.headers)
        response = self.request.get(
            url,
            headers=self.headers,
        )
        self._attach_response("GET", response)
        return response

    def create_user(self, email, password):
        url = f"{self.base_url}/api/users"
        payload = {
            "email": email,
            "password": password,
        }
        self._attach_request("POST", url, headers=self.headers, payload=payload)
        response = self.request.post(
            url,
            headers=self.headers,
            data=payload,
        )
        self._attach_response("POST", response)
        return response

    def update_user(self, user_id, name, job):
        url = f"{self.base_url}/api/users/{user_id}"
        payload = {
            "name": name,
            "job": job,
        }
        self._attach_request("PUT", url, headers=self.headers, payload=payload)
        response = self.request.put(
            url,
            headers=self.headers,
            data=payload,
        )
        self._attach_response("PUT", response)
        return response

    def delete_user(self, user_id):
        url = f"{self.base_url}/api/users/{user_id}"
        self._attach_request("DELETE", url, headers=self.headers)
        response = self.request.delete(
            url,
            headers=self.headers,
        )
        self._attach_response("DELETE", response)
        return response
