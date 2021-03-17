package com.plushih.common.excel;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTransformer {

  public final static String SHEET_NAME = "sheetName";

  private XLSTransformer    transformer        = new XLSTransformer();
  private ExcelTemplateBase excelTemplateImage = new ExcelTemplateImage();
  private ExcelTemplateBase excelTemplateMerge = new ExcelTemplateMerge();

  public void transform(Map<String, Object> paramMap, String sourcePath, String targetPath) {
    List<Map<String, Object>> paramMapList = new ArrayList<Map<String, Object>>();
    paramMapList.add(paramMap);
    transform(paramMapList, sourcePath, targetPath);
  }

  public void transform(List<Map<String, Object>> paramMapList, String sourcePath, String targetPath) {
    InputStream is = null;
    OutputStream os = null;
    try {
      // 1-1. JXLS 형식의 엑셀 문서를 읽어와서 POI객체인 workbook으로 만들어 줌
      is = new FileInputStream(new File(sourcePath).getAbsoluteFile());
      //Workbook workbook = transformer.transformXLS(is, paramMap);
      List<String> sheetNames = new ArrayList<String>();
      for (int i = 0; i < paramMapList.size(); i++) {
        Map<String, Object> paramMap = paramMapList.get(i);
        sheetNames.add((String) paramMap.get(SHEET_NAME));
      }
      Workbook workbook =
        transformer.transformMultipleSheetsList(is, paramMapList, sheetNames, "map", new HashMap<String, Object>(), 0);

      // 1-2. 전용 템플릿을 사용하여 image 처리
      excelTemplateImage.template(workbook);

      // 1-3. 전용 템플릿을 사용하여 merge 처리
      excelTemplateMerge.template(workbook);

      // 1-4. 변경된 내용을 해당 파일에 다시 저장
      File excelOutputFile = new File(targetPath).getAbsoluteFile();
      os = new FileOutputStream(excelOutputFile);
      workbook.write(os);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (is != null) {
          is.close();
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      try {
        if (os != null) {
          os.close();
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
