package com.plushih.services.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.FileUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.entities.SiteTempFileEntity;
import com.plushih.entities.common.CommonRuntimeException;
import com.plushih.entities.file.SavedFileEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service("SiteFileUploadService")
public class SiteFileUploadServiceImpl extends CiServiceImpl implements SiteFileUploadService {

//  @Value("#{fileConfig['imagePath']}")
//  private String imagePath;
  @Value("#{fileConfig['filePath']}")
  private String filePath;


  @Override
  public SavedFileEntity uploadImage(MultipartFile uploadImage, long limitSize, String directoryType, String fileBbs, int seq) {
    SavedFileEntity savedFileEntity             = new SavedFileEntity();
    String uploadPath                           = filePath + directoryType;
    // 이미지 체크
    if(!uploadImage.getContentType().contains("image")) {
      throw new CommonRuntimeException("upload.image");
    }

    String extension = "";

    String[] validExt = {"jpg","gif","png"};
    int i = uploadImage.getOriginalFilename().lastIndexOf('.');
    if (i > 0) {
        System.out.println(uploadImage.getOriginalFilename());
        System.out.println(uploadImage.getOriginalFilename().substring(i+1));
        extension = uploadImage.getOriginalFilename().substring(i+1).toLowerCase();
        System.out.println(extension);
      Boolean res = false;
      for (int ix = 0; ix< validExt.length; ix++) {

        if (extension.equals(validExt[ix])) {
          res = true;
        }
      }
        System.out.println(extension+"::::::"+res);
      if(!res){
        throw new CommonRuntimeException("upload.image");
      }


    } else {
      throw new CommonRuntimeException("upload.image");
    }

    // 파일 사이즈 체크
    if(limitSize > 0 && uploadImage.getSize() > limitSize) {
      throw new CommonRuntimeException("upload.image");
    }
    try {
      savedFileEntity = FileUtils.saveFile(uploadPath, uploadImage);

      /** 파일 DB에 저장 */
      this.setAttachFile(savedFileEntity, fileBbs, Default.FileType.IMG, seq);

    } catch (Exception e) {
      throw new CommonRuntimeException("upload.image");
    }

    return savedFileEntity;
  }

