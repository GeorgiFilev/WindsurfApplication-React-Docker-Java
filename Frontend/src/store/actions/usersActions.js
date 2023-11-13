import {GET_USERS, USERS_ERROR} from '../types'
import axios from 'axios'

export const getUsers = () => async dispatch => {
    
    try{
        const res = await axios.get(`http://localhost:8080/people/all`)
        dispatch( {
            type: GET_USERS,
            payload: res.data
        })
    }
    catch(e){
        dispatch( {
            type: USERS_ERROR,
            payload: console.log(e),
        })
    }

}

export const addUsers = (user) => async dispatch =>{
    try{
        const res = await axios.post(`http://localhost:8080/people/add`,user)
        dispatch({
            type:'POST_USER',
            payload:res.data
        })
    }
    catch(e){
        dispatch({
            type:'POST_USER_ERROR',
            payload:console.log(e)
        })
    }
}