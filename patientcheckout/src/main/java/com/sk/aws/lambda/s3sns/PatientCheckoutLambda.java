package com.sk.aws.lambda.s3sns;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientCheckoutLambda {

	ObjectMapper mapper = new ObjectMapper();
	AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
	AmazonSNS sns = AmazonSNSClientBuilder.defaultClient();
	
	public void handleRequest(S3Event event) throws Exception {
		event.getRecords().forEach(record ->{
			S3ObjectInputStream s3InputStream = s3.getObject(record.getS3().getBucket().getName(),
					record.getS3().getObject().getKey()).getObjectContent();
			try {
				List<PatientCheckoutEvent> s3ObjectInputStreamList = Arrays.asList(mapper.readValue(s3InputStream, PatientCheckoutEvent[].class));
				System.out.println("s3ObjectInputStreamList: "+s3ObjectInputStreamList);
				s3InputStream.close();
				s3ObjectInputStreamList.forEach(inputStream ->{
					try {
						sns.publish(System.getenv("PATIENT_CHECKOUT_TOPIC"),mapper.writeValueAsString(inputStream));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});
			} catch (StreamReadException e) {
				e.printStackTrace();
			} catch (DatabindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
