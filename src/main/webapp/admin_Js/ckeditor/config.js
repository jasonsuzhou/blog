/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.filebrowserUploadUrl="uploadImg.do";
	config.enterMode=CKEDITOR.ENTER_BR;
	//config.extraPlugins = 'insertpre';
	config.extraPlugins = 'codesnippet';
	config.allowedContent = true;
	config.entities = false;
	config.codeSnippet_theme = 'school_book';
};
