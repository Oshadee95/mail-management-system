//----------------------------------
//   File          : js/pages/tables/datatable_basic.js
//   Type          : JS file
//   Version       : 1.0.0
//   Last Updated  : April 4, 2017
//----------------------------------

'use strict';

$(function() {
	$('.styled').uniform();
});
$(function() {

	// DataTable setup
	$('.datatable').DataTable({
		autoWidth: false,
//		order: [[ 0, 'desc' ]],
		dom: '<"datatable-header"fl><"datatable-scroll-lg"t><"datatable-footer"ip>',
		language: {
			search: '<span>Search:</span> _INPUT_',
			lengthMenu: '<span>Show:</span> _MENU_',
			paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
		},
//		lengthMenu: [ 100, 100 ],
//		displayLength: 2,
	});

	$('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');
	$('.dataTables_filter input[type=search]').attr('class', 'form-control');
   
	$('.dataTables_length select').select2({
		minimumResultsForSearch: Infinity,
		width: '100px'
	});
});
