package com.david.giczi.calculator.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfManager {

	private TemplateFileManager templateFileManager;
	private String PDF_FOLDER_PATH = "./TravelCostCalculatorData/Utnyilvantartasok/";
	public String pdfAbsolutePath = new File(PDF_FOLDER_PATH).getAbsolutePath();
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
			
			addHeadToPdf(doc, yearDotMonth);
			addTitleToPdf(doc);
			addLogoToPdf(doc);
			
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
	
	private void addHeadToPdf(Document doc, String yearDotMonth) throws DocumentException {
		float[] columnWidths = {3f, 7f, 3f, 7f};
		
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		
		PdfPCell employerNameTextCell = new PdfPCell(new Phrase("Cég neve:", normalFont));
		employerNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(employerNameTextCell);
		PdfPCell companyNameCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getEmployerName(), normalFont));
		companyNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(companyNameCell);
		
		PdfPCell workerNameTextCell = new PdfPCell(new Phrase("Munkavállaló neve:", normalFont));
		workerNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(workerNameTextCell);
		PdfPCell workerNameCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getWorkerName(), normalFont));
		workerNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(workerNameCell);
		table.completeRow();
		
		PdfPCell companyAddressTextCell = new PdfPCell(new Phrase("Cég címe:", normalFont));
		companyAddressTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(companyAddressTextCell);
		PdfPCell companyAddressCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getEmployerAddress(), normalFont));
		companyAddressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(companyAddressCell);
		PdfPCell plateNumberTextCell = new PdfPCell(new Phrase("Forgalmi rendszám:", normalFont));
		plateNumberTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(plateNumberTextCell);
		PdfPCell plateNumberCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getPlate(), normalFont));
		plateNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(plateNumberCell);
		table.completeRow();
		
		PdfPCell dateTextCell = new PdfPCell(new Phrase("Költséghely:", normalFont));
		dateTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(dateTextCell);
		PdfPCell emptyCell = new PdfPCell(new Phrase("", normalFont));
		table.addCell(emptyCell);
		PdfPCell yearMonthTextCell = new PdfPCell(new Phrase("Év, hónap:", normalFont));
		yearMonthTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(yearMonthTextCell);
		PdfPCell yearMonthCell = new PdfPCell(new Phrase(yearDotMonth, normalFont));
		yearMonthCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(yearMonthCell);
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
		PdfPCell belowTextCell = new PdfPCell(new Phrase("a munkába járással kapcsolatos utazási költségtérítéshez", normalFont));
		normalFont.setColor(BaseColor.DARK_GRAY);
		belowTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		belowTextCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(belowTextCell);

		doc.add(table);
	}

	
	private void addLogoToPdf(Document doc) throws MalformedURLException, IOException, DocumentException {
		
		byte[] imageSource = this.getClass().getResourceAsStream("/logo/kesz_logo.png").readAllBytes(); 
		Image logo = Image.getInstance(imageSource);
		logo.scaleAbsolute(500, 50);
		doc.add(logo);
	}
		//cell.setGrayFill(0.5f);
		
	public static void main(String[] args) {
		TemplateFileManager manager = new TemplateFileManager();
		manager.readTemplateFile("GicziD.txt");
		PdfManager pdf = new PdfManager();
		pdf.createAndOpenPDFile("2021. december");
	}
}
