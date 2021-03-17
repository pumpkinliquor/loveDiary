package com.plushih.services.front;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.FileUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.BbsAttachFileEntity;
import com.plushih.entities.BbsTempFileEntity;
import com.plushih.entities.common.CommonRuntimeException;
import com.plushih.entities.file.SavedFileEntity;
import com.plushih.services.ci.CiServiceImpl;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service("FileUploadService")
public class FileUploadServiceImpl  extends CiServiceImpl implements FileUploadService {

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
  public boolean deleteFile(String bafSeq) throws Exception{

    if(bafSeq!=null ){

        plusActiveRecord db = new plusActiveRecord();
        db.select("*");
        db.from("plus_bbs_attach_file");
        db.where("baf_seq",bafSeq);

        BbsAttachFileEntity bbsAttachFileEntity = new BbsAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            bbsAttachFileEntity = oMapper.convertValue(getRow(db), bbsAttachFileEntity.getClass());


            if(bbsAttachFileEntity==null){

            } else {
                String filePath = bbsAttachFileEntity.getBafPath()+"/"+bbsAttachFileEntity.getBafFile();
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
  public boolean deleteFile(String bbSeq,String bafBbs) throws Exception{

    if(bbSeq!=null ){

        plusActiveRecord db = new plusActiveRecord();
        db.select("*");
        db.from("plus_bbs_attach_file");
        db.where("bb_seq",bbSeq);
        db.where("baf_bbs",bafBbs);
        //BbsAttachFileEntity bbsAttachFileEntity = new BbsAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            //List<BbsAttachFileEntity> bbsFiles = oMapper.convertValue(getList(db), new TypeReference<List<BbsAttachFileEntity>>(){});
            CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BbsAttachFileEntity.class);
            List<BbsAttachFileEntity> bbsFiles = oMapper.convertValue(getList(db), typeReference);

            for(BbsAttachFileEntity  bbsAttachFileEntity: bbsFiles){

                if(bbsAttachFileEntity==null){

                } else {
                    //String filePath = bbsAttachFileEntity.get("bafPath")+"/"+bbsAttachFileEntity.get("bafFile");
                    String filePath = bbsAttachFileEntity.getBafPath()+"/"+bbsAttachFileEntity.getBafFile();
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
//  public boolean deleteFile(BbsAttachFileEntity bbsAttachFileEntity) throws Exception{
//    if(bbsAttachFileEntity.getBbSeq()!=null && bbsAttachFileEntity.getBafSeq()>0){
//
//
//      /*
//      List <BbsAttachFileEntity> attachFileEntity = boardAttachFileDAO.getBoardAttachFileList(bbsAttachFileEntity);
//
//      if(attachFileEntity.size()>0) {
//        for(BbsAttachFileEntity tempData:attachFileEntity){
//          try{
//            System.out.println(tempData.getBafSeq()+" : 파일SEQ");
//            int result = boardAttachFileDAO.deleteBoardAttachFile(tempData);
//            String filePath = tempData.getBafPath()+"/"+tempData.getBafFile();
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
    BbsAttachFileEntity boardAttachFileEntity = new BbsAttachFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardAttachFileEntity.setBafBbs(fileBbs);
    boardAttachFileEntity.setBafType(fileType);                                //파일타입
    boardAttachFileEntity.setBafOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardAttachFileEntity.setBafFile(savedFileEntity.getSavedFileName());      //저장이름
    boardAttachFileEntity.setBafPath(savedFileEntity.getSavedPath());          //저장경로
    boardAttachFileEntity.setBbSeq(seq);                                       //해당 테이블 SEQ


    db.from("plus_bbs_attach_file");
    try {
        db.select("baf_seq");
        db.where("bb_seq",String.valueOf(seq));
        db.where("baf_bbs",fileBbs);

        //파일seq
        Integer fileSeq= getSeq(db);
        db.add("bb_seq",seq+"");
        db.add("baf_bbs",fileBbs);
        db.add("baf_code",fileBbs);
        db.add("baf_or_file",savedFileEntity.getOriginalFileName());
        db.add("baf_file",savedFileEntity.getSavedFileName());
        db.add("baf_path",savedFileEntity.getSavedPath());
        db.add("reg_date", DateUtils.getDate2String(new Date()));
        if(fileSeq==null){

            setInsert(db,false);
        } else {
            setUpdate(db,false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    //setInsert()

    //boardAttachFileDAO.insertBoardAttachFile(boardAttachFileEntity);
    //savedFileEntity.setSavedSeq(boardAttachFileEntity.getIbafSeq());


    return true;
  }


  private boolean setAttachFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType, int seq,String fileKey) {
    BbsAttachFileEntity boardAttachFileEntity = new BbsAttachFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardAttachFileEntity.setBafBbs(fileBbs);
    boardAttachFileEntity.setBafCode(fileKey);                                //파일타입
    boardAttachFileEntity.setBafOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardAttachFileEntity.setBafFile(savedFileEntity.getSavedFileName());      //저장이름
    boardAttachFileEntity.setBafPath(savedFileEntity.getSavedPath());          //저장경로
    boardAttachFileEntity.setBbSeq(seq);                                       //해당 테이블 SEQ


    db.from("plus_bbs_attach_file");
    try {
        db.select("*");
        db.where("bb_seq",String.valueOf(seq));
        db.where("baf_bbs",fileBbs);
        db.where("baf_code",fileKey);


        //파일seq


        BbsAttachFileEntity bbsAttachFileEntity = new BbsAttachFileEntity();
        try {
            ObjectMapper oMapper = new ObjectMapper();
            bbsAttachFileEntity = oMapper.convertValue(getRow(db), boardAttachFileEntity.getClass());



            db.add("bb_seq",seq+"");
            db.add("baf_bbs",fileBbs);
            db.add("baf_code",fileKey);
            db.add("baf_or_file",savedFileEntity.getOriginalFileName());
            db.add("baf_file",savedFileEntity.getSavedFileName());
            db.add("baf_path",savedFileEntity.getSavedPath());
            db.add("reg_date", DateUtils.getDate2String(new Date()));
            if(bbsAttachFileEntity==null){
                setInsert(db,false);
            } else {
                String filePath = bbsAttachFileEntity.getBafPath()+"/"+bbsAttachFileEntity.getBafFile();
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
    //savedFileEntity.setSavedSeq(boardAttachFileEntity.getIbafSeq());


    return true;
  }






  @Override
  public BbsTempFileEntity uploadTempFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs) {
    SavedFileEntity savedFileEntity;
    String uploadPath = filePath + directoryType;
    BbsTempFileEntity bbsTempFileEntity =  new BbsTempFileEntity();
    // 파일 사이즈 체크
    if(limitSize > 0 && uploadFile.getSize() > limitSize) {
      throw new CommonRuntimeException("max size : " + limitSize);
    }
    try {
      savedFileEntity = FileUtils.saveFile(uploadPath, uploadFile);


      /** 파일 DB에 저장 */
      bbsTempFileEntity = this.setAttachTempFile(savedFileEntity, fileBbs, Default.FileType.FILE);
      bbsTempFileEntity.setSavedFileEntity(savedFileEntity);
    } catch (Exception e) {
      throw new CommonRuntimeException("file upload error");
    }

    return bbsTempFileEntity;
  }

  private BbsTempFileEntity setAttachTempFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType) {
    BbsTempFileEntity boardTempFileEntity = new BbsTempFileEntity();

    plusActiveRecord db = new plusActiveRecord();

    boardTempFileEntity.setBtfBbs(fileBbs);
    boardTempFileEntity.setBtfCode(fileType);                                //파일타입
    boardTempFileEntity.setBtfOrFile(savedFileEntity.getOriginalFileName()); //원본이름
    boardTempFileEntity.setBtfFile(savedFileEntity.getSavedFileName());      //저장이름
    boardTempFileEntity.setBtfPath(savedFileEntity.getSavedPath());          //저장경로


    db.from("plus_bbs_temp_file");
    try {


        db.add("btf_bbs",fileBbs);
        db.add("btf_code",fileBbs);
        db.add("btf_or_file",savedFileEntity.getOriginalFileName());
        db.add("btf_file",savedFileEntity.getSavedFileName());
        db.add("btf_path",savedFileEntity.getSavedPath());
        db.add("reg_date", DateUtils.getDate2String(new Date()));
        setInsert(db,false);
        boardTempFileEntity.setBtfSeq(db.insert_id);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return boardTempFileEntity;
  }
  /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param bbsAttachFileEntity
     * @return
     */
    @Override
    public List<BbsAttachFileEntity> getBbsAttachFileList(BbsAttachFileEntity bbsAttachFileEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("baf.*");
        dbEntity.from("plus_bbs_attach_file baf");

        if(StringUtils.isEmpty(bbsAttachFileEntity.getBafSeq()+"")){
            throw new Exception("파일다운로드 정보가 없습니다.");
        } else {
            dbEntity.where("baf_seq",String.valueOf(bbsAttachFileEntity.getBafSeq()));
        }
//        if(StringUtils.isEmpty(bbsAttachFileEntity.getBafBbs())){
//            throw new Exception("파일다운로드 정보가 없습니다.");
//        } else {
//            dbEntity.where("baf_bbs",bbsAttachFileEntity.getBafBbs());
//        }
//        if(StringUtils.isEmpty(bbsAttachFileEntity.getBafType()+"")){
//            throw new Exception("파일다운로드 정보가 없습니다.");
//        } else {
//            dbEntity.where("baf_type",bbsAttachFileEntity.getBafType());
//        }


        List<BbsAttachFileEntity> dataList = null;
        try {
            //dataHashList  = getList(dbEntity);
            dataList = convertReal(getList(dbEntity),new BbsAttachFileEntity());
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
    public Map<String, Object> getBbsAttachFiles(String bbBbs,String bbSeq) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("baf.*");
        dbEntity.where("baf_bbs",bbBbs);
        dbEntity.where("bb_seq",bbSeq);
        dbEntity.from("plus_bbs_attach_file baf");

        List<Map<String, Object>> getFileList = getList(dbEntity);
        Map<String, Object> fileMap = new HashMap<String, Object>();
        for(Map<String, Object> row:getFileList ){
            fileMap.put(String.valueOf(row.get("bafCode")),row);

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
