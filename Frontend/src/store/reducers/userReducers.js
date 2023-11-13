import {GET_USERS, USERS_ERROR} from '../types'

const initialState = {
    users:[],
    loading:true
}

export default function(state = initialState, action){

    switch(action.type){

        case GET_USERS:
        return {
            ...state,
            users:action.payload,
            loading:false

        }
        case 'POST_USER':
            return{
                ...state,
                users:action.payload,
                loading:false
            }
        case USERS_ERROR:
            console.log('error during users actions')    
        return{
            ...state
            }
        default: return state
    }

}