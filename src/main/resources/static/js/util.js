var vm = new Vue();
Message = new Object();
Message.success=function (msg) {
    vm.$message({
        showClose: true,
        type: 'success',
        message: msg
    });
};
Message.error=function (msg) {
    vm.$message({
        showClose: true,
        message: msg,
        type: 'error'
    });
};
Message.info=function (msg) {
    vm.$message({
        showClose: true,
        type: 'info',
        message: msg
    });
}

//发送post请求
function post(url,params,callback) {
    axios({
        method:'post',
        url:url,
        data: params,
        headers: {'token': window.localStorage.getItem('token')},
    }).then(function (response) {
        var code = response.data.code;
        var msg = response.data.msg;
        var obj = response.data.obj;
        if (code == 0 || code == -1) {
            Message.error(msg);

            return;
        }
        callback(response.data);
    }).catch(function (error) {
        vm.$notify.error({
            title: '错误',
            message: '发送请求失败',
            position: 'bottom-right'
        });
    });
}

//发送get请求
function get(url,params,callback) {
    axios({
        method:'get',
        url:url,
        params:params,
        headers: {'token': window.localStorage.getItem('token')},
    }).then(function (response) {
        var code = response.data.code;
        var msg = response.data.msg;
        var obj = response.data.obj;
        if (code == 0 || code == -1) {
            Message.error(msg);

            return;
        }
        callback(response.data);
    }).catch(function (error) {
        vm.$notify.error({
            title: '错误',
            message: error,
            position: 'bottom-right'
        });
    });
}

//发送get请求
function del(url,params,callback) {
    axios({
        method:'delete',
        url:url,
        data: params,
        headers: {'token': window.localStorage.getItem('token')},
    }).then(function (response) {
        var code = response.data.code;
        var msg = response.data.msg;
        var obj = response.data.obj;
        if (code == 0 || code == -1) {
            Message.error(msg);

            return;
        }
        callback(response.data);
    }).catch(function (error) {
        vm.$notify.error({
            title: '错误',
            message: error,
            position: 'bottom-right'
        });
    });
}

