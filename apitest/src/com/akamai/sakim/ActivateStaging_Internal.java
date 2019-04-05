package com.akamai.sakim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.exceptions.RequestSigningException;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridRequestSigner;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class ActivateStaging_Internal {
	
	ClientCredential credential = ClientCredential.builder().accessToken("akab-bebzcgfwgesdlify-s42ihc3uxzyzdzhk")
			.clientToken("akab-kzyvzzj2wcisrplg-kr5rphcusl6naoyf")
			.clientSecret("waysIYRC4V9X+Os5NsptQ2gzZpjOa5Xw0YiKN4AONP0=")
			.host("akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {
		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		
		URI uri = URI.create("https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/activations?contractId=ctr_C-1BBSI2U&groupId=grp_61240");

		String requestBody = "{\"propertyVersion\": 63,\"network\": \"STAGING\",\"note\": \"Sample activation\",\"useFastFallback\": false,\"notifyEmails\": [\"sakim@akamai.com\"],\"acknowledgeWarnings\": [\"msg_7a2aa72bca3f894607c126436861cb73fb82d677\"]}";
		HttpContent content = new ByteArrayContent("application/json", requestBody.getBytes());

		HttpRequest activateStaging_request = requestFactory.buildPostRequest(new GenericUrl(uri), content);
		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
		googleHttpSigner.sign(activateStaging_request, activateStaging_request);
		String responseBody = activateStaging_request.execute().parseAsString();
		System.out.println(responseBody);

	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		ActivateStaging_Internal lp = new ActivateStaging_Internal();
		lp.run();

	}

}
