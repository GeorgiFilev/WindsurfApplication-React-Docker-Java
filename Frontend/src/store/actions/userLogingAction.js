import {GET_USER, USER_ERROR} from '../types'
import axios from 'axios'

export const getUser = (id) => async dispatch => {
    
    try{
        const res = await axios.get(`http://localhost:8080/people/` + id)
        dispatch( {
            type: GET_USER,
            payload: res.data
        })
        console.log("logged user");
    }
    catch(e){
        dispatch( {
            type: USER_ERROR,
            payload: console.log(e),
        })
    }

}

