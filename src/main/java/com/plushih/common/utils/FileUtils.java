package com.plushih.common.utils;

import com.plushih.entities.file.FilePathInfoEntity;
import com.plushih.entities.file.SavedFileEntity;
import org.springframework.web.multipart.MultipartFile;
//import SCSL.*;
import java.io.*;

/**
 * Created by sangyong on 10/10/14.
 */
public class FileUtils {

  public static FilePathInfoEntity getFilePathInfo ( final File file ) {

    String filePath = file.getPath();
    String fileName = getFilename( filePath );

    String fileExtension = getFileExtension( filePath );
    String fileNameExcludeExtension = fileName.substring( 0, fileName.length() - fileExtension.length() - 1 );

    FilePathInfoEntity mFilePathInfoEntity = new FilePathInfoEntity();
    mFilePathInfoEntity.setFileName( fileName );
    mFilePathInfoEntity.setFileExtension( fileExtension );
    mFilePathInfoEntity.setFileNameExcludeExtension( fileNameExcludeExtension );

    return mFilePathInfoEntity;
  }

  public static String getFilename ( final String filePath ) {

    return getFilename( new File( filePath ) );
  }

  public static String getFilename ( final File file ) {

    String filePath = file.getAbsolutePath();
    int pathIdx = filePath.lastIndexOf( file.separator );
    return filePath.substring( pathIdx + 1, filePath.length() );
  }

  public static String getFileExtension ( final String filePath ) {

    int pathIdx = filePath.lastIndexOf( "." );
    return filePath.substring( pathIdx + 1, filePath.length() );
  }

  public static String getFileExtension ( final File file ) {

    if ( file.isDirectory() )
      return "";

    String filePath = file.getPath();
    int pathIdx = filePath.lastIndexOf( "." );
    return filePath.substring( pathIdx + 1, filePath.length() );
  }

  public static Object readObject ( final String fileName ) throws Exception {

    return readObject( fileName, Object.class );
  }

  public static < T > T readObject ( final String fileName, final Class< T > clazz ) throws Exception {

    return readObject( new File( fileName ), clazz );
  }

  public static < T > T readObject ( final File file, final Class< T > clazz ) throws Exception {

    ObjectInputStream oisData = new ObjectInputStream( new FileInputStream( file ) );
    Object resultObj = oisData.readObject();
    oisData.close();

    return (T) resultObj;
  }

  public static Object writeObject ( final Object o, final String fileName ) throws Exception {

    return writeObject( o, new File( fileName ) );
  }

  public static Object writeObject ( final Object o, final File file ) throws Exception {

    ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( file ) );
    oos.writeObject( o );
    Object resultObj = o;
    oos.close();

    return resultObj;
  }

  public static void writeString ( final String contentString, final String filePath ) {

  }

  public static void writeString ( final String contentString, final File file ) {

  }

  public static SavedFileEntity saveFile ( final String savePath, final MultipartFile sourcefile ) throws Exception {

    if ( sourcefile == null || sourcefile.isEmpty() ) {
      return null;
    }

    String uploadFileName = sourcefile.getOriginalFilename();

    File saveFolder = new File( savePath );
    if ( !saveFolder.exists() || saveFolder.isFile() ) {
      saveFolder.mkdirs();
    }

    File targetFile = getTimebaseFileName( new File( savePath + "/" + uploadFileName ) );
    sourcefile.transferTo( targetFile );

    SavedFileEntity savedFileEntity = new SavedFileEntity();
    savedFileEntity.setOriginalFileName( uploadFileName );
    savedFileEntity.setSavedFileName( targetFile.getName() );
    savedFileEntity.setSavedFileExtension( getFileExtension( targetFile ).toLowerCase() );
    savedFileEntity.setSavedFileSize( targetFile.length() );
    savedFileEntity.setSavedPath( savePath );

    return savedFileEntity;
  }

  private static File getTimebaseFileName ( final File file ) {

    return new File( file.getParent(), System.currentTimeMillis() + StringUtils.getRandomString(3) + "." + getFileExtension( file ) );
  }

  public static void deleteFile ( final String filePath ) throws FileNotFoundException {

    File file = new File( filePath );

    if ( file.exists() ) {
      file.delete();
    } else {
      //throw new FileNotFoundException( "delete file not found : " + file.getAbsolutePath() );
    }
  }

  /**
   * 파일 암호화
   */
  public static int fileEnc(String srcFile, String dstFile) {
//    String srcFile,dstFile;

//    srcFile="/appl/PREMSServer/softcamp/03_Sample/test.xls";
//    dstFile="/appl/PREMSServer/softcamp/03_Sample/test_Enc.xls";

//    SLDsFile sFile = new SLDsFile();
//
//    sFile.SettingPathForProperty("/appl/PREMSServer/softcamp/02_Module/02_ServiceLinker/softcamp.properties");
//
//    sFile.SLDsInitDAC();
//    sFile.SLDsAddUserDAC("PREMS", "111001100", 0, 0, 0);
    int ret=1;
//    ret = sFile.SLDsEncFileDACV2("/appl/PREMSServer/softcamp/04_KeyFile/keyDAC_SVR0.sc", "system", srcFile, dstFile, 1);
//    System.out.println("SLDsEncFileDAC :" + ret);
    return ret;

  }

  /**
   * 파일 복호화
   */
  public static void fileDec(String srcFile, String dstFile) {
  //  String srcFile,dstFile;

//    srcFile="/appl/PREMSServer/softcamp/03_Sample/test_Enc.xls";
//    dstFile="/appl/PREMSServer/softcamp/03_Sample/test_Dec.xls";

//    SLDsFile sFile = new SLDsFile();
//
//    sFile.SettingPathForProperty("/appl/PREMSServer/softcamp/02_Module/02_ServiceLinker/softcamp.properties");
//
//    int retVal = sFile.CreateDecryptFileDAC (
//            "/appl/PREMSServer/softcamp/04_KeyFile/keyDAC_SVR0.sc",
//            "PREMS",
//            srcFile,
//            dstFile);
//    System.out.println( " CreateDecryptFileDAC [" + retVal + "]");
  }

}
