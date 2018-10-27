function myTeam_createTeam(teamForm, addDynamicTags){
    var params  = {};
    params["teamName"]=teamForm.name;
    params["type"]=teamForm.type;
    params["amount"]=teamForm.amount;
    params["desc"]=teamForm.desc;
    params["member"]=addDynamicTags;
    post("/team",params,function (data) {
        Message.success(data.msg);
    })
}

function myTeam_changeTeam(teamInfo) {
        var params  = {};
        get("/team",params,function (data) {
            for (var i = 0, j = data.obj.length; i < j ; i ++) {
                var team = data.obj[i];
                teamInfo.push({
                    totalAmount: team.total,
                    balance: team.balance,
                    name: team.name,
                    num:team.num,
                    desc: team.desc,
                    members:team.members,
                    teamId:team.teamId
                })
            }
        })
}

//移除团队
function myTeam_deleteTeam(index, rows) {
    // rows.splice(index, 1);
    var params  = {};
    params["teamId"]=rows[index].teamId;
    del("/team",params,function (data) {
        Message.success(data.msg);
    })
}