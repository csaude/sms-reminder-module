package org.openmrs.module.smsreminder.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SMSReminderSendSMSController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/smsreminder/smssenderlist", method = RequestMethod.GET)
	public ModelAndView patientListSender() {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("smss", smsReminderService.getNotificationPatients());

		return modelAndView;
	}

	@RequestMapping(value = "/module/smsreminder/smssenderlist", method = RequestMethod.POST)
	public void downloadExcelFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		createExcelFile(SmsReminderUtils.getService().getNotificationPatients(), response);
	}

	private void writeNotificationList(NotificationPatient notificationPatient, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(notificationPatient.getNid());

		cell = row.createCell(1);
		cell.setCellValue(notificationPatient.getFullName());

		cell = row.createCell(2);
		cell.setCellValue(notificationPatient.getGender());

		cell = row.createCell(3);
		cell.setCellValue(notificationPatient.getPhoneNumber());

		cell = row.createCell(4);
		SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
		String cellValue = datetemp.format(notificationPatient.getLastVisitDate());
		cell.setCellValue(cellValue);

		cell = row.createCell(5);
		cell.setCellValue(datetemp.format(notificationPatient.getNextVisitDate()));

		cell = row.createCell(6);
		cell.setCellValue(datetemp.format(notificationPatient.getArtStartDate()));

		cell = row.createCell(7);
		cell.setCellValue(notificationPatient.getReminderDays());

		cell = row.createCell(8);
		cell.setCellValue(notificationPatient.getSentType().name());

	}

	public void createHeaderRow(Sheet sheet) {
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);

		Row row = sheet.createRow(0);

		Cell cellNid = row.createCell(0);
		cellNid.setCellStyle(cellStyle);
		cellNid.setCellValue("NID");

		Cell cellNome = row.createCell(1);
		cellNome.setCellStyle(cellStyle);
		cellNome.setCellValue("Nome Completo");

		Cell cellSexo = row.createCell(2);
		cellSexo.setCellStyle(cellStyle);
		cellSexo.setCellValue("Sexo");

		Cell number = row.createCell(3);
		number.setCellStyle(cellStyle);
		number.setCellValue("Telefone");

		Cell lastVisit = row.createCell(4);
		lastVisit.setCellStyle(cellStyle);
		lastVisit.setCellValue("Ultima Visita");

		Cell nextVisit = row.createCell(5);
		nextVisit.setCellStyle(cellStyle);
		nextVisit.setCellValue("Proxima Visita");

		Cell artInitiation = row.createCell(6);
		artInitiation.setCellStyle(cellStyle);
		artInitiation.setCellValue("Inicio de TARV");

		Cell days = row.createCell(7);
		days.setCellStyle(cellStyle);
		days.setCellValue("Dias Remaneicentes");

		Cell type = row.createCell(8);
		type.setCellStyle(cellStyle);
		type.setCellValue("Tipo de Paciente");

	}

	public void createExcelFile(List<NotificationPatient> notificationPatients, HttpServletResponse response)
			throws Exception {
		try (ByteArrayOutputStream outByteStream = new ByteArrayOutputStream()) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("NOTIFICATION");
			sheet.setColumnWidth(0, 8000);
			sheet.setColumnWidth(1, 8000);
			sheet.setColumnWidth(2, 8000);
			sheet.setColumnWidth(3, 8000);
			sheet.setColumnWidth(4, 8000);
			sheet.setColumnWidth(5, 8000);
			sheet.setColumnWidth(6, 8000);
			sheet.setColumnWidth(7, 8000);
			sheet.setColumnWidth(8, 8000);

			createHeaderRow(sheet);

			int rowCount = 0;

			for (NotificationPatient notificationPatient : notificationPatients) {
				Row row = sheet.createRow(++rowCount);
				writeNotificationList(notificationPatient, row);
			}

			workbook.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			sheet.setColumnWidth(7, 8000);

			response.setHeader("Expires:", "0");
			response.setHeader("Content-Disposition", "attachment; filename=Notification Load Data Details.xls");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			workbook.close();
		}
	}

}
