import { combineReducers } from 'redux'
import userReducer from './userReducers'
import postReducer from './postReducers'
import loggedUserReducer from './loggedUserReducer'
import messageReducer from './messageReducer'

export default combineReducers({
  users: userReducer,
  posts: postReducer,
  user: loggedUserReducer,
  messages:messageReducer
})