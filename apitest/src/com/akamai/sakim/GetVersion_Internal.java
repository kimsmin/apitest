package com.akamai.sakim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.exceptions.RequestSigningException;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridRequestSigner;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class GetVersion_Internal {
	
	ClientCredential credential = ClientCredential.builder().accessToken("akab-bebzcgfwgesdlify-s42ihc3uxzyzdzhk")
			.clientToken("akab-kzyvzzj2wcisrplg-kr5rphcusl6naoyf")
			.clientSecret("waysIYRC4V9X+Os5NsptQ2gzZpjOa5Xw0YiKN4AONP0=")
			.host("akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {
		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		
		URI uri = URI.create("https://akab-wwstu3bvulrikd3z-bsithmyjodkqc2lv.luna.akamaiapis.net/papi/v1/properties/prp_242425/versions/65?contractId=ctr_C-1BBSI2U&groupId=grp_61240");
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));

		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
		googleHttpSigner.sign(request, request);

		HttpResponse response = request.execute();
		String responseBody = response.parseAsString();
		System.out.println(responseBody);

	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		GetVersion_Internal lp = new GetVersion_Internal();
		lp.run();

	}

}
