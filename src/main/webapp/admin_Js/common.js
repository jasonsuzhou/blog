function U() {
    var url = arguments[0] || [];
    var param = arguments[1] || {};
    var url_arr = url.split('/');

    if (!$.isArray(url_arr) || url_arr.length < 2 || url_arr.length > 3) {
        return '';
    }

    if (url_arr.length == 2)
        url_arr.unshift(_GROUP_);

    var pre_arr = ['g', 'm', 'a'];

    var arr = [];
    for (d in pre_arr)
        arr.push(pre_arr[d] + '=' + url_arr[d]);

    for (d in param)
        arr.push(d + '=' + param[d]);

    return _APP_+'?'+arr.join('&');
}

function initBackPage(url) {
	$('#backid').click(function(){
		window.location.href = url;
	});
}

function initDelAction(url, id) {
	if (confirm("Are your sure to delete？")) {
		window.location.href = url + "?id=" + id;
	}
}
function initEditAction(url, id) {
	window.location.href = url + "?id=" + id;
}
function initAddAction(url) {
	$('#addnew').click(function() {
		window.location.href = url;
	});
}

function calPagers(curPage, perPage, totalCount) {
	var totalPage = totalCount < perPage ? 1
			: (totalCount % perPage == 0 ? totalCount / perPage
					: parseInt(totalCount / perPage) + 1);
	var prePage = curPage == 1 ? 1 : curPage - 1;
	var nextPage = curPage == totalPage ? totalPage : curPage + 1;
	return {
		totalPage : totalPage,
		prePage : prePage,
		nextPage : nextPage,
		lastPage : totalPage
	};
}
function appendPaginator(curPage, perPage, totalCount, url) {
	var pagers = calPagers(curPage, perPage, totalCount);
	$('#paginator').empty();
	var pager = totalCount + " Records Page:" + curPage + "/" + pagers.totalPage + 
				"<a href='javascript:queryList("+1+","+url+")'>First</a>"+
				"<a href='javascript:queryList("+pagers.prePage+","+url+")'>Previous</a>"+
				"<a href='javascript:queryList("+pagers.nextPage+","+url+")'>Next</a>"+
				"<a href='javascript:queryList("+pagers.lastPage+","+url+")'>Last</a>";
	$('#paginator').append(pager);
}

function queryList(curPage, url) {
	$.ajax({
		url : url,
		type : 'POST',
		data : {curPage:curPage, perPage:10},
		dataType : 'json',
		success : function(data) {
			appendRowData(data.dataList);
			appendPaginator(curPage, 10, data.totalCount, url);
		},
		error : function(data) {
			alert("Internal server error, please try later.");
		}
	});
}
