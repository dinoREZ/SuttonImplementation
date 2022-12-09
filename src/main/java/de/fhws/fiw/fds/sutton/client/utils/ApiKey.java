/*
 * Copyright (c) peter.braun@fhws.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.sutton.client.utils;

public class ApiKey {
	private final String apiKeyHeader;

	private final String apiKeyValue;

	public ApiKey(final String apiKeyHeader, final String apiKeyValue) {
		this.apiKeyHeader = apiKeyHeader;
		this.apiKeyValue = apiKeyValue;
	}

	public String getApiKeyHeader() {
		return this.apiKeyHeader;
	}

	public String getApiKeyValue() {
		return this.apiKeyValue;
	}
}
