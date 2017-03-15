package com.example.glproject.parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.glproject.businessobjects.GenericTask;

//try catch a mettre
public class SpreadsheetParser implements Parser {

	public List<GenericTask> parse(String file) {
		List<GenericTask> list = new ArrayList<GenericTask>();
		FileInputStream input = null;

		try {
			input = new FileInputStream(new File(file));
			Workbook workbook = new XSSFWorkbook(input);
			Sheet firstSheet = workbook.getSheetAt(0); //verifier que pas null
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				GenericTask gt = new GenericTask();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int column = cell.getColumnIndex();

					// A MODIFIER
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
						// gt.setThresholdInterval(cell.getStringCellValue());
						break;
					case 4:
						// gt.setSource(cell.getStringCellValue());
						break;
					case 5:
						// gt.setRef(cell.getStringCellValue());
						break;
					case 6:
						gt.setMen((int) cell.getNumericCellValue());
						break;
					case 7:
						// gt.setmPerH(cell.getStringCellValue());
						break;
					case 8:
						gt.setApplicability(cell.getStringCellValue());
						break;
					}
					// A MODIFIER
				}

				list.add(gt);
			}

			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}