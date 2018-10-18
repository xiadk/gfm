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

function myTeam_changeTeam(tag, teamInfo, dynamicTags) {
    if ("allTeam" == tag.name) {
        dynamicTags.push("事事都","ddd");
        var params  = {};
        get("/team",params,function (data) {
            for (var i = 0, j = data.obj.length; i < j ; i ++) {
                var team = data.obj[i];
                teamInfo.push({
                    totalAmount: team.total,
                    balance: team.balance,
                    name: team.name,
                    num:team.num,
                    desc: team.desc
                })
            }
        })
        /*teamInfo.push({
            totalAmount: 120,
            balance: 15,
            name: '好滋好味鸡蛋仔',
            num:5,
            desc: '荷兰优质淡奶，奶香浓而不腻'
        }, {
            totalAmount: 120,
            balance: 15,
            name: '好滋好味鸡蛋仔',
            num:5,
            desc: '荷兰优质淡奶，奶香浓而不腻'
        });*/
    } else {

    }
}