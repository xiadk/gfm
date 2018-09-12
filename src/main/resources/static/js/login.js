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
                    axios.post('/doLogin', {
                        username: this.ruleForm2.pass,
                        imageCode: this.ruleForm2.imageCode
                    }).then(function (response) {

                            var code = response.data.code;
                            var msg = response.data.msg;
                            var obj = response.data.obj;
                            if (code == -1) {
                                ctor.$message({
                                    showClose: true,
                                    message: msg,
                                    type: 'error'
                                });

                                return;
                            }
                            if (code == -2) {

                            }

                        window.location.href="/home";

                    }).catch(function (error) {
                            console.log(error);
                        });
                } else {
                    console.log('error submit!!');
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