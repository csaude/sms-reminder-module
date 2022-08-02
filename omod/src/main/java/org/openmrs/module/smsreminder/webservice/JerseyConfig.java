/**
 *
 */
package org.openmrs.module.smsreminder.webservice;

import org.glassfish.jersey.server.ResourceConfig;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.springframework.stereotype.Service;

@Service
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		this.register(DeliveryReportStatus.class);
	}
}
