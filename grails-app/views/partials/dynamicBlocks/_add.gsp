<div id="count_${id}" style="display: none;">0</div>
<div id="parent_${id}"></div>
<g:if test="${!addBtnId}">
	<input id="add_${id}" type="button" value="Add"/>
</g:if>

<r:script>
	function initializeTag(addButton, id, elem, min, max, onComplete, limitReachedMsg, removeBtnLabel) {
		// bind event handler to the "click" JS event of the "Add" button
		addButton.click(function() {
			addItem(id, elem, min, max, onComplete, limitReachedMsg, removeBtnLabel);
		});

		// add the initial number of items
		for (var i = 0; i < min; i++) {
			addButton.click();
		}
	}

	$(function () {
		// get the "Add" button
		var addButton = ${addBtnId ? "\$('#$addBtnId')" : "\$('#add_$id')"};

		// import the dynamicBlocks.js file if it has not been imported yet
		if (!window['addItem']) {
			$.getScript("${resource(dir: 'js', file: 'dynamicBlocks.js')}", function() {
				initializeTag(addButton, '${id}', '${elem}', ${min}, ${max}, '${onComplete}', '${limitReachedMsg}', '${removeBtnLabel}');
			});
		} else {
			initializeTag(addButton, '${id}', '${elem}', ${min}, ${max}, '${onComplete}', '${limitReachedMsg}', '${removeBtnLabel}');
		}
	});
</r:script>