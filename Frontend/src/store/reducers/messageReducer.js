
const initialState = {
    messages:[]
}

export default function(state = initialState, action){

    switch(action.type){

        case 'GET_MESSAGE':
          console.log(action.payload);
        return {
            messages:action.payload
        }
        default: return state
    }

}