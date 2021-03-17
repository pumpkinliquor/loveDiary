<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>문항등록</h2>

	<div id="wrapList">
			<table class="srchT srchTSearch">
				<colgroup>
					<col width="150px">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2">검색</th>
						<td>
							<div class="mb10">
								<span class="tit">등록일</span>
								<div class="date_div">
			                      <span class="ipt_dates"><input type="date" class="ipt_date" name="sdate" id="sdate"></span><span>~</span>
			                      <span class="ipt_dates"><input type="date" class="ipt_date" name="edate" id="edate"></span>
			                    </div>
		                    </div>
		                    <div>
		                    	<span class="tit">상태</span>
								<div class="radio_wrap rdo_wrap1 codex USE_YN_ALL" type="radio" data="searchUseYn"></div>
		                    </div>
		                </td>
		            </tr>
		            <tr>
		            	<td>
							<div class="srch_div mb10">

								<select class="s_p1 codex LEV" type="select" name="levId" data="levId">
								</select>
								<select class="s_p1 codex SUB" type="select" name="subId" data="subId">
								</select>
								<select class="s_p2 codex UNIT" type="select" name="unitId" data="unitId">
								</select>

							</div>
							<div class="srch_div mb10">
								<select class="s_p4 codex ACV" type="select" name="acvId" data="acvId">
								</select>
								<select class="s_p2 codex ACA" type="select" name="acaId" data="acaId">
								</select>
							</div>
							<div class="srch_div">
								<select class="s_p1" name="searchType" id="searchType">
									<option value="">검색구분</option>
									<option value="bb.qst_Key">문항코드</option>
<%--									<option value="cmtr_Key">해설코드</option>--%>
<%--									<option value="cpt_Key">개념코드</option>--%>
								</select>
								<input type="text" name="searchString" id="searchString" class="ipt3">
								<button type="submit" class="btn btn-st1 btnSearch">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<table id="gridElement" class="table table-bordered table-hover dataTable dtr-inline fs15" >
				<colgroup>
					<col width="3%">
					<col width="6%">
					<col width="5%">
					<col width="5%">
					<col width="5%">

					<col width="15%">
					<col width="6%">
					<col width="6%">
					<col width="5%">

					<col width="10%">
					<col width="3%">
				</colgroup>
				<thead>
					<tr>
						<th class="sort_x">번호</th>
						<th>문항코드</th>
						<th>레벨</th>
						<th>과목</th>
						<th>문제분류</th>

						<th>성취기준</th>
						<th>연결해설</th>
						<th>연결개념</th>
						<th>상태</th>
						<th class="sort_x">등록/수정</th>

						<th>풀이회원</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>

	        <div class="tbl-foot">
				<div class="paging">
					<a href="#" class="prev">
						<i class="fas fa-caret-left"></i>
					</a>
					<span class="on">1</span>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">4</a>
					<a href="#">5</a>
					<a href="#">6</a>
					<a href="#">7</a>
					<a href="#">8</a>
					<a href="#">9</a>
					<a href="#">10</a>
					<a href="#" class="next">
						<i class="fas fa-caret-right"></i>
					</a>
				</div>
				<a href="#" class="btn btn-offer btnReg">등록</a>
			</div>
		</div>

