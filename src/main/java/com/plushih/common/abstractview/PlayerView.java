package com.plushih.common.abstractview;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by sangyong on 10/10/14.
 */
public class PlayerView extends AbstractView {

  /**
   * model에 filePath와 fileName을 추가하여 전달
   * filePath : 실제 파일 경로
   * fileName : 보여질 파일 명
   *
   * @param model
   * @param request
   * @param response
   * @throws Exception
   */
  @Override
  protected void renderMergedOutputModel(Map<String, Object> model,
                                         HttpServletRequest request, HttpServletResponse response)
    throws Exception {

    //실제 파일경로
    File file = new File((String) model.get("filePath"));
    //보여 질 파일명
    String fileName = (String) model.get("fileName");

    response.setContentType("application/octet-stream");
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
    response.setHeader("Content-type", "video/mp4");
    response.setHeader("Content-Transfer-Encoding", "binary");

    OutputStream out = response.getOutputStream();
    FileInputStream fis = null;

    try {
      fis = new FileInputStream(file);
      FileCopyUtils.copy(fis, out);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (Exception e) {
          throw new IOException("ImageView IO error", e);
        }




      }
    }
    out.flush();
  }
}
