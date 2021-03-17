package com.plushih.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelImageUtils {

	public static int addPicture(Sheet sheet, String imageFileName, int rowIndex, int columnIndex, Double ratio) {
		return addPicture(sheet, imageFileName, rowIndex, columnIndex, ratio, Workbook.PICTURE_TYPE_JPEG);
	}
	public static int addPicture(Sheet sheet, String imageFileName, int rowIndex, int columnIndex, Double ratio, int pictureType) {
		return addPicture(sheet, imageFileName, rowIndex, columnIndex, 0, 0, ratio, pictureType);
	}
	public static int addPicture(Sheet sheet, String imageFileName, int row1Index, int col1Index, int row2Index, int col2Index) {
		return addPicture(sheet, imageFileName, row1Index, col1Index, row2Index, col2Index, Workbook.PICTURE_TYPE_JPEG);
	}
	public static int addPicture(Sheet sheet, String imageFileName, int row1Index, int col1Index, int row2Index, int col2Index, int pictureType) {
		return addPicture(sheet, imageFileName, row1Index, col1Index, row2Index, col2Index, null, pictureType);
	}
	private static int addPicture(Sheet sheet, String imageFileName, int row1Index, int col1Index, int row2Index, int col2Index, Double ratio, int pictureType) {
		Workbook workbook = sheet.getWorkbook();
		InputStream is = null;
		int pictureIndex = -1;
		try {
			// 1. Workbook에 이미지 삽입하기
			is = new FileInputStream(new File(imageFileName).getAbsoluteFile());
			pictureIndex = workbook.addPicture(IOUtils.toByteArray(is), pictureType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		// 2. 이미지 위치 상세정보 기술
		CreationHelper helper = workbook.getCreationHelper();
		Drawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = helper.createClientAnchor();
		anchor.setCol1(col1Index);
		anchor.setRow1(row1Index);
		if (ratio == null) {
			anchor.setCol2(col2Index);
			anchor.setRow2(row2Index);	
		}
		anchor.setAnchorType(2);
		
		Picture pict = drawing.createPicture(anchor, pictureIndex);
		if (ratio != null) {
			pict.resize(ratio);
		}
		
		return pictureIndex;
	}
}
