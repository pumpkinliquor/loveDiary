<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="org.bouncycastle.util.Pack" %>
<%
    request.setCharacterEncoding("UTF-8");
    String fileInfo = "";
    int size = 10 * 1024 * 1024; //byte
    String encoding = "UTF-8";

    String defaultPath = request.getSession().getServletContext().getRealPath("/");
    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

    String filePath = defaultPath + "img" + File.separator + "smarteditor2" + File.separator + dtFormat.format(new Date()) + File.separator;
    try {

        (new File(filePath)).mkdirs();
    } catch(Exception e){
        e.printStackTrace();
    }
    FileRenamePolicy policy = new DefaultFileRenamePolicy();
    MultipartRequest mrequest
            = new MultipartRequest(request //MultipartRequest를 만들기 위한 request
            , filePath //저장 위치
            , size //최대크기
            , encoding //인코딩 타입
            , policy); //파일 정책

    File uploadFile = mrequest.getFile("file");
    String fileName = uploadFile.getName();
    long uploadFile_length = uploadFile.length();

    if (fileName != null) {
        String autoFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        String fileName_suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String[] suffixArr = {"jpg", "png", "bmp", "gif"};

        int cnt = 0;
        for (int i = 0; i < suffixArr.length; i++) {
            if (fileName_suffix.equals(suffixArr[i])) {
                cnt++;
            }
        }

        if (cnt == 0) {
            out.println("NOTALLOW_" + fileName);
        } else {
//            String defaultPath = request.getSession().getServletContext().getRealPath("/");
//            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//
//            String filePath = defaultPath + "/img" + File.separator + "smarteditor2" +File.separator+ dtFormat.format(new Date()) +File.separator;
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            String autoFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
//            String rFileName = filePath + autoFileName;
//            InputStream is = request.getInputStream();
//            OutputStream os = new FileOutputStream(rFileName);
//            int num;
//            byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
//            while ((num = is.read(b, 0, b.length)) != -1) {
//                os.write(b, 0, num);
//            }
//            if (is != null) {
//                is.close();
//            }
//            os.flush();
//            os.close();

            fileInfo += "&bNewLine=false";
            fileInfo += "&sFileName=" + fileName;
            fileInfo += "&sFileURL=/img/smarteditor2/" + dtFormat.format(new Date()) + "/" + autoFileName;
            //out.println(fileInfo);

            %>
{"files":[{"oriname":"<%=fileName%>","name":"<%=fileName%>","size":4454,"type":"image\/<%=fileName_suffix%>>","url":"/img/smarteditor2/<%=dtFormat.format(new Date())%>/<%=fileName%>","width":200,"height":200}]}
<%
        }
    }


%>

<%--<%=fileInfo%>--%>
<%--<html>--%>
<%--<head><title>   </title></head>--%>
<%--<body>--%>
<%--<script type="text/javascript">--%>
<%--try{--%>
<%--    var editorId = opener.parent.oEditors.getById.keys[0]--%>
<%--    console.log(editorId);--%>
<%--    parent.parent.oEditors.getById[editorId].exec("PASTE_HTML", ['wefwfwefwef']);--%>
<%--    //opener.parent.getById["nttCn"].exec("PASTE_HTML", [sHTML]);--%>
<%--    //opener.parent.pasteHTML("/img/smarteditor2/<%=dtFormat.format(new Date())%>/<%=fileName%>");--%>
<%--    &lt;%&ndash;//parent.parent.insertIMG('<%=id.replaceAll("<","&lt;").replaceAll(">","&gt;")%>','<%=filename.replaceAll("<","&lt;").replaceAll(">","&gt;")%>');&ndash;%&gt;--%>
<%--    &lt;%&ndash;//parent.parent.oEditors.getById['<%=id.replaceAll("<","&lt;").replaceAll(">","&gt;")%>'].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");&ndash;%&gt;--%>
<%--} catch(e){--%>
<%--    console.log('e');--%>
<%--	alert(e);--%>
<%--}--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


