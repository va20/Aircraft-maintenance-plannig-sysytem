package com.glproject.groupe3.util;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.persistence.ElasticSearchClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class SpreadsheetParser implements Parser {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

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
					// case 4:
					// gt.setPeriodicity(cell.getStringCellValue());
					// break;
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
						DateTime d = new DateTime(cell.getDateCellValue());
						flight.setDepTime(d);
						break;
					case 5:
						DateTime date = new DateTime(cell.getDateCellValue());
						flight.setArrTime(date);
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