/**获取团队**/
function tally_obtainTeam(obj) {
    obj.options=[];
    var params  = {};
    get("/team",params,function (data) {
        for (var i = 0, j = data.obj.length; i < j ; i ++) {
            var team = data.obj[i];
            obj.options.push({
                label: team.name,
                value:team.teamId
            })
        }
    })
}

/**选择队员**/
function tally_selectMember(obj) {
    obj.membersData=[];
    var params  = {};
    if (obj.form.team == null){
        Message.error("请先选择团队");

        return;
    }
    params["teamId"]=obj.form.team;
    get("/members",params,function (data) {
        for (var i = 0, j = data.obj.length; i < j ; i ++) {
            var member = data.obj[i];
            obj.membersData.push({
                label: member,
                key: member,
                pinyin: member
            })
        }
    })
}

/**创建活动**/
function tally_createEvent(obj) {
    var params  = {};
    params["activeName"]=obj.form.name;
    params["region"]=obj.form.region;
    params["date"]=obj.form.date;
    params["time"]=obj.form.time;
    params["delivery"]=obj.form.delivery;
    params["type"]=obj.form.type;
    params["desc"]=obj.form.desc;
    params["amount"]=obj.form.amount;
    params["members"]=obj.form.members;
    params["team"]=obj.form.team;
    post("/active",params,function (data) {
        Message.success(data.msg);
        obj.$refs['form'].resetFields();
    })
}