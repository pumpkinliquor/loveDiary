<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!--
<footer>
    footer
</footer>
-->
<script id="tplTrView" type="text/x-jquery-tmpl">
<form id="form" class="wrapElement" method="post" action="\${url}" onSubmit="return false;" enctype="multipart/form-data">
    <table id="pageContent" class="table table-striped table-bordered table-hover" >
        <tbody class="tableElement">
        </tbody>
    </table>
    <div class="btns text-right">
            <button class="btn btn-primary btn-sm btnEditElement" data="\${updateUrl}">수정</button>
            <button class="btn btn-danger btn-sm btnDeleteElement" data="\${deleteUrl}">삭제</button>
    </div>
</form>
</script>

<script id="tplAddress" type="text/x-jquery-tmpl">
    <tr>
        <th class="bdnoneL">주소</th>
        <td class="bdnoneR" colspan="3">
            <input type="hidden" name="addrObject" id="addrObject"/>
            <input type="hidden" name="aiSeq" id="aiSeq"/>
            <div class="row">
                <div class="col-3">
                    <input type="text" name="aiPostcode" id="aiPostcode" placeholder="우편번호를 입력해주세요" class="inp  text" value="\${addressInfo.aiPostcode}" readonly="readonly"/>
                </div>
                <div class="col-2">
                    <span><button class="btn btn-inverse btn-sm btnAddressElement">우편번호선택</button></span>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-7">
                    <input type="text" name="aiRoadaddress" id="aiRoadaddress" placeholder="주소를 입력해주세요" class="inp  text"  value="\${addressInfo.aiRoadaddress}" readonly="readonly"/>
                </div>
                <div class="col-5">
                    <input type="text" name="aiAddressdetail" id="aiAddressdetail" placeholder="상세주소를 입력해주세요" class="inp  text"  value="\${addressInfo.aiAddressdetail}" />
                </div>
            </div>
        </td>
    </tr>
</script>

<script id="tplTabView" type="text/x-jquery-tmpl">
<form id="form" class="wrapElement" method="post" action="\${url}" onSubmit="return false;" enctype="multipart/form-data">
    <div class="row">
        <div class="col-12">
            <div class="card pageContent">
                <div class="card-body">
                <table class="table table-striped table-bordered table-hover" >
                    <tbody class="tableElement">
                    </tbody>
                </table>

                <div class="btns text-right">
                        <button class="btn btn-primary btn-sm btnEditElement" data="\${updateUrl}">수정</button>
                        <button class="btn btn-danger btn-sm btnDeleteElement" data="\${deleteUrl}">삭제</button>
                </div>
                </div>
            </div>
        </div>
     </div>
</form>
</script>

<script id="tplPageTitle" type="text/x-jquery-tmpl">
    <div class="col-md-5 align-self-center">
        <h3 class="text-themecolor">\${pageTitle}</h3>
    </div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"></li>
            <li class="breadcrumb-item">
                <a href="javascirpt:;"><button type="button" class="btn btn-block btn-sm btn-outline-info newTab">\${newTabTitle}</button></a>
            </li>
        </ol>
    </div>
    <div class="col-12">
        <div class="card-body-line" style=""></div>
    </div>
</script>

<script id="tplGridView" type="text/x-jquery-tmpl">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="group_02 dataTables_wrapper ">
                        <div class="wrapElement ">
                        <table id="gridElement" class=" nowrap table table-striped table-bordered table-hover dataTable no-footer dtr-inline" role="grid">
                            <tbody class="tableElement">
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<div id="postcodeLayer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;z-index:100;">
    <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>