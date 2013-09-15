<script type="text/javascript" src="${base}/admin/js/jquery.pager.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	$("div.page").pager({
		pagenumber: ${page.currentPage},
		pagecount: ${page.pageCount},
		totalItem:${page.totalCount},
		buttonClickCallback: function(toPage){
			$("input[name='page']").val(toPage);
			$("form[name='listForm']").submit();
		}
	});

})
</script>
<div class="page">
</div>
<input type="hidden" name="page" value="${page.currentPage}" />
