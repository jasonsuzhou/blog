function getSummaryContent(content) {
	var newContent = content.substr(0, 100);
	var len = newContent.indexOf("<pre>");
	if (len != -1) {
		newContent = newContent.substr(0, len);
	}
	len = newContent.indexOf("<img");
	if (len != -1) {
		newContent = newContent.substr(0, len);
	}
	return newContent + "...";
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