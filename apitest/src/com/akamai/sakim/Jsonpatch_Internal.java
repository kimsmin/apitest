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
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class Jsonpatch_Internal {

	ClientCredential credential = ClientCredential.builder().accessToken("akab-bebzcgfwgesdlify-s42ihc3uxzyzdzhk")
			.clientToken("akab-kzyvzzj2wcisrplg-kr5rphcusl6naoyf")
			.clientSecret("waysIYRC4V9X+Os5NsptQ2gzZpjOa5Xw0YiKN4AONP0=")
			.host("akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {

		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);

		String etag="\"4212471fa07c29a3e26cd0aa6cd0547fcb998529\"";//etag of newly created version
		//Jsonpatch
		String requestBody="[{\"op\": \"replace\",\"path\": \"/rules/children/5/criteria/0/options/values\",\"value\": [\"au\", \"aaa\"]}]";
		HttpContent content=new ByteArrayContent("application/json-patch+json", requestBody.getBytes());
		URI uri = URI.create(
				"https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions/65/rules?contractId=ctr_C-1BBSI2U&groupId=grp_61240&validateRules=false");
		HttpRequest patch_request = requestFactory.buildPatchRequest(new GenericUrl(uri), content);
		HttpHeaders headers = patch_request.getHeaders();
		headers.setIfMatch(etag);
		googleHttpSigner.sign(patch_request, patch_request);		
		String responseBody=patch_request.execute().parseAsString();
		System.out.println(responseBody);
	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		Jsonpatch_Internal jp = new Jsonpatch_Internal();
		jp.run();

	}

}
