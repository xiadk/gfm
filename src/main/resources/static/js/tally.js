/**获取团队**/
function tally_obtainTeam(options, loading) {
    var params  = {};
    get("/team",params,function (data) {
        for (var i = 0, j = data.obj.length; i < j ; i ++) {
            var team = data.obj[i];
            options.push({
                label: team.name,
                value:team.teamId
            })
        }
    })
}

/**选择队员**/
function tally_selectMember(membersData,form) {
    var params  = {};
    if (form.team == null){
        Message.error("请先选择团队");

        return;
    }
    params["teamId"]=form.team;
    get("/members",params,function (data) {
        for (var i = 0, j = data.obj.length; i < j ; i ++) {
            var member = data.obj[i];
            membersData.push({
                label: member,
                key: member,
                pinyin: member
            })
        }
    })
}

/**创建活动**/
function tally_createEvent(form) {
    var params  = {};
    params["activeName"]=form.name;
    params["region"]=form.region;
    params["date"]=form.date;
    params["time"]=form.time;
    params["delivery"]=form.delivery;
    params["type"]=form.type;
    params["desc"]=form.desc;
    params["amount"]=form.amount;
    params["members"]=form.members;
    params["team"]=form.team;
    post("/active",params,function (data) {
        Message.success(data.msg);
    })
}