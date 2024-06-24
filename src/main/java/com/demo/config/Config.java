package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

@Configuration
public class Config {

	private String accesskey = "<>";
	private String secretkey = "<>";
	
	@Bean
	public String loadSecretData(){
		String secret = getSecret();
		System.out.println("in datasource : "+secret);
		
		return secret;
	}
	
	private String getSecret() {

	    String secretName = "keyValue";
	    String region = "ap-south-1";

	    AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
	    		.withRegion(region)
	    		.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey, secretkey)))
	    		.build();
	            
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
	    	    
	    GetSecretValueResult getSecretValueResponse;

	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        throw e;
	    }

	    if(getSecretValueResponse.getSecretString() != null) {
		    String secret = getSecretValueResponse.getSecretString();
		    System.out.println(" secret : "+secret);
		    return secret;
	    }	    
	    return null;
	}	
	
}
