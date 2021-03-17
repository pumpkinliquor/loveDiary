package com.plushih.common.utils;

import com.plushih.common.constant.Default;
import com.plushih.entities.common.CommonRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nahyeon on 15. 2. 21.
 */
public class ThumbnailUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThumbnailUtils.class);
  public static void saveThumbnail ( String uploadPath, String fileName, int thumbnailSize ) throws IOException {

    if ( StringUtils.isEmpty( fileName )) {
      LOGGER.info("failed to save image");
      throw new CommonRuntimeException("no image file", "noImage");
    }

    String savePath = uploadPath + Default.UploadDirectory.THUMBNAIL + "/";
    String saveFile = savePath + fileName;
    String loadFile = uploadPath + "/" + fileName;
    int maxDim = thumbnailSize;

    File saveFolder = new File( savePath );
    boolean isNotExistPath = !saveFolder.exists() || saveFolder.isFile();
    if (isNotExistPath && saveFolder.mkdirs()) {
      LOGGER.info("make directory for image");
    }

    File save = new File(saveFile.replaceAll("/", "\\" + File.separator));
    FileInputStream fis = new FileInputStream(loadFile.replaceAll("/", "\\" + File.separator));
    BufferedImage im = ImageIO.read( fis );
    Image inImage = new ImageIcon(loadFile).getImage();
    double scale;
    if (inImage.getWidth(null) > inImage.getHeight(null)) {
      scale = (double) maxDim / (double) inImage.getWidth(null);
    } else {
      scale = (double) maxDim / (double) inImage.getHeight(null);
    }

    int scaledW = (int) (scale * inImage.getWidth(null));
    int scaledH = (int) (scale * inImage.getHeight(null));
    BufferedImage thumb = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = thumb.createGraphics();
    g2.drawImage(im, 0, 0, scaledW, scaledH, null);
    ImageIO.write(thumb, "jpg", save);
  }
}
