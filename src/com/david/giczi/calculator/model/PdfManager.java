package com.david.giczi.calculator.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
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
	private Font smallFont;
	private Font middleFont;
	private Font middleBoldFont;
	private Font titleTextFont;
	private Font largeBoldFont;
	private Double sumDistance;
	
	
	public PdfManager()  {
	
		templateFileManager = new TemplateFileManager();
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		doc = new Document(PageSize.A4);
		try {
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
			smallFont = new Font(baseFont, 6, Font.NORMAL);
			middleFont = new Font(baseFont, 8, Font.NORMAL);
			middleBoldFont = new Font(baseFont, 8, Font.BOLD);
			largeBoldFont = new Font(baseFont, 9, Font.BOLD);
			titleTextFont = new Font(baseFont, 16, Font.BOLD);
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
			addEmptyRow(doc);
			addTitleToPdf(doc);
			addEmptyRow(doc);
			addTableToPdf(doc, yearDotMonth);
			addFooterToPdf(doc);
			addEmptyRow(doc);
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
		PdfPCell roadRegisterTextCell = new PdfPCell(new Phrase("Útnyilvántartás", titleTextFont));
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
		
		List<Day> daysOfMonth = templateFileManager
				.createActualMonthByDaysOfMonthDisplayer(daysButtonStore)
				.stream().filter(d -> d.getNumberOfMonth() != -1 && d.isWorkDay()).collect(Collectors.toList());
		
		for (int i = 0; i < daysOfMonth.size(); i++) {
		
				String date = new MonthManager().getDateOfDay(yearDotMonth, daysOfMonth.get(i));
				PdfPCell oddNumberCell = new PdfPCell(new Phrase( ++ itemCounter + ".", middleFont));
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
				
				PdfPCell evenNumberCell = new PdfPCell(new Phrase( ++ itemCounter + ".", middleFont));
				evenNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				if(i == daysOfMonth.size() - 1) {
					evenNumberCell.setBorderWidthBottom(1.5f);
				}
				evenNumberCell.setGrayFill(0.9f);
				table.addCell(evenNumberCell);
				PdfPCell dateOfTravelToBackCell = new PdfPCell(new Phrase(date, middleFont));
				dateOfTravelToBackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				if(i == daysOfMonth.size() - 1) {
					dateOfTravelToBackCell.setBorderWidthBottom(1.5f);
				}
				dateOfTravelToBackCell.setGrayFill(0.9f);
				table.addCell(dateOfTravelToBackCell);
				PdfPCell starterBackPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getEmployerAddress(), smallFont));
				starterBackPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				if(i == daysOfMonth.size() - 1) {
					starterBackPlaceCell.setBorderWidthBottom(1.5f);
				}
				starterBackPlaceCell.setGrayFill(0.9f);
				table.addCell(starterBackPlaceCell);
				PdfPCell targetBackPlaceCell = new PdfPCell(new Phrase(templateFileManager
						.getTemplateFileData().getWorkerAddress(), smallFont));
				targetBackPlaceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				if(i == daysOfMonth.size() - 1) {
					targetBackPlaceCell.setBorderWidthBottom(1.5f);
				}
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
		sumDistanceTextCell.setBorderWidthLeft(1.5f);
		sumDistanceTextCell.setBorderWidthTop(1.5f);
		sumDistanceTextCell.setBorderWidthBottom(1.5f);
		table.addCell(sumDistanceTextCell);
		PdfPCell emptyCell = new PdfPCell(new Phrase(null, middleFont));
		emptyCell.setBorderWidthLeft(1.5f);
		emptyCell.setBorderWidthTop(1.5f);
		emptyCell.setBorderWidthRight(1.5f);
		emptyCell.setBorderWidthBottom(1.5f);
		table.addCell(emptyCell);
		PdfPCell sumDistanceValueCell = new PdfPCell(new Phrase(calcSummaDistance(), middleFont));
		sumDistanceValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		sumDistanceValueCell.setBorderWidthBottom(1.5f);
		sumDistanceValueCell.setBorderWidthTop(1.5f);
		sumDistanceValueCell.setBorderWidthRight(1.5f);
		table.addCell(sumDistanceValueCell);
		table.completeRow();
	}
	
	private void addFooterToPdf(Document doc) throws DocumentException {
		
		float[] firstRowColumnWidth = {4f};
		PdfPTable firstRowTable = new PdfPTable(firstRowColumnWidth);
		firstRowTable.getDefaultCell().setFixedHeight(15);
		firstRowTable.setWidthPercentage(70f);
		PdfPCell infoCell1 = new PdfPCell(new Phrase("Leadás: tárgy hónapot követõ hónap 1-jéig!", middleBoldFont));
		infoCell1.setBorder(Rectangle.NO_BORDER);
		firstRowTable.addCell(infoCell1);
		firstRowTable.completeRow();
		doc.add(firstRowTable);
		
		float[] secondRowColumnWidths = {20f, 11f, 3f};
		PdfPTable secondRowTable = new PdfPTable(secondRowColumnWidths);
		secondRowTable.getDefaultCell().setFixedHeight(15);
		secondRowTable.setWidthPercentage(100f);
		PdfPCell emptyCell1 = new PdfPCell(new Phrase(null, middleBoldFont));
		emptyCell1.setBorder(Rectangle.NO_BORDER);
		secondRowTable.addCell(emptyCell1);
		PdfPCell infoCell2 = new PdfPCell(new Phrase("Munkába járás km elszámolás összege:", middleBoldFont));
		secondRowTable.addCell(infoCell2);
		PdfPCell sumCostCell = new PdfPCell(new Phrase(calcSummaTravelCost(), largeBoldFont));
		sumCostCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		secondRowTable.addCell(sumCostCell);
		secondRowTable.completeRow();
		
		PdfPCell emptyCell2 = new PdfPCell(new Phrase(null, middleBoldFont));
		emptyCell2.setBorder(Rectangle.NO_BORDER);
		secondRowTable.addCell(emptyCell2);
		PdfPCell emptyCell3 = new PdfPCell(new Phrase(null, middleBoldFont));
		emptyCell3.setBorder(Rectangle.NO_BORDER);
		secondRowTable.addCell(emptyCell3);
		middleFont.setColor(BaseColor.DARK_GRAY);
		PdfPCell pricePerDistanceCell = new PdfPCell(
				new Phrase("(" + templateFileManager.getTemplateFileData().getPricePerDistance() +" Ft/km)", middleFont));
		pricePerDistanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pricePerDistanceCell.setBorder(Rectangle.NO_BORDER);
		secondRowTable.addCell(pricePerDistanceCell);
		doc.add(secondRowTable);
		secondRowTable.completeRow();
		
		float[] thirdRowColumnWidths = {15f, 10f, 15f};
		PdfPTable thirdRowTable = new PdfPTable(thirdRowColumnWidths);
		thirdRowTable.getDefaultCell().setFixedHeight(15);
		thirdRowTable.setWidthPercentage(80f);
		PdfPCell employerSignatureLineCell = new PdfPCell(new Phrase(" ", middleBoldFont));
		employerSignatureLineCell.setBorderWidthLeft(0);
		employerSignatureLineCell.setBorderWidthTop(0);
		employerSignatureLineCell.setBorderWidthRight(0);
		thirdRowTable.addCell(employerSignatureLineCell);
		PdfPCell emptyCell4 = new PdfPCell(new Phrase(null, middleBoldFont));
		emptyCell4.setBorderWidthLeft(0);
		emptyCell4.setBorderWidthTop(0);
		emptyCell4.setBorderWidthBottom(0);
		emptyCell4.setBorderWidthRight(0);
		thirdRowTable.addCell(emptyCell4);
		PdfPCell workerSignatureLineCell = new PdfPCell(new Phrase(" ", middleBoldFont));
		workerSignatureLineCell.setBorderWidthTop(0);
		workerSignatureLineCell.setBorderWidthRight(0);
		workerSignatureLineCell.setBorderWidthLeft(0);
		thirdRowTable.addCell(workerSignatureLineCell);
		thirdRowTable.completeRow();
		
		addEmptyRow(doc);
		
		middleFont.setColor(BaseColor.BLACK);
		PdfPCell employerTextCell = new PdfPCell(new Phrase("egységvezetõ aláírása", middleFont));
		employerTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		employerTextCell.setBorderWidthLeft(0);
		employerTextCell.setBorderWidthTop(0);
		employerTextCell.setBorderWidthBottom(0);
		employerTextCell.setBorderWidthRight(0);
		thirdRowTable.addCell(employerTextCell);
		PdfPCell emptyCell5 = new PdfPCell(new Phrase(null, middleBoldFont));
		emptyCell5.setBorderWidthLeft(0);
		emptyCell5.setBorderWidthTop(0);
		emptyCell5.setBorderWidthBottom(0);
		emptyCell5.setBorderWidthRight(0);
		thirdRowTable.addCell(emptyCell5);
		PdfPCell workerTextCell = new PdfPCell(new Phrase("munkavállaló aláírása", middleFont));
		workerTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		workerTextCell.setBorderWidthLeft(0);
		workerTextCell.setBorderWidthTop(0);
		workerTextCell.setBorderWidthBottom(0);
		workerTextCell.setBorderWidthRight(0);
		thirdRowTable.addCell(workerTextCell);
		thirdRowTable.completeRow();
		doc.add(thirdRowTable);
	
		addEmptyRow(doc);
		
		float[] lastRowColumnWidth = {30f};
		PdfPTable lastRowTable = new PdfPTable(lastRowColumnWidth);
		lastRowTable.getDefaultCell().setFixedHeight(15);
		lastRowTable.setWidthPercentage(100f);
		PdfPCell infoTextCell = new PdfPCell(
				new Phrase("Az egységvezetõ aláírása nélkül a nyilvántartás alapján készült elszámolás nem fizethetõ ki!", middleFont));
		infoTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		infoTextCell.setBorderWidthLeft(0);
		infoTextCell.setBorderWidthTop(0);
		infoTextCell.setBorderWidthBottom(0);
		infoTextCell.setBorderWidthRight(0);
		lastRowTable.addCell(infoTextCell);
		lastRowTable.completeRow();
		doc.add(lastRowTable);
	}
	
	private void addEmptyRow(Document doc) throws DocumentException {
		
		float[] emptyRowColumnWidth = {30f};
		PdfPTable emptyRowTable = new PdfPTable(emptyRowColumnWidth);
		emptyRowTable.getDefaultCell().setFixedHeight(15);
		emptyRowTable.setWidthPercentage(100f);
		PdfPCell emptyCell = new PdfPCell(
				new Phrase(" ", middleFont));
		emptyCell.setBorderWidthLeft(0);
		emptyCell.setBorderWidthTop(0);
		emptyCell.setBorderWidthBottom(0);
		emptyCell.setBorderWidthRight(0);
		emptyRowTable.addCell(emptyCell);
		emptyRowTable.completeRow();
		doc.add(emptyRowTable);
	}
	
	private String calcSummaDistance() {
		
		int travelCounter = 0;
		
		for (Day day : templateFileManager.createActualMonthByDaysOfMonthDisplayer(daysButtonStore)) {
			if(day.getNumberOfMonth() != -1 && day.isWorkDay()) {
				travelCounter += 2;
				
			}
		}
		
		sumDistance = travelCounter * 
				Double.parseDouble(templateFileManager.getTemplateFileData().getDistance());
		
		return sumDistance % 1 == 0 ? sumDistance.toString()
				.substring(0, sumDistance.toString().indexOf(".")) + " km" : String.format("%.1f", sumDistance) + " km";
	}
	
	private String calcSummaTravelCost() {
		
		double summaTravelCost = 
				sumDistance * Double.parseDouble(templateFileManager.getTemplateFileData().getPricePerDistance());
		
		int summaTravelCostIntegerValue = (int) Math.round(summaTravelCost);
		int lastIntegerDigit = summaTravelCostIntegerValue % 10;
		
		switch (lastIntegerDigit) {
		case 1:
			summaTravelCostIntegerValue --;
			break;
		case 2:
			summaTravelCostIntegerValue -= 2;
			break;
		case 3:
			summaTravelCostIntegerValue += 2;
			break;
		case 4:
			summaTravelCostIntegerValue ++;
			break;
		case 6:
			summaTravelCostIntegerValue --;
			break;
		case 7:
			summaTravelCostIntegerValue -= 2;
			break;
		case 8:
			summaTravelCostIntegerValue += 2;
			break;
		case 9:
			summaTravelCostIntegerValue ++;
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("###,###.### Ft");
		
		return decimalFormat.format(summaTravelCostIntegerValue);
	}
}
