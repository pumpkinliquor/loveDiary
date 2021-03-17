<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
String fileInfo = "";
    String fileName = request.getHeader("file-name");
    String fileName_suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    String[] suffixArr = { "jpg", "png", "bmp", "gif" };

    int cnt = 0;
    for (int i = 0; i < suffixArr.length; i++) {
        if (fileName_suffix.equals(suffixArr[i])) {
            cnt++;
        }
    }

    if (cnt == 0) {
        out.println("NOTALLOW_" + fileName);
    } else {
        String defaultPath = request.getSession().getServletContext().getRealPath("/");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

        String filePath = defaultPath + "/img" + File.separator + "smarteditor2" +File.separator+ dtFormat.format(new Date()) +File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String autoFileName = UUID.randomUUID().toString() +fileName.substring(fileName.lastIndexOf("."));
        String rFileName = filePath + autoFileName;
        InputStream is = request.getInputStream();
        OutputStream os = new FileOutputStream(rFileName);
        int num;
        byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
        while ((num = is.read(b, 0, b.length)) != -1) {
            os.write(b, 0, num);
        }
        if (is != null) {
            is.close();
        }
        os.flush();
        os.close();

        fileInfo += "&bNewLine=false";
        fileInfo += "&sFileName=" + fileName;
        fileInfo += "&sFileURL=/img/smarteditor2/"+dtFormat.format(new Date())+"/"+autoFileName;
        out.println(fileInfo);
    }


%>
<%=fileInfo%>
<%--<html>--%>
<%--<head><title>   </title></head>--%>
<%--<body>--%>
<%--<script type="text/javascript">--%>
<%--try{--%>
<%--    parent.parent.insertIMG('<%=id.replaceAll("<","&lt;").replaceAll(">","&gt;")%>','<%=filename.replaceAll("<","&lt;").replaceAll(">","&gt;")%>');--%>
<%--    parent.parent.oEditors.getById['<%=id.replaceAll("<","&lt;").replaceAll(">","&gt;")%>'].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");--%>
<%--} catch(e){--%>
<%--	alert(e);--%>
<%--}--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


