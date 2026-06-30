$(function () {
    $('.btn-delete').click(function () {
        var url = $(this).data('url');
        
        if (typeof window.Swal !== 'undefined') {
            window.Swal.fire({
                title: '您确定要删除吗？',
                text: "此操作不可撤销!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then((result) => {
                if(result.isConfirmed) {
                    location.href = url;
                }
            });
        } else {
            if(confirm('您确定要删除吗？此操作不可撤销!')) {
                location.href = url;
            }
        }
    });

    $('.input-date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minView : 0,
        todayHighlight: true, // 今天时间高亮
        format: 'yyyy-mm-dd hh:ii:ss',
        todayBtn: true
    });
});