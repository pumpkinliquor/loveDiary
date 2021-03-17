var $grid = '';

var selRowMap = new JqMap();
var rowCount = 0;
var selectedValueArray = [];

/** 체크박스 선택 시 필요한 자료가 object일때 index생성 */
var searchObjectArray = function(selectValue, objectArray) {
  var index = -1;
  $.each(objectArray, function(idx, that){
    if(that.rowNum == selectValue.rowNum) {
      index = idx;
    }
  });
  return index;
};

/** 체크박스 선택 시 배열에 기본키 or row 추가 */
/** object를 넘길 경우 rowNum 함께 넘겨 사 용 */
var setSelectedValue = function(selectValue, isSelected) {
  var index = -1;
  if(typeof selectValue === 'object') {
    index = searchObjectArray(selectValue, selectedValueArray);
  } else {
    index = $.inArray(selectValue, selectedValueArray);
  }
  if (!isSelected && index >= 0) {
    selectedValueArray.splice(index, 1);
  } else if (isSelected && index < 0) {
    selectedValueArray.push(selectValue);
  }
};

/** 페이징 이동 시 검색값 & 체크박스 key 저장 */
var pagingHoldData = function(pgButton) {
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var selarrrow = $grid.jqGrid('getGridParam', 'selarrrow');
    selRowMap.put(currentPage, selarrrow);
};


//TODO: pageEntity rowStartNumber, rowEndNumber 사용
/*var makeRecordText = function(pageEntity) {
  var rowStartNumber = JqGridFormatter.rowNumberFormat();
  var rowEndNumber = rowStartNumber - (pageEntity.pageListSize-1);

  var tableData = $( '#pager_right' ).find( 'div' );
  tableData.empty();
  tableData.append('View [{0}] - [{1}] of [{2}]'.format(rowStartNumber, rowEndNumber, pageEntity.dataSize));
};*/


/** 체크박스 유지 및 검색값 셋팅 */
var loadFn = function(pageEntity) {
  AjaxHandler.success(pageEntity);
  rowCount = 0;
  var currentPage = $grid.jqGrid('getGridParam', 'page');
  var searchParamString = pageEntity.searchParamString;
  if (CommonUtils.isNotEmpty(searchParamString)) {
    $('#searchParamString').val(searchParamString);
  }
  var selarrrows = selRowMap.get(currentPage);
  if (CommonUtils.isNotEmpty(selarrrows)) {
    $.each(selarrrows, function(index, value) {
      $grid.jqGrid('setSelection', value, false);
    });
  }

  var listCount = $grid.jqGrid('getGridParam', 'records');
  var tableData = $( '#pager_right' ).find( 'div' );
  tableData.empty();
  tableData.append('총 게시물 수: {0}'.format(listCount));


  //makeRecordText(pageEntity);로
  /*var listCount = $grid.jqGrid('getGridParam', 'records');
  $('#listCount').text(listCount);*/
};

var errorFn = function(jqXHR, textStatus, errorThrown) {
  //console.log('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
  console.log('HTTP status code: ' + jqXHR.status + '\n' + 'textStatus: ' + textStatus + '\n' + 'errorThrown: ' + errorThrown);
  alert('목록 조회에 실패했습니다.');
};

/**
 * 숫자 천단위 콤마 찍어주기
 * @param cellValue
 * @returns {*}
 */
var numberFommat = function(cellValue){
  var str = cellValue;
  return commify(str);
};

function commify(n) {
  var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
  n += '';                          // 숫자를 문자열로 변환

  while (reg.test(n))
    n = n.replace(reg, '$1' + ',' + '$2');

  return n;
}

function jqGridSearch(searchParam) {
    var commonParam = {
        searchKey : $.trim($('#searchKey').val()),
        searchValue : $.trim($('#searchValue').val()),
        startDate : $.trim($('#startDate').val()),
        endDate : $.trim($('#endDate').val()),
        searchUserKind : $.trim($('#searchUserKind').val()),
        searchUserType : $.trim($('#searchUserType').val()),
        searchUserState : $.trim($('#searchUserState').val())
    };

    searchParam = searchParam || commonParam;

    $grid.jqGrid('setGridParam', {
        'postData' : searchParam,
        'page' : '1'
    }).trigger('reloadGrid');
    return false;
}

/** 검색값 문자열 생성 */
function setSearch(searchData) {
    var totalSearchData = {};
    var commonSearchData = {
        searchKey : $.trim($('#searchKey').val()),
        searchValue : $.trim($('#searchValue').val()),
        startDate : $.trim($('#startDate').val()),
        endDate : $.trim($('#endDate').val())
    };

    if (CommonUtils.isEmpty(searchData)) {
        totalSearchData = commonSearchData;
    }
    else {
        totalSearchData = $.extend( {}, commonSearchData, searchData );
    }

    $grid.jqGrid('setGridParam', {
        'postData' : totalSearchData,
        'page' : '1'
    }).trigger('reloadGrid');
    return false;
}

