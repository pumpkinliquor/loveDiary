<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">

    <div class="col-md-5 align-self-center">
        <h3 class="text-themecolor">목록</h3>
    </div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><?//1라인 삭제금지?></li>
            <li class="breadcrumb-item">
                <a href="/totaladmin/sites/write"><button type="button" class="btn btn-block btn-sm btn-outline-info">신규등록</button></a>
            </li>
        </ol>
    </div>
    <div class="col-12">
            <div class="card-body-line" style=""></div>

    </div>
</div>
<!-- End Bread crumb and right sidebar toggle -->
<!-- Start Page Content -->
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
                <?//php echo $output;?>
            </div>
        </div>
    </div>
</div>
        