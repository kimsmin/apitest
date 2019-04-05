package com.akamai.sakim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.exceptions.RequestSigningException;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridRequestSigner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class GetAPITester {

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		GetAPITester gat=new GetAPITester();
		gat.signEachRequest();

	}

    ClientCredential credential = ClientCredential.builder()
            .accessToken("akab-k6vz46hwfpafnysq-c4d7ti5qv3vmf4mr")
            .clientToken("akab-fis4s4lnfcf57zzd-ddmt3eh4sdxpzmz4")
            .clientSecret("lM5IlI4/dWe9QNBcShp9zbbVyYC9CWBzmkYo2oLPKOo=")
            .host("akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net")
            .build();

    public void signEachRequest() throws URISyntaxException, IOException, RequestSigningException {
//    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    	JsonFactory JSON_FACTORY = new JacksonFactory();
    	
        HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
        
//      HttpTransport HTTP_TRANSPORT = new NetHttpTransport(); 
        HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
        URI uri = URI.create("https://akab-37c734oyas7tflm6-aa64qcucxjhtbwgg.luna.akamaiapis.net/papi/v0/contracts");
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));

        GoogleHttpClientEdgeGridRequestSigner googleHttpSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
        googleHttpSigner.sign(request, request);
        
        HttpResponse response = request.execute();
        String responseBody=response.parseAsString();
        
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(responseBody);
        
//    	JsonNode rootArray = mapper.readTree(responseBody);
//    	for(JsonNode root : rootArray){
  		String accountId = root.path("accountId").asText();
   		System.out.println("accountId="+accountId);
    		
    	JsonNode items = root.path("contracts").path("items");
    	for(JsonNode item : items){
    			String contractId=item.path("contractId").asText();
    			String contractTypeName=item.path("contractTypeName").asText();
    			System.out.println("----------------------------------------");
    			System.out.println("contractId="+contractId);
    			System.out.println("contractTypeName="+contractTypeName);
    	}
        
//        responseBody=gson.toJson(new JsonParser().parse(responseBody));
        System.out.println(responseBody);

    }
    
	
}
