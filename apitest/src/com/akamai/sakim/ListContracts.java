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

public class ListContracts {
	
	ClientCredential credential = ClientCredential.builder().accessToken("akab-k6vz46hwfpafnysq-c4d7ti5qv3vmf4mr")
			.clientToken("akab-fis4s4lnfcf57zzd-ddmt3eh4sdxpzmz4")
			.clientSecret("lM5IlI4/dWe9QNBcShp9zbbVyYC9CWBzmkYo2oLPKOo=")
			.host("akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {
		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		URI uri = URI.create("https://akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net/papi/v1/contracts");
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));

		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
		googleHttpSigner.sign(request, request);

		HttpResponse response = request.execute();
		String responseBody = response.parseAsString();
		System.out.println(responseBody);

	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		ListContracts lc = new ListContracts();
		lc.run();

	}

}
