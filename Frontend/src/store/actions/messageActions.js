
export const getMessage = (message) => {

  return(dispatch,getState) => {
    if(message){
    let messages = getState().messages.messages;
    console.log(messages);
  
    messages.push(message);
    dispatch({type:'GET_MESSAGE',payload:[]});
    dispatch({type:'GET_MESSAGE',payload:messages})
    }
  }

}