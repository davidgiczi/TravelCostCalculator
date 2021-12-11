package com.david.giczi.calculator.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JButton;
import com.david.giczi.calculator.view.DaysOfMonthDisplayer;
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
	private Font smallFont;
	private Font middleFont;
	private Font middleBoldFont;
	private Font largeBoldFont;
	
	
	public PdfManager()  {
	
		templateFileManager = new TemplateFileManager();
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		doc = new Document(PageSize.A4);
		try {
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
			smallFont = new Font(baseFont, 6, Font.NORMAL);
			middleFont = new Font(baseFont, 8, Font.NORMAL);
			middleBoldFont = new Font(baseFont, 8, Font.BOLD);
			largeBoldFont = new Font(baseFont, 16, Font.BOLD);
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
		
			addHeaderToPdf(doc, yearDotMonth);
			addTitleToPdf(doc);
			addTableToPdf(doc, yearDotMonth);
			addFooterToPdf(doc);
			//addLogoToPdf(doc);
			
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
	
	private void addHeaderToPdf(Document doc, String yearDotMonth) throws DocumentException {
		float[] columnWidths = {3f, 7f, 3f, 7f};
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		
		PdfPCell employerNameTextCell = new PdfPCell(new Phrase("Cég neve:", middleFont));
		employerNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		employerNameTextCell.setBorderWidthTop(1.5f);
		table.addCell(employerNameTextCell);
		PdfPCell companyNameCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getEmployerName(), middleFont));
		companyNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		companyNameCell.setBorderWidthTop(1.5f);
		table.addCell(companyNameCell);
		
		PdfPCell workerNameTextCell = new PdfPCell(new Phrase("Munkavállaló neve:", middleFont));
		workerNameTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		workerNameTextCell.setBorderWidthTop(1.5f);
		table.addCell(workerNameTextCell);
		PdfPCell workerNameCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getWorkerName(), middleFont));
		workerNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		workerNameCell.setBorderWidthTop(1.5f);
		table.addCell(workerNameCell);
		table.completeRow();
		
		PdfPCell companyAddressTextCell = new PdfPCell(new Phrase("Cég címe:", middleFont));
		companyAddressTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(companyAddressTextCell);
		PdfPCell companyAddressCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getEmployerAddress(), middleFont));
		companyAddressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(companyAddressCell);
		PdfPCell plateNumberTextCell = new PdfPCell(new Phrase("Forgalmi rendszám:", middleFont));
		plateNumberTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(plateNumberTextCell);
		PdfPCell plateNumberCell = new PdfPCell(new Phrase(templateFileManager.getTemplateFileData().getPlate(), middleFont));
		plateNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(plateNumberCell);
		table.completeRow();
		
		PdfPCell costPlaceTextCell = new PdfPCell(new Phrase("Költséghely:", middleFont));
		costPlaceTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		costPlaceTextCell.setBorderWidthBottom(1.5f);
		table.addCell(costPlaceTextCell);
		PdfPCell emptyCell = new PdfPCell(new Phrase(null, middleFont));
		emptyCell.setBorderWidthBottom(1.5f);
		table.addCell(emptyCell);
		PdfPCell yearMonthTextCell = new PdfPCell(new Phrase("Év, hónap:", middleFont));
		yearMonthTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		yearMonthTextCell.setBorderWidthBottom(1.5f);
		table.addCell(yearMonthTextCell);
		PdfPCell yearMonthCell = new PdfPCell(new Phrase(yearDotMonth, middleFont));
		yearMonthCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		yearMonthCell.setBorderWidthBottom(1.5f);
		table.addCell(yearMonthCell);
		table.completeRow();
		doc.add(table);
	}
	
	private void addTitleToPdf(Document doc) throws DocumentException {
		float[] columnWidth = {20f};
		PdfPTable table = new PdfPTable(columnWidth);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		PdfPCell roadRegisterTextCell = new PdfPCell(new Phrase("Útnyilvántartás", largeBoldFont));
		roadRegisterTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roadRegisterTextCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(roadRegisterTextCell);
		table.completeRow();
		PdfPCell belowTextCell = new PdfPCell(new Phrase("a munkába járással kapcsolatos utazási költségtérítéshez", middleFont));
		middleFont.setColor(BaseColor.DARK_GRAY);
		belowTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		belowTextCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(belowTextCell);
		doc.add(table);
	}

	
	private void addLogoToPdf(Document doc) throws MalformedURLException, IOException, DocumentException {
		
		byte[] imageSource = this.getClass().getResourceAsStream("/logo/kesz_logo.png").readAllBytes(); 
		Image logo = Image.getInstance(imageSource);
		logo.scaleAbsolute(520, 60);
		doc.add(logo);
	}
	
	private void addTableToPdf(Document doc, String yearDotMonth) throws DocumentException {
		float[] columnWidths = {2f, 3f, 9f, 9f, 5f, 3f, 3f};
		middleFont.setColor(BaseColor.BLACK);
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setFixedHeight(15);
		table.setWidthPercentage(100f);
		
		PdfPCell sszCell = new PdfPCell(new Phrase("Ssz.", middleFont));
		sszCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		sszCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		sszCell.setRowspan(3);
		sszCell.setBorderWidthTop(1.5f);
		table.addCell(sszCell);
		PdfPCell dateOfTravelCell = new PdfPCell(new Phrase("Az utazás idõpontja", middleFont));
		dateOfTravelCell.setRowspan(2);
		dateOfTravelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		dateOfTravelCell.setBorderWidthTop(1.5f);
		table.addCell(dateOfTravelCell);
		PdfPCell roadTrackCell = new PdfPCell(new Phrase("Útvonal", middleFont));
		roadTrackCell.setColspan(2);
		roadTrackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		roadTrackCell.setBorderWidthTop(1.5f);
		table.addCell(roadTrackCell);
		PdfPCell partnerCells = new PdfPCell(new Phrase("Felkeresett üzleti partner(ek)", middleFont));
		partnerCells.setRowspan(2);
		partnerCells.setHorizontalAlignment(Element.ALIGN_CENTER);
		partnerCells.setBorderWidthTop(1.5f);
		table.addCell(partnerCells);
		PdfPCell distanceTextCell = new PdfPCell(new Phrase("Megtett kilométer", middleFont));
		distanceTextCell.setColspan(2);
		distanceTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		distanceTextCell.setBorderWidthTop(1.5f);
		table.addCell(distanceTextCell);
		PdfPCell fromCell = new PdfPCell(new Phrase("honnan", middleFont));
		fromCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(fromCell);
		PdfPCell whereCell = new PdfPCell(new Phrase("hova", middleFont));
		whereCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(whereCell);
		PdfPCell businessCell = new PdfPCell(new Phrase("üzleti", middleFont));
		businessCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(businessCell);
		PdfPCell privateCell = new PdfPCell(new Phrase("magán", middleFont));
		privateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(privateCell);
		
		PdfPCell aCell = new PdfPCell(new Phrase("a)", middleFont));
		aCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(aCell);
		PdfPCell bCell = new PdfPCell(new Phrase("b)", middleFont));
		bCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(bCell);
		PdfPCell cCell = new PdfPCell(new Phrase("c)", middleFont));
		cCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cCell);
		PdfPCell dCell = new PdfPCell(new Phrase("d)", middleFont));
		dCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(dCell);
		PdfPCell eCell = new PdfPCell(new Phrase("e)", middleFont));
		eCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(eCell);
		PdfPCell fCell = new PdfPCell(new Phrase("f)", middleFont));
		fCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(fCell);
		table.completeRow();
		createDataCells(doc, table, yearDotMonth);
		
	}
	
	private void createDataCells(Document doc, PdfPTable table, String yearDotMonth) throws DocumentException {
		
		int itemCounter = 0;
		
		for (Day day : templateFileManager.createActualMonthByDaysOfMonthDisplayer(daysButtonStore)) {
			if(day.getNumberOfMonth() != -1 && day.isWorkDay()) {
				String date = new MonthManager().getDateOfDay(yearDotMonth, day);
				PdfPCell oddNumberCell = new PdfPCell(new Phrase(++itemCounter + ".", middleFont));
				oddNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(oddNumberCell);
				PdfPCell dateOfTravelToTargetCell = new PdfPCell(new Phrase(date, middleFont));
				dateOfTravelToTargetCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(dateOfTravelToTargetCell);
				PdfPCell starterPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getWorkerAddress(), smallFont));
				starterPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(starterPlaceCell);
				PdfPCell targetPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getEmployerAddress(), smallFont));
				targetPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(targetPlaceCell);
				PdfPCell partnerCell1 = new PdfPCell(new Phrase(null, middleFont));
				table.addCell(partnerCell1);
				PdfPCell businessCell1 = new PdfPCell(new Phrase(null, middleFont));
				table.addCell(businessCell1);
				String distance = templateFileManager.getTemplateFileData().getDistance().contains(".") ?
												templateFileManager.getTemplateFileData().getDistance() : 
												templateFileManager.getTemplateFileData().getDistance() + ".0";
				PdfPCell startDistanceCell = new PdfPCell(new Phrase(distance, middleFont));
				startDistanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(startDistanceCell);
				table.completeRow();
				
				PdfPCell evenNumberCell = new PdfPCell(new Phrase(++itemCounter + ".", middleFont));
				evenNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				evenNumberCell.setGrayFill(0.9f);
				table.addCell(evenNumberCell);
				PdfPCell dateOfTravelToBackCell = new PdfPCell(new Phrase(date, middleFont));
				dateOfTravelToBackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				dateOfTravelToBackCell.setGrayFill(0.9f);
				table.addCell(dateOfTravelToBackCell);
				PdfPCell starterBackPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getEmployerAddress(), smallFont));
				starterBackPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				starterBackPlaceCell.setGrayFill(0.9f);
				table.addCell(starterBackPlaceCell);
				PdfPCell targetBackPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getWorkerAddress(), smallFont));
				targetBackPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				targetBackPlaceCell.setGrayFill(0.9f);
				table.addCell(targetBackPlaceCell);
				PdfPCell partnerCell2 = new PdfPCell(new Phrase(null, middleFont));
				partnerCell2.setGrayFill(0.9f);
				table.addCell(partnerCell2);
				PdfPCell businessCell2 = new PdfPCell(new Phrase(null, middleFont));
				businessCell2.setGrayFill(0.9f);
				table.addCell(businessCell2);
				PdfPCell backDistanceCell = new PdfPCell(new Phrase(distance, middleFont));
				backDistanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				backDistanceCell.setGrayFill(0.9f);
				table.addCell(backDistanceCell);
				table.completeRow();
			}
		}
		
		addSummaRowToTable(doc, table);
		doc.add(table);
	}
	
	private void addSummaRowToTable(Document doc, PdfPTable table) {
		
		PdfPCell invisibleCell1 = new PdfPCell(new Phrase(null, middleFont));
		invisibleCell1.setBorder(Rectangle.NO_BORDER);
		table.addCell(invisibleCell1);
		PdfPCell invisibleCell2 = new PdfPCell(new Phrase(null, middleFont));
		invisibleCell2.setBorder(Rectangle.NO_BORDER);
		table.addCell(invisibleCell2);
		PdfPCell invisibleCell3 = new PdfPCell(new Phrase(null, middleFont));
		invisibleCell3.setBorder(Rectangle.NO_BORDER);
		table.addCell(invisibleCell3);
		PdfPCell invisibleCell4 = new PdfPCell(new Phrase(null, middleFont));
		invisibleCell4.setBorder(Rectangle.NO_BORDER);
		table.addCell(invisibleCell4);
		PdfPCell sumDistanceTextCell = new PdfPCell(new Phrase("Összesen:", middleBoldFont));
		sumDistanceTextCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		sumDistanceTextCell.setBorderWidthBottom(1.5f);
		table.addCell(sumDistanceTextCell);
		PdfPCell emptyCell = new PdfPCell(new Phrase(null, middleFont));
		emptyCell.setBorderWidthBottom(1.5f);
		table.addCell(emptyCell);
		PdfPCell sumDistanceValueCell = new PdfPCell(new Phrase(calcSummaDistance(), middleFont));
		sumDistanceValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		sumDistanceValueCell.setBorderWidthBottom(1.5f);
		table.addCell(sumDistanceValueCell);
		table.completeRow();
	}
	
	private void addFooterToPdf(Document doc) {
		
	}
	
	private String calcSummaDistance() {
		
		int travelCounter = 0;
		
		for (Day day : templateFileManager.createActualMonthByDaysOfMonthDisplayer(daysButtonStore)) {
			if(day.getNumberOfMonth() != -1 && day.isWorkDay()) {
				travelCounter += 2;
				
			}
		}
		
		Double sumDistance = travelCounter * 
				Double.parseDouble(templateFileManager.getTemplateFileData().getDistance());
		
		return sumDistance % 1 == 0 ? sumDistance.toString()
				.substring(0, sumDistance.toString().indexOf(".")) + " km" : sumDistance.toString() + " km";
	}
	
	public static void main(String[] args) {
		
		MonthManager month = new MonthManager();
		DaysOfMonthDisplayer displayer = new DaysOfMonthDisplayer();
		displayer.addButtonsOfDaysToTheFrame(month.createMonth(2021, 7));
		displayer.addOtherMonthAskingLabelsToTheFrame("2021. augusztus");
		TemplateFileManager manager = new TemplateFileManager();
		manager.readTemplateFile("G3Dolgozo.txt");
		PdfManager pdf = new PdfManager();
		pdf.setDaysButtonStore(displayer.getjButtonStoreForDays());
		pdf.createAndOpenPDFile(displayer.getActualYearDotMonthAsString());
	}
}
