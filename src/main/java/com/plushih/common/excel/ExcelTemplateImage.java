package com.plushih.common.excel;

import com.plushih.common.utils.ExcelImageUtils;
import com.plushih.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.regex.Matcher;

public class ExcelTemplateImage extends ExcelTemplateBase {

  public final static class RegexConstants {

    // ex) {{image|C://abcd/def/img.jpg|2|4}}
    public static final String IMAGE_PATTERN          = "\\{\\{(image)\\|(.*)\\|([0-9]*)\\|([0-9]*)\\}\\}";
    public static final int    IMAGE_GROUP_OPERATION  = 1;
    public static final int    IMAGE_GROUP_IMAGE_PATH = 2;
    public static final int    IMAGE_GROUP_COLSPAN    = 3;
    public static final int    IMAGE_GROUP_ROWSPAN    = 4;
  }

  @Override
  public void template(Cell cell) {
    String content = cell.getStringCellValue();
    Sheet sheet = cell.getSheet();
    Matcher mMatcher = StringUtils.getMatcher(RegexConstants.IMAGE_PATTERN, content);
    StringBuffer sb = new StringBuffer();
    boolean isMatch = false;
    while (mMatcher.find()) {
      isMatch = true;
      // ex) {{image|C://abcd/def/img.jpg|2|4}}
      String matchText = mMatcher.group();
      String operation = mMatcher.group(RegexConstants.IMAGE_GROUP_OPERATION);
      String imagePath = mMatcher.group(RegexConstants.IMAGE_GROUP_IMAGE_PATH);
      String colspan = mMatcher.group(RegexConstants.IMAGE_GROUP_COLSPAN);
      String rowspan = mMatcher.group(RegexConstants.IMAGE_GROUP_ROWSPAN);

      // operation 추가시 분리필요
      if (operation.equals("image")) {
        if (!StringUtils.isEmptyWithTrim(imagePath)) {
          ExcelImageUtils.addPicture(sheet, imagePath, cell.getRowIndex(), cell.getColumnIndex(),
                                     cell.getRowIndex() + Integer.parseInt(rowspan),
                                     cell.getColumnIndex() + Integer.parseInt(colspan));
        }
      }

      sb.append(content.replace(matchText, ""));
    }
    if (isMatch) {
      cell.setCellValue(sb.toString());
    }
  }
}
