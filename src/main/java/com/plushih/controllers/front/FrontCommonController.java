package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.entities.BbsAttachFileEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.front.FileUploadService;
import com.plushih.services.front.SiteFileUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/common")
public class FrontCommonController extends CoreController {


  @Autowired
  private FileUploadService fileUploadService;


  @Autowired
  private SiteFileUploadService siteFileUploadService;

  /**
   * 이미지 출력 - '<img src="${contextPath}/common/imgView?filePath=${itemAttachFile.filePath}&fileName=${itemAttachFile.fileName}" />'
   * @param model
   * @param fileName
   * @param filePath
   * @return
   */
  @RequestMapping(value = "/imgView__x")
  public String imageViewx(Model model,
                          @RequestParam(required = true, value = "fileName") String fileName,
                          @RequestParam(required = false, value = "filePath") String filePath) {

    model.addAttribute("fileName", fileName);
    model.addAttribute("filePath", filePath);
    return "imageView";
  }
  /**
   * 이미지 출력 - '<img src="${contextPath}/common/imgView?filePath=${itemAttachFile.filePath}&fileName=${itemAttachFile.fileName}" />'
   * @param bbsAttachFileEntity
   * @param bbsAttachFileEntity
   * @return
   */
  @RequestMapping(value = "/imgView")
  public String imageView(BbsAttachFileEntity bbsAttachFileEntity, Model model, HttpServletRequest request) throws Exception {

      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafSeq()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }
      String referer = (String)request.getHeader("REFERER");
      if(referer==null){
        throw new Exception("파일 정보가 없습니다.");
      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafType()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
    List<BbsAttachFileEntity> bbsAttachFileEntityList= fileUploadService.getBbsAttachFileList(bbsAttachFileEntity);
    if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
      throw new Exception("이미지 정보가 없습니다.");
    }

    System.out.println(bbsAttachFileEntityList.get(0).getBafPath()+"/"+bbsAttachFileEntityList.get(0).getBafFile());
    model.addAttribute("filePath", bbsAttachFileEntityList.get(0).getBafPath()+"/"+bbsAttachFileEntityList.get(0).getBafFile());
    model.addAttribute("fileName", bbsAttachFileEntityList.get(0).getBafOrFile());

    return "imageView";
  }
   /**
   * 이미지 출력 - '<img src="${contextPath}/common/imgView?filePath=${itemAttachFile.filePath}&fileName=${itemAttachFile.fileName}" />'
   * @param bbsAttachFileEntity
   * @param bbsAttachFileEntity
   * @return
   */
  @RequestMapping(value = "/playerView")
  public String playerView(BbsAttachFileEntity bbsAttachFileEntity, Model model, HttpServletRequest request ) throws Exception {

      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafSeq()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }

      String referer = (String)request.getHeader("REFERER");
      if(referer==null){
        throw new Exception("파일 정보가 없습니다.");
      }

