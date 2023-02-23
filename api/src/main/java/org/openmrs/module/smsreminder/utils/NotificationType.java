package org.openmrs.module.smsreminder.utils;

public enum NotificationType {

	PICKUP_DRUG("LEVANTAMENTOS"), CONSULTATION("CONSULTAS"), LTFU("FALTOSOS");

	private final String name;

	private NotificationType(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
