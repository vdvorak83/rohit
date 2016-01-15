package com.programsji.viewresolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.programsji.bo.Person;

public class PersonExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Person> personlist = (List<Person>) model.get("personlist");
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("id");
		row.createCell(1).setCellValue("firstname");
		row.createCell(2).setCellValue("lastname");
		int i = 0;
		for (Person person : personlist) {
			i = i + 1;
			HSSFRow r = sheet.createRow(i);
			r.createCell(0).setCellValue(person.getId());
			r.createCell(1).setCellValue(person.getFname());
			r.createCell(2).setCellValue(person.getLname());
		}
	}
}
