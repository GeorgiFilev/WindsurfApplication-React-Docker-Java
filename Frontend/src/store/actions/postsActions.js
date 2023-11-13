import {GET_POSTS, POSTS_ERROR} from '../types'
import axios from 'axios'

export const getPosts = () => async dispatch => {
    
    try{
        const res = await axios.get(`http://localhost:8080/posts/all`)
        dispatch( {
            type: GET_POSTS,
            payload: res.data
        })
    }
    catch(e){
        dispatch( {
            type: POSTS_ERROR,
            payload: console.log(e),
        })
    }
}

export const addPost =(id,title,content) => async dispatch =>{
    try{
        const res = await axios.post(`http://localhost:8080/people/addPost/`+id,{"title": title,"content": content})
        .then(()=>{
            dispatch({
                type:'POST_POST',
                // payload:res.data
                payload:res.data
            })
        })
       
    }
    catch(e){
        dispatch({
            type:'POST_POST_ERROR',
            payload:console.log(e),
        })
    }
}
export const getPost= (id) => async dispatch=>{
    try{
        const res =await axios.get(`http://localhost:8080/posts/`+id)
        dispatch( {
            type: 'GET_POST',
            payload: res.data
        })
    }
    catch(e){
        dispatch( {
            type: 'GET_POST_ERROR',
            payload: console.log(e),
        })
    }
}
