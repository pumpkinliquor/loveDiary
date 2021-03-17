package com.plushih.services.front;

import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.entities.BbsTempFileEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.entities.SiteTempFileEntity;
import com.plushih.entities.file.SavedFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SiteFileUploadService {
  SavedFileEntity uploadImage(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs, int seq);
  SavedFileEntity uploadFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs, int seq);
  SavedFileEntity uploadFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs, int seq,String fileKey);
  //boolean deleteFile(SiteAttachFileEntity bbsAttachFileEntity) throws Exception;
  boolean deleteFile(String bafSeq) throws Exception;
  boolean deleteFile(String bbSeq,String bafBbs) throws Exception;
  void createDirectory(String filePath);
  //boolean setAttachFile(SavedFileEntity savedFileEntity, String fileBbs, String fileType, int seq);

  List<SiteAttachFileEntity> getSiteAttachFileList(SiteAttachFileEntity bbsAttachFileEntity) throws Exception;
  Map<String, Object> getSiteAttachFiles(String bbBbs, String bbSeq) throws Exception;


  SiteTempFileEntity uploadTempFile(MultipartFile uploadFile, long limitSize, String directoryType, String fileBbs);
}