  @Override
  public SavedFileEntity uploadFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs, int seq) {
    SavedFileEntity savedFileEntity;
    String uploadPath = filePath + directoryType;

    // 파일 사이즈 체크
    if(limitSize > 0 && uploadFile.getSize() > limitSize) {
      throw new CommonRuntimeException("max size : " + limitSize);
    }
    try {
      savedFileEntity = FileUtils.saveFile(uploadPath, uploadFile);

      //Debug.log(new Gson().toJson(savedFileEntity));

      /** 파일 DB에 저장 */
      this.setAttachFile(savedFileEntity, fileBbs, Default.FileType.FILE, seq);
    } catch (Exception e) {
      throw new CommonRuntimeException("file upload error");
    }

    return savedFileEntity;
  }

   @Override
  public SavedFileEntity uploadFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs, int seq, String fileKey) {
    SavedFileEntity savedFileEntity;
    String uploadPath = filePath + directoryType;

    // 파일 사이즈 체크
    if(limitSize > 0 && uploadFile.getSize() > limitSize) {
      throw new CommonRuntimeException("max size : " + limitSize);
    }
    try {
      savedFileEntity = FileUtils.saveFile(uploadPath, uploadFile);

      //Debug.log(new Gson().toJson(savedFileEntity));

      /** 파일 DB에 저장 */
      this.setAttachFile(savedFileEntity, fileBbs, Default.FileType.FILE, seq, fileKey);
    } catch (Exception e) {
      throw new CommonRuntimeException("file upload error");
    }

    return savedFileEntity;
  }

  @Override
  public boolean deleteFile(String safSeq) throws Exception{

    if(safSeq!=null ){

        plusActiveRecord db = new plusActiveRecord();
        db.select("*");
        db.from("plus_site_attach_file");
        db.where("saf_seq",safSeq);

        SiteAttachFileEntity siteAttachFileEntity = new SiteAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            siteAttachFileEntity = oMapper.convertValue(getRow(db), siteAttachFileEntity.getClass());


            if(siteAttachFileEntity==null){

            } else {
                String filePath = siteAttachFileEntity.getSafPath()+"/"+siteAttachFileEntity.getSafFile();
                System.out.println(filePath);
                FileUtils.deleteFile(filePath);
                setDelete(db,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    return true;
  }

    @Override
  public boolean deleteFile(String bbSeq,String safBbs) throws Exception{

    if(bbSeq!=null ){

        plusActiveRecord db = new plusActiveRecord();
        db.select("*");
        db.from("plus_site_attach_file");
        db.where("seq",bbSeq);
        db.where("saf_bbs",safBbs);
        //SiteAttachFileEntity siteAttachFileEntity = new SiteAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            //List<SiteAttachFileEntity> bbsFiles = oMapper.convertValue(getList(db), new TypeReference<List<SiteAttachFileEntity>>(){});
            CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, SiteAttachFileEntity.class);
            List<SiteAttachFileEntity> bbsFiles = oMapper.convertValue(getList(db), typeReference);

            for(SiteAttachFileEntity  siteAttachFileEntity: bbsFiles){

                if(siteAttachFileEntity==null){

                } else {
                    //String filePath = siteAttachFileEntity.get("safPath")+"/"+siteAttachFileEntity.get("safFile");
                    String filePath = siteAttachFileEntity.getSafPath()+"/"+siteAttachFileEntity.getSafFile();
                    System.out.println(filePath);
                    FileUtils.deleteFile(filePath);
                    setDelete(db,false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    return true;
  }
//  @Override
//  public boolean deleteFile(SiteAttachFileEntity siteAttachFileEntity) throws Exception{
//    if(siteAttachFileEntity.getBbSeq()!=null && siteAttachFileEntity.getSafSeq()>0){
//
//
//      /*
//      List <SiteAttachFileEntity> attachFileEntity = boardAttachFileDAO.getBoardAttachFileList(siteAttachFileEntity);
//
//      if(attachFileEntity.size()>0) {
//        for(SiteAttachFileEntity tempData:attachFileEntity){
//          try{
//            System.out.println(tempData.getSafSeq()+" : 파일SEQ");
//            int result = boardAttachFileDAO.deleteBoardAttachFile(tempData);
//            String filePath = tempData.getSafPath()+"/"+tempData.getSafFile();
//            FileUtils.deleteFile(filePath);
//          } catch(Exception ex ){
//            ex.printStackTrace();;
//          }
//        }
//
//      }
//      */
//
//    }
//
//    return true;
//  }

  @Override
  public void createDirectory(String filePath) {
    File saveFolder = new File( filePath );
    if ( !saveFolder.exists() || saveFolder.isFile() ) {
      saveFolder.mkdirs();
    }
  }

  private boolean setAttachFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType, int seq) {
    SiteAttachFileEntity boardAttachFileEntity = new SiteAttachFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardAttachFileEntity.setSafBbs(fileBbs);
    boardAttachFileEntity.setSafType(fileType);                                //파일타입
    boardAttachFileEntity.setSafOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardAttachFileEntity.setSafFile(savedFileEntity.getSavedFileName());      //저장이름
    boardAttachFileEntity.setSafPath(savedFileEntity.getSavedPath());          //저장경로
    boardAttachFileEntity.setSeq(seq);                                       //해당 테이블 SEQ


    db.from("plus_site_attach_file");
    try {
        db.select("saf_seq");
        db.where("seq",String.valueOf(seq));
        db.where("saf_bbs",fileBbs);

        //파일seq
        Integer fileSeq= getSeq(db);
        db.add("seq",seq+"");
        db.add("saf_bbs",fileBbs);
        db.add("saf_code",fileBbs);
        db.add("saf_or_file",savedFileEntity.getOriginalFileName());
        db.add("saf_file",savedFileEntity.getSavedFileName());
        db.add("saf_path",savedFileEntity.getSavedPath());
        db.add("reg_date", DateUtils.getDate2String(new Date()));
        if(fileSeq==null || fileSeq==0){

            setInsert(db,false);
        } else {
            setUpdate(db,false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    //setInsert()

    //boardAttachFileDAO.insertBoardAttachFile(boardAttachFileEntity);
    //savedFileEntity.setSavedSeq(boardAttachFileEntity.getIsafSeq());


    return true;
  }


  private boolean setAttachFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType, int seq,String fileKey) {
    SiteAttachFileEntity boardAttachFileEntity = new SiteAttachFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardAttachFileEntity.setSafBbs(fileBbs);
    boardAttachFileEntity.setSafCode(fileKey);                                //파일타입
    boardAttachFileEntity.setSafOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardAttachFileEntity.setSafFile(savedFileEntity.getSavedFileName());      //저장이름
    boardAttachFileEntity.setSafPath(savedFileEntity.getSavedPath());          //저장경로
    boardAttachFileEntity.setSeq(seq);                                       //해당 테이블 SEQ


    db.from("plus_site_attach_file");
    try {
        db.select("*");
        db.where("seq",String.valueOf(seq));
        db.where("saf_bbs",fileBbs);
        db.where("saf_code",fileKey);


        //파일seq


        SiteAttachFileEntity siteAttachFileEntity = new SiteAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            siteAttachFileEntity = oMapper.convertValue(getRow(db), boardAttachFileEntity.getClass());



            db.add("seq",seq+"");
            db.add("saf_bbs",fileBbs);
            db.add("saf_code",fileKey);
            db.add("saf_or_file",savedFileEntity.getOriginalFileName());
            db.add("saf_file",savedFileEntity.getSavedFileName());
            db.add("saf_path",savedFileEntity.getSavedPath());
            db.add("reg_date", DateUtils.getDate2String(new Date()));
            if(siteAttachFileEntity==null){
                setInsert(db,false);
            } else {
                String filePath = siteAttachFileEntity.getSafPath()+"/"+siteAttachFileEntity.getSafFile();
                FileUtils.deleteFile(filePath);
                setUpdate(db,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    //setInsert()

    //boardAttachFileDAO.insertBoardAttachFile(boardAttachFileEntity);
    //savedFileEntity.setSavedSeq(boardAttachFileEntity.getIsafSeq());


    return true;
  }






  @Override
  public SiteTempFileEntity uploadTempFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs) {
    SavedFileEntity savedFileEntity;
    String uploadPath = filePath + directoryType;
    SiteTempFileEntity siteTempFileEntity =  new SiteTempFileEntity();
    // 파일 사이즈 체크
    if(limitSize > 0 && uploadFile.getSize() > limitSize) {
      throw new CommonRuntimeException("max size : " + limitSize);
    }
    try {
      savedFileEntity = FileUtils.saveFile(uploadPath, uploadFile);


      /** 파일 DB에 저장 */
      siteTempFileEntity = this.setAttachTempFile(savedFileEntity, fileBbs, Default.FileType.FILE);
      siteTempFileEntity.setSavedFileEntity(savedFileEntity);
    } catch (Exception e) {
      throw new CommonRuntimeException("file upload error");
    }

    return siteTempFileEntity;
  }

  private SiteTempFileEntity setAttachTempFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType) {
    SiteTempFileEntity boardTempFileEntity = new SiteTempFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardTempFileEntity.setStfBbs(fileBbs);
    boardTempFileEntity.setStfCode(fileType);                                //파일타입
    boardTempFileEntity.setStfOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardTempFileEntity.setStfFile(savedFileEntity.getSavedFileName());      //저장이름
    boardTempFileEntity.setStfPath(savedFileEntity.getSavedPath());          //저장경로


    db.from("plus_site_temp_file");
    try {


        db.add("stf_bbs",fileBbs);
        db.add("stf_code",fileBbs);
        db.add("stf_or_file",savedFileEntity.getOriginalFileName());
        db.add("stf_file",savedFileEntity.getSavedFileName());
        db.add("stf_path",savedFileEntity.getSavedPath());
        db.add("reg_date", DateUtils.getDate2String(new Date()));
        setInsert(db,false);
        boardTempFileEntity.setStfSeq(db.insert_id);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return boardTempFileEntity;
  }
  /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param siteAttachFileEntity
     * @return
     */
    @Override
    public List<SiteAttachFileEntity> getSiteAttachFileList(SiteAttachFileEntity siteAttachFileEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("saf.*");
        dbEntity.from("plus_site_attach_file saf");

        if(StringUtils.isEmpty(siteAttachFileEntity.getSafSeq()+"")){
            throw new Exception("파일다운로드 정보가 없습니다.");
        } else {
            dbEntity.where("saf_seq",String.valueOf(siteAttachFileEntity.getSafSeq()));
        }
//        if(StringUtils.isEmpty(siteAttachFileEntity.getSafBbs())){
//            throw new Exception("파일다운로드 정보가 없습니다.");
//        } else {
//            dbEntity.where("saf_bbs",siteAttachFileEntity.getSafBbs());
//        }
//        if(StringUtils.isEmpty(siteAttachFileEntity.getSafType()+"")){
//            throw new Exception("파일다운로드 정보가 없습니다.");
//        } else {
//            dbEntity.where("saf_type",siteAttachFileEntity.getSafType());
//        }


        List<SiteAttachFileEntity> dataList = null;
        try {
            //dataHashList  = getList(dbEntity);
            dataList = convertReal(getList(dbEntity),new SiteAttachFileEntity());
    //
    //            Debug.log(dataList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param bbsAttachFileEntity
     * @return
     */
    @Override
    public Map<String, Object> getSiteAttachFiles(String bbBbs,String bbSeq) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("saf.*");
        dbEntity.where("saf_bbs",bbBbs);
        dbEntity.where("seq",bbSeq);
        dbEntity.from("plus_site_attach_file saf");

        List<Map<String, Object>> getFileList = getList(dbEntity);
        Map<String, Object> fileMap = new HashMap<String, Object>();
        for(Map<String, Object> row:getFileList ){
            fileMap.put(String.valueOf(row.get("safCode")),row);

        }

//        List<BbsAttachFileEntity> dataList = null;
//        try {
//            //dataHashList  = getList(dbEntity);
//            dataList = convertReal(getList(dbEntity),new BbsAttachFileEntity());
//    //
//    //            Debug.log(dataList.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return fileMap;
    }
}
