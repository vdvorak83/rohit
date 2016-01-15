package com.programsji.viewresolvers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.programsji.bo.Person;

//@Component
//public class PersonExcelView extends AbstractExcelView {
//
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model,
//			HSSFWorkbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		List<Person> personList = (List<Person>) model.get("personlist");
//
//		HSSFSheet sheet = workbook.createSheet();
//		HSSFRow row = sheet.createRow(0);
//		row.getCell(0).setCellValue("Rohit");
//		row.getCell(1).setCellValue("Rohit");
//	}
// }
