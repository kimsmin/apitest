package com.akamai.sakim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.exceptions.RequestSigningException;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridRequestSigner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class CreateVersion_Internal {

	ClientCredential credential = ClientCredential.builder().accessToken("akab-bebzcgfwgesdlify-s42ihc3uxzyzdzhk")
			.clientToken("akab-kzyvzzj2wcisrplg-kr5rphcusl6naoyf")
			.clientSecret("waysIYRC4V9X+Os5NsptQ2gzZpjOa5Xw0YiKN4AONP0=")
			.host("akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {

		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();

		URI uri = URI.create(
				"https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions?contractId=ctr_C-1BBSI2U&groupId=grp_61240");

		//Create new version based on production version
		int version = 44; // this value should be dynamically retrieved from production version in real environment
		String etag="\"5bfaa644b1f4f7f09144a2ae7320d9009231d2c4\"";

		String requestBody = "{\"createFromVersion\":" + version + ",\"createFromVersionEtag\": " + etag + "}";
		HttpContent content = new ByteArrayContent("application/json", requestBody.getBytes());

		HttpRequest createVersion_request = requestFactory.buildPostRequest(new GenericUrl(uri), content);
		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
		googleHttpSigner.sign(createVersion_request, createVersion_request);
		String responseBody = createVersion_request.execute().parseAsString();
		
		//Get versionLink of the new version
		ObjectMapper mapper = new ObjectMapper();
		JsonNode newVersion=mapper.readTree(responseBody);
		String versionLink=newVersion.path("versionLink").asText();

		//Get etag of the new version
		uri = URI.create("https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net"+versionLink);
		HttpRequest getEtag_request=requestFactory.buildGetRequest(new GenericUrl(uri));
		googleHttpSigner.sign(getEtag_request, getEtag_request);
		responseBody = getEtag_request.execute().parseAsString();
		
		System.out.println(responseBody);
		
		/*
		JsonNode newEtag=mapper.readTree(responseBody);
		System.out.println("old etag="+etag);
		etag=newEtag.path("versions").path("items").get(0).path("etag").asText();
		System.out.println("new etag="+etag);
		version=newEtag.path("versions").path("items").get(0).path("propertyVersion").asInt();

		//Update rule tree via PUT request
		requestBody=root.toString();
		content=new ByteArrayContent("application/json", requestBody.getBytes());
		uri = URI.create(
				"https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions/"+version+"/rules?contractId=ctr_C-1BBSI2U&groupId=grp_61240&validateRules=false");
		HttpRequest update_request = requestFactory.buildPutRequest(new GenericUrl(uri), content);
		HttpHeaders headers = update_request.getHeaders();
		headers.setIfMatch("\""+etag+"\"");
		googleHttpSigner.sign(update_request, update_request);		
		responseBody=update_request.execute().parseAsString();
		System.out.println(responseBody);
		*/
	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		CreateVersion_Internal lp = new CreateVersion_Internal();
		lp.run();

	}

}
