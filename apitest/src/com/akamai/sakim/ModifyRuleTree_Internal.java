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
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class ModifyRuleTree_Internal {

	ClientCredential credential = ClientCredential.builder().accessToken("akab-bebzcgfwgesdlify-s42ihc3uxzyzdzhk")
			.clientToken("akab-kzyvzzj2wcisrplg-kr5rphcusl6naoyf")
			.clientSecret("waysIYRC4V9X+Os5NsptQ2gzZpjOa5Xw0YiKN4AONP0=")
			.host("akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {

		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();

		URI uri = URI.create(
				"https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions/44/rules?contractId=ctr_C-1BBSI2U&groupId=grp_61240&validateRules=true&validateMode=fast");
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));

		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
		googleHttpSigner.sign(request, request);

		HttpResponse response = request.execute();
		String responseBody = response.parseAsString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(responseBody);
		JsonNode value = root.path("rules").path("children").get(5).path("criteria").get(0).path("options")
				.path("values");
		
        System.out.println("Before: "+value);
        ((ArrayNode) value).add("aaa");
        System.out.println("After: "+value);
        
/*
		String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		System.out.println("Before Update " + resultOriginal);

		((ArrayNode) value).add("aaa");
		String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		System.out.println("After Update " + resultUpdate);

		uri = URI.create(
				"https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions/44/rules?contractId=ctr_C-1BBSI2U&groupId=grp_61240&validateRules=true&validateMode=fast");
*/
		// System.out.println(responseBody);

	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		ModifyRuleTree_Internal lp = new ModifyRuleTree_Internal();
		lp.run();

	}

}
