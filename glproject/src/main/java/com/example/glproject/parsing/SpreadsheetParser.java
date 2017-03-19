package com.example.glproject.parsing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.GenericTask;
import com.example.glproject.persistence.ElasticSearchClient;

//try catch a mettre
public class SpreadsheetParser implements Parser {
	ElasticSearchClient esc = ElasticSearchClient.getInstance();

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
						gt.setZone((int) cell.getNumericCellValue());
						break;
					case 2:
						gt.setDescr(cell.getStringCellValue());
						break;
					case 3:
						gt.setPeriodicity((long) cell.getNumericCellValue());
						break;
					case 4:
						gt.setHangar(cell.getBooleanCellValue());
						break;
					case 5:
						gt.setDuration((long) cell.getNumericCellValue());
						break;
					case 6:
						gt.setMen((int) cell.getNumericCellValue());
						break;
					case 7:
						gt.setApplicability(cell.getStringCellValue());
						break;
					}
				}
				
				//add generictask to database
				DAOFactory.getGenericTaskDAO().add(gt);
			}

			mpd.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}