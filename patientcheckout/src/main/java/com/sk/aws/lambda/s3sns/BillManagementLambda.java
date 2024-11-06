package com.sk.aws.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BillManagementLambda {

	ObjectMapper mapper = new ObjectMapper();
	
	public void handleRequest(SNSEvent event) throws Exception {
		event.getRecords().forEach(record ->{
			PatientCheckoutEvent patientCheckoutEvent;
			try {
				patientCheckoutEvent = mapper.readValue(record.getSNS().getMessage(), PatientCheckoutEvent.class);
				System.out.println("patientCheckoutEvent : "+patientCheckoutEvent); 
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		});
	}
}
