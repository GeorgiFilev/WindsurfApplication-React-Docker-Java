import {GET_POSTS} from '../types';
//import {POST_POST} from '../types';

const initialState = {
    posts:[],
    loading:true
}

export default function(state = initialState, action){

    switch(action.type){

        case GET_POSTS:
        return {
            ...state,
            posts:action.payload,
            loading:false

        }
        case 'POST_POST':
            return{
                ...state,
                posts:action.payload,
                loading:false
            }
        case 'POST_POST_ERROR':
            console.log('create post error',action.err)
            return state;

        case 'GET_POST':
            console.log('post given')
            return{
                ...state,
                post:action.payload,
                loading:false
            }
        case 'GET_POST_ERROR':
            console.log('get post error',action.err)
            return state;
        default: return state
    }

}