<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/aigo/questionExcute" method="post" enctype="multipart/form-data">
<input type="hidden" name="qstId" id="qstId" value="" />
<input type="hidden" name="qstKey" id="qstKey" value="" />
<input type="hidden" name="qstRelNotId" id="qstRelNotId" value="" />
<input type="hidden" name="qstRelNotData" id="qstRelNotData" value="" />
<input type="hidden" name="fileLists" id="fileLists" value="" />
<input type="hidden" id="start" name="start" value="" />
<input type="hidden" id="length" name="length" value="" />

	<h3>표준분류</h3>
			<table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="15%">
	        		<col width="25%">
	        		<col width="15%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr class="EDIT copy">
	        			<th>문항코드</th>
	        			<td colspan="3" class="td_inblck"><span class="qstKey"></span> <a href="#" class="btn btn-default  btnCopy">복제</a> <p class="point ">※ 복제 시, 새로운 코드로 문항이 복제됩니다.</p></td>
	        		</tr>
	        		<tr>
	        			<th>과목 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code SUB" type="select" data="subId"></div>
	        			</td>
	        			<th>레벨 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code LEV" type="select" data="levId"></div>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>문제분류 <em class="point">*</em></th>
	        			<td colspan="3">
	        				<select class="s_p2 code ACA" type="select" data="acaId" name="acaId" id="acaId">
	        					<option>확인문제</option>
	        				</select>
	        				<select class="s_p2 code SUBACA" type="select" data="subAcaId" name="subAcaId" id="subAcaId">
	        					<option>유사2차</option>
	        				</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>단원분류 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code UNIT" type="select" data="unitId"></div>
	        			</td>
	        			<th>성취기준 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code ACV" type="select" data="acvId"></div>
	        			</td>
	        		</tr>
	        	</tbody>
	        </table>

	        <h3>필수정보</h3>
			<table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr class="EDIT hidden">
	        			<th>출제순서</th>
	        			<td colspan="3" class="td_inblck qstOrder">99 <a href="#" class="btn btn-default">자동넘버링</a> </td>
	        		</tr>
	        		<tr>
	        			<th>문제타입 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code QSTTYPE" type="radio" data="qstType"></div>
	        			</td>
	        			<th class="QSTTYPETD">선지유형 <em class="point">*</em></th>
	        			<td class="QSTTYPETD">
	        				<div class="radio_wrap rdo_wrap1 code QSTTYPENUM" type="radio" data="qstTypeNum"></div>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>정답 <em class="point">*</em></th>
	        			<td><input type="text" name="qstValue" id="qstValue" class="ipt3"></td>
	        			<th>배점</th>
	        			<td><input type="text" name="qstPoint" id="qstPoint" class="ipt3"></td>
	        		</tr>
	        	</tbody>
	        </table>

	        <h3>문제입력</h3>
	        <p class="point mb5">이미지 로컬에서 직접 업로드 하는 경우 업로드 가능 파일: jpg, png, 업로드 파일 용량: 5mb 이하<br>이미지 경로로 입력하는 경우 반드시 Aigo 이미지 서버 주소를 사용하세요. </p>

	        <div class="tab_menu_wrap">
		        <ul class="tab_menu qTab">
		        	<li class="tab tab1 active"><a href="javascript:void(0);">발문</a></li>
					<li class="tab tab2"><a href="javascript:void(0);">지문 (그래프 및 보기박스)</a></li>
					<li class="tab tab3"><a href="javascript:void(0);">선지</a></li>
				</ul>
			</div>
			<div class="qTab_cont_wrap">
				<div class="tab_cont1 tab_cont active">
					<div class="tbl_top">
						<a href="javascript:void(0)" class="btn btn-default btn_reset fR"><i class="fas fa-undo"></i> RESET</a>
					</div>

					<!-- 콘텐츠타입이 이미지 일 때 -->
					<table class="tbl-st writeT mb">
			        	<colgroup>
			        		<col width="17%">
			        		<col width="*">
			        	</colgroup>
			        	<tbody>
			        		<tr>
			        			<th>콘텐츠타입 <em class="point">*</em></th>
			        			<td>
			        				<div class="radio_wrap rdo_wrap1 code CONTYPE qstContType" type="radio" data="qstContType"></div>
			        			</td>
			        		</tr>
			        		<tr class="qstContType I">
			        			<th>발문 <em class="point">*</em></th>
			        			<td class="file" data="qstContType">


			        			</td>
			        		</tr>
			        		<tr class="qstContType T">
			        			<th>발문 <em class="point">*</em></th>
			        			<td>
			        				<div class="editor_div">
										<textarea class="form-control html-editor" name="qstContTypeText" id="qstContTypeText" style="height:200px;"></textarea>
									</div>
			        			</td>
			        		</tr>
			        	</tbody>
			        </table>
				</div>

				<div class="tab_cont2 tab_cont active">
					<div class="tbl_top">
						<a href="javascript:void(0)" class="btn btn-default btn_reset fR"><i class="fas fa-undo"></i> RESET</a>
					</div>

					<!-- 콘텐츠타입이 이미지 일 때 -->
					<table class="tbl-st writeT mb">
			        	<colgroup>
			        		<col width="17%">
			        		<col width="*">
			        	</colgroup>
			        	<tbody>
			        		<tr>
			        			<th>콘텐츠타입 <em class="point">*</em></th>
			        			<td>
			        				<div class="radio_wrap rdo_wrap1 code CONTYPE qstCont02Type" type="radio" data="qstCont02Type"></div>
			        			</td>
			        		</tr>
			        		<tr class="qstCont02Type I">
			        			<th>지문 <br>(그래프 및 보기박스)</th>
			        			<td class="file" data="qstCont02Type">

			        			</td>
			        		</tr>
			        		<tr class="qstCont02Type T">
			        			<th>지문 <br>(그래프 및 <br>보기박스)</th>
			        			<td>
			        				<div class="editor_div">
										<textarea class="form-control html-editor" name="qstCont02TypeText" id="qstCont02TypeText" style="height:200px;"></textarea>
									</div>
			        			</td>
			        		</tr>
			        	</tbody>
			        </table>
				</div>

				<div class="tab_cont3 tab_cont">
					<div class="tbl_top">
						<a href="javascript:void(0)" class="btn btn-default btn_reset fR"><i class="fas fa-undo"></i> RESET</a>
					</div>
					<!-- 콘텐츠타입이 이미지 일 때 -->
					<table class="tbl-st writeT mb">
			        	<colgroup>
			        		<col width="17%">
			        		<col width="*">
			        	</colgroup>
			        	<tbody>
			        		<tr>
			        			<th>콘텐츠타입 <em class="point">*</em></th>
			        			<td  colspan="2">
			        				<div class="radio_wrap rdo_wrap1 code CONTYPE qstCont03Type" type="radio" data="qstCont03Type"></div>
			        			</td>
			        		</tr>
			        		<tr class="qstCont03Type I">
			        			<th>선지</th>
			        			<td class=" file" data="qstCont03Type" colspan="2">

			        			</td>
			        		</tr>
			        		<!-- 문제타입 객관식 -->
			        		<tr class="qstCont03Type T">
			        			<th rowspan="5">선지 <em class="point">*</em></th>
			        			<td class="q_num">(1)</td>
			        			<td><input type="text" name="qstAnswer01" id="qstAnswer01" placeholder="$\log _{5} 2$" /> </td>
			        		</tr>
			        		<tr class="qstCont03Type T">
			        			<td class="q_num">(2)</td>
			        			<td><input type="text" name="qstAnswer02" id="qstAnswer02" placeholder="$\log _{5} 2$" /></td>
			        		</tr>
			        		<tr class="qstCont03Type T">
			        			<td class="q_num">(3)</td>
			        			<td><input type="text" name="qstAnswer03" id="qstAnswer03" placeholder="$\log _{5} 2$" /></td>
			        		</tr>
			        		<tr class="qstCont03Type T">
			        			<td class="q_num">(4)</td>
			        			<td><input type="text" name="qstAnswer04" id="qstAnswer04" placeholder="$\log _{5} 2$" /></td>
			        		</tr>
			        		<tr class="qstCont03Type T">
			        			<td class="q_num">(5)</td>
			        			<td><input type="text" name="qstAnswer05" id="qstAnswer05" placeholder="$\log _{5} 2$" /></td>
			        		</tr>

			        		<tr class="qstCont03Type T S ">
			        			<th>선지 <em class="point">*</em></th>
			        			<td  colspan="2">
									<div class="editor_div">
										<textarea class="form-control html-editor" name="qstCont03TypeText" id="qstCont03TypeText" style="height:200px;"></textarea>
									</div>
								</td>
			        		</tr>
			        	</tbody>
			        </table>
				</div>
			</div>

			<h3>추가정보</h3>
			<table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="14%">
	        		<col width="*">
	        		<col width="14%">
	        		<col width="*">
	        		<col width="14%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr>
	        			<th>제작방식</th>
	        			<td colspan="5">
	        				<select style="width:20.4%" class="qstAddEtc01" name="qstAddEtc01" id="qstAddEtc01">
	        					<option value="">선택</option>
	        					<option value="E01">기출</option>
	        					<option value="E02">자체</option>
	        					<option value="E03">EBS</option>
	        					<option value="E04">기타</option>
	        				</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>기출년도</th>
	        			<td>
	        				<select class="qstAddEtc02" name="qstAddEtc02" id="qstAddEtc02">
	        					<option value="">선택</option>
	        					<option value="2021">2021</option>
	        					<option value="2020">2020</option>
	        					<option value="2019">2019</option>
	        					<option value="2018">2018</option>
	        					<option value="2017">2017</option>
	        					<option value="2016">2016</option>
	        					<option value="2015">2015</option>
	        					<option value="2014">2014</option>
	        					<option value="2013">2013</option>
	        					<option value="2012">2012</option>
	        					<option value="2111">2011</option>
	        					<option value="2010">2010</option>
	        				</select>
	        			</td>
	        			<th>기출월</th>
	        			<td>
	        				<select class="qstAddEtc03" name="qstAddEtc03" id="qstAddEtc03">
	        					<option value="">선택</option>
	        					<option value="01">1월</option>
	        					<option value="02">2월</option>
	        					<option value="03">3월</option>
	        					<option value="04">4월</option>
	        					<option value="05">5월</option>
	        					<option value="06">6월</option>
	        					<option value="07">7월</option>
	        					<option value="08">8월</option>
	        					<option value="09">9월</option>
	        					<option value="10">10월</option>
	        					<option value="11">11월</option>
	        					<option value="12">12월</option>
	        				</select>
	        			</td>
	        			<th>기출종류</th>
	        			<td>
	        				<select class="qstAddEtc04" name="qstAddEtc04" id="qstAddEtc04">
	        					<option value="">선택</option>
	        					<option value="ETC0401">평가원</option>
	        					<option value="ETC0402">교육청</option>
	        				</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>내용영역</th>
	        			<td>
	        				<select class="qstAddEtc05" name="qstAddEtc05" id="qstAddEtc05">
	        					<option>선택</option>
	        					<option></option>
	        					<option></option>
	        				</select>
	        			</td>
	        			<th>평가영역</th>
	        			<td colspan="3">
	        				<select class="qstAddEtc06" name="qstAddEtc06" id="qstAddEtc06">
	        					<option>선택</option>
	        					<option></option>
	        					<option></option>
	        				</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>권장풀이시간</th>
	        			<td colspan="5" class="td_inblck">
	        				<input type="text" name="qstAddEtc07" id="qstAddEtc07" class="txt_c plx" style="width:20.4%">
	        				<p class="point">※ mm:ss 형식으로 입력 바랍니다.</p>
	        			</td>
	        		</tr>

	        	</tbody>
	        </table>

	        <h3>연결개념</h3>
	        <table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr>
	        			<th>개념요소 선택</th>
	        			<td>
	        				<select multiple="" class="listbox">
	                        </select>
	                        <button type="button" class="btn btn-st1 btn_srch btnPopup">검색</button>
	        			</td>
	        		</tr>
	        	</tbody>
	        </table>

	        <h3>정답율 통계치</h3>
	        <table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr>
	        			<th>자동/수동 <em class="point">*</em></th>
	        			<td>

							<div class="radio_wrap rdo_wrap1 code QSTPRETYPE" type="radio" data="qstPreType"></div>

	        			</td>
	        		</tr>
	        		<tr>
	        			<th>난이도 기준</th>
	        			<td class="pdx">
	        				<!-- 수동일 때 -->
	        				<div style="display:block">
		        				<table class="tbl-in">
		        					<colgroup>
		        						<col width="*">
		        						<col width="*">
		        						<col width="*">
		        						<col width="*">
		        						<col width="*">
		        					</colgroup>
		        					<thead>
		        						<tr>
		        							<th>A</th>
		        							<th>B</th>
		        							<th>C</th>
		        							<th>D</th>
		        							<th>E</th>
		        						</tr>
		        					</thead>
		        					<tbody>
		        						<tr>
			        						<td class="pd">(<input type="number" name="qstPreS01" id="qstPreS01" class="ipt_s">)%  ~ (<input type="number" name="qstPreE01" id="qstPreE01" class="ipt_s">)%</td>
			        						<td class="pd">(<input type="number" name="qstPreS02" id="qstPreS02" class="ipt_s">)%  ~ (<input type="number" name="qstPreE02" id="qstPreE02" class="ipt_s">)%</td>
			        						<td class="pd">(<input type="number" name="qstPreS03" id="qstPreS03" class="ipt_s">)%  ~ (<input type="number" name="qstPreE03" id="qstPreE03" class="ipt_s">)%</td>
			        						<td class="pd">(<input type="number" name="qstPreS04" id="qstPreS04" class="ipt_s">)%  ~ (<input type="number" name="qstPreE04" id="qstPreE04" class="ipt_s">)%</td>
			        						<td class="pd">(<input type="number" name="qstPreS05" id="qstPreS05" class="ipt_s">)%  ~ (<input type="number" name="qstPreE05" id="qstPreE05" class="ipt_s">)%</td>
		        						</tr>
		        					</tbody>
		        				</table>
	        				</div>

	        				<!-- 자동일 때 -->
	        				<div style="display:block">
		        				<table class="tbl-in" style="width:40%">
		        					<colgroup>
		        						<col width="*">
		        						<col width="25%">
		        					</colgroup>
		        					<thead>
		        						<tr>
		        							<th>정답율</th>
		        							<th>난이도 등급</th>
		        						</tr>
		        					</thead>
		        					<tbody>
		        						<tr>
		        							<td>0%  ~ 25%</td>
		        							<td>A</td>
		        						</tr>
		        						<tr>
		        							<td>25%  ~ 50%</td>
		        							<td>B</td>
		        						</tr>
		        						<tr>
		        							<td>50%  ~ 75%</td>
		        							<td>C</td>
		        						</tr>
		        						<tr>
		        							<td>75%  ~ 90%</td>
		        							<td>D</td>
		        						</tr>
		        						<tr>
		        							<td>90%  ~ 100%</td>
		        							<td>E</td>
		        						</tr>
		        					</tbody>
		        				</table>
	        				</div>
	        			</td>
	        		</tr>
	        	</tbody>
	        </table>

	        <table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr>
	        			<th>상태 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code USE_YN2" type="radio" data="useYn"></div>
							<p class="point mt5 USE_YN2">※ 공개 시 바로 사용자에게 문제가 노출됩니다.</p>
	        			</td>
	        		</tr>
					<tr class="EDIT hidden">
						<th>등록/수정</th>
						<td class="show_iddate">이지현(admin) yyyy-mm-dd hh:mm:ss</td>
					</tr>
	        	</tbody>
	        </table>
	        <div class="tbl-foot">
				<a href="javascript:;" class="btn btn-list btnList">목록</a>
				<a href="javascript:;" class="btn btn-offer btnSubmit">저장</a>
			</div>

