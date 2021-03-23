/**
 * @license Copyright (c) 2003-2020, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';

    // config.uiColor = '#dc6e91';
    // config.width = 800;
    config.width = '90%';//默认100%
    config.height = 400;//默认200px
    // config.toolbar= [
    //     { name: 'clipboard', items: [ 'Undo', 'Redo' ] },
    //     { name: 'styles', items: [ 'Styles', 'Format' ] },
    //     { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Strike', '-', 'RemoveFormat' ] },
    //     { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
    //     { name: 'links', items: [ 'Link', 'Unlink' ] },
    //     { name: 'insert', items: [ 'Image', 'Table' ] },
    //     { name: 'tools', items: [ 'Maximize' ] },
    //     { name: 'editing', items: [ 'Scayt' ] }
    // ];


    config.removePlugins = 'easyimage';
    config.extraPlugins = 'image2,uploadimage';


    // Upload dropped or pasted file to the CKFinder connector (note that the response type is set to JSON).
    config.uploadUrl = '/cjCKEditor/uploader/file';
    // Upload dropped or pasted images to the CKFinder connector (note that the response type is set to JSON).
    config.imageUploadUrl = '/cjCKEditor/uploader/image';

    // filebrowserBrowseUrl: '/cjCKEditor/uploader/file',//需要仔细考虑，应该是查看服务器文件的连接
    config.filebrowserUploadUrl = '/cjCKEditor/uploader/file';


    // Simplify the Image and Link dialog windows. The "Advanced" tab is not needed in most cases.
    config.removeDialogTabs= 'image:advanced;link:advanced';


    //codesnippet
    // config.extraPlugins = 'codesnippet';
    // config.codeSnippet_theme='monokai_sublime';


};
