package org.openmrs.module.smsreminder.utils;

public enum MensageStatus {

	DELIVERED("ENTREGUE"), NOT_DELIVERY("NAO ENTREGUE"), ON_HOLD("EM ESPERA");

	private final String name;

	private MensageStatus(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
