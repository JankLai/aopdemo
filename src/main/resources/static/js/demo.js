//source D:\J2EE\DatabaseStandard\sql\init.sql

//qustion表包含klass_seminar_id,attendance_id(问题所针对的发言id ),team_id,student_id,is_selected,score
//学生端：两个subscribe->接收切换小组状态（左上） ->接收抽取提问状态（弹窗）  ->接收提问数 （右上） 一个send()
//->发送提问申请
var stompClient = null; //全局变量

// var seminarId = 5;
// var classId = 21; //测试用！！！！！！！！！
// var teamId=4;
// var classSeminarId=9;
var seminarId=parseInt(localStorage.getItem("seminarId"));
var classId=parseInt(localStorage.getItem("classId"));
var classSeminarId=parseInt(localStorage.getItem("classSeminarId"));

window.onload = function() {
    $("#CourseName").text(localStorage.getItem("courseName"));
    $("#seminarName").text(localStorage.getItem("seminarName"))
    //提问权限
    var is_quested = localStorage.getItem("is_quested");
    if (is_quested == null) {
        localStorage.setItem("is_quested", 0);
    }
    //右上角提问数更新
    var questionNum_stu=localStorage.getItem("questionNum_stu");
    if(questionNum_stu!=null){
        $("#AskerNum").text(questionNum_stu);
    }

    $.ajax({
        async: false, //——————                                         //进入这个页面后再取出数据加载在页面中h
        type: "GET",
        //————————————————————————————————————url路径修改
        url: "/attendance/" + seminarId + "/class/" + classId + "/liveAttendance", //seminar/{seminarId}/class/{classId}/attendance
        contentType: "application/json;charest=utf-8",
        success: function(data) {
            //设置第一个展示的小组attendanceId和小组名
            localStorage.setItem("attendanceId", data[0]["id"]);

            localStorage.setItem("preTeamId", data[0]["team"]["id"]);
            // localStorage.setItem("preTeamName", data[0]["team"]["name"]);

            var attendanceIdArray = new Array();
            var preTeamIdArray = new Array();
            var preTeamNameArray = new Array();
            for (var i = 0; i < data.length; i++) {
                attendanceIdArray.push(data[i]["id"]);
                preTeamIdArray.push(data[i]["team"]["id"]);
                //preTeamNameArray.push(data[i]["team"]["name"]);
                preTeamNameArray.push(data[i]["team"]["class1"]["serial"]+"-"+data[i]["team"]["serial"]);
                if (data[i]["presented"]) {
                    console.log(data[i]["team"]["name"] + "正在展示");
                    localStorage.setItem("preTeamId", data[i]["team"]["id"]);
                }
            }
            // //设置展示小组attendanceId,teamId,teamName队列
            //存一个String进去，到时候再parse出来
            localStorage.setItem(
                "attendanceIdArray",
                JSON.stringify(attendanceIdArray)
            );
            localStorage.setItem("preTeamIdArray", JSON.stringify(preTeamIdArray));
            localStorage.setItem(
                "preTeamNameArray",
                JSON.stringify(preTeamNameArray)
            );

        },
        error: function() {
            alert("获得小组展示信息失败");
        }
    });

    //加载左边展示队列

    var preTeamIdArray = JSON.parse(localStorage.getItem("preTeamIdArray")); //->这两项展示打分需要用到
    var preTeamNameArray = JSON.parse(localStorage.getItem("preTeamNameArray"));
    if (preTeamIdArray.length == null) {
        alert("当前无讨论课！");
        //window.location.href="";
    }

    for (var i = 0; i < preTeamIdArray.length; i++) {
        var teamId = preTeamIdArray[i];
        var teamName = preTeamNameArray[i];
        var order=i+1;
        var html=
            '<div class="div1">'+
            '<span>第'+ order+'组:</span><a id="pre' +teamId + '">' + teamName + '</a>'+
            '</div>';
        $("#TeamAll").append(
            html//'<p id="pre' + teamId + '" class="TeamNo">' + teamName + "</p>" //<p id="pre123">teamName</p>
        );
    }
    var preTeamId = localStorage.getItem("preTeamId");
    if (preTeamId != null) {
        $("#pre" + preTeamId).css("color", "red");
    }

    //防止多台设备
    if (stompClient != null) stompClient.disconnect();

    var socket = new SockJS("/livescore-websocket");
    stompClient = Stomp.over(socket);
    //如果想让客户端订阅多个目的地destinations，可以使用同一个回调函数来接收messages
    //即同一个connect,多个subscribe
    stompClient.connect(
        //一下有两个 subscribe
        {},
        function(frame) {
            //获取左上角展示组名，接收这个消息同时将右上角提问数清零
            stompClient.subscribe(
                "/topic/next/" + seminarId + "/" + classId,
                function(scoredata) {
                    //把提问权限调回来，因为进入新的小组展示，提问过的学生可以再次提问
                    localStorage.setItem("is_quested", 0);
                    //清除提问人数
                    localStorage.removeItem("questionNum_stu");
                    $("#AskerNum").text("0");
                    var scoreJson = JSON.parse(scoredata.body);
                    if (scoreJson.attendanceId == "finish") {
                        alert("讨论课已结束！");
                        localStorage.removeItem("seminarNow");
                        localStorage.removeItem("preTeamId");
                        localStorage.removeItem("attendanceId");
                        localStorage.removeItem("preTeamIdArray");
                        localStorage.removeItem("preTeamNameArray");
                        localStorage.removeItem("attendanceIdArray");
                        localStorage.removeItem("is_quested");
                        localStorage.removeItem("questionNum_stu");
                        window.location.href="StudentSeminar1.html";
                    }
                    else{
                        localStorage.setItem("attendanceId", scoreJson.attendanceId);
                        localStorage.setItem("preTeamId", scoreJson.preTeamId);
                        var preTeamId = localStorage.getItem("preTeamId");
                        if (preTeamId != null) {
                            $("#pre" + preTeamId).css("color", "red");
                        }

                        var value = scoreJson.countValue;
                        $("#pre" + preTeamIdArray[value - 1]).css("color", "black");
                        //localStorage.setItem("count_stu",count_stu);
                    }
                }
            );
            //获取右上角提问学生数，学生端发送，学生端/老师端都能接收
            stompClient.subscribe(
                "/topic/quest/" + seminarId + "/" + classId,
                function(scoredata) {
                    //提问数更新
                    var questionNum_stu=localStorage.getItem("questionNum_stu");
                    if(questionNum_stu==null){
                        localStorage.setItem("questionNum_stu",0);
                    }
                    questionNum_stu++;
                    localStorage.setItem("questionNum_stu",questionNum_stu);
                    $("#AskerNum").text(questionNum_stu);

                }
            );
            //老师抽取提问
            stompClient.subscribe(
                "/topic/livescore/" + seminarId + "/" + classId,
                function(scoredata) {
                    var scoreJson = JSON.parse(scoredata.body);

                    $("#askTeam").text(scoreJson.questTeamName);
                    $("#asker").text(scoreJson.questStudentName);
                    $("#askerID").text(scoreJson.account);
                    $("#window").show();

                    var scoreJson = JSON.parse(scoredata.body);
                }
            );
        }
    );
};
//学生发送提问请求
function quest() {

    //此处测试阶段须在controller 模拟出几个学生ID
    var studentId=localStorage.getItem("userId");
    var account=localStorage.getItem("account");
    var studentName=localStorage.getItem("studentName");
    var teamId=localStorage.getItem("teamId");
    var attendanceId =localStorage.getItem("attendanceId");
    var teamName=localStorage.getItem("teamName");//由klass_serial+team_serial构成
    var is_quested=localStorage.getItem("is_quested");
    if(is_quested==null){
        localStorage.setItem("is_quested",0);

    }
    var is_quested=localStorage.getItem("is_quested");
    var asked=false;
    if (is_quested == 0) {
        localStorage.setItem("is_quested", 1);
        stompClient.send(
            //                                      // in query studentId?前端传当前的用户Id
            "/app/studentApplyQuest/" + seminarId + "/" + classId,
            {},
            JSON.stringify({
                studentId: studentId, //然后return 一个questionId
                account:account,
                studentName:studentName,
                teamName:teamName,
                attendanceId: attendanceId, //questTeamName,studentName(老师提问队列要用到)
                teamId:teamId,
                classSeminarId:classSeminarId,
                asked:asked
            })
        );
    } else {
        //do-noting 因为该学生已经在该展示的时候提问过了
        alert("亲，本轮点一次就好^V^")
    }
}
function hideWindow(){
    $("#window").hide();
}