// 既然用了Ajax就不应该用OnClick事件。
// 1、点击Button按钮，先执行OnClientClick客户端脚本，return true时进行页面回发。
// 2、使用Ajax异步请求，回调函数接收数据之前，页面已经回发了。原来的页面已经不存在，上哪接收异步请求返回的结果。
$(document).ready(function () {
    bind();
    bindSearch();
})

function bind(){

    $(document).keydown(function(event){
        if(event.keyCode==13){
            var alumniID=parseInt($("#id").val());
            $.ajax({
                async:false,
                type: "GET",
                url: "/alumni/"+alumniID,
                success: function (data) {

                    if(data!=null&&data!=""&&data.id!=-1){
                        $("#name").val(data.name);
                        $("#sex").val(data.sex);
                        $("#bir").val(data.birthday);
                        $("#in").val(data.inSchoolDate);
                        $("#out").val(data.outSchoolDate);
                        $("#city").val(data.workingCity);
                        $("#unit").val(data.workingUnit);
                        $("#job").val(data.job);
                        $("#phone").val(data.phoneNum);
                        $("#email").val(data.email);
                        $("#wechat").val(data.wechat);

                    }
                    else if(data.id==-1){
                        alert(data.id)
                        window.location.href="index.html";
                    }
                    else{
                        alert("无此ID!")
                        $("#name").val("");
                        $("#sex").val("");
                        $("#bir").val("");
                        $("#in").val("");
                        $("#out").val("");
                        $("#city").val("");
                        $("#unit").val("");
                        $("#job").val("");
                        $("#phone").val("");
                        $("#email").val("");
                        $("#wechat").val("");
                    }
                },
                error:function () {
                    alert("出现错误!!");
                    window.location.href="index.html";
                }
            });
        }
    });

}

function bindSearch() {
    $("#search").on('click',function () {
        var alumniID=parseInt($("#id").val());
        $.ajax({
            async:false,
            type: "GET",
            url: "/alumni/"+alumniID,
            success: function (data) {
                if(data!=null&&data!=""&&data.id!=-1){
                    $("#name").val(data.name);
                    $("#sex").val(data.sex);
                    $("#bir").val(data.birthday);
                    $("#in").val(data.inSchoolDate);
                    $("#out").val(data.outSchoolDate);
                    $("#city").val(data.workingCity);
                    $("#unit").val(data.workingUnit);
                    $("#job").val(data.job);
                    $("#phone").val(data.phoneNum);
                    $("#email").val(data.email);
                    $("#wechat").val(data.wechat);

                }
                else if(data.id==-1){
                    window.location.href="index.html";
                }
                else{
                    alert("无此ID!")
                    $("#name").val("");
                    $("#sex").val("");
                    $("#bir").val("");
                    $("#in").val("");
                    $("#out").val("");
                    $("#city").val("");
                    $("#unit").val("");
                    $("#job").val("");
                    $("#phone").val("");
                    $("#email").val("");
                    $("#wechat").val("");
                }
            },
            error:function () {
                alert("出现错误!!");
            }
        });
    })
}
