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

public class Jsonpatch_LG {

	ClientCredential credential = ClientCredential.builder().accessToken("akab-k6vz46hwfpafnysq-c4d7ti5qv3vmf4mr")
			.clientToken("akab-fis4s4lnfcf57zzd-ddmt3eh4sdxpzmz4")
			.clientSecret("lM5IlI4/dWe9QNBcShp9zbbVyYC9CWBzmkYo2oLPKOo=")
			.host("akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net").build();

	public void run() throws URISyntaxException, IOException, RequestSigningException {

		HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);

		String etag="\"40fbddeb81ee184bb2fc9bb9ad33368e5b1c2761\"";//etag of newly created version
		//Jsonpatch
		String requestBody="[{\"op\": \"replace\",\"path\": \"/rules/children/2/children/2/children/2/criteria/0/options/values\",\"value\": [\"/us/sample-invalid-sample-path\", \"/test1\"]}]";
		HttpContent content=new ByteArrayContent("application/json-patch+json", requestBody.getBytes());
		URI uri = URI.create(
				"https://akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net/papi/v1/properties/prp_521830/versions/10/rules?contractId=ctr_C-1KC3L8L&groupId=grp_122545&validateRules=false");
		HttpRequest patch_request = requestFactory.buildPatchRequest(new GenericUrl(uri), content);
		HttpHeaders headers = patch_request.getHeaders();
		headers.setIfMatch(etag);
		googleHttpSigner.sign(patch_request, patch_request);		
		String responseBody=patch_request.execute().parseAsString();
		System.out.println(responseBody);
	}

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		Jsonpatch_LG jp = new Jsonpatch_LG();
		jp.run();

	}

}
