package org.openmrs.module.smsreminder.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.scheduler.tasks.AbstractTask;

/**
 * Created by nelson.mahumane on 20-10-2015.
 */
public class SendSmsReminderTask extends AbstractTask {
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public void execute() {

		Context.openSession();
		this.log.info("Starting send SMS ... ");
		this.log.info("End send SMS ... ");
}
}