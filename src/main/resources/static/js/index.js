// 既然用了Ajax就不应该用OnClick事件。
// 1、点击Button按钮，先执行OnClientClick客户端脚本，return true时进行页面回发。
// 2、使用Ajax异步请求，回调函数接收数据之前，页面已经回发了。原来的页面已经不存在，上哪接收异步请求返回的结果。
window.onload=function (ev) {
    $.ajax({
        async:false,
        type: "POST",
        url: "/admin/init",
        success: function (response) {

        },
        error:function () {
            alert("出现错误!");
        }
    });
    bind();
}

function bind(){
    $("#btn").on('click',function (event) {
        adminId=$('#id').val();
        password=$('#password').val();
        console.log(adminId);
        console.log(password);

        if(adminId==null||adminId==""){
            alert("请输入ID！")
        }
        else if(password==null||password==""){
            alert("请输入密码！")
        }
        else{
            $.ajax({
                async:false,
                type: "GET",
                url: "/admin/"+adminId+"/"+password,
                //dataType: "application/json;charest=utf-8",
                success: function (response) {
                    //alert(response)
                    if(response==null||response==""){
                        alert("账号名或密码错误，请重新输入！");
                    }else {
                        localStorage.setItem("adminId",adminId);
                        window.location.href="alumni.html"
                    }
                },
                error:function () {
                    alert("出现错误!");
                }
            });

        }
    })
}
