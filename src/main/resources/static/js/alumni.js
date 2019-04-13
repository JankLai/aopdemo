window.onload=function () {

    $.ajax({
        async: false,
        type: "GET",
        url: "/alumni/all",
        //dataType: "application/json;charest=utf-8",
        success: function (data) {

            //var data = msg.data;

            for (i in data) {
                var str = "";
                str += "<tr>" +
                    "<td align='center'>" + data[i].id + "</td>" +
                    "<td align='center'>" + data[i].name + "</td>" +
                    "<td align='center'>" + data[i].sex + "</td>" +
                    "<td align='center'>" + data[i].birthday + "</td>" +
                    "<td align='center'>" + data[i].inSchoolDate + "</td>" +
                    "<td align='center'>" + data[i].outSchoolDate + "</td>" +
                    "<td align='center'>" + data[i].workingCity + "</td>" +
                    "<td align='center'>" + data[i].workingUnit + "</td>" +
                    "<td align='center'>" + data[i].job + "</td>" +
                    "<td align='center'>" + data[i].phoneNum + "</td>" +
                    "<td align='center'>" + data[i].email + "</td>" +
                    "<td align='center'>" + data[i].wechat + "</td>" +
                    "<td align='center'><button id='modify" + data[i].id + "' style='color:red;background-color: black;border: none'>修改</button></td>" +
                    "<td align='center'><button id='delete" + data[i].id + "' style='color:red;background-color: black;border:none'>删除</button></td>" +

                    "</tr>";
                $("#tbody").append(str);

                bindDelete(data[i].id);
                bindModify(data[i].id);
            }

        },
        error: function () {
            alert("查询失败")
        }
    });
    bindNew();
    bindUpdate();
    bindExit();
    bindSearch();
    bindDownload();
}


//新增记录
function bindNew() {
    $("#new").on('click', function () {
        window.location.href="new.html";

    })
}

//重新生成1000条数据
function bindUpdate() {
    $("#update").on('click', function () {
        if(confirm("确认重新生成所有数据吗？")){
            $.ajax({
                async: false,
                type: "PUT",
                url: "/alumni/update/all",
                contentType: "application/json",
                success: function (response) {
                    alert("重新生成成功!")
                    window.location.href = "alumni.html"
                },
                error: function () {
                    alert("出现错误!");
                }
            });
        }


    })
}

function bindSearch() {
    $("#search").on('click',function () {
        window.location.href="search.html";
    })
}

//退出系统，返回登陆界面
function bindExit() {
    var adminId=parseInt(localStorage.getItem("adminId"))
    $("#exit").on('click', function () {
        if(confirm("确认退出登陆？")){
            $.ajax({
                async: false,
                type: "GET",
                url: "/admin/logout/" +adminId,
                success: function (response) {

                    window.location.href="index.html"
                },
                error: function () {
                    alert("出现错误!");
                }
            });


        }


    })
}

//修改一条信息
function bindModify(id) {
    $("#modify" + id).on('click', function () {
        modify(id);
    })
}

//删除一条信息
function bindDelete(id) {
    $("#delete" + id).on('click', function () {
        del(id);
    })

}

function modify(id) {
    localStorage.setItem("modifyId", id);
    window.location.href = "modify.html"
}

function del(id) {
    if(confirm("您确认要删除这条记录吗？")){
        $.ajax({
            async: false,
            type: "DELETE",
            url: "/alumni/delete/" + id,
            success: function (response) {
                alert("删除成功!")
                window.location.href = "alumni.html"
            },
            error: function () {
                alert("删除失败!");
            }
        });
    }

}

function bindDownload() {
    $("#download").on('click',function () {
        $.ajax({
            async: false,
            type: "POST",
            url: "/alumni/download",
            success: function (response) {
                alert("下载成功!路径为 D:/excel/汇总.xlsx")
            },
            error: function () {
                alert("出现错误!");
            }
        });
    })
}

