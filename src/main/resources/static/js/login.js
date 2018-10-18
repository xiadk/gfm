var Main = {
    data() {
        var checkImageCode = (rule, value, callback) => {
            if (!value) {
                return callback(new Error(' '));
            } else {
                callback();
            }

        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error(' '));
            } else {
                callback();
            }
        };
        return {
            ruleForm2: {
                pass: '',
                imageCode: ''
            },
            url:"/imageCode",
            rules2: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                imageCode: [
                    { validator: checkImageCode, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {

                    var username = this.ruleForm2.pass;
                    var imageCode = this.ruleForm2.imageCode;

                    var params = {
                        username: username,
                        imageCode: imageCode
                    };
                    post('/doLogin',params,function (resp) {
                        var token  = resp.obj;
                        window.localStorage.setItem('token', token);
                        //用户未注册
                        if (resp.code == -2) {
                            //注册弹框
                            ctor.$confirm('用户名未注册, 是否注册?', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {

                                params={username: username};
                                post('/doRegister',params,function (resp) {
                                    Message.success("注册成功，请再次登陆");
                                });

                            }).catch(() => {
                                Message.info("已取消注册");
                            });

                            return;
                        }

                        window.location.href="/home";
                    });

                } else {
                    Message.error("不能为空")
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        upImageCode() {
            var timenow = new Date();
            this.url="/imageCode?d="+timenow;
        }
    }
}
var Ctor = Vue.extend(Main)
var ctor = new Ctor().$mount('#app')

