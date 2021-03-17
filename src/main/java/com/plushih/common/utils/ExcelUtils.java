package com.plushih.common.utils;

import com.plushih.common.exceptions.ExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

  public final static class ExcelResult {
    // 엑셀 전용
    /** 갯수가 일치하지 않음 */
    public final static String COUNT_NOT_EQUAL     = "notCountEqual";
    /** 컬럼 갯수가 일치하지 않음 */
    public final static String COL_COUNT_NOT_EQUAL = "notColCountEqual";
    /** 파일이 없음 */
    public final static String NO_FILE             = "noFile";
    /** 잘못된 확장자임 */
    public final static String WRONG_EXT           = "wrongExt";
    /** 필수입력항목임 */
    public final static String IS_REQUIRED         = "isRequired";
    /** 잘못된 값임 */
    public final static String IS_INVALID          = "isInvalid";
    /** 결과 없음 */
    public final static String NO_RESULT           = "noResult";
  }

  private static Workbook getWorkbook(InputStream is, String fileName) throws IOException, InvalidFormatException {
    if (StringUtils.isEmpty(fileName)) {
      throw new FileNotFoundException("Excel File not found.");
    } else if (fileName.endsWith(".xls")) {
      return new HSSFWorkbook(new POIFSFileSystem(is));
    } else if (fileName.endsWith(".xlsx")) {
      return new XSSFWorkbook(OPCPackage.open(is));
    }
    throw new IllegalArgumentException("File extension not allow. filename is " + fileName);
  }

  private static Map<String, Object> getStreamMap(Object source) throws IOException {
    Map<String, Object> streamMap = new HashMap<String, Object>();
    if (source instanceof File) {
      File file = ((File) source);
      if (file.isFile()) {
        streamMap.put("fileName", file.getName());
        streamMap.put("is", new FileInputStream(file));
      }
    } else if (source instanceof MultipartFile) {
      MultipartFile file = ((MultipartFile) source);
      if (!file.isEmpty()) {
        streamMap.put("fileName", file.getOriginalFilename());
        streamMap.put("is", file.getInputStream());
      }
    }
    if (streamMap.get("is") == null) {
      throw new IOException("File input stream is null.");
    }
    return streamMap;
  }

  private static Workbook getWorkbook(Object sourceFile) throws IOException, InvalidFormatException {
    Map<String, Object> streamMap = getStreamMap(sourceFile);
    InputStream is = (InputStream) streamMap.get("is");
    String fileName = String.valueOf(streamMap.get("fileName"));
    return getWorkbook(is, fileName);
  }

  private static String[][][] getExcelData(Object source,
                                           int firstRowPosition) throws IOException, InvalidFormatException {
    return getExcelData(source, firstRowPosition, -1);
  }

  private static String[][][] getExcelData(Object source, int firstRowPosition,
                                           int lastRowPosition) throws IOException, InvalidFormatException {
    String[][][] result;

    Workbook workbook = getWorkbook(source);
    int workbookLength = workbook == null ? 0 : workbook.getNumberOfSheets();

    result = new String[workbookLength][][];
    for (int i = 0; i < workbookLength; i++) {
      Sheet sheet = workbook.getSheetAt(i);
      int firstRowNum = sheet.getFirstRowNum() + firstRowPosition;
      int lastRowNum = lastRowPosition == -1 ? sheet.getLastRowNum() : lastRowPosition;

      if (lastRowNum - firstRowNum > -1) {
        result[i] = new String[lastRowNum - firstRowNum + 1][];
      }
      for (int j = firstRowNum; j <= lastRowNum; j++) {
        Row row = sheet.getRow(j);
        int jj = j - firstRowNum;
        if (row != null) {
          result[i][jj] = new String[row.getLastCellNum()];
          for (int k = row.getFirstCellNum(); k <= row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if (cell == null) {
              continue;
            }

            int cellType = cell.getCellType();
            if (cellType == Cell.CELL_TYPE_NUMERIC) {
              double d = cell.getNumericCellValue();
              if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
                String cellText = formatter.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d));
                result[i][jj][k] = cellText;
              } else {
                result[i][jj][k] = String.valueOf((long) cell.getNumericCellValue());
              }
            } else if (cellType == Cell.CELL_TYPE_STRING) {
              result[i][jj][k] = cell.getStringCellValue();
            } else if (cellType == Cell.CELL_TYPE_FORMULA) {
              switch (cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC :
                  result[i][jj][k] = String.valueOf(cell.getNumericCellValue());
                  break;
                case Cell.CELL_TYPE_STRING :
                  result[i][jj][k] = cell.getRichStringCellValue().getString();
                  break;
                default :
                  result[i][jj][k] = cell.getCellFormula();
              }
            } else if (cellType == Cell.CELL_TYPE_BLANK) {
              result[i][jj][k] = "";
            } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
              result[i][jj][k] = String.valueOf(cell.getBooleanCellValue());
            } else if (cellType == Cell.CELL_TYPE_ERROR) {
              result[i][jj][k] = String.valueOf(cell.getErrorCellValue());
            }
          }
        }
      }
    }
    return result;
  }

  public static <T> List<T> getInfoList(Class<T> c, Object file, String[] columnArray,
                                        Integer firstRowPosition) throws IOException, InvalidFormatException, ExcelException, IllegalAccessException, InstantiationException, NoSuchFieldException {
    //[]sheet,[]row,[]col
    String[][][] excelData = ExcelUtils.getExcelData(file, firstRowPosition);
    int workSheetLength = 1;//ListUtils.findLength(excelData);
    List<T> rowList = new ArrayList<T>();
    //long start = new Date().getTime();
    for (int i = 0; i < workSheetLength; i++) {
      int rowLength = (excelData[i] == null) ? 0 : excelData[i].length;
      if (rowLength == 0) {
        throw new ExcelException(ExcelResult.NO_RESULT);
      }

      for (int j = 0; j < rowLength; j++) {
        int colLength = (excelData[i][j] == null) ? 0 : excelData[i][j].length;
        if (colLength > columnArray.length) {
          throw new ExcelException(ExcelResult.COL_COUNT_NOT_EQUAL);
        }
        T venueInfo = c.newInstance();
        int emptyColCount = 0;
        for (int k = 0; k < colLength; k++) {
          Field field = c.getDeclaredField(columnArray[k]);
          field.setAccessible(true);
          if ("int".equals(String.valueOf(field.getType()))) {
            field.setInt(venueInfo, Integer.valueOf(excelData[i][j][k]));
          } else if ("float".equals(String.valueOf(field.getType()))) {
            field.setFloat(venueInfo, Float.valueOf(excelData[i][j][k]));
          } else if ("double".equals(String.valueOf(field.getType()))) {
            field.setDouble(venueInfo, Double.valueOf(excelData[i][j][k]));
          } else {
            field.set(venueInfo, excelData[i][j][k]);
          }
          if (StringUtils.isEmptyWithTrim(excelData[i][j][k])) {
            emptyColCount++;
          }

        }
        if (emptyColCount != colLength) {
          rowList.add(venueInfo);
        }
      }
    }
    return rowList;
  }

  public static <T> List<T> getInfoList(Class<T> c, Object file, String[] columnArray) throws Exception {
    return getInfoList(c, file, columnArray, 1);
  }

  private static String getColAlphabet(Integer i) throws Exception {
    if (i > 255) {
      throw new IndexOutOfBoundsException();
    }
    int A = 65;
    char leftChar = (char) (i / 26 == 0 ? 0 : ((i - 26) / 26 + A));
    char rightChar = (char) (i % 26 + A);
    return (leftChar == 0 ? "" : leftChar) + "" + rightChar;
  }

  private static void setCellValue(Field field, Object valueObject, Cell cell) throws Exception {
    String type = field.getType() + "";
    if ("int".equals(type)) {
      cell.setCellValue((double) field.getInt(valueObject));
    } else if ("float".equals(type)) {
      cell.setCellValue((double) field.getFloat(valueObject));
    } else if ("double".equals(type)) {
      cell.setCellValue(field.getDouble(valueObject));
    } else if ("class java.lang.String".equals(type)) {
      cell.setCellValue(String.valueOf(field.get(valueObject)));
    } else if ("class java.sql.Timestamp".equals(type)) {
      Timestamp stamp = (Timestamp) field.get(valueObject);
      cell.setCellValue(DateUtils.getDate2String(new java.util.Date(stamp.getTime()), "yyyy-MM-dd",
                                                 java.util.Locale.KOREA));
    } else if ("class java.lang.Integer".equals(type)) {
      Integer val = (Integer) field.get(valueObject);
      cell.setCellValue((double) val.intValue());
    } else if ("short".equals(type)) {
      cell.setCellValue((double) field.getShort(valueObject));
    } else if ("char".equals(type)) {}
  }
}