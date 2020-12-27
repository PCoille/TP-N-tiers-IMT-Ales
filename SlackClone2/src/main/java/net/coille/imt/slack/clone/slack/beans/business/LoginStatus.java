package net.coille.imt.slack.clone.slack.beans.business;

public enum LoginStatus {
	SUCCESS("success"),
	WRONG_ID("wrong_id"),
	NOT_A_MEMBER("not_a_member");
	
	private String value;
	
	private LoginStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
