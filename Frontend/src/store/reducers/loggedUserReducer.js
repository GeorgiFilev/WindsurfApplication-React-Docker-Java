import { GET_USER, USER_ERROR} from '../types'

const initialState = {
    user:[],
    loading:true
}

export default function(state = initialState, action){

    switch(action.type){

        case GET_USER:
        return {
            user:action.payload,
            loading:false
        }
        case 'DISPLAY_USER':
          return{
            ...state,
            loading: false
          }
        case 'REMOVE_USER':
          return{
            user:null,
            loading:true
          }
        case USER_ERROR:
            console.log('error during users actions')    
          return{
            ...state
          }
        default: return state
      }


}