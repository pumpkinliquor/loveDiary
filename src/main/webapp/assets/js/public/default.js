var noneNum = /[0-9]/g;
var noneEng = /[a-zA-Z]/g;
$.extend({
	call : function(url, data, callback, isAsync) {
		isAsync = false;
		$.ajax({
			url : url,
			type : 'post',
			data : data,
			async : isAsync,
			success : callback
		})
	},
	viewData : function(fieldKey, wrapElement, value) {
		value = $.trim(value);
		$('#' + fieldKey, wrapElement).val(value);
		var tdElement = $('td').has($('#' + fieldKey, wrapElement).not(':hidden input'));
		console.log(tdElement.index());
		if (tdElement.length) {

			$('td').has($('#' + fieldKey, wrapElement).not(':hidden input')).html(value);
			$('td').has($('[id^=' + fieldKey + ']', wrapElement).not(':hidden input')).html(value);
			tdElement.addClass('added')
			$('#' + fieldKey, wrapElement).not(':hidden input').remove();
			$('[id^=' + fieldKey + ']', wrapElement).not(':hidden input').remove();
		}
	},
	autoLink : function(id) {
		var container = document.getElementById(id);
        var doc = container.innerHTML;
        var regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");
        var regEmail = new RegExp("([xA1-xFEa-z0-9_-]+@[xA1-xFEa-z0-9-]+\.[a-z0-9-]+)","gi");
        
        container.innerHTML = doc.replace(regURL,"<a href='$1://$2' target='_blank'>$1://$2</a>").replace(regEmail,"<a href='mailto:$1'>$1</a>");
	},
	formSubmit : function(action, paramMap, option) {
		var $form = $('<form>').attr($.extend({
			action : action,
			method : 'post'
		}, option));

		for ( var key in paramMap) {
			try {
				$.each($.makeArray(paramMap[key]), function(index, element) {
					$form.append($('<input>').attr({
						name : key,
						type : 'hidden'
					}).val(typeof element === 'object' ? JSON.stringify(element) : element));
				});
			} catch (e) {
				log(e);
			}
		}

		$form.appendTo(document.body).submit().remove();
	}
});
var home = {
	getElPass : function(el, element) {
		return {
			element : element,
			elGetAttr : $(el).attr(element)
		}
	},
	aigoGo : function(url, $f, callback) {
		var data = $f.serialize();
		$.ajax({
			url : url,
			type : 'post',
			data : data,
			success : callback
		});
	},
	aigoRemoveAll : function(el) { 
		//nvl : id>name>class
		var elList = el.replace(/ /g, "").split(",");
		for(var i = 0; i < elList.length; i++) {
			if(elList[i] != "") {
				var elGetThisArr = ($(elList[i]).attr("id") == "") ? ($(elList[i]).attr("name") == "") ? this.getElPass(elList[i], "class") : this.getElPass(elList[i], "name") : this.getElPass(elList[i], "id");
				var elGetElement = elGetThisArr.element;
				var $elGetAttr = elGetThisArr.elGetAttr;
				var $elGetAttrNoneNum = $elGetAttr.replace(noneNum, "");
		
				$("[" + elGetElement + "^='" + $elGetAttrNoneNum + "']").not("[" + elGetElement + "='" + $elGetAttr + "']").each(function(i, e) {
					$(e).remove();
				});
				
				$("[" + elGetElement + "='" + $elGetAttr + "']").css("display", "");
				$("[" + elGetElement + "='" + $elGetAttr + "']").has("input, select, textarea").removeAttr("disabled");
				$("[" + elGetElement + "='" + $elGetAttr + "']").has("input, select, textarea").removeAttr("readonly");
			}
		}
	}
};

$.fn.serializeObject = function() {
	var obj = {};

	$.each(this.serializeArray(), function() {
		if (obj[this.name]) {
			if (!obj[this.name].push) {
				obj[this.name] = [ obj[this.name] ];
			}

			obj[this.name].push(this.value || '');
		} else {
			obj[this.name] = this.value || '';
		}
	});

	return obj;
};

function comma(num) {
	var len, point, str;
	
	num = num + "";
	point = num.length % 3;
	len = num.length;
	
	str = num.substring(0, point);
	while (point < len) {
		if (str != "") str += ",";
		str += num.substring(point, point + 3);
		point += 3;
	}
	
	return str;
}