// 既然用了Ajax就不应该用OnClick事件。
// 1、点击Button按钮，先执行OnClientClick客户端脚本，return true时进行页面回发。
// 2、使用Ajax异步请求，回调函数接收数据之前，页面已经回发了。原来的页面已经不存在，上哪接收异步请求返回的结果。
$(document).ready(function () {
    bind();
})

function bind(){
    $(".sub").on('click',function (event) {
        the_name=$('#name').val();
        sex=$('#sex').val();
        birthday=$('#bir').val();
        inSchoolDate=$('#in').val();
        outSchoolDate=$('#out').val();
        workingCity=$('#city').val();
        workingUnit=$('#unit').val();
        job=$('#job').val();
        phoneNum=$('#phone').val();
        email=$('#email').val();
        wechat=$('#wechat').val();


        if(the_name==null||the_name==""){
            alert("请输入姓名！")
        }
        else{
            var alumni={
                'name':the_name,
                'sex':sex,
                'birthday':birthday,
                'inSchoolDate':inSchoolDate,
                'outSchoolDate':outSchoolDate,
                'workingCity':workingCity,
                'workingUnit':workingUnit,
                'job':job,
                'phoneNum':phoneNum,
                'email':email,
                'wechat':wechat
            }
            $.ajax({
                async:false,
                type: "POST",
                url: "/alumni/insert",
                contentType:"application/json",
                data:JSON.stringify(alumni),
                success: function (response) {
                    alert("信息录入成功!")
                    window.location.href="alumni.html"
                },
                error:function () {
                    alert("出现错误!");
                }
            });

        }
    })
}
