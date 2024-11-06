package com.sk.aws.lambda.s3sns;

public class PatientCheckoutEvent {
	private String firstName;
	private String lastName;
	private String middleName;
	private String ssn;
	
	public PatientCheckoutEvent() {
	}
	public PatientCheckoutEvent(String firstName, String lastName, String middleName, String ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.ssn = ssn;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	@Override
	public String toString() {
		return "PatientCheckoutEvent [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", ssn=" + ssn + "]";
	}
	
	
}
