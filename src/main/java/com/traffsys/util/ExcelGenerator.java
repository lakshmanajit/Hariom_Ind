package com.traffsys.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.traffsys.entity.QCResultMaster;

public class ExcelGenerator {

	public static byte[] generateExcel(List<QCResultMaster> qlist) throws IOException
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("QC Report");
		Row header = sheet.createRow(0);

		header.createCell(0).setCellValue("Sr No");
		header.createCell(1).setCellValue("Lot Number");
		header.createCell(2).setCellValue("Product");
		header.createCell(3).setCellValue("Inspection");
		header.createCell(4).setCellValue("Result");
		header.createCell(5).setCellValue("Defect");
		header.createCell(6).setCellValue("Defect Qty");
		header.createCell(7).setCellValue("Remarks");
		header.createCell(8).setCellValue("Created Date");
		
		// Data Fill In cell
		DateTimeFormatter formatter =
		        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		int rowNum = 1;
		for(QCResultMaster qc:qlist )
		{
			Row row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(rowNum);
			row.createCell(1).setCellValue(qc.getLotBatch().getLotNumber());
			row.createCell(2).setCellValue(qc.getLotBatch().getmProduct().getProductName());
			row.createCell(3).setCellValue(qc.getInspection().getInspectionName());
			row.createCell(4).setCellValue(qc.getResultStatus());
			row.createCell(5).setCellValue(qc.getDefect()==null?"-":qc.getDefect().getDefectName());
			row.createCell(6).setCellValue(
				    qc.getDefectQuantity() == null ? 0 : qc.getDefectQuantity()
				);
			row.createCell(7).setCellValue(qc.getRemarks());
			row.createCell(8).setCellValue(
			        qc.getCreatedDate().format(formatter));
			rowNum++;
		}
		for (int i = 0; i < 9; i++) {

		    sheet.autoSizeColumn(i);

		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		workbook.write(out);

		byte[] data = out.toByteArray();

		out.close();
		workbook.close();

		return data;
		
	}

	

}
