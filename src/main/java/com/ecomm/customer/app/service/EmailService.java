package com.ecomm.customer.app.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ecomm.customer.app.modal.CustomerInfo;
import com.ecomm.customer.app.modal.EmailTemplate;


@Service
public class EmailService {

	@Value("${application.send.email.url}")	
	private String emailUrl;
	
	public String sendEmail(CustomerInfo customerInfo) {
		RestTemplate restTemplate = new RestTemplate();
		EmailTemplate emailTemp = new EmailTemplate();
		String subject = "Welcome...!";
		String emailBody = "Hello" +customerInfo.getCustomerName()+ "," +"\n"+
		"Welcome to Eficens E-comm app." +
		"We are happy to add a member to our family." +
		"\n"+"Thank You," +"\n"+"E-Comm Support Team";
		emailTemp.setToAddress(customerInfo.getCustomerEmail());
		emailTemp.setSubject(subject);
		emailTemp.setEmailBody(emailBody);
		emailTemp.setAttachmentRequired(false);
		ResponseEntity<String> response = restTemplate.postForEntity(emailUrl, emailTemp , String.class );
		return response.getBody();
	}
	
}
