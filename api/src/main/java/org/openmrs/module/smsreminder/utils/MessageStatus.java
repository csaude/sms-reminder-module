package org.openmrs.module.smsreminder.utils;

public enum MessageStatus {

	DELIVERED("ENTREGUE"), NOT_DELIVERY("NAO ENTREGUE"), ON_HOLD("EM ESPERA");

	private final String name;

	private MessageStatus(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
