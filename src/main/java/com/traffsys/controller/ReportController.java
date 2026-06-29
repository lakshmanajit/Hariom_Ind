package com.traffsys.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.traffsys.service.ReportService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController 
{
	@Autowired
	private ReportService reportService;
	
	  @GetMapping("/qc/excel")
	    public ResponseEntity<byte[]> downloadQCExcel() throws IOException, java.io.IOException {

	        byte[] excel = reportService.generateQCExcelReport();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=QC_Report.xlsx");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(excel);

	    }
	    
	    
	    @GetMapping("/qc/pdf")
	    public ResponseEntity<byte[]> downloadQCPdf() throws IOException, DocumentException {

	        byte[] pdf = reportService.generateQCPdfReport();

	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=QC_Report.pdf");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdf);
	    }
	

}
