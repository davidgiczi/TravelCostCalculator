package com.david.giczi.calculator.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfManager {

	private TemplateFileManager templateFileManager;
	private String PDF_FOLDER_PATH = "./TravelCostCalculatorData/Utnyilvantartasok/";
	private Font normalFont = new Font(FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
	private Font boldFont = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private Document doc;
	private PdfWriter writer;
	
	public PdfManager() {
		templateFileManager = new TemplateFileManager();
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		doc = new Document(PageSize.A4);
	}
	
	public void createAndOpenPDFile(String yearDotMonth) {
		
		try {
			File folder = new File(PDF_FOLDER_PATH);
			
			if(!folder.exists()) {
				folder.mkdir();
			}
			
			PdfWriter.getInstance(doc, new FileOutputStream(PDF_FOLDER_PATH + createPDFileName(yearDotMonth)));
			doc.open();
			
			createHeadOfPdf(doc);
			addTitleToPdf(doc);
			
			Desktop.getDesktop().open(new File(PDF_FOLDER_PATH + createPDFileName(yearDotMonth)));
			
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		finally {
			
			if(doc != null)
			doc.close();
			if(writer != null)
			writer.close(); 
			
		}
		
	}
	
	public Boolean isPDFileExist(String yearDotMonth) {
		return new File(PDF_FOLDER_PATH + createPDFileName(yearDotMonth)).exists();
	}
	
	public String createPDFileName(String yearDotMonth) {
		String fileName = TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME
				.substring(0, TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME.length() - 4);
		return fileName + "_utnyilvantartas_" + yearDotMonth.replace('.', '_') + ".pdf";
	}
	
	private void createHeadOfPdf(Document doc) throws DocumentException {
		float[] columnWidths = {3f, 7f, 3f, 7f};
		
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		PdfPCell companyNameTextCell = new PdfPCell(new Phrase("Cég neve:", normalFont));
		companyNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(companyNameTextCell);
		PdfPCell companyNameCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getEmployerName(), normalFont));
		companyNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(companyNameCell);
		PdfPCell employerNameTextCell = new PdfPCell(new Phrase("Munkavállaló neve:", normalFont));
		employerNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(employerNameTextCell);
		table.completeRow();
		
		PdfPCell companyAddressTextCell = new PdfPCell(new Phrase("Cég címe:", normalFont));
		companyAddressTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(companyAddressTextCell);
		table.completeRow();
		PdfPCell dateTextCell = new PdfPCell(new Phrase("Költséghely:", normalFont));
		dateTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(dateTextCell);
		table.completeRow();
		doc.add(table);
	}
	
	private void addTitleToPdf(Document doc) throws DocumentException {
		float[] columnWidth = {20f};
		PdfPTable table = new PdfPTable(columnWidth);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		PdfPCell roadRegisterTextCell = new PdfPCell(new Phrase("Útnyilvántartás", boldFont));
		roadRegisterTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roadRegisterTextCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(roadRegisterTextCell);
		table.completeRow();
		PdfPCell textCell = new PdfPCell(new Phrase("a munkába járással kapcsolatos utazási költségtérítéshez", normalFont));
		normalFont.setColor(BaseColor.DARK_GRAY);
		textCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		textCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(textCell);

		doc.add(table);
	}
	
	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		   
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		//cell.setGrayFill(0.5f);
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		   
		 }
	
	public static void main(String[] args) {
		TemplateFileManager manager = new TemplateFileManager();
		manager.readTemplateFile("GicziD.txt");
		PdfManager pdf = new PdfManager();
		pdf.createAndOpenPDFile("2021. december");
	}
}
