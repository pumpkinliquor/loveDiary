<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function(){
        var rowData = lgUI.grid.subGrid${subGridId}.getRowData(${rowId});
        console.log(rowData);
        lgUI.event.subGridDblClickAfter($('#popupInfoElement'),rowData,${rowId});
    });
</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">이력상세목록</a></li>
    </ul>
    <div id="tabs-grid">
        <div class="group_02" >
            <div id="popupInfoElement" >
            </div>
        </div>
    </div>
</div>




