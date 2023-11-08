import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
    state: { // 存放状态
        loginStatus: false,
        authData: {},
        appUserId: '',
        token:'',
    },
    getters: {
        loginStatus: state => {
            
        },
        token: state => {
            return state.token
        },
        authData: state => {
            return state.authData
        },
    },
    mutations: { 
		//更改状态的逻辑，同步操作
        //所有mutations中的方法的第一个参数一定是state变量，用来进行对state中的状态的操作
        //第二个参数是可选参数，用于调用该 mutations 方法的时候传参
        wxLogin(state,params){
            console.log('params :'+JSON.stringify(params))
            state.authData = params.authData
            state.token = params.token
            state.loginStatus = true
        },
        authData(state,authData){
            console.log('vuex:authData :'+JSON.stringify(authData))
            state.authData = authData
        },
        setToken(state,token){
            console.log('setToken:'+token)
            state.token = token
        },
    },
    actions: {
        //mutation中只能进行同步操作，actions可以进行异步操作
    },
})

export default store