window.JqGridFormatter = (function(JqGridFormatter, $, undefined) {

  var oneDepthFormat = function(cellValue, options, rowObject) {
    var categoryCode = rowObject.oneCategoryCode;
    var twoCategoryCode = rowObject.twoCategoryCode;
    var threeCategoryCode = rowObject.threeCategoryCode;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter;
    if (CommonUtils.isNotEmpty(categoryCode) && CommonUtils.isEmpty(twoCategoryCode) && CommonUtils.isEmpty(threeCategoryCode)) {
      formatter = '<a href="#" onclick="myPageMenuDetail({0}, \'{1}\', \'{2}\')">{3}</a>'.format(currentPage, categoryCode, 'oneCategoryCode', cellValue);
    } else {
      formatter = cellValue;
    }
    return formatter;
  };

  var twoDepthFormat = function(cellValue, options, rowObject) {
    var categoryCode = rowObject.twoCategoryCode;
    var threeCategoryCode = rowObject.threeCategoryCode;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter;
    if (CommonUtils.isNotEmpty(categoryCode) && CommonUtils.isEmpty(threeCategoryCode)) {
      formatter = '<a href="#" onclick="myPageMenuDetail({0}, \'{1}\', \'{2}\')">{3}</a>'.format(currentPage, categoryCode, 'twoCategoryCode', cellValue);
    } else if (CommonUtils.isNotEmpty(cellValue)){
      formatter = cellValue;
    } else {
      formatter = '-';
    }
    return formatter;
  };

  var threeDepthFormat = function(cellValue, options, rowObject) {
    var categoryCode = rowObject.threeCategoryCode;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter;
    if (CommonUtils.isNotEmpty(categoryCode)) {
      formatter = '<a href="#" onclick="myPageMenuDetail({0}, \'{1}\', \'{2}\')">{3}</a>'.format(currentPage, categoryCode, 'threeCategoryCode', cellValue);
    } else {
      formatter = '-';
    }
    return formatter;
  };

  /** 업무게시판 전체목록 상세Link */
  var intranetBoardAllTitleFormat = function(cellValue, options, rowObject) {
    var boardNumberParam = rowObject.boardNumber;
    var boardGroupKeyParam = rowObject.boardGroupKey;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter = '<a href="#" onclick="intranetBoardAllDetail({0}, {1}, {2})">{3}</a>'.format(currentPage, boardGroupKeyParam, boardNumberParam, cellValue);
    return formatter;
  };

	/** 물품문의게시판 상세Link */
	var inquiryTitleFormat = function(cellValue, options, rowObject) {
		var boardNumberParam = rowObject.boardNumber;
    var boardGroupKeyParam = rowObject.boardGroupKey;
		var currentPage = $grid.jqGrid('getGridParam', 'page');
		var formatter = '<div class="ellipsis al-left" style="width: 90%;"><a href="#" onclick="inquiryDetail({0}, {1}, {2})">{3}</a></div>'.format(currentPage, boardNumberParam, boardGroupKeyParam, cellValue);
		return formatter;
	};

	/** 물품문의게시판 답변자 이름(아이디) */
	var commentWriterKeyFormat = function(cellValue, options, rowObject) {
		var userName = '';
    if (CommonUtils.isNotEmpty(rowObject.boardComment.userName)) {
      userName = rowObject.boardComment.userName;
    }
		var formatter = '{0}({1})'.format(userName, cellValue);
		if (CommonUtils.isEmpty(cellValue)) {
			formatter = '';
		}
		return formatter;
	};

	/** 물품문의게시판 답변상태 */
	var commentStateFormat = function(cellValue, options, rowObject) {
    var formatter = '답변대기';
    if (rowObject.boardComment.commentKey > 0) {
      formatter = '답변완료';
    }
		return formatter;
	};

	/** 물품문의게시판 공개여부 */
	var isOpenFormat = function(cellValue) {
		return cellValue == 1 ? '공개' : '비공개';
	};

  /** 글번호 출력 */
  var rowNumberFormat = function() {
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var pageListSizeParam = $grid.jqGrid('getGridParam', 'rowNum');
    var total = $grid.jqGrid('getGridParam', 'records');
    var rowNumber = total - ((currentPage - 1) * pageListSizeParam) - rowCount;
    rowCount++;

    return rowNumber;
  };

  /** 업무게시판, 고객센터 게시판 */
  var boardTitleFormat = function(cellValue, options, rowObject) {
    var boardNumberParam = rowObject.boardNumber;
    var boardGroupKeyParam = rowObject.boardGroupKey;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter = '<div class="ellipsis al-left" style="width: 90%;"><a href="#" onclick="boardDetail({0}, {1}, {2})">{3}</a></div>'.format(currentPage, boardNumberParam, boardGroupKeyParam, cellValue);
    return formatter;
  };

	/** 등록자 이름(아이디) */
  var writerKeyFormat = function(cellValue, options, rowObject) {
    var userName = rowObject.userName;
    var formatter = '{0}({1})'.format(userName, cellValue);
    return formatter;
  };

  /** 회원명 이름(아이디) */
  var nameFormat = function(cellValue, options, rowObject) {
    var id = '';
    var cellName = options.colModel.name;
    for(var key in rowObject) {
      if(key === cellName) {
        id = rowObject[cellName.replace('Name', 'Id')];
        if(CommonUtils.isEmpty(id)) {
          id = rowObject[cellName.replace('Name', 'Key')];
        }
      }
    }
    var formatter = CommonUtils.isEmpty(cellValue) ? '-' : '{0}({1})'.format(cellValue, id);
    return formatter;
  };
  var goDetailNameFormat = function(cellValue, options, rowObject) {
    var nameFormat = window.JqGridFormatter.nameFormat(cellValue, options, rowObject);
    var detailLink = '<a href="javascript:;" onclick="TargetAuctionBuySellDetail.goBuySellDetailByGrid({0}, {1})">{2}</a>'.format(rowObject.auctionUserKey, rowObject.isSeller, nameFormat);
    return detailLink;
  }

	/** 웹로그 게시판 목록 작성내용 말줄임 적용 */
  var contentsFormat = function(cellValue) {
    var cellValueText = '' + cellValue;
    cellValueText = cellValueText.replace(/<.*?>/g,'');
    var formatter = '<div class="ellipsis al-center" style="width: 90%;">{0}</div>'.format(cellValueText);
    return formatter;
  };

    /** 로그 상세페이지 Link */
    var logFormat = function(cellValue, options, rowObject) {
        var logkey = rowObject.logkey;
        var currentPage = $grid.jqGrid('getGridParam', 'page');
        var formatter = '<a href="#" onclick="logInfoDetail('+currentPage+','+rowObject.logKey+')">'+rowObject.memo+'</a>'.format(currentPage, logkey, cellValue);
        return formatter;
    };

    /** 메인배너 상세페이지 Link */
  var mainBannerTitleFormat = function(cellValue, options, rowObject) {
    var bannerPopupKey = rowObject.bannerPopupKey;
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    var formatter      = '<a href="#" onclick="mainBannerDetailUrl({0}, {1})">{2}</a>'.format(currentPage, bannerPopupKey, cellValue);
    return formatter;
  };

  /** 서브배너 상세페이지 Link */
  var subBannerTitleFormat = function(cellValue, options, rowObject) {
    var bannerPopupKey = rowObject.bannerPopupKey;
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    var formatter      = '<a href="#" onclick="subBannerDetailUrl({0}, {1})">{2}</a>'.format(currentPage, bannerPopupKey, cellValue);
    return formatter;
  };

  /** 배너요청 상세페이지 Link */
  var bannerRequestTitleFormat = function(cellValue, options, rowObject) {
    var bannerRequestKey = rowObject.bannerRequestKey;
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    var formatter      = '<a href="#" onclick="bannerRequestDetailUrl({0}, {1})">{2}</a>'.format(currentPage, bannerRequestKey, cellValue);
    return formatter;
  };

  /** 팝업 상세페이지 Link */
  var popupTitleFormat = function(cellValue, options, rowObject) {
    var bannerPopupKey = rowObject.bannerPopupKey;
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    var formatter      = '<a href="#" onclick="popupDetailUrl({0}, {1})">{2}</a>'.format(currentPage, bannerPopupKey, cellValue);
    return formatter;
  };

  /**  보낸쪽지함 사용자 상세 Link */
  var receiveUserDetailFormat = function(cellValue, options, rowObject) {
    var receiveUserKey   = rowObject.receiveUserKey;
    var userKindCode     = rowObject.userKindCode;
    var formatter        = '<a href="#" onclick="userDetailUrl(\'{0}\', \'{1}\')">{2}</a>'.format(receiveUserKey, userKindCode, cellValue);
    return formatter;
  };

  /** 쪽지 상세페이지 Link */
  var messageDetailFormat = function(cellValue, options, rowObject) {
    var messageKey    = rowObject.messageKey;
    var formatter     = '<a href="#" onclick="messageDetailUrl({0})">{1}</a>'.format(messageKey, cellValue);
    return formatter;
  };

  /** Empty Area Format */
  var emptyAreaFormat = function(cellValue, options, rowObject) {
    var formatter;

    if(CommonUtils.isEmpty(cellValue)) {
      formatter  = '-';
    } else {
      formatter = cellValue;
    }
    return formatter;
  };

  /** 삶의흔적 > 회차관리 '마감'버튼 추가 */
  var closeBtnFormat = function(cellValue, options, rowObject) {
    var isClose         = rowObject.isClose;
    var auctionRoundKey = rowObject.auctionRoundKey;
    var btnString       = '마감';
    var formatter      =  null;

    formatter = '<a href="#" class="btn" onclick="OfflineRound.closeRound({0})">{1}</a>'.format(auctionRoundKey, btnString);

    return formatter;
  };

  /** 삶의흔적 > 회차관리 상세페이지 Link */
  var offlineRoundDetailFormat = function(cellValue, options, rowObject) {
    var auctionRoundKey = rowObject.auctionRoundKey;
    var currentPage     = $grid.jqGrid('getGridParam', 'page');
    var formatter       = '<a href="#" onclick="offlineRoundDetailUrl({0}, {1})">{2}</a>'.format(currentPage, auctionRoundKey, cellValue);
    return formatter
  };

  /** 삶의흔적 > 출품자 상세페이지 Link */
  var offlineUserDetailFormat = function(cellValue, options, rowObject) {
    var auctionUserKey  = rowObject.auctionUserKey;
    var formatter       = '<a href="#" onclick="offlineSellerDetailUrl({0})">{1}</a>'.format(auctionUserKey, cellValue);
    return formatter;
  };

  /** 삶의흔적 > 물품 변동내역 > 물품 상세페이지 Link */
  var offlineItemDetailFormat = function(cellValue, options, rowObject) {
    var itemKey         = rowObject.itemKey;
    var auctionRoundKey = rowObject.auctionRoundKey;
    var formatter       = '<a href="#" onclick="offlineItemDetailUrl(\'{0}\',\'{1}\')">{2}</a>'.format(itemKey, auctionRoundKey, cellValue);
    return formatter;
  };

  /** 회차별 출품자 검색 팝업 data input */
  var sellerCodeFormat = function(cellValue, options, rowObject) {
    var userId     = rowObject.userKey;
    var userName   = rowObject.userName;
    var sellerCode = rowObject.sellerCode;
    var formatter  = '<a href="#" onclick="setOfflineSellerInfo(\'{0}\', \'{1}\', \'{2}\')">{3}</a>'.format(userId, userName, sellerCode, cellValue);
    return formatter;
  };

  /** 회차별 물품 검색 팝업 data input */
  var itemNameFormat = function(cellValue, options, rowObject) {
    var itemKey             = rowObject.itemKey;
    var itemName            = rowObject.itemName;
    var offlineAuctionOrder = rowObject.offlineAuctionOrder;
    var strStartMoney       = rowObject.strStartMoney;
    var startMoney          = rowObject.startMoney;
    var formatter           = '<a href="#" onclick="setOfflineItemInfo(\'{0}\', \'{1}\', \'{2}\', \'{3}\', \'{4}\')">{5}</a>'.format(itemKey, itemName, offlineAuctionOrder, strStartMoney, startMoney, cellValue);
    return formatter;
  };

  /** 회차별 비딩번호 검색 팝업 data input */
  var bidNumberFormat = function(cellValue, options, rowObject) {
    var userId     = rowObject.userKey;
    var userName   = rowObject.userName;
    var bidNumber  = rowObject.bidNumber;
    var formatter  = '<a href="#" onclick="setOfflineBidderInfo(\'{0}\', \'{1}\', \'{2}\')">{3}</a>'.format(userId, userName, bidNumber, cellValue);
    return formatter;
  };

  /** 캘린더 리스트 날짜 포맷 */
  var calendarDetailFormat = function(cellValue, options, rowObject) {
    var calendarKey = rowObject.calendarKey;
    var formatter = '<a href="javascript:;" onclick="Calendar.goDetailByGrid(' + calendarKey + ');">' + rowObject.title  + '</a>';
    return formatter;
  };

   /** 고객센터 구매후기 총점수 */
  var totalAssessmentFormat = function(cellValue, options, rowObject) {
    var assessmentItem     = rowObject.assessmentItem;
    var assessmentDelivery = rowObject.assessmentDelivery;
    var assessmentPacking  = rowObject.assessmentPacking;
    var totalAssessment = ((assessmentItem + assessmentDelivery + assessmentPacking)/3).toFixed(1);
    return totalAssessment;
  };

  /** 고객센터 구매후기 이미지 */
  var itemImageFormat = function(cellValue, options, rowObject) {
    var fileName  = rowObject.fileName;
    var filePath  = rowObject.filePath;
    if (CommonUtils.isNotEmpty(rowObject.thumbnailPath)) {
      filePath = rowObject.thumbnailPath;
    }
    var formatter = '<img src="{0}/common/imgView?fileName={1}&filePath={2}" onerror="this.src=\'{0}/resources/images/admin/no-image.png\'" width="30px" height="30px" />'.format(contextPath, fileName, filePath);
    return formatter;
  };

  /** 시크릿경매 공개상태 공개여부 */
  var isPublicFormat = function(cellValue, options, rowObject) {
    return cellValue == 1 ? '공개' : '비공개';
  };

  /** 시크릿경매 낙찰일 여부 */
  var isSuccessfulBidDate = function(cellValue, options, rowObject) {
    return CommonUtils.isEmpty(cellValue) ? '-' : cellValue;
  };

  /** 시크릿경매 판매자 포맷 */
  var sellerFormat = function(cellValue, options, rowObject) {
    return CommonUtils.isEmpty(cellValue) ? '-' : '코베이중개';
  };

  /** 시크릿경매 상세 이동 */
  var goSecretDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="SecretSaleAuction.goDetailByGrid({0}, \'{1}\')">{2}</a>'.format(rowObject.secretSaleAuctionKey, options.colModel.name, cellValue);
  };

  /** target경매관리 상세 이동 */
  var goTargetAuctionDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="TargetAuctionRound.goDetailByGrid({0})">{1}</a>'.format(rowObject.auctionRoundKey, cellValue);
  };

  /** target경매관리 경매일 포맷 */
  var bidDateFormat = function(cellValue, options, rowObject) {
    return cellValue + ' ~ ' + rowObject.roundEndFullDate;
  };

  /** target경매관리 사용여부 포맷 */
  var isUseCodeFormat = function(cellValue, options, rowObject) {
    return cellValue === '0' ? '미사용' : '사용';
  };

  /** target경매관리 판/구매자 리스트로 이동 */
  var goBuySellDetailList = function(cellValue, options, rowObject) {
    var countValue = CommonUtils.isEmpty(cellValue) ? '0' : cellValue;
    return '<a href="javascript:;" onclick="TargetAuctionBuySell.goBuySellDetailListByGrid({0}, {1})">{2}</a>'.format(rowObject.auctionRoundKey, (options.colModel.name === 'sellerCount' ? '1' : '0'), countValue);
  };

  /** 경매방관리 물품게시기간 포맷 */
  var noticeDateFormat = function(cellValue, options, rowObject) {
    return cellValue + ' ~ ' + rowObject.noticeEndDate;
  };

  /** 경매방관리 상세 이동 */
  var goAuctionRoomDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="AuctionRoomRound.goDetailByGrid({0})">{1}</a>'.format(rowObject.auctionRoundKey, cellValue);
  };

  /** 경매방관리 신청자 목록 Link */
  var auctionApplicantListUrl = function(cellValue, options, rowObject) {
    return '<a href="#" onclick="auctionApplicantListUrl({0})">{1}</a>'.format(rowObject.auctionRoundKey, cellValue);
  };

  /** 경매방관리 신청자 상세 Link */
  var auctionApplicantDetailUrl = function(cellValue, options, rowObject) {
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    return '<a href="#" onclick="auctionApplicantDetailUrl({0}, {1})">{2}({3})</a>'.format(currentPage, rowObject.requestEntryKey, cellValue, rowObject.userName);
  };

  /** 경매방관리 상태값 */
  var auctionRoomCloseNameFormat = function(cellValue, options, rowObject) {
    return cellValue == 0 ? '진행중' : (cellValue == 1 ? '종료' : '대기');
  };

  /** 메일스킨 관리 */
  var mailSkinNameFormat = function(cellValue, options, rowObject) {
    var mailSkinKeyParam = rowObject.mailSkinKey;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter = '<div class="ellipsis al-left" style="width: 90%;"><a href="#" onclick="mailSkinDetail({0}, {1})">{2}</a></div>'.format(currentPage, mailSkinKeyParam, cellValue);
    return formatter;
  };
  /** 메일스킨 라디오 */
  var mailSkinRadioFormat = function(cellValue, options, rowObject) {
    var mailSkinKeyParam = rowObject.mailSkinKey;
    var currentPage = $grid.jqGrid('getGridParam', 'page');
    var formatter = '<input type="radio" name="mailSkinSelect" id="mailSkinSelect" value="{0}"/>'.format(cellValue);
    return formatter;
  };

  /** 온라인경매 > 감정의견관리 > 감정의견 팝업 */
  var openAppraisalPop = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="AppraisalManagement.openAppraisalPop({0})">{1}</a>'.format(rowObject.appraisalCommentKey, cellValue);
  };

  /** 온라인경매 > 감정의견관리 > 경매관리로 이동 */
  var goAuctionManagement = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="AppraisalManagement.goAuctionManagement(\'{0}\')">{1}</a>'.format(rowObject.itemKey, cellValue);
  };

  /** 온라인경매 > 관리자의견내역 > 관리자의견내용 팝업 */
  var openItemStaffCommentPop = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="ItemStaffCommentManagement.openItemStaffCommentPop({0})">{1}</a>'.format(rowObject.itemStaffCommentKey, cellValue);
  };

  /** 온라인경매 > 관리자의견내역 : 사용자 물품 상세페이지로 이동 */
  var goOnlineItemDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="ItemStaffCommentManagement.goOnlineItemDetail(\'{0}\')">{1}</a>'.format(rowObject.itemKey, cellValue);
  };

  /** 온라인경매 > 관리자의견내역 > 판매자물품내용추가 페이지 */
  var goItemAddContentsForm = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="ItemAddContentsManagement.goItemAddContentsForm(\'{0}\')">{1}</a>'.format(rowObject.itemAddContentsKey, CommonUtils.isEmpty(cellValue) ? '-' : cellValue);
  };

  /** 온라인경매 > 관리자의견내역 > 추천경매제외자 상세 페이지 */
  var goRecommendExceptUserDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="RecommendExceptUser.goDetailByGrid(\'{0}\')">{1}</a>'.format(rowObject.exceptKey, cellValue);
  };

  /** 온라인경매 > 관리자의견내역 > 추천경매제외자 상태 포맷 */
  var isApplyFormat = function(cellValue, options, rowObject) {
    return cellValue == 1 ? '적용' : '해제';
  };


  /** 출금금지회원관리 금지회원 상세 Link */
  var stopWithdrawDetailUrl = function(cellValue, options, rowObject) {
    var currentPage    = $grid.jqGrid('getGridParam', 'page');
    return '<a href="#" onclick="StopWithdrawManagement.stopWithdrawDetail({0}, {1})">{2}</a>'.format(currentPage, rowObject.stopWithdrawKey, cellValue);
  };

  /** 매매보호서비스 > 배송내역확인 > 물품 상세페이지 이동 */
  var goAuctionDetail = function(cellValue, options, rowObject) {
    return '<a href="javascript:;" onclick="DeliveryUtils.goAuctionDetails(\'{0}\')">{1}</a>'.format(rowObject.itemKey, rowObject.itemKey);
  };

  /** 컨텐츠 상세페이지 */
  var contentsDetailUrl = function(cellValue, options, rowObject) {
    var itemKey       = rowObject.itemKey;
    var auctionTypeEn = rowObject.auctionTypeEn;
    var locationUrl   = contextPath + '/' + auctionTypeEn + '/detail?itemKey=' + itemKey;
    var formatter  = '<a href="' + locationUrl + '" target="_blank">' + cellValue + '</a>';
    return formatter;
  };

  /**
   * date formatter
   * @param cellValue
   * @param options
   * @returns {string}
   */
  var dateFormat = function(cellValue, options) {
    var dateFormat = options.colModel.formatoptions.dateFormat;
    var printDate = '-';
    if (CommonUtils.isNotEmpty(cellValue)) {
      printDate = $.format.date(cellValue, dateFormat);
    }
    return printDate
  };

  /**
   * date period formatter
   * @param cellValue
   * @param options
   * @param rowObject
   * @returns {string}
   */
  var datePeriodFormat = function(cellValue, options, rowObject) {
    var dateFormat = options.colModel.formatoptions.dateFormat;
    var startDate = options.colModel.formatoptions.startDate || 'startDate';
    var endDate = options.colModel.formatoptions.endDate || 'endDate';
    var period = '-';
    if (CommonUtils.isNotEmpty(rowObject[startDate]) && CommonUtils.isNotEmpty(rowObject[endDate])) {
      period = $.format.date(rowObject[startDate], dateFormat) + '~<br>' + $.format.date(rowObject[endDate], dateFormat);
    }
    return period;
  };

  /*var numberFormat = function(cellValue) {
    return CommonUtils.printWon(cellValue);
  };*/

  var wonFormat = function(cellValue) {
    Number(cellValue);
    return CommonUtils.printWon(cellValue);
  };

  var pointerFormat = function(cellValue) {
    return '<span class="pointer">' + cellValue + '</span>';
  };

  var pointerNameFormat = function(cellvalue, options, rowObject) {
    return '<span class="pointer">' + nameFormat(cellvalue, options, rowObject) + '</span>';
  };

  var pointerPrintWonFormat = function(cellValue) {
//    CommonUtils.printWon(Number(cellValue));
    return '<span class="pointer">' + cellValue + '</span>';
  };

  /** 숫자 빈값(0) 포맷 */
  var numberEmptyFormat = function(cellValue) {
    return cellValue == 0 ? '-' : cellValue;
  };

  var pointerAndEmptyFormat = function(cellValue) {
    var formatter;

    if(CommonUtils.isEmpty(cellValue)) {
      formatter  = '-';
    } else {
      formatter = '<span class="pointer">' + cellValue + '</span>';
    }

    return formatter;
  };

  /**
   * 판매자/구매자 포맷
   * option
   *  type ; seller / buyer
   *  isPointer : true / false
   *  lineFeed : true / false
   *  clickEvent
   */
  var auctionUserIdAndName = function(cellValue, options, rowObject) {
    var type = options.colModel.formatoptions.type;
    var isPointer = options.colModel.formatoptions.isPointer;
    var lineFeed = options.colModel.formatoptions.lineFeed;
    var clickEvent = options.colModel.formatoptions.clickEvent;
    var userId = '';
    var userName = '';

    if ( type == 'seller' ) {
      userId = rowObject.sellerKey;
      userName = rowObject.sellerName;
    }
    else if ( type == 'buyer' ) {
      userId = rowObject.buyerKey;
      userName = rowObject.buyerName;
    }
    else if ( type == 'judger' ) {
      userId = rowObject.judgerKey;
      userName = rowObject.judgerName;
    }
    else if ( type == 'user' ) {
      userId = rowObject.userId;
      userName = rowObject.userName;
    }

    if (CommonUtils.isEmpty(userId)) {
      return '-';
    }

    var info = userId;
    info += lineFeed == true ? '<br/>' : '';
    info +='(' + userName + ')';

    if ( isPointer ) {
      info = JqGridFormatter.pointer(info);
    }

    if ( clickEvent == 'userDetail' ) {
      info = '<a href="' + contextPath + '/admin/userManagement/userDetail?userId=' + userId + '" target="_blank">' + info + '</a>';
    }

    return info;
  };

  return {
    rowNumberFormat             : rowNumberFormat,
    boardTitleFormat            : boardTitleFormat,
    writerKeyFormat             : writerKeyFormat,
    contentsFormat              : contentsFormat,
    logFormat                   : logFormat,
    mainBannerTitleFormat       : mainBannerTitleFormat,
    subBannerTitleFormat        : subBannerTitleFormat,
    bannerRequestTitleFormat    : bannerRequestTitleFormat,
    popupTitleFormat            : popupTitleFormat,
    receiveUserDetailFormat     : receiveUserDetailFormat,
    messageDetailFormat         : messageDetailFormat,
    isOpenFormat                : isOpenFormat,
    commentStateFormat          : commentStateFormat,
    closeBtnFormat              : closeBtnFormat,
    calendarDetailFormat        : calendarDetailFormat,
    commentWriterKeyFormat      : commentWriterKeyFormat,
    inquiryTitleFormat          : inquiryTitleFormat,
    offlineRoundDetailFormat    : offlineRoundDetailFormat,
    offlineUserDetailFormat     : offlineUserDetailFormat,
    offlineItemDetailFormat     : offlineItemDetailFormat,
    sellerCodeFormat            : sellerCodeFormat,
    bidNumberFormat             : bidNumberFormat,
    totalAssessmentFormat       : totalAssessmentFormat,
    itemImageFormat             : itemImageFormat,
    intranetBoardAllTitleFormat : intranetBoardAllTitleFormat,
    nameFormat                  : nameFormat,
    isPublicFormat              : isPublicFormat,
    isSuccessfulBidDate         : isSuccessfulBidDate,
    sellerFormat                : sellerFormat,
    goSecretDetail              : goSecretDetail,
    goTargetAuctionDetail       : goTargetAuctionDetail,
    bidDateFormat               : bidDateFormat,
    isUseCodeFormat             : isUseCodeFormat,
    goBuySellDetailList         : goBuySellDetailList,
    noticeDateFormat            : noticeDateFormat,
    goAuctionRoomDetail         : goAuctionRoomDetail,
    auctionRoomCloseNameFormat  : auctionRoomCloseNameFormat,
    openAppraisalPop            : openAppraisalPop,
    goAuctionManagement         : goAuctionManagement,
    goDetailNameFormat          : goDetailNameFormat,
    mailSkinNameFormat          : mailSkinNameFormat,
    mailSkinRadioFormat         : mailSkinRadioFormat,
    emptyAreaFormat             : emptyAreaFormat,
    openItemStaffCommentPop     : openItemStaffCommentPop,
    goRecommendExceptUserDetail : goRecommendExceptUserDetail,
    isApplyFormat               : isApplyFormat,
    contentsDetailUrl           : contentsDetailUrl,
    goItemAddContentsForm       : goItemAddContentsForm,
    threeDepthFormat            : threeDepthFormat,
    twoDepthFormat              : twoDepthFormat,
    oneDepthFormat              : oneDepthFormat,
    datePeriod                  : datePeriodFormat,
    date                        : dateFormat,
    //number                      : numberFormat,
    won                         : wonFormat,
    pointer                     : pointerFormat,
    goOnlineItemDetail          : goOnlineItemDetail,
    numberEmptyFormat           : numberEmptyFormat,
    //myPageMenuDateFormat        : myPageMenuDateFormat
    itemNameFormat              : itemNameFormat,
    pointerAndEmptyFormat       : pointerAndEmptyFormat,
    auctionUserIdAndName        : auctionUserIdAndName,
    auctionApplicantListUrl     : auctionApplicantListUrl,
    auctionApplicantDetailUrl   : auctionApplicantDetailUrl,
    pointerPrintWonFormat       : pointerPrintWonFormat,
    stopWithdrawDetailUrl       : stopWithdrawDetailUrl,
    pointerNameFormat           : pointerNameFormat,
    goAuctionDetail             : goAuctionDetail
  }
})(window.JqGridFormatter || {}, jQuery);

