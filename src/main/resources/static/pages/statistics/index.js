new Vue({
    el: '#app',
    data: function (){
        return{
            questionnaireName: localStorage.getItem("questionnaireName"),
            questionData: [],
            tableData: [],
            dialogVisible: false
        }
    },
    async mounted(){
        await this.getQuestionList()
        let infoData = []

        this.questionData.forEach((value, index) => {
            let questionOption = JSON.parse(value.questionOption)
            let count = 0
            infoData.push({questionId: value.id, count, questionName: value.questionName, questionOption})
        })
        this.tableData = infoData
        console.log(infoData)
    },
    methods: {
        async getQuestionList(){
            let params = {
                questionnaireId: localStorage.getItem("questionnaireId")
            }
            let res = await fetch('/question/statistic', {method: 'POST', body: JSON.stringify(params),
                headers: {"Content-Type": "application/json"}}).then(e =>{
                return e.json()
            })
            this.questionData = res.data
        },
        async getQuestionCount(){

        },
        handleClickBack(){
            history.back()

        }
    }
})