import SockJs from 'sockjs-client';
import Stomp from 'stompjs';
import { getMessage } from '../store/actions/messageActions';
import store from '../store/store'

let stompClient = null;

export const socket={
  connect : () => {
    let socket = new SockJs('http://localhost:8080/socket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, () => {
            stompClient.subscribe('/store', message => {
                store.dispatch(getMessage( JSON.parse(message.body)))
            });
        });
  },

  send: (message) => {
    stompClient.send("/messages", {}, JSON.stringify(message));
    console.log(message);
}
}