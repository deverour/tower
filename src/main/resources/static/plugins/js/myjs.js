
$(document).ready(function() {

    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
    $("#head").load("head.html");
    $("#header").load("header.html");
    $("#sidebar").load("sidebar.html");
    $("#footer").load("footer.html");
});
