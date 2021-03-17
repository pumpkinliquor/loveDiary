package com.plushih.common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ExcelTemplateBase {

  public void template(Workbook workbook) {
    int sheetLength = workbook.getNumberOfSheets();
    for (int i = 0; i < sheetLength; i++) {
      Sheet sheet = workbook.getSheetAt(i);
      template(sheet);
    }
  }

  public void template(Sheet sheet) {
    int firstRowNum = sheet.getFirstRowNum();        //시작행
    int lastRowNum = sheet.getLastRowNum();            //종료행

    for (int i = firstRowNum; i <= lastRowNum; i++) {
      Row row = sheet.getRow(i);
      if (row == null) {
        continue;
      }
      for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
        Cell cell = row.getCell(j);
        if (cell == null) {
          continue;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
          template(cell);
        }
      }
    }
  }

  public abstract void template(Cell cell);
}