</form>

<div class="modal-wrap pop01 ">
    <div class="modal">
		<div class="modal-header">
			<h1>연결개념선택</h1>
		</div>
		<div class="content">
			<table class="srchT mb srchTnot">
				<colgroup>
					<col width="120px">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>검색</th>
		            	<td>
							<div class="srch_div mb10">
								<select class="s_p2 codex SUB" type="select" data="subId">
									<option>과목</option>
									<option></option>
								</select>
								<select class="s_p2 codex ACV" type="select" data="acvId">
									<option>성취기준</option>
									<option></option>
								</select>
							</div>
							<div class="srch_div">
								<select class="s_p2" name="searchType">
									<option value="">선택하세요</option>
									<option value="notKey">개념코드</option>
									<option value="notName">개념요소 명</option>
								</select>
								<input type="text" name="searchType" class="ipt4">
								<button type="button" class="btn btn-st1 btnSearchNot">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<p class="txt-st1 mb">* 총 <em>3개</em>의 개념코드 선택 되었습니다. </p>
			<table class="popT1" id="gridElementNot">
				<colgroup>
					<col width="14%">
					<col width="17%">
					<col width="*">
					<col width="20%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>개념코드</th>
						<th>과목</th>
						<th>성취기준</th>
						<th>개념요소명</th>
						<th>

						</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

		</div>
		<div class="modal-footer">
	       <button class="btn btn-primary fR btnSelect">선택</button>
	    </div>

		<a href="#" class="close-pop close-btn" >&#10005;</a>
    </div>
