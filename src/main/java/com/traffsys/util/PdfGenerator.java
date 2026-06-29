package com.traffsys.util;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.traffsys.entity.QCResultMaster;


public class PdfGenerator {

	public static byte[] generatePdf(List<QCResultMaster> qList) throws DocumentException 
	{
		
		DateTimeFormatter formatter =
		        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

		Paragraph title = new Paragraph("QC Result Report", titleFont);

		title.setAlignment(Element.ALIGN_CENTER);

		document.add(title);

		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(8);

		table.setWidthPercentage(100);
		table.addCell("Lot");
		table.addCell("Product");
		table.addCell("Inspection");
		table.addCell("Result");
		table.addCell("Defect");
		table.addCell("Qty");
		table.addCell("Remarks");
		table.addCell("Date");
		

		
		for (QCResultMaster qc : qList) {

		    table.addCell(qc.getLotBatch().getLotNumber());

		    table.addCell(qc.getLotBatch().getmProduct().getProductName());

		    table.addCell(qc.getInspection().getInspectionName());

		    table.addCell(qc.getResultStatus());

		    table.addCell(
		            qc.getDefect() == null
		                    ? "-"
		                    : qc.getDefect().getDefectName());

		    table.addCell(String.valueOf(
		            qc.getDefectQuantity()));

		    table.addCell(qc.getRemarks());

		    table.addCell(
		            qc.getCreatedDate().format(formatter).toString());
		    
		    
		    
		}
		document.add(table);

		document.close();

		return out.toByteArray();
	}

}
