package com.plushih.common.abstractview;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by sangyong on 10/10/14.
 */
public class FileDownloadView extends AbstractView {

	private static final String UTF_8 = "UTF-8";

	protected final void renderMergedOutputModel ( final Map< String, Object > modelMap,
	                                               final HttpServletRequest req,
	                                               final HttpServletResponse res ) throws IOException {
		String fileName = (String) modelMap.get( "orgFileName" );
		final String userAgent = req.getHeader( "User-Agent" );
		File file = (File) modelMap.get( "downloadFile" );
		if ( userAgent.contains( "MSIE" ) ) fileName = URLEncoder.encode( fileName, UTF_8 );
		else if ( userAgent.contains( "Trident" ) ) fileName = URLEncoder.encode( fileName, UTF_8 ).replaceAll("\\+", "%20");
		else if ( userAgent.contains( "Mozilla" ) ) fileName = new String( fileName.getBytes( UTF_8 ), "8859_1" );
		else fileName = new String( fileName.getBytes( Charset.defaultCharset() ), UTF_8 );
		res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setContentType("application/octet-stream");
		res.setContentLength((int) file.length());
		OutputStream out = res.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( file );
			FileCopyUtils.copy( fis, out );
		} catch ( IOException ie ) {
			throw new IOException( "Failed to copy file.", ie );
		} finally {
			if ( fis != null ) {
				try {
					fis.close();
				} catch ( IOException ie ) {
					throw new IOException( "Failed to close file stream.", ie );
				}
			}
		}
		out.flush();
	}
}