//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafType()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
    List<BbsAttachFileEntity> bbsAttachFileEntityList= fileUploadService.getBbsAttachFileList(bbsAttachFileEntity);
    if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
      throw new Exception("이미지 정보가 없습니다.");
    }

    System.out.println(bbsAttachFileEntityList.get(0).getBafPath()+"/"+bbsAttachFileEntityList.get(0).getBafFile());
    model.addAttribute("filePath", bbsAttachFileEntityList.get(0).getBafPath()+"/"+bbsAttachFileEntityList.get(0).getBafFile());
    model.addAttribute("fileName", bbsAttachFileEntityList.get(0).getBafOrFile());

    return "playerView";
  }

  /**
   * 이미지 출력 - '<img src="${contextPath}/common/imgView?filePath=${itemAttachFile.filePath}&fileName=${itemAttachFile.fileName}" />'
   * @param siteAttachFileEntity
   * @param siteAttachFileEntity
   * @return
   */
  @RequestMapping(value = "/siteImgView")
  public String siteImgView(SiteAttachFileEntity siteAttachFileEntity, Model model, HttpServletRequest request) throws Exception {

      if(StringUtils.isEmpty(siteAttachFileEntity.getSafSeq()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }
      String referer = (String)request.getHeader("REFERER");
      if(referer==null){
        throw new Exception("파일 정보가 없습니다.");
      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafType()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
    List<SiteAttachFileEntity> bbsAttachFileEntityList= siteFileUploadService.getSiteAttachFileList(siteAttachFileEntity);
    if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
      throw new Exception("이미지 정보가 없습니다.");
    }

    System.out.println(bbsAttachFileEntityList.get(0).getSafPath()+"/"+bbsAttachFileEntityList.get(0).getSafFile());
    model.addAttribute("filePath", bbsAttachFileEntityList.get(0).getSafPath()+"/"+bbsAttachFileEntityList.get(0).getSafFile());
    model.addAttribute("fileName", bbsAttachFileEntityList.get(0).getSafOrFile());

    return "imageView";
  }

  /**
   * 이미지 출력 - '<img src="${contextPath}/common/imgView?filePath=${itemAttachFile.filePath}&fileName=${itemAttachFile.fileName}" />'
   * @param siteAttachFileEntity
   * @param siteAttachFileEntity
   * @return
   */
  @RequestMapping(value = "/sitePlayerView")
  public String sitePlayerView(SiteAttachFileEntity siteAttachFileEntity, Model model, HttpServletRequest request) throws Exception {

      if(StringUtils.isEmpty(siteAttachFileEntity.getSafSeq()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }
      String referer = (String)request.getHeader("REFERER");
      if(referer==null){
        throw new Exception("파일 정보가 없습니다.");
      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafType()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
    List<SiteAttachFileEntity> bbsAttachFileEntityList= siteFileUploadService.getSiteAttachFileList(siteAttachFileEntity);
    if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
      throw new Exception("이미지 정보가 없습니다.");
    }

    System.out.println(bbsAttachFileEntityList.get(0).getSafPath()+"/"+bbsAttachFileEntityList.get(0).getSafFile());
    model.addAttribute("filePath", bbsAttachFileEntityList.get(0).getSafPath()+"/"+bbsAttachFileEntityList.get(0).getSafFile());
    model.addAttribute("fileName", bbsAttachFileEntityList.get(0).getSafOrFile());

    return "playerView";
  }

  /**
   * 에디터 이미지 팝업
   *
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/editorUploadPopup", method = RequestMethod.GET)
  public String getEditorUploadForm() throws Exception {
    return "smartEditor/popup/photo_uploader"; //TODO: 관리자 사용자 구분 필요한지 물어볼 것
  }

  @RequestMapping(value = "/fileDown", method = RequestMethod.GET)
  public String fileDown(BbsAttachFileEntity bbsAttachFileEntity, @RequestParam(required = false, value = "bafSeq", defaultValue = "") String bafSeq, Model model)
          throws Exception {

//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafSeq()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafCode()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }

      List<BbsAttachFileEntity> bbsAttachFileEntityList= fileUploadService.getBbsAttachFileList(bbsAttachFileEntity);
        if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
          throw new Exception("파일다운로드 정보가 없습니다.");
        }

    model.addAttribute("downloadFile", new File(bbsAttachFileEntityList.get(0).getBafPath()+"/"+bbsAttachFileEntityList.get(0).getBafFile()));
    model.addAttribute("orgFileName", bbsAttachFileEntityList.get(0).getBafOrFile());
    return "downloadView";
  }

  @RequestMapping(value = "/siteFileDown", method = RequestMethod.GET)
  public String siteFileDown(SiteAttachFileEntity siteAttachFileEntity, @RequestParam(required = false, value = "safSeq", defaultValue = "") String bafSeq, Model model)
          throws Exception {

//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafSeq()+"")){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
//      if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//        throw new Exception("파일다운로드 정보가 없습니다.");
//      }
      if(StringUtils.isEmpty(siteAttachFileEntity.getSafCode()+"")){
        throw new Exception("파일다운로드 정보가 없습니다.");
      }

      List<SiteAttachFileEntity> bbsAttachFileEntityList= siteFileUploadService.getSiteAttachFileList(siteAttachFileEntity);
        if(bbsAttachFileEntityList==null || bbsAttachFileEntityList.size()==0 ) {
          throw new Exception("파일다운로드 정보가 없습니다.");
        }

    model.addAttribute("downloadFile", new File(bbsAttachFileEntityList.get(0).getSafPath()+"/"+bbsAttachFileEntityList.get(0).getSafFile()));
    model.addAttribute("orgFileName", bbsAttachFileEntityList.get(0).getSafOrFile());
    return "downloadView";
  }
  /**
   * 파일다운로드 - '<a href="${contextPath}/common/fileDown?path=${attachFile.filePath}&orgFileName=${fn:replace(attachFile.fileName, ',', '.')}"> ${attachFile.fileName}</a>'
   * @param path
   * @param orgFileName
   * @param model
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/fileDown__x", method = RequestMethod.GET)
  public String fileDownx(String path, String orgFileName, Model model)
    throws IOException {
    model.addAttribute("downloadFile", new File(path));
    model.addAttribute("orgFileName", orgFileName);
    return "downloadView";
  }


  /**
   * 빈페이지 호출
   * @param model
   * @return
   */
  @RequestMapping(value = "/emptyTemp", method = RequestMethod.POST)
  public String emptyTemp(@RequestParam(required = false, value = "page", defaultValue = "") String page, Model model) {
    return page;
  }


}
