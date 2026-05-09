import { getLogger } from "../utilities/logger.js";

export class ReqResApiService {
  constructor(request, baseUrl, apiKey, testInfo = undefined) {
    this.logger = getLogger(this.constructor.name);
    this.request = request;
    this.baseUrl = baseUrl.replace(/\/$/, "");
    this.testInfo = testInfo;
    this.headers = {
      "x-api-key": apiKey,
      "Content-Type": "application/json"
    };
  }

  async attachRequest(method, url, headers = undefined, payload = undefined) {
    const safeHeaders = { ...(headers || {}) };
    if (safeHeaders["x-api-key"]) {
      safeHeaders["x-api-key"] = "***";
    }

    await this.attachJson(`${method} request`, {
      method,
      url,
      headers: safeHeaders,
      payload
    });
  }

  async attachResponse(method, response) {
    await this.attachJson(`${method} response`, {
      status: response.status(),
      headers: response.headers(),
      body: await response.text()
    });
  }

  async attachJson(name, data) {
    if (!this.testInfo) {
      return;
    }

    await this.testInfo.attach(name, {
      body: JSON.stringify(data, null, 4),
      contentType: "application/json"
    });
  }

  async getUsers(page = 2) {
    const url = `${this.baseUrl}/api/users?page=${page}`;
    await this.attachRequest("GET", url, this.headers);
    const response = await this.request.get(url, { headers: this.headers });
    await this.attachResponse("GET", response);
    return response;
  }

  async getSingleUser(userId) {
    const url = `${this.baseUrl}/api/users/${userId}`;
    await this.attachRequest("GET", url, this.headers);
    const response = await this.request.get(url, { headers: this.headers });
    await this.attachResponse("GET", response);
    return response;
  }

  async createUser(email, password) {
    const url = `${this.baseUrl}/api/users`;
    const payload = { email, password };
    await this.attachRequest("POST", url, this.headers, payload);
    const response = await this.request.post(url, {
      headers: this.headers,
      data: payload
    });
    await this.attachResponse("POST", response);
    return response;
  }

  async updateUser(userId, name, job) {
    const url = `${this.baseUrl}/api/users/${userId}`;
    const payload = { name, job };
    await this.attachRequest("PUT", url, this.headers, payload);
    const response = await this.request.put(url, {
      headers: this.headers,
      data: payload
    });
    await this.attachResponse("PUT", response);
    return response;
  }

  async deleteUser(userId) {
    const url = `${this.baseUrl}/api/users/${userId}`;
    await this.attachRequest("DELETE", url, this.headers);
    const response = await this.request.delete(url, { headers: this.headers });
    await this.attachResponse("DELETE", response);
    return response;
  }
}
