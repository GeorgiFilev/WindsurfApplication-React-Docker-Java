import React, { Component } from 'react';
import SockJsClient from 'react-stomp';
import Cookies from 'js-cookie';

class Message extends Component{
constructor(props) {
  super(props);
  this.state = {
      messages: [],
      text: "",
      person_id: "",
      
  }
}



sendMessage = () => {
  this.clientRef.sendMessage('/app/user-all', JSON.stringify({
      name: this.state.name,
      message: this.state.typedMessage
  }));
};

componentDidMount(){
 const name = Cookies.get("user_username");
 this.setState({
   name
 })
}



render() {
  return (
      <div>
          {/* <NameComponent setName={this.setName}/> */}
          <div className="align-center">
              <h1>Welcome to Web Sockets</h1>
              <br/><br/>
          </div>
          <div className="align-center">
              User : <p className="title1"> {this.state.name}</p>
          </div>
          <div className="align-center">
              <br/><br/>
              <table>
                  <tr>
                      <td>
                          <textarea id="outlined-basic" label="Enter Message to Send" variant="outlined"
                                     onChange={(event) => {
                                         this.setState({typedMessage: event.target.value});
                                     }}/>
                      </td>
                      <td>
                          <button variant="contained" color="primary"
                                  onClick={this.sendMessage}>Send</button>
                      </td>
                  </tr>
              </table>
          </div>
          <br/><br/>
          <div className="align-center">
              {
                this.state.messages ?
                this.state.messages.map(message =>
                  <div key={message.name}> {message.typedMessage} - {message.name}
                  </div>
                )
                  :
                  null
                
              }
          </div>
          <SockJsClient url='http://localhost:8080/websocket-chat/'
                        topics={['/topic/user']}
                        onConnect={() => {
                            console.log("connected");
                        }}
                        onDisconnect={() => {
                            console.log("Disconnected");
                        }}
                        onMessage={(msg) => {
                            var jobs = this.state.messages;
                            jobs.push(msg);
                            this.setState({messages: jobs});
                            console.log(this.state);
                        }}
                        ref={(client) => {
                            this.clientRef = client;
                            console.log(client);
                        }}/>
      </div>
  )
}
// displayMessages = () => {
//   return (
//       <div>
//           {this.state.messages.map(msg => {
//               return (
//                   <div key="id">
//                       {this.state.name == msg.name ?
//                           <div>
//                               <p className="title1">{msg.name} : </p><br/>
//                               <p>{msg.message}</p>
//                           </div> :
//                           <div>
//                               <p className="title2">{msg.name} : </p><br/>
//                               <p>{msg.message}</p>
//                           </div>
//                       }
//                   </div>)
//           })}
//       </div>
//   );
// };
}
export default Message;
