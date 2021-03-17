package com.plushih.common.excel;

import com.plushih.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.regex.Matcher;

public class ExcelTemplateMerge extends ExcelTemplateBase {

  public final static class RegexConstants {

    // ex) "Development{{merge|2|2}}"
    public static final String MERGE_PATTERN         = "\\{\\{(merge)\\|([-0-9]*)\\|([-0-9]*)\\}\\}";
    public static final int    MERGE_GROUP_OPERATION = 1;
    public static final int    MERGE_GROUP_COLSPAN   = 2;
    public static final int    MERGE_GROUP_ROWSPAN   = 3;
  }

  @Override
  public void template(Cell cell) {
    String content = cell.getStringCellValue();
    Sheet sheet = cell.getSheet();

    Matcher m = StringUtils.getMatcher(RegexConstants.MERGE_PATTERN, content);
    StringBuffer sb = new StringBuffer();
    boolean isMatch = false;
    while (m.find()) {
      isMatch = true;
      String matchText = m.group(); // ex) {{merge|2|2}}
      String operation = m.group(RegexConstants.MERGE_GROUP_OPERATION);
      int colspan = Integer.parseInt(m.group(RegexConstants.MERGE_GROUP_COLSPAN));
      int rowspan = Integer.parseInt(m.group(RegexConstants.MERGE_GROUP_ROWSPAN));

      // operation 추가시 분리필요
      if (operation.equals("merge")) {
        int rowIdx = cell.getRowIndex();  //시작 행번호
        int rowIdxDx = cell.getRowIndex() - 1 + rowspan; //마지막 행번호
        int columnIdx = cell.getColumnIndex(); //시작 열번호
        int columnIdxDx = cell.getColumnIndex() - 1 + colspan; //마지막 열번호
        if (rowspan < 0) {
          rowIdxDx = rowIdx;
          rowIdx = cell.getRowIndex() + rowspan + 1;
        }
        if (colspan < 0) {
          columnIdxDx = columnIdx;
          columnIdx = cell.getColumnIndex() + colspan + 1;
        }
        sheet.addMergedRegion(new CellRangeAddress(
          rowIdx,
          rowIdxDx,
          columnIdx,
          columnIdxDx
        ));
      }

      sb.append(content.replace(matchText, ""));
    }
    if (isMatch) {
      cell.setCellValue(sb.toString());
    }
  }
}
