package com.glproject.groupe3.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.businessobjects.GenericTask;

public class SpreadsheetParser implements Parser {

	public void importFile(InputStream mpd) {
		try {
			Workbook workbook = new XSSFWorkbook(mpd);
			Sheet firstSheet = workbook.getSheetAt(0);
			if (firstSheet == null)
				return;

			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0)
					nextRow = iterator.next();

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				GenericTask gt = new GenericTask();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int column = cell.getColumnIndex();

					switch (column) {
					case 0:
						gt.setTaskNumber(cell.getStringCellValue());
						break;
					case 1:
						gt.setType(cell.getStringCellValue());
						break;
					case 2:
						gt.setZone((int) cell.getNumericCellValue());
						break;
					case 3:
						gt.setDescr(cell.getStringCellValue());
						break;
					case 4:
						gt.setPeriodicity(cell.getStringCellValue());
						break;
					case 5:
						gt.setHangar(cell.getStringCellValue());
						break;
					case 6:
						gt.setMen((int) cell.getNumericCellValue());
						break;
					case 7:
						gt.setApplicability(cell.getStringCellValue());
						break;
					}
				}

				((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO()).add(gt,
						Constants.GENERIC_TASKS, gt.getTaskNumber());
				System.out.println(gt.toString());
			}

			mpd.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void importFlights(InputStream flightFile) {
		try {
			Workbook workbook = new XSSFWorkbook(flightFile);
			Sheet firstSheet = workbook.getSheetAt(0);
			CreationHelper createHelper = workbook.getCreationHelper();
			if (firstSheet == null)
				return;

			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0)
					nextRow = iterator.next();

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Flight flight = new Flight();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int column = cell.getColumnIndex();

					switch (column) {
					case 0:
						flight.setIdPlane((long) cell.getNumericCellValue());
						break;
					case 1:
						flight.setCommercial(cell.getStringCellValue());
						break;
					case 2:
						flight.setDepAirport(cell.getStringCellValue());
						break;
					case 3:
						flight.setArrAirport(cell.getStringCellValue());
						break;
					case 4:
						CellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
						cell = nextRow.createCell(1);
						DateTime d = new DateTime();
						cell.setCellValue(d.toDate());
						cell.setCellStyle(cellStyle);
						flight.setDepTime(d);
						break;
					case 5:
						DataFormatter dataFormatter = new DataFormatter();
						String cellStringValue = dataFormatter.formatCellValue(cell);

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						DateTime d2 = null;

						try {
							d2 = new DateTime(dateFormat.parse(cellStringValue));

						} catch (ParseException e) {
							e.printStackTrace();
						}

						cell.setCellValue(cellStringValue);
						flight.setDepTime(d2);
						break;
					}
				}

				((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight,
						Constants.FLIGHTS, flight.getCommercial());
				System.out.println(flight.toString());
			}

			flightFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}