</div>
<script type="text/javascript">
var gridElement =null,gridElementNot =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,USE_YN_ALL,USE_YN2,ACA,ACV,LEV,ACA,UNIT,SUB,CONTYPEALL,QSTTYPENUM,QSTTYPE,CONTYPE,QSTPRETYPE'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $('#qstAddEtc07').mask('00:00');
    $.each(plus.codes,function(k,v){
      //$('.code.'+k).addCodeItem(v,true)
		$('.code.'+k).each(function(){
			$(this).addCodeItem(v,true)
		})
      	$('.codex.'+k).addCodeItem(v,true);
    });
    $('.code.CONTYPEALL :radio').click(function(){
    	$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	});
    $('.code.CONTYPE :radio').click(function(){
    	var dataId= $(this).closest('.CONTYPE').attr('data');
    	$('tr.'+dataId).hide();
		$('tr.'+dataId+'.'+$(this).attr('value')).show();
	});
    $('.code.USE_YN2 :radio').change(function(){
    	$('p.USE_YN2').hide();
    	if($('.code.USE_YN2 :radio:checked').val()=='y'){
    		$('p.USE_YN2').show()
		}
	});
    $('.code.QSTTYPE :radio').change(function(){
    	//alert($('.code.QSTTYPE :radio:checked').val());
		$('.QSTTYPETD').hide();
		if($('.code.QSTTYPE :radio:checked').val()=='S'){
			$('.QSTTYPETD').removeAttr('style');
		}
	})
	$('.btnCopy').click(function(){
		if(confirm('해당 문항을 복제하시겠습니까?')){
			$.call('/plusadmin/ajax/aigo/questionCopy',$(this).closest('form').domJson(),function(r){

			})
		}
	})

	/* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
	sDate.setMonth(sDate.getMonth()-1);

	$("#sdate").val(sDate.format('yyyy-MM-dd'));
	$("#edate").val(eDate.format('yyyy-MM-dd'));




    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            subId:{required:true}
            ,levId:{required:true}
            ,acaId:{required:true}
            ,acvId:{required:true}
            ,unitId:{required:true}
            ,qstType:{required:true}
            ,qstValue:{required:true}
            ,qstPreType:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        rowData['qstContTypeText'] =rowData['qstContTypeText']||'';
		rowData['qstCont02TypeText'] =rowData['qstCont02TypeText']||'';
		rowData['qstCont03TypeText'] =rowData['qstCont03TypeText']||'';
		$('.copy').hide();
        if(rowData['qstId']!='0'){
        	$('.copy').css('display','table-row');
		}
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);

        //AD_LE_02_01
		$('.listbox').empty();
		if($.trim(rowData['qstRelNotId'])){

			$.post('/plusadmin/ajax/aigo/notionList',{qstRelNotId:rowData['qstRelNotId']},function(r){
				if(r.resultList){
					$.each(r.resultList,function(k,v){
						plus.makeElement('option',[v['notKey'],v['cptName']].join('|'),{value:v['notId']}).appendTo($('.listbox'));
					});
				}
			});
		}


        rowData['qstContType'] = rowData['qstContType']=='T'?'T':'I';
        rowData['qstCont02Type'] = rowData['qstCont02Type']=='T'?'T':'I';
        rowData['qstCont03Type'] = rowData['qstCont03Type']=='T'?'T':'I';

        $('.code.qstContType :radio[value='+rowData['qstContType']+']').prop('checked',true);
        $('.code.qstCont02Type :radio[value='+rowData['qstCont02Type']+']').prop('checked',true);
        $('.code.qstCont03Type :radio[value='+rowData['qstCont03Type']+']').prop('checked',true);

        setTimeout(function(){

			$('tr.qstContType').hide();
			$('tr.qstCont02Type').hide();
			$('tr.qstCont03Type').hide();
			$('tr.qstContType.'+rowData['qstContType']).show();
			$('tr.qstCont02Type.'+rowData['qstCont02Type']).show();
			$('tr.qstCont03Type.'+rowData['qstCont03Type']).show();
			$('.tab_cont').removeClass('active')
			$('.tab_cont:eq(0)').addClass('active');
		},1000);



    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
    	$('.USE_YN_ALL :radio:eq(0)').click();
        plus.evnet.submitAfter=function(){
            $('#wrapList').show();
            $('#wrapEdit').hide();

            var info = gridElement.page.info();
            //console.log(info);
            //alert(JSON.stringify(info));
            gridElement.ajax.reload(null,false);
        }
        /* 그리드완료된후 호출 */
        plus.event.gridComplet=function(){
            //전체선택 체크박스 클릭
        }
        plus.renderer.qstRelNotId=function(d, t, r){
        	var d= $.trim(r.qstRelNotId);
        	if(d=='NULL') {
				d = '';
			}
        	var notCount = 0;
        	if(d){
        		notCount = d.split(',').length;
			}


        	return notCount + " 개";;
		}
        plus.event.seqCheckBox=function(d, t, r){
            var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
            var id = plus.getId(r.notId);
            plus.makeInput('checkbox','',{'class':"custom-control-input notId",id:"notId"+(id),value:r.notId}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'notId'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.renderer.qstCmtrKey=function(r,t,c){

        	var res ='';
        	if(r){
        		res = 'CMTR'+plus.lpad(r,5,'0');
			}
        	return res;
		}

        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }
// <th class="sort_x">번호</th>
// 						<th>문항코드</th>
// 						<th>레벨</th>
// 						<th>과목</th>
// 						<th>문제분류</th>
//
// 						<th>성취기준</th>
// 						<th>연결해설</th>
// 						<th>연결개념</th>
// 						<th>상태</th>
// 						<th class="sort_x">등록/수정</th>
//
// 						<th>풀이회원</th>
        gridColumn.push({'data': 'umSeq', 'title': '순번', 'type': 'rownum', hidden: false,render:plus.renderer.rrownum});
        gridColumn.push({'data':'qstKey','title':'문항코드',render:plus.renderer.clickbox});
        gridColumn.push({'data':'levName','title':'레벨'});
        gridColumn.push({'data':'subName','title':'과목명'});
        gridColumn.push({'data':'acaName','title':'문제분류'});
        gridColumn.push({'data':'acvName','title':'성취기준명','class':'tl',render:plus.renderer.clickbox});

        //gridColumn.push({'data':'notName','title':'개념요소명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'cmtrId','title':'연결해설',render:plus.renderer.qstCmtrKey});
        gridColumn.push({'data':'notType2','title':'연결개념',render:plus.renderer.qstRelNotId});

        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN2'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
        gridColumn.push({'data':'rcount','title':'풀이회원'});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/questionList',{},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(rowData,info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');
			$('#wrapList').hide();
            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            rowData['start']=info['start'];
            rowData['length']=info['length'];

            $('.listbox').empty();

            var rData = [];
            if(rowData['qstRelNotData']){
            	try{
            		rData = $.parseJSON(rowData['qstRelNotData']);
				} catch(e){

				}

			}

            $.each(rData,function(k,v){
				plus.makeElement('option',v['notData'],{value:v['notId']}).appendTo($('.listbox'));
			});
            $('.listbox option').dblclick(function(){
				if(confirm('해당 개념요소를 삭세하시겠습니까')){
					$(this).remove();
				}
			})

            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }

    $('.btnPopup').click(function(){
		$('.pop01').addClass('is-visible');

		var gridColumn2 = [];
		 gridColumn2.push({'data':'notKey','title':'개념코드'});
        gridColumn2.push({'data':'subName','title':'과목명'});
        gridColumn2.push({'data':'acvName','title':'성취기준명','class':'tl',render:plus.renderer.clickbox});

        gridColumn2.push({'data':'cptName','title':'개념요소명','class':'tl',render:plus.renderer.clickbox});
        gridColumn2.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
		$('.pop01 .mb em').html('0개');
		gridElementNot = plus.makeGrid('#gridElementNot',gridColumn2,plus.makeAjax('/plusadmin/ajax/aigo/notionList',$('.srchTnot').domJson(),'resultList'),{attr:'속성',drawCallback:function(){
			$('#gridElementNot tbody :checkbox').change(function(){
				var length = $('#gridElementNot tbody :checkbox:checked').length
					$('.pop01 .mb em').html(length+'개');
			});

			}});
	});
	$('.btnSearchNot').click(function(){
		var gridColumn2 = [];
		 gridColumn2.push({'data':'notKey','title':'개념코드'});
        gridColumn2.push({'data':'subName','title':'과목명'});
        gridColumn2.push({'data':'acvName','title':'성취기준명','class':'tl',render:plus.renderer.clickbox});

        gridColumn2.push({'data':'cptName','title':'개념요소명','class':'tl',render:plus.renderer.clickbox});
        gridColumn2.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
		$('.pop01 .mb em').html('0개');
		gridElementNot = plus.makeGrid('#gridElementNot',gridColumn2,plus.makeAjax('/plusadmin/ajax/aigo/notionList',$('.srchTnot').domJson(),'resultList'),{attr:'속성',drawCallback:function(){
			$('#gridElementNot tbody :checkbox').change(function(){
				var length = $('#gridElementNot tbody :checkbox:checked').length
					$('.pop01 .mb em').html(length+'개');
			});

			}});
	})
	$('.btnSelect').click(function(){
		var checkeds = [];
		var checkedValue = [];
		var checkedData = [];
		$('.pop01 tbody :checkbox:checked').each(function(){
			checkedValue.push($(this).val());
			checkeds.push(gridElementNot.row($(this).closest('tr')).data());




		});
		if(checkedValue.length==0){
			//alert(checkedValue.length+ '개의 개념코드를 선택하시겠습니까')
			alert('1개이상의 개념을 선택해주세요.');
			return false;
		}
		if(confirm(checkedValue.length + "개의 개념을 연결하시겠습니까?")){
			$('.listbox option').remove();
			$.each(checkeds,function(k,v){
				plus.makeElement('option',[v['notKey'],v['cptName']].join('|'),{value:v['notId']}).appendTo($('.listbox'));
			});
			$('.listbox option').dblclick(function(){
				if(confirm('해당 개념요소를 삭세하시겠습니까')){
					$(this).remove();
				}
			})
			$('.listbox').find('a').click(function(){
				$(this).closest('option').remove();
			})
			$('#qstRelNotId').val(checkedValue.join(','));

			$('.pop01').removeClass('is-visible');
		}
	});


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();

    // popup Tab
	tabMenu();




    $('.btnSearch').click(function(){
        //gridElement.ajax.reload();
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/questionList',$('.srchTSearch').domJson(),'resultList'),{attr:'속성'});
    });
    $('#searchString').keypress(function(e){
        if(e.keyCode==13){
            $('.btnSearch').click();
        }
    });
    $('.code.ACA').change(function(){

		$.call('/plusadmin/ajax/aigo/categorySubList',{parentAcaId:$(this).val()},function(r) {
			if (r['resultList']) {
				var subData = {};
				$.each(r['resultList'], function(k, v) {
						subData[v['acaId']] = v['acaName'];

				});
				$('.code.SUBACA').addCodeItem(subData,true)
			}
		})
	})
    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
    	$('.listbox').empty();
        plus.frontPage.show($('#wrapEdit'), {qstId:'0','start':0,length:0,useYn:'y',qstPreType:'A',qstType:'S',qstTypeNum:'4',subAcaId:'',qstContType:'I',qstContTypeText:'',qstCont02TypeText:'',qstCont02Type:'I',qstCont03Type:'I',qstPreS01:'0',qstPreE01:'25',qstPreS02:'25',qstPreE02:'50',qstPreS03:'50',qstPreE03:'75',qstPreS04:'75',qstPreE04:'90',qstPreS05:'90',qstPreE05:'100',qstCont03TypeText:'',qstAnswer01:'',qstAnswer02:'',qstAnswer03:'',qstAnswer04:'',qstAnswer05:'',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
    });
    /* 삭제 버튼*/
    $('.btnDelete').click(function(){
          var delBbsSeqs = [];
          $.each($('#gridElement').find(':checked'),function(){
            delBbsSeqs.push($(this).val());
          });
          if(delBbsSeqs.length==0){
            alert('1건 이상의 데이터를 선택해주세요');
          }
          // plus.event.tplDelete($(this).closest('form'),);
          // $(this).closest('form').submit();

        if(confirm('삭제 하시겠습니까?')) {
          $.call('/plusadmin/ajax/bbs/bbsDelete', {delBbsSeqs: delBbsSeqs.join(',')}, function(r) {
            plus.event.tplCallback(r)
            gridElement.ajax.reload();
          })
        }
    });
    $('.btnList').click(function() {
      $('#wrapList').show();
      $('#wrapEdit').hide();
    });
    $('.btnSubmit').click(function(){
		var fileLists = [];
		  $(':file').each(function(){
			var data = $(this).data();
			if(data['safSeq']){
			  fileLists.push(data);
			}
		  });

    	var contypeall =   $('.code.CONTYPEALL :radio:checked').val();
    	if(contypeall=='T'){
    		$('.html-editor').each(function(){
    			var editorId = $(this).attr('id');
    			oEditors.getById[editorId].exec("UPDATE_CONTENTS_FIELD", []);
    			//$('.html-editor').attr('id');
			})


			if($('#notText').val()==""){
				Swal.fire(
						'[내용] 항목은 필수입니다.',
						'',
						'error'
				)
				return false;
			}
		}
    	var rdata = [];
		if($('.listbox option').length!=0){
			$('.listbox option').each(function(){
				rdata.push({'notId':$(this).val(),'notData':$(this).text()});
			});
		}
		$('#qstRelNotData').val(JSON.stringify(rdata));

      $(this).closest('form').find('input[name=fileLists]').val(JSON.stringify(fileLists));
      $(this).closest('form').submit();
      return false;
    });



});

</script>