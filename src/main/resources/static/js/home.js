var Main = {
    data() {
        const generateData2 = _ => {
            const data = [];
            const cities = ['上海', '北京', '广州', '深圳', '南京', '西安', '成都'];
            const pinyin = ['shanghai', 'beijing', 'guangzhou', 'shenzhen', 'nanjing', 'xian', 'chengdu'];
            cities.forEach((city, index) => {
                data.push({
                    label: city,
                    key: index,
                    pinyin: pinyin[index]
                });
            });
            return data;
        };

        return {
            /**我的账单初始化参数**/
            formInline: {
                user: ''
            },
            currentPage1: 5,
            currentPage2: 5,
            currentPage3: 5,
            currentPage4: 4,
            pickerOptions2: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            value4: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
            value5: '',
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄',
                tag: '家'
            }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄',
                tag: '公司'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄',
                tag: '家'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄',
                tag: '公司'
            }],


            /**随手记初始化参数**/
            form: {
                name: '',
                region: '',
                date: '',
                amount: 0,
                time: '',
                delivery: false,
                type: '',
                resource: '',
                desc: '',
                members:[]
            },
            options: [],
            membersData: [],


            /** 我的团队*/
            tabPosition: 'left',
            teamInfo: [],
            /*dynamicTags: [{name:'夏德康',money:13}, {name:'海哥',money:18}, {name:'鑫爷',money:15}],*/
            // dynamicTags: [],
            inputVisible: false,
            inputValue: '',
            addDynamicTags: [],
            addInputVisible: false,
            addInputValue: '',
            labelPosition: 'right',
            teamForm: {
                name: '',
                type: '',
                amount:0,
                desc:'',
                members:[],
                teamId:0
            }

        };
    },
    methods: {
        changeHomeTabs(tab){
            //我的团队数据初始化
            if (tab.index == 2) {
                this.teamInfo =[];
                myTeam_changeTeam(this.teamInfo);
            }

        },
        /**我的账单方法**/
        formatter(row, column) {
            return row.address;
        },
        filterTag(value, row) {
            return row.tag === value;
        },
        filterHandler(value, row, column) {
            const property = column['property'];
            return row[property] === value;
        },
        onSubmit() {
            console.log('submit!');
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        },

        /**随手记方法**/
        createEvent() {
            tally_createEvent(this.form);
        },
        obtainTeam() {
            this.options=[];
            tally_obtainTeam(this.options, this.loading);
        },
        filterMethod(query, item) {
            return item.pinyin.indexOf(query) > -1;
        },
        selectMember(){
            this.membersData=[];
            tally_selectMember(this.membersData, this.form);
        },


        /**我的团队方法**/
        deleteRow(index, rows) {
            myTeam_deleteTeam(index, rows);
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        showInput() {
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

        handleInputConfirm() {
            let inputValue = this.inputValue;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            this.inputVisible = false;
            this.inputValue = '';
        },
        addHandleInputConfirm() {
            let addInputValue = this.addInputValue;
            if (addInputValue) {
                this.addDynamicTags.push(addInputValue);
            }
            this.addInputVisible = false;
            this.addInputValue = '';
        },
        addHandleClose(tag) {
            this.addDynamicTags.splice(this.addDynamicTags.indexOf(tag), 1);
        },
        addShowInput() {
            this.addInputVisible = true;
            this.$nextTick(_ => {
                this.$refs.addSaveTagInput.$refs.input.focus();
            });
        },
        createTeam(){
            myTeam_createTeam(this.teamForm, this.addDynamicTags);
        }
        ,
        changeTeam(tag){
            this.teamInfo=[];
            if (tag.index == 0) {
                //所有团队
                myTeam_changeTeam(this.teamInfo);
            }
        }
    }
}
var Ctor = Vue.extend(Main)
var ctor = new Ctor().$mount('#app')

