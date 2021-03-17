package com.plushih.common.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by sangyong on 10/10/14.
 */
public class ZipUtils {

  // 압축 레벨 0-9
  private static final int COMPRESSION_LEVEL = 8;
  private static final int BUFFER_SIZE       = 1024 * 2;

  public static void zip ( final String targetPath,
                           final String zipOutputPath ) throws FileNotFoundException, IOException {

    // 압축 대상(sourcePath)이 디렉토리나 파일이 아니면 리턴한다.
    File sourceFile = new File( targetPath );
    if ( !sourceFile.isFile() && !sourceFile.isDirectory() ) {
      throw new FileNotFoundException( "압축 대상의 파일을 찾을 수가 없습니다." );
    }

    File zipOutputParent = new File( zipOutputPath ).getParentFile();
    if ( !zipOutputParent.exists() )
      zipOutputParent.mkdirs();

    FileOutputStream sourceFileOutputStream = null;
    BufferedOutputStream sourceFileBufferdOutputStream = null;
    ZipOutputStream mZipOutputStream = null;

    try {

      sourceFileOutputStream = new FileOutputStream( zipOutputPath );
      sourceFileBufferdOutputStream = new BufferedOutputStream( sourceFileOutputStream );
      mZipOutputStream = new ZipOutputStream( sourceFileBufferdOutputStream );
      mZipOutputStream.setLevel( COMPRESSION_LEVEL );

      zipAddFile( "", targetPath, mZipOutputStream );

      mZipOutputStream.finish();

    } catch ( IOException e ) {
      throw new IOException( e );
    } finally {
      if ( mZipOutputStream != null ) {
        mZipOutputStream.close();
      }
      if ( sourceFileBufferdOutputStream != null ) {
        sourceFileBufferdOutputStream.close();
      }
      if ( sourceFileOutputStream != null ) {
        sourceFileOutputStream.close();
      }
    }
  }

  private static void zipAddFile ( final String filePath, final String sourceFilePath,
                                   final ZipOutputStream mZipOutputStream ) throws IOException {

    File sourceFile = new File( sourceFilePath );

    File[] fileArray;
    if ( sourceFile.isFile() ) {
      File[] singleFileArray = { sourceFile };
      fileArray = singleFileArray;
    } else {
      fileArray = sourceFile.listFiles();
    }

    // 파일 압축
    for ( int i = 0; i < fileArray.length; i++ ) {
      if ( fileArray[i].isDirectory() ) {
        String subPath = filePath + "/" + fileArray[i].getName();
        mZipOutputStream.putNextEntry( new ZipEntry( subPath + "/" ) );
        zipAddFile( subPath, fileArray[i].getPath(), mZipOutputStream );
      } else {
        BufferedInputStream bis = new BufferedInputStream( new FileInputStream( fileArray[i].getPath() ) );

        mZipOutputStream
          .putNextEntry( new ZipEntry( filePath + "/" + FileUtils.getFilename( fileArray[i].getPath() ) ) );

        byte[] buffer = new byte[BUFFER_SIZE];
        int cnt = 0;
        while ( ( cnt = bis.read( buffer, 0, BUFFER_SIZE ) ) != -1 ) {
          mZipOutputStream.write( buffer, 0, cnt );
        }
        mZipOutputStream.closeEntry();
        bis.close();
      }
    }
  }
}