window.JqGridOptions = (function(JqGridOptions, $, undefined) {

  var commonOption = {
    datatype : 'json',
    mtype : 'POST',
    jsonReader : {
      root : 'dataList',
      total : 'pageTotalCount',
      page : 'currentPage',
      records : 'dataSize',
      userData : 'userData'
    },
    loadComplete : loadFn,
    loadError : errorFn,
    sortable : false,
    pager : '#pager',
    pagerpos : 'center',
    pgbuttons : true,
    pginput : true,
    hoverrows : false,
    rowNum : '10',
    height : '452px',
    viewrecords : true,
    recordtext : 'View [{0}] - [{1}] of [{2}]',
    emptyrecords : '해당 데이터가 존재하지 않습니다.'
  };

  /**
   * JqGridOption 설정 초기화
   * 필수 옵션 { url, colNames, colModel }
   * 이외 필요 옵션은 오버라이딩 및 추가 하여 사용
   * @param optionParam : {}
   * @returns {{}}}
   */
  var initOption = function(optionParam) {
    alert("aa");
    console.log(optionParam);
    optionParam = optionParam || {};
    return $.extend(true, commonOption, optionParam);
  };

  var boardOptions = {
     colNames:[ '번호', '제목', '등록자', '등록일' ],
     colModel:[
         { name : 'rowNum',   width : 70,  align : 'center', sortable : false, formatter : JqGridFormatter.rowNumberFormat },
         { name : 'title',         width : 120, align : 'left',   sortable : false, formatter : JqGridFormatter.boardTitleFormat },
         { name : 'writerKey',     width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.writerKeyFormat },
         { name : 'insertDate',         width : 60, align : 'center', sortable : true, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} }
     ]
  };

  var mainBannerOptions = {
    colNames : [ 'rowNum', 'bannerPopupKey', '구분', '제목', '게시기간', '등록자', '등록일' ],
    colModel : [
      { name : 'rowNum'         ,  width : 70 ,  align : 'center' ,  hidden    : true      , sortable : false  },
      { name : 'bannerPopupKey' ,  width : 70 ,  align : 'center' ,  hidden    : true      , sortable : false  },
      { name : 'categoryName'   ,  width : 90 ,  align : 'center' ,  sortable  : false    },
      { name : 'title'          ,  width : 200,  align : 'center' ,  formatter : JqGridFormatter.mainBannerTitleFormat  },
      { name : 'expirationDate' ,  width : 280,  align : 'center'},
      { name : 'writerNameAndId',  width : 100,  align : 'center'},
      { name : 'strInsertDate'  ,  width : 140,  align : 'center'}
    ]
  };

  var subBannerOptions = {
    colNames : [ 'rowNum', 'bannerPopupKey', '구분', '제목', '게시기간', '등록자', '등록일' ],
    colModel : [
      { name : 'rowNum'         ,  width : 70 ,  align : 'center' ,  hidden    : true    ,  sortable : false   },
      { name : 'bannerPopupKey' ,  width : 30 ,  align : 'center' ,  sortable  : false   ,  hidden   : true    },
      { name : 'categoryName'   ,  width : 90 ,  align : 'center' ,  sortable  : false  },
      { name : 'title'          ,  width : 200,  align : 'center' ,  formatter : JqGridFormatter.subBannerTitleFormat  },
      { name : 'expirationDate' ,  width : 280,  align : 'center'},
      { name : 'writerNameAndId',  width : 100,  align : 'center'},
      { name : 'strInsertDate'  ,  width : 140,  align : 'center'}
    ]
  };

  var onlineItemListPopupOptions = {
    colNames : [ 'rowNum', '물품번호', '물품명', '시작가', '판매자', 'expirationDate'],
    colModel : [
      { name : 'rowNum'          ,  hidden: true, formatter : JqGridFormatter.rowNumberFormat },
      { name : 'itemKey'         ,  width : 100,  align : 'center' ,  sortable  : false  },
      { name : 'itemName'        ,  width : 120,  align : 'center' ,  sortable  : false  },
      { name : 'startMoney'      ,  width : 80 ,  align : 'center'},
      { name : 'sellerNameAndId' ,  width : 100,  align : 'center'},
      { name : 'expirationDate'  ,  hidden    : true }

    ]
  };

  var bannerRequestOptions = {
    colNames : [ '번호', 'bannerRequestKey', '배너명', '등록자', '등록일' ],
    colModel : [
      { name : 'rowNum'           ,  width : 70 ,  align : 'center' , formatter : JqGridFormatter.rowNumberFormat,  sortable : false  },
      { name : 'bannerRequestKey' ,  width : 70 ,  align : 'center' , hidden    : true     ,  sortable : false  },
      { name : 'title'            ,  width : 200,  align : 'center' , formatter : JqGridFormatter.bannerRequestTitleFormat },
      { name : 'writerNameAndId'  ,  width : 100,  align : 'center'},
      { name : 'strInsertDate'    ,  width : 140,  align : 'center'}
    ]
  };

  var popupOptions = {
    colNames : [ 'rowNum', 'bannerPopupKey', '제목', '게시기간', '등록자', '등록일', '사용여부' ],
    colModel : [
      { name : 'rowNum'         , width : 70 ,  align : 'center' , formatter : JqGridFormatter.rowNumberFormat,  hidden   : true  ,  sortable : false  },
      { name : 'bannerPopupKey' , width : 30 ,  align : 'center' , hidden    : true     ,  sortable : false},
      { name : 'title'          , width : 200,  align : 'center' , formatter : JqGridFormatter.popupTitleFormat },
      { name : 'expirationDate' , width : 280,  align : 'center'},
      { name : 'writerNameAndId', width : 100,  align : 'center'},
      { name : 'strInsertDate'  , width : 140,  align : 'center'},
      { name : 'popupUseType'   , width : 90 ,  align : 'center' , sortable  : false      }
    ]
  };

  var sendMessageOptions = {
    colNames : [ 'messageKey', '번호', '받는사람', '보낸사람', '쪽지내용', '보낸시각', 'receiveUserKey', 'userKindCode', 'senderCode' ],
    colModel : [
      { name : 'messageKey'       , hidden  : true , sortable : false  },
      { name : 'rowNum'           , width: 70 , align: 'center' , sortable: false, formatter: JqGridFormatter.rowNumberFormat},
      { name : 'receiverNameAndId', width: 100, align: 'center' , sortable: false, formatter: JqGridFormatter.receiveUserDetailFormat},
      { name : 'sender'           , width: 150, align: 'center'},
      { name : 'contents'         , width: 320, align: 'center' , formatter: JqGridFormatter.messageDetailFormat },
      { name : 'strSendDate'      , width: 140, align: 'center'},
      { name : 'receiveUserKey'   , width: 70 , align: 'center' , hidden  : true , sortable : false  },
      { name : 'userKindCode'     , width: 70 , align: 'center' , hidden  : true , sortable : false  },
      { name : 'senderCode'       , width: 70 , align: 'center' , hidden  : true , sortable : false  }
    ]
  };

  var itemReviewOptions = {
    colNames : [ '번호', '카테고리', '총점수', '이미지', '물품번호', '물품명', '작성자', '등록일', 'itemReviewKey'],
    colModel : [
      { name : 'rowNum'              ,  width : 70 ,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.rowNumberFormat},
      { name : 'auctionTypeName'     ,  width : 80,  align : 'center' ,  sortable  : false },
      { name : 'totalAssessment' ,  width : 70,  align : 'center' ,  sortable  : false, formatter : JqGridFormatter.totalAssessmentFormat},
      { name : 'itemImage'           ,  width : 100,  align : 'center' ,  sortable  : false, formatter : JqGridFormatter.itemImageFormat },
      { name : 'itemKey'             ,  width : 120,  align : 'center' ,  sortable  : false },
      { name : 'itemName'            ,  width : 140 ,  align : 'center' ,  sortable  : false, formatter : JqGridFormatter.pointer },
      { name : 'writerName'          ,  width : 70 ,  align : 'center' ,  sortable  : false },
      { name : 'strInsertDate'       ,  width : 90 ,  align : 'center' ,  sortable  : false},
      { name : 'itemReviewKey', hidden : true}
    ]
  };

  var calendarOptions = {
    colNames : [ '번호', '구분', '제목', '날짜', '등록자', 'calendarKey' ],
    colModel : [
      { name : 'calendarNum'    ,  width : 70 , align : 'center', sortable : false },
      { name : 'typeCodeName'   ,  width : 100, align : 'center', sortable : false },
      { name : 'title'          ,  width : 200, align : 'center', sortable : false, formatter : JqGridFormatter.calendarDetailFormat },
      { name : 'calendarDate'   ,  width : 280, align : 'center', sortable : false },
      { name : 'userName'       ,  width : 100, align : 'center', sortable : false },
      { name : 'calendarKey'    ,  width : 100, align : 'center', sortable : false, hidden : true }
    ]
  };

  var itemRegisterOptions = {
    colNames : [ '번호', '회원명', '등록자', '등록일', 'itemRegisterKey' ],
    colModel : [
      { name : 'itemRegisterNum',  width : 70 , align : 'center', sortable : false },
      { name : 'userName'       ,  width : 200, align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'writerName'     ,  width : 200, align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'insertDate'     ,  width : 200, align : 'center', sortable : false },
      { name : 'itemRegisterKey',  width : 100, align : 'center', sortable : false, hidden : true }
    ]
  };

  var offlineSellerOptions = {
    colNames : [ 'rowNum', 'auctionUserKey', '판매자코드', '출품자명', '이메일', '주소', '연락처', '출품갯수', '등록수', '미등록수', '반출수' ],
    colModel : [
      { name : 'rowNum'              ,  hidden  : true , formatter : JqGridFormatter.rowNumberFormat},
      { name : 'auctionUserKey'      ,  width : 70 ,  align : 'center' ,  sortable  : false  ,  hidden  : true},
      { name : 'sellerCode'          ,  width : 70 ,  align : 'center' ,  sortable  : true   ,  formatter : JqGridFormatter.offlineUserDetailFormat, index : 'au.SELLER_CODE'},
      { name : 'userNameAndId'       ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'email'               ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'fullAddress'         ,  width : 220,  align : 'center' ,  sortable  : false },
      { name : 'cellPhone'           ,  width : 140,  align : 'center' ,  sortable  : false },
      { name : 'totalItemCount'      ,  width : 40 ,  align : 'center' ,  sortable  : false },
      { name : 'enrolledItemCount'   ,  width : 40 ,  align : 'center' ,  sortable  : false },
      { name : 'unEnrolledItemCount' ,  width : 40 ,  align : 'center' ,  sortable  : false },
      { name : 'exportItemCount'     ,  width : 40 ,  align : 'center' ,  sortable  : false }
    ]
  };

  var offlineRoundOptions = {
    colNames : [ 'rowNum', 'auctionRoundKey', '회차', '회차명', '경매시작일', '등록자', '사용여부', '등록일', '마감' ],
    colModel : [
      { name : 'rowNum'            ,  hidden  : true , formatter : JqGridFormatter.rowNumberFormat},
      { name : 'auctionRoundKey'   ,  width : 70 ,  align : 'center' ,  sortable  : false  , hidden  : true},
      { name : 'round'             ,  width : 70 ,  align : 'center' ,  sortable  : true   , index : 'ar.ROUND'},
      { name : 'roundName'         ,  width : 140,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.offlineRoundDetailFormat},
      { name : 'strStartDate'      ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'writerNameAndId'   ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'isUseType'         ,  width : 70 ,  align : 'center' ,  sortable  : false },
      { name : 'strInsertDate'     ,  width : 120,  align : 'center' ,  sortable  : false },
      { name : 'isClose'           ,  width : 70 ,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.closeBtnFormat}
    ]
  };

  var offlineItemEditLogOptions = {
    colNames : [ 'auctionRoundKey', '경매순번', '물품번호', '물품명', '변동내역', '등록자', '등록일' ],
    colModel : [
      { name : 'auctionRoundKey'  ,  width : 70  ,  align : 'center' ,  sortable  : false  , hidden  : true},
      { name : 'auctionOrder'     ,  width : 100 ,  align : 'center' ,  sortable  : false },
      { name : 'itemKey'          ,  width : 150 ,  align : 'center' ,  sortable  : false , formatter : JqGridFormatter.offlineItemDetailFormat},
      { name : 'itemName'         ,  width : 200 ,  align : 'center' ,  sortable  : false },
      { name : 'note'             ,  width : 150 ,  align : 'center' ,  sortable  : false },
      { name : 'writerNameAndId'  ,  width : 150 ,  align : 'center' ,  sortable  : false },
      { name : 'strInsertDate'    ,  width : 150 ,  align : 'center' ,  sortable  : false }
    ]
  };

  var offlineItemPopupListOptions = {
    colNames : [ '경매순서', '물품명', '시작금액', 'itemKey', 'startMoney' ],
    colModel : [
      { name : 'offlineAuctionOrder' ,  width   : 40 ,  align : 'center' ,  sortable  : false },
      { name : 'itemName'            ,  width   : 150,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.itemNameFormat},
      { name : 'strStartMoney'       ,  width   : 80 ,  align : 'center' ,  sortable  : false },
      { name : 'itemKey'             ,  hidden  : true },
      { name : 'startMoney'          ,  hidden  : true }
    ]
  };

  var sellerPopupOptions = {
    colNames : [ '번호', '출품자코드', '출품자', 'userKey' ],
    colModel : [
      { name : 'rowNum'          ,  width   : 40 ,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.rowNumberFormat },
      { name : 'sellerCode'      ,  width   : 80 ,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.sellerCodeFormat},
      { name : 'userNameAndId'   ,  width   : 80 ,  align : 'center' ,  sortable  : false },
      { name : 'userKey'         ,  hidden  : true }
    ]
  };

  var bidderPopupOptions = {
    colNames : [ '비딩번호', '회원명', 'userKey' ],
    colModel : [
      { name : 'bidNumber'       ,  width   : 80 ,  align : 'center' ,  sortable  : false  , formatter : JqGridFormatter.bidNumberFormat},
      { name : 'userNameAndId'   ,  width   : 80 ,  align : 'center' ,  sortable  : false },
      { name : 'userKey'         ,  hidden  : true }
    ]
  };

  var offlineBidNumberOptions = {
    colNames : [ 'rowNum', 'offlineBiddingNumberKey', '비딩번호', '회원명', '등록자', '등록일' ],
    colModel : [
      { name : 'rowNum'                  ,  hidden  : true , formatter : JqGridFormatter.rowNumberFormat},
      { name : 'offlineBiddingNumberKey' ,  width : 70 ,  align : 'center' ,  sortable  : false  ,  hidden  : true},
      { name : 'bidNumber'               ,  width : 70 ,  align : 'center' ,  sortable  : false },
      { name : 'userNameAndId'           ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'writerNameAndId'         ,  width : 100,  align : 'center' ,  sortable  : false },
      { name : 'strInsertDate'           ,  width : 120,  align : 'center' ,  sortable  : false }
    ]
  };

  var inquiryOptions = {
    colNames:[ '번호', '종류', '물품번호', '제목', '공개여부', '등록자', '답변상태', '답변자', '등록일', '처리일', 'commentWriterName', 'userName' ],
    colModel:[
      { name : 'rowNum',                    width : 100, align : 'center', formatter : JqGridFormatter.rowNumberFormat, sortable : false },
      { name : 'categoryCodeName',          width : 100, align : 'center', sortable : false },
      { name : 'itemKey',                   width: 100, align: 'center', sortable : false },
      { name : 'title',                     width : 120, align : 'left', sortable : false, formatter : JqGridFormatter.inquiryTitleFormat },
      { name : 'isOpen',                    width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.isOpenFormat },
      { name : 'writerKey',                 width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.writerKeyFormat },
      { name : 'boardComment.commentState', width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.commentStateFormat },
      { name : 'boardComment.writerKey',    width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.commentWriterKeyFormat },
      { name : 'insertDate',                width : 60, align : 'center', sortable : true, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} },
      { name : 'boardComment.insertDate',   width : 60, align : 'center', sortable : true, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} },
      { name : 'boardComment.writerName',   hidden : true},
      { name : 'userName',                  hidden : true }
    ]
  };

  var secretSaleAuctionOptions = {
    colNames : [ '번호', '이미지', '물품명', '입찰수', '낙찰자', '판매자', '입찰마감일시', '경매상태', '공개상태', '낙찰일', '등록일', 'fileName', 'filePath' ],
    colModel : [
      { name : 'rowNum',                  width : 50,   align : 'center', sortable : false, formatter : JqGridFormatter.rowNumberFormat },
      { name : 'fileName',                width : 100,  align : 'center', sortable : false, formatter : JqGridFormatter.itemImageFormat },
      { name : 'productName',             width : 150,  align : 'center', sortable : false, formatter : JqGridFormatter.goSecretDetail },
      { name : 'bidCount',                width : 50,   align : 'center', sortable : false, formatter : JqGridFormatter.goSecretDetail },
      { name : 'successfulBidderName',    width : 100,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'sellerName',              width : 100,  align : 'center', sortable : false, formatter : JqGridFormatter.sellerFormat },
      { name : 'bidCloseFullDate',        width : 130,  align : 'center', sortable : false },
      { name : 'secretSaleTypeCodeName',  width : 60,   align : 'center', sortable : false },
      { name : 'isPublic',                width : 60,   align : 'center', sortable : false, formatter : JqGridFormatter.isPublicFormat },
      { name : 'successfulBidDate',       width : 80,   align : 'center', sortable : false, formatter : JqGridFormatter.isSuccessfulBidDate },
      { name : 'insertDate',              width : 80,   align : 'center', sortable : false },
      { name : 'fileName', hidden : true },
      { name : 'filePath', hidden : true }
    ]
  };

  var targetAuctionRoundOptions = {
    colNames : [ '회차', '회차명', '경매일', '등록자', '사용여부', '등록일' ],
    colModel : [
      { name : 'round',               width : 50,  align : 'center', sortable : false },
      { name : 'roundName',           width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.goTargetAuctionDetail },
      { name : 'roundStartFullDate',  width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.bidDateFormat },
      { name : 'writerName',          width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'isUseCode',           width : 50,  align : 'center', sortable : false, formatter : JqGridFormatter.isUseCodeFormat },
      { name : 'strInsertDate',       width : 100, align : 'center', sortable : false }
    ]
  };

  var targetAuctionBuySellOptions = {
    colNames : [ '회차', '회차명', '경매일', '판매자수', '구매자수' ],
    colModel : [
      { name : 'round',               width : 50,  align : 'center', sortable : false },
      { name : 'roundName',           width : 130, align : 'center', sortable : false },
      { name : 'roundStartFullDate',  width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.bidDateFormat },
      { name : 'sellerCount',         width : 70,  align : 'center', sortable : false, formatter : JqGridFormatter.goBuySellDetailList },
      { name : 'buyerCount',          width : 70,  align : 'center', sortable : false, formatter : JqGridFormatter.goBuySellDetailList }
    ]
  };

  var targetAuctionBuySellDetailOptions = {
    colNames : [ '번호', '회원명', '등록자', '등록일' ],
    colModel : [
      { name : 'rowNum',        width : 50,  align : 'center', sortable : false },
      { name : 'userName',      width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.goDetailNameFormat },
      { name : 'writerName',    width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'strInsertDate', width : 70,  align : 'center', sortable : false }
    ]
  };

  var auctionRoomRoundOptions = {
    colNames : [ '회차', '회차명', '경매일', '물품게시기간', '등록자', '상태', '등록일' ],
    colModel : [
      { name : 'round',               width : 50,  align : 'center', sortable : false },
      { name : 'roundName',           width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.goAuctionRoomDetail },
      { name : 'roundStartFullDate',  width : 130, align : 'center', sortable : false },
      { name : 'noticeStartDate',     width : 130, align : 'center', sortable : false, formatter : JqGridFormatter.noticeDateFormat },
      { name : 'writerName',          width : 100, align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'isClose',             width : 50,  align : 'center', sortable : false, formatter : JqGridFormatter.auctionRoomCloseNameFormat },
      { name : 'strInsertDate',       width : 100, align : 'center', sortable : false }
    ]
  };

  var mailSkinOptions = {
    colNames:[ '발송팀', /*'분류',*/ '구분', '스킨명', '발송시기', '등록자', '등록일', 'mailSkinKey' ],
    colModel:[
      { name : 'sendDepartmentName', width : 70,  align : 'center', sortable : false },
      /*{ name : 'mailSkinKindName',   width : 80,  align : 'center', sortable : false },*/
      { name : 'mailSkinType',       width : 80, align : 'center',   sortable : false  },
      { name : 'mailSkinName',       width : 120, align : 'left', sortable : false, formatter : JqGridFormatter.mailSkinNameFormat },
      { name : 'sendPurpose',        width : 80, align : 'left', sortable : false },
      { name : 'writerKey',          width : 60, align : 'center', sortable : false, formatter : JqGridFormatter.writerKeyFormat },
      { name : 'strInsertDate',      width : 60, align : 'center', sortable : false },
      { name : 'mailSkinKey',        hidden : true }
    ]
  };

  var appraisalManagementOptions = {
    colNames : [ '번호', '물품번호', '물품명', '판매자', '입찰', '감정의견내용', '경매상태', '감정회원', '의견등록일', '판매자 아이디' ],
    colModel : [
      { name : 'rowNum',              width : 50,  align : 'center', sortable : false },
      { name : 'itemKey',             width : 70,  align : 'center', sortable : false },
      { name : 'itemName',            width : 200, align : 'left', sortable : false, formatter : JqGridFormatter.goAuctionManagement },
      { name : 'writerName',          width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.pointerNameFormat },
      { name : 'bidCount',            width : 50,  align : 'center', sortable : false },
      { name : 'appraisalComment',    width : 200, align : 'left', sortable : false, formatter : JqGridFormatter.openAppraisalPop },
      { name : 'stateName',           width : 60,  align : 'center', sortable : false },
      { name : 'appraisalWriterName', width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'appraisalDate',       width : 60,  align : 'center', sortable : false },
      { name : 'writerKey', hidden : true}
    ]
  };

  var itemStaffCommentManagementOptions = {
    colNames : [ '번호', '물품번호', '물품명', '의견내용', '판매자', '작성자', '상태', '의견등록일' ],
    colModel : [
      { name : 'rowNum',                     width : 50,  align : 'center', sortable : false },
      { name : 'itemKey',                    width : 70,  align : 'center', sortable : false },
      { name : 'itemName',                   width : 200, align : 'left', sortable : false, formatter : JqGridFormatter.goOnlineItemDetail },
      { name : 'itemStaffComment',           width : 200, sortable : false, formatter : JqGridFormatter.openItemStaffCommentPop },
      { name : 'writerName',                 width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'itemStaffCommentWriterName', width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'stateName',                  width : 60,  align : 'center', sortable : false },
      { name : 'itemStaffCommentInsertDate', width : 60,  align : 'center', sortable : false }
    ]
  };

  var itemAddContentsManagementOptions = {
    colNames : [ '번호', '물품번호', '물품명', '추가내용', '판매자', '상태', '등록일' ],
    colModel : [
      { name : 'rowNum',          width : 50,  align : 'center', sortable : false },
      { name : 'itemKey',         width : 70,  align : 'center', sortable : false },
      { name : 'itemName',        width : 190, align : 'left', sortable : false },
      { name : 'itemAddContents', width : 190, sortable : false, formatter : JqGridFormatter.goItemAddContentsForm },
      { name : 'writerName',      width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'stateName',       width : 80,  align : 'center', sortable : false },
      { name : 'insertDateStr',   width : 60,  align : 'center', sortable : false }
    ]
  };

  var auctionRequestOptions = {
    colNames : [ '회차', '회차명', '경매일시', '참여신청자수' ],
    colModel : [
      { name : 'round',               width : 50,  align : 'center', sortable  : false },
      { name : 'roundName',           width : 150, align : 'center', sortable : false, formatter : JqGridFormatter.auctionApplicantListUrl },
      { name : 'startDate',  width : 150, align : 'center', sortable : true, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} },
      { name : 'requesterCount',      width : 100, align : 'center', sortable : false }
    ]
  };

  var auctionApplicantOptions = {
    colNames : [ '번호', 'requestEntryKey', '참여신청자', '입찰가능물품수', '총입찰가능액', '상태', '신청일', '신청자명' ],
    colModel : [
      { name : 'rowNum',               width : 50,  align : 'center', sortable  : false , formatter : JqGridFormatter.rowNumberFormat },
      { name : 'requestEntryKey' ,  width : 70 ,  align : 'center' ,  hidden    : true      , sortable : false  },
      { name : 'writerKey',           width : 150, align : 'center', sortable : false, formatter : JqGridFormatter.auctionApplicantDetailUrl },
      { name : 'itemCount',      width : 100, align : 'center', sortable : false },
      { name : 'totPay',  width : 150, align : 'center', sortable : true, formatter : numberFommat },
      { name : 'stateCodeName',      width : 100, align : 'center', sortable : false },
      { name : 'insertDate',      width : 100, align : 'center', sortable : false, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} },
      { name : 'userName', hidden: true}
    ]
  };

  var myPageMenuOptions = {
    colNames : [ '메뉴', '1depth', '2depth', '3depth', '변경자', '최근변경일' ],
    colModel : [
      { name : 'menu',               width : 50,  align : 'center', sortable : false },
      { name : 'oneDepth',           width : 150, align : 'center', sortable : false, formatter : JqGridFormatter.oneDepthFormat },
      { name : 'twoDepth',           width : 150, align : 'center', sortable : false, formatter : JqGridFormatter.twoDepthFormat},
      { name : 'threeDepth',         width : 150, align : 'center', sortable : false, formatter : JqGridFormatter.threeDepthFormat },
      { name : 'editorKey',          width : 60, align : 'center', sortable : false, formatter : JqGridFormatter.writerKeyFormat },
      { name : 'updateDate',         width : 60, align : 'center', sortable : true, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'} }
    ]
  };

  var recommendExceptUserOptions = {
    colNames : [ 'exceptKey', '번호', '회원명', '내용', '등록자', '상태', '등록일' ],
    colModel : [
      { name : 'exceptKey',      hidden : true },
      { name : 'rowNum',         width : 50,  align : 'center', sortable : false },
      { name : 'exceptUserName', width : 70,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'reason',         width : 190, sortable : false, formatter : JqGridFormatter.goRecommendExceptUserDetail },
      { name : 'writerName',     width : 90,  align : 'center', sortable : false, formatter : JqGridFormatter.nameFormat },
      { name : 'isApply',        width : 80,  align : 'center', sortable : false, formatter : JqGridFormatter.isApplyFormat },
      { name : 'insertDate',     width : 60,  align : 'center', sortable : false }
    ]
  };

  var withdrawOptions = {
    colNames : [
      '결제번호', '금액', '상태', '출금구분', '출금은행',
      '계좌번호', '예금주', '사용자', '등록일', '완료일'
    ],
    colModel : [
      { name: 'payKey',             width: 100, align: 'center', sortable: false},
      { name: 'withdrawAmount',            width: 80,  align: 'center', sortable: false, formatter: JqGridFormatter.pointerPrintWonFormat },
      { name: 'stateCodeName',            width: 80,  align: 'center', sortable: false },
      { name: 'outTypeCodeName',      width: 100, align: 'center', sortable: false},
      { name: 'bankCodeName', width: 70,  align: 'center', sortable: false},
      { name: 'accountNumber', width: 70, align: 'center', sortable: false},
      { name: 'accountHolder',       width: 70,  align: 'center', sortable: false},
      { name: 'writerKey',    width: 70,  align: 'center', sortable: false},
      { name: 'insertDate',        width: 70,  align: 'center', sortable: false, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'}},
      { name: 'completeDate',      width: 70,  align: 'center', sortable: false, formatter: JqGridFormatter.date, formatoptions: {dateFormat : 'yyyy.MM.dd'}}

    ]
  };


  return {
    init                              : initOption,
    boardOptions                      : boardOptions,
    mainBannerOptions                 : mainBannerOptions,
    subBannerOptions                  : subBannerOptions,
    popupOptions                      : popupOptions,
    sendMessageOptions                : sendMessageOptions,
    onlineItemListPopupOptions        : onlineItemListPopupOptions,
    itemReviewOptions                 : itemReviewOptions,
    offlineRoundOptions               : offlineRoundOptions,
    offlineBidNumberOptions           : offlineBidNumberOptions,
    offlineItemEditLogOptions         : offlineItemEditLogOptions,
    bannerRequestOptions              : bannerRequestOptions,
    calendarOptions                   : calendarOptions,
    offlineSellerOptions              : offlineSellerOptions,
    secretSaleAuctionOptions          : secretSaleAuctionOptions,
    itemRegisterOptions               : itemRegisterOptions,
    inquiryOptions                    : inquiryOptions,
    sellerPopupOptions                : sellerPopupOptions,
    bidderPopupOptions                : bidderPopupOptions,
    targetAuctionRoundOptions         : targetAuctionRoundOptions,
    targetAuctionBuySellOptions       : targetAuctionBuySellOptions,
    targetAuctionBuySellDetailOptions : targetAuctionBuySellDetailOptions,
    itemAddContentsManagementOptions  : itemAddContentsManagementOptions,
    auctionRoomRoundOptions           : auctionRoomRoundOptions,
    mailSkinOptions                   : mailSkinOptions,
    auctionRequestOptions             : auctionRequestOptions,
    appraisalManagementOptions        : appraisalManagementOptions,
    itemStaffCommentManagementOptions : itemStaffCommentManagementOptions,
    myPageMenuOptions                 : myPageMenuOptions,
    recommendExceptUserOptions        : recommendExceptUserOptions,
    offlineItemPopupListOptions       : offlineItemPopupListOptions,
    auctionApplicantOptions           : auctionApplicantOptions,
    withdrawOptions                   : withdrawOptions
  }
})(window.JqGridOptions || {}, jQuery);