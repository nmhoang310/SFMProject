/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
$(document).ready(function () {
    //Student-service-staff
    /**
     * Import Student Enrollment Information
     */
    $(document)
        .on("click", "#studentEnrollmentInformation",
        function () {
            //start loading icon
            var $icon = $(this).find(".glyphicon.glyphicon-refresh"),
                animateClass = "glyphicon-refresh-animate";
            $icon.addClass(animateClass);

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var oMyForm = new FormData();
            var files = $('#enrollmentInformation')[0].files[0]
            oMyForm.append("file", files);
            oMyForm.append('_csrf', token);
            $.ajax({
                dataType: 'json',
                url: "/nhan-vien/cong-tac-sinh-vien/import-thong-tin-nhap-hoc",
                data: oMyForm,
                beforeSend: function (xhr) {
                    // here it is
                    xhr.setRequestHeader(header, token);
                },
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function (result) {
                    $icon.removeClass(animateClass);
                    alert('upload ok : ' + result.result);
                    alert('Upload Successful');
                },
                error: function (result) {
                    $icon.removeClass(animateClass);
                    alert('Upload Successful');
                }
            });
        })
    /**
     * Get Suggest term
     */
    $(document).on("click", "#btnAddTerm", function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var data = "_csrf=" + token;
        $.ajax({
            url: "/nhan-vien/dao-tao/tao-hoc-ky-mau",
            type: "post",
            data : data,
            beforeSend: function (xhr) {
                // here it is
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                var startDate = new Date(response.startDate);
                var endDate = new Date(response.endDate);

                $('#termName').val(response.termName);
                $('#startDate').val($.datepicker.formatDate('dd MM yy', startDate));
                $('#endDate').val($.datepicker.formatDate('dd MM yy', endDate));
                $('#note').val(response.note);

                $('#termModal').modal('show');
            },
            error: function (jqXHR, textStatus, errorThrown) {
               console.log('error');
            }


        });
    });


    var formatTime = function(unixTimestamp) {
        var dt = new Date(unixTimestamp * 1000);

        var hours = dt.getHours();
        var minutes = dt.getMinutes();
        var seconds = dt.getSeconds();

        // the above dt.get...() functions return a single digit
        // so I prepend the zero here when needed
        if (hours < 10)
            hours = '0' + hours;

        if (minutes < 10)
            minutes = '0' + minutes;

        if (seconds < 10)
            seconds = '0' + seconds;

        return hours + ":" + minutes + ":" + seconds;
    }


    /*
     $.ajax({
     url: "/",
     data: $("#studentEnrollmentInformation").serialize(),
     type: "POST",
     processData: false,
     contentType: false,

     success: function (data) {
     $(response).html(data);
     },

     error: function (xhr, ajaxOptions, thrownError) {
     if (xhr.readyState == 0 || xhr.status == 0) {
     // not really an error
     return;
     } else {
     alert("XHR Status = " + xhr.status);
     alert("Thrown Error = " + thrownError);
     alert("AjaxOptions = " + ajaxOptions)
     }
     }

     });*/


    /**
     * Import Student Financial Information
     */
    $(document)
        .on("click", "#financialInformation",
        function () {
            //start loading icon
            var $icon = $(this).find(".glyphicon.glyphicon-refresh"),
                animateClass = "glyphicon-refresh-animate";
            $icon.addClass(animateClass);

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var oMyForm = new FormData();
            var files = $('#financialInformationFile')[0].files[0];
            oMyForm.append("file", files);
            oMyForm.append('_csrf', token);
            $.ajax({
                dataType: 'json',
                url: "/nhan-vien/tai-chinh/nhap-danh-sach-dong-hoc-phi",
                data: oMyForm,
                beforeSend: function (xhr) {
                    // here it is
                    xhr.setRequestHeader(header, token);
                },
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function (result) {
                    $icon.removeClass(animateClass);
                    alert('upload ok : ' + result.result);
                    alert('Upload Successful');
                },
                error: function (result) {
                    $icon.removeClass(animateClass);
                    alert('Upload Successful');
                }
            });
        });
});

/**
 * Import Student result
 */
function importStudyResult(classId, subInSemId, fileId){
    //start loading icon
    var $icon = $(this).find(".glyphicon.glyphicon-refresh"),
        animateClass = "glyphicon-refresh-animate";
    $icon.addClass(animateClass);

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var oMyForm = new FormData();
    var files = $(fileId)[0].files[0];
    oMyForm.append("file", files);
    oMyForm.append('_csrf', token);
    oMyForm.append("classId", classId);
    oMyForm.append("subInSemId", subInSemId);
    $.ajax({
        dataType: 'json',
        url: "/nhan-vien/dao-tao/import-ket-qua-hoc-tap",
        data: oMyForm,
        beforeSend: function (xhr) {
            // here it is
            xhr.setRequestHeader(header, token);
        },
        type: "POST",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function (result) {
            $icon.removeClass(animateClass);
            alert('upload ok : ' + result.result);
            alert('Upload Successful');
        },
        error: function (result) {
            $icon.removeClass(animateClass);
            alert('Upload Successful');
        }
    });
};