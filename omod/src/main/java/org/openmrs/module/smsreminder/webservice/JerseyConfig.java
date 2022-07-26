/**
 *
 */
package org.openmrs.module.smsreminder.webservice;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Service;

import mz.org.fgh.disaapi.integ.resources.viralload.ViralLoadResource;

/**
 * @author Stélio Moiane
 *
 */
@Service
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		this.register(ViralLoadResource.class);
	}
}
