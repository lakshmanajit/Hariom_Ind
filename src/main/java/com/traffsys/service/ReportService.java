package com.traffsys.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.traffsys.Repository.QCResultDtoInterface;
import com.traffsys.entity.QCResultMaster;
import com.traffsys.util.ExcelGenerator;
import com.traffsys.util.PdfGenerator;

@Service
public class ReportService {

	@Autowired
	private QCResultDtoInterface dtoInterface;
	
	public byte[] generateQCExcelReport() throws IOException 
	{
		List<QCResultMaster> qlist = dtoInterface.findAll();
		
		return ExcelGenerator.generateExcel(qlist);
	}



	public byte[] generateQCPdfReport() throws DocumentException 
	{
		List<QCResultMaster> qList =dtoInterface.findAll();
		
		return PdfGenerator.generatePdf(qList);
	}

}
