package com.david.giczi.calculator.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfManager {

	private TemplateFileManager templateFileManager;
	private String PDF_FOLDER_PATH = "./TravelCostCalculatorData/Utnyilvantartasok/";
	public String pdfAbsolutePath = new File(PDF_FOLDER_PATH).getAbsolutePath();
	private JButton[] daysButtonStore;
	private Document doc;
	private PdfWriter writer;
	private Font normalFont;
	private Font boldFont;
	
	public PdfManager()  {
	
		templateFileManager = new TemplateFileManager();
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		doc = new Document(PageSize.A4);
		try {
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
			normalFont = new Font(baseFont, 8, Font.NORMAL);
			boldFont = new Font(baseFont, 16, Font.BOLD);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDaysButtonStore(JButton[] daysButtonStore) {
		this.daysButtonStore = daysButtonStore;
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
			addTableToPdf(doc);
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
	
	private void addTableToPdf(Document doc) throws DocumentException {
		float[] columnWidths = {2f, 3f, 8f, 8f, 5f, 3f, 3f};
		normalFont.setColor(BaseColor.BLACK);
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		
		PdfPCell sszCell = new PdfPCell(new Phrase("Ssz.", normalFont));
		sszCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		sszCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		sszCell.setRowspan(3);
		table.addCell(sszCell);
		PdfPCell dateOfTravelCell = new PdfPCell(new Phrase("Az utazás idõpontja", normalFont));
		dateOfTravelCell.setRowspan(2);
		dateOfTravelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(dateOfTravelCell);
		PdfPCell roadTrackCell = new PdfPCell(new Phrase("Útvonal", normalFont));
		roadTrackCell.setColspan(2);
		roadTrackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(roadTrackCell);
		PdfPCell partnerCells = new PdfPCell(new Phrase("Felkeresett üzleti partner(ek)", normalFont));
		partnerCells.setRowspan(2);
		partnerCells.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(partnerCells);
		PdfPCell distanceTextCell = new PdfPCell(new Phrase("Megtett kilométer", normalFont));
		distanceTextCell.setColspan(2);
		distanceTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(distanceTextCell);
		PdfPCell fromCell = new PdfPCell(new Phrase("honnan", normalFont));
		fromCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(fromCell);
		PdfPCell whereCell = new PdfPCell(new Phrase("hova", normalFont));
		whereCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(whereCell);
		PdfPCell businessCell = new PdfPCell(new Phrase("üzleti", normalFont));
		businessCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(businessCell);
		PdfPCell privateCell = new PdfPCell(new Phrase("magán", normalFont));
		privateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(privateCell);
		
		PdfPCell aCell = new PdfPCell(new Phrase("a)", normalFont));
		aCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(aCell);
		PdfPCell bCell = new PdfPCell(new Phrase("b)", normalFont));
		bCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(bCell);
		PdfPCell cCell = new PdfPCell(new Phrase("c)", normalFont));
		cCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cCell);
		PdfPCell dCell = new PdfPCell(new Phrase("d)", normalFont));
		dCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(dCell);
		PdfPCell eCell = new PdfPCell(new Phrase("e)", normalFont));
		eCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(eCell);
		PdfPCell fCell = new PdfPCell(new Phrase("f)", normalFont));
		fCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(fCell);
		table.completeRow();
		createDataCells(doc, table);
		
	}
	
	private void createDataCells(Document doc, PdfPTable table) throws DocumentException {
		
		int workDayCounter = 0;
		
		for (Day day : templateFileManager.createActualMonthByDaysOfMonthDisplayer(daysButtonStore)) {
			if(day.getNumberOfMonth() != -1 && day.isWorkDay()) {
				PdfPCell numberCell = new PdfPCell(new Phrase(++workDayCounter + ".", normalFont));
				numberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(numberCell);
				PdfPCell cell2 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell3);
				PdfPCell cell4 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell5);
				PdfPCell cell6 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell6);
				PdfPCell cell7 = new PdfPCell(new Phrase(" ", normalFont));
				table.addCell(cell7);
			}
		}
		
		doc.add(table);
	}
	
		//cell.setGrayFill(0.5f);
		
	public static void main(String[] args) {
		TemplateFileManager manager = new TemplateFileManager();
		manager.readTemplateFile("GicziD.txt");
		PdfManager pdf = new PdfManager();
		pdf.createAndOpenPDFile("2021. december");
	}
}
