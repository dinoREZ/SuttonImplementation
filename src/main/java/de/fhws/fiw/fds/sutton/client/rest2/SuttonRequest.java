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

package de.fhws.fiw.fds.sutton.client.rest2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.owlike.genson.Genson;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.ApiKey;
import de.fhws.fiw.fds.sutton.client.utils.Authentication;
import de.fhws.fiw.fds.sutton.client.utils.Header;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SuttonRequest {
	protected String uriTemplate;
	protected String mediaType;
	protected HttpVerb httpVerb;
	protected AbstractClientModel requestModel;
	protected byte[] requestImage;
	protected Authentication authentication;
	protected ApiKey apiKey;
	protected String jwtTokenName;
	protected List<Header> headers = new LinkedList<>();

	public SuttonRequest() {
		this.mediaType = "application/json";
	}

	public final void setRequestModel(final AbstractClientModel requestModel) {
		this.requestModel = requestModel;
	}

	public SuttonResponse execute() {
		this.setAllUnsetQueryParameterToEmptyString();
		final MediaType mediaType = MediaType.parse(this.mediaType);
		final OkHttpClient client = new OkHttpClient();
		final String serializedRequest = serializeRequestModel();
		final RequestBody body =
				serializedRequest != null ? RequestBody.create(mediaType, serializedRequest) : null;

		Request request =
				new Request.Builder().url(this.uriTemplate).method(httpVerb.name(), body).build();

		try {
			Response response = client.newCall(request).execute();
			return new SuttonResponse(response, this);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public final void setUriTemplate(final String uriTemplate) {
		this.uriTemplate = uriTemplate;
	}

	public final void setMediaType(final String mediaType) {
		this.mediaType = mediaType;
	}

	public final void setHttpVerb(final HttpVerb httpVerb) {
		this.httpVerb = httpVerb;
	}

	public final void setRequestImage(final byte[] requestImage) {
		this.requestImage = requestImage;
	}

	public final void setAuthentication(final Authentication authentication) {
		this.authentication = authentication;
	}

	public final void setApiKey(final ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public final void setJwtTokenName(String jwtTokenName) {
		this.jwtTokenName = jwtTokenName;
	}

	public final void addHeader(String header, String value) {
		this.headers.add(new Header(header, value));
	}

	public final void addHeader(Header header) {
		this.headers.add(header);
	}

	public void replace(final String template, final long value) {
		replace(template, Long.toString(value));
	}

	public void replace(final String template, String value) {
		try {
			value = URLEncoder.encode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.uriTemplate = this.uriTemplate.replace(template, value);
	}

	protected final String serializeRequestModel() {
		if (this.requestModel != null) {
			final Genson genson = new Genson();
			return genson.serialize(this.requestModel);
		}
		else {
			return null;
		}
	}

	protected final void setAllUnsetQueryParameterToEmptyString() {

		String[] splitedUri =
				this.uriTemplate == null ? new String[0] : this.uriTemplate.split("\\?");

		if (splitedUri.length == 2) {
			String query = this.uriTemplate.split("\\?")[1];
			List<AbstractMap.SimpleEntry<String, String>> list =
					Pattern.compile("&").splitAsStream(query)
							.map(s -> Arrays.copyOf(s.split("="), 2))
							.map(o -> new AbstractMap.SimpleEntry<String, String>(o[0],
									replacePlaceholdersWithEmptyString(o[1])))
							.collect(Collectors.toList());

			this.uriTemplate = rebuildUriTemplate(list);
		}
	}

	private String rebuildUriTemplate(List<AbstractMap.SimpleEntry<String, String>> list) {
		String reBuildUriTemplate = this.uriTemplate.split("\\?")[0];
		ListIterator<AbstractMap.SimpleEntry<String, String>> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (!iterator.hasPrevious()) {
				reBuildUriTemplate += "?";
			}

			AbstractMap.SimpleEntry<String, String> queryParameterEntry = iterator.next();

			reBuildUriTemplate +=
					queryParameterEntry.getKey() + "=" + queryParameterEntry.getValue();

			if (iterator.hasNext()) {
				reBuildUriTemplate += "&";
			}
		}
		return reBuildUriTemplate;
	}

	private String replacePlaceholdersWithEmptyString(final String decodedValue) {
		if (decodedValue == null || (decodedValue.startsWith("{") && decodedValue.endsWith("}")
				&& decodedValue.length() > 2)) {
			return "";
		}
		else {
			return decodedValue;
		}
	}

	public enum HttpVerb {
		GET, POST, PUT, DELETE
	}
}
