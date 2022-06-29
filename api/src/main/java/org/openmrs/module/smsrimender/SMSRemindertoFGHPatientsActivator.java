package org.openmrs.module.smsrimender;

/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class SMSRemindertoFGHPatientsActivator extends BaseModuleActivator {

  private Log log = LogFactory.getLog(this.getClass());


  @Override
  public void contextRefreshed() {
    log.debug("SMSRIMENDER Reports Module refreshed");
  }

  @Override
  public void willRefreshContext() {
    log.debug("Refreshing SMSRIMENDER  Module");
  }

  @Override
  public void willStart() {
    log.debug("Starting SMSRIMENDER  Module");
  }

  @Override
  public void willStop() {
    log.debug("Stopping SMSRIMENDER Module");
    try {
      log.debug("SMSRIMENDER Reports purged");
    } catch (Exception e) {
      log.error("An error occured trying to purge EPTS reports", e);
    }
  }

  /** @see #started() */
  public void started() {
      log.info("Started SMSRIMENDER Module");
  }

  /** @see #stopped() */
  public void stopped() {
    log.info("Stopped SMSRIMENDER Module");
  }
}