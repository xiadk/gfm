function myBill_tab(obj) {
    obj.tableData=[];
    var params  = {};
    params["activeName"] = obj.activeForm.name;
    params["date"] = obj.activeForm.date;
    get("/bill",params,function (data) {
        for (var i = 0, j = data.obj.length; i < j ; i ++) {
            var activeRecords = data.obj[i];
            obj.tableData.push({
                date: activeRecords.activeTime,
                name:  activeRecords.activeName,
                amount: activeRecords.amount,
                desc: activeRecords.desc,
                region: activeRecords.region,
                tag: activeRecords.tag,
            })
        }
    })
}