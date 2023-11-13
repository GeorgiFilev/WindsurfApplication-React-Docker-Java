import React, { Component } from 'react'
import {connect} from 'react-redux'
// import {getPosts} from '../../store/actions/postsActions'
import axios from 'axios'
import { Link } from 'react-router-dom';
import Cookies from 'js-cookie';
import SockJsClient from 'react-stomp';
import { socket } from '../../services/socket.service';
import {v4 as uuidv4} from 'uuid';
// import SockJs from 'sockjs-client';



class ProjectDetails extends Component{
  state = {
    post:[],
    message:"",
  }

  componentDidMount(){   
 

    const id = this.props.match.params.id;
  axios.get(`http://localhost:8080/posts/` + id)
    .then(res => {
      const post = res.data;
      this.setState( {
        post
      } );
    })
  }
  componentDidUpdate(prevProps, prevState, snapshot){
    if(prevState.post.messages && this.props.messages[0] && prevProps.messages != this.props.messages){
    if(prevState.post.messages[prevState.post.messages.length-1] != this.props.messages[this.props.messages.length -  1] && this.state.post.messages){
      let postMessages = this.state.post.messages;
      let lastMessage = this.props.messages[this.props.messages.length-1];
      if(lastMessage.post_id == this.state.post.id){
        postMessages.push(lastMessage)
        this.setState({
          post:{
            ...this.state.post,
            messages: postMessages
          }
        })
        // console.log(this.props.messages);
      }
      }
     
  }
  }
  handleDelete=(e) =>{
    const id = this.state.post.id
    axios.delete(`http://localhost:8080/posts/delete/`+ id)
  }

  handleChange=(e)=>{
    this.setState({
      message: e.target.value 
    })
  }

  // handleSendMessage=(e)=>{
    
  //   const postID = this.state.post.id;
  //   const personID = Cookies.get("user_id");
  //   const message = this.state.message;
  //   axios.post(`http://localhost:8080/posts/addMessage/`+this.state.post.id,{ "text":message,"post_id":postID,"person_id":personID})
  //   .then( this.setState({
  //     message:""
  //   })
  //   )
  // }
  sendMessage = () => {
    const postID = this.state.post.id;
    const personID = Cookies.get("user_id");
    const author = Cookies.get("user_username");
    const message = this.state.message;

    // this.clientRef.sendMessage('/app/user-all', JSON.stringify({
    //   "text":message,"post_id":postID,"person_id":personID,"author":author
    // }))
    socket.send( {"text":message,"post_id":postID,"person_id":personID,"author":author});
    
    console.log(this.props.messages);
    document.getElementById("input").value="";
}

   
    render(){
      console.log(Cookies.get("user_id"),this.state.post.personID); 
    // console.log(this.state.post.messages);
      return (

        

        <div className="ark section project-details blue border ">
        
        {/* <SockJsClient url='http://localhost:8080/websocket-chat/'
    topics={['/topic/user']}
    onConnect={() => {
        console.log("connected");
    }}
    onDisconnect={() => {
        console.log("Disconnected");
    }}
    onMessage={(msg) => {
                              console.log(msg);
                              
                              var messages = this.state.post.messages;
                              messages.push(msg);
                              this.setState({ messages: messages});
                              console.log(this.state);
                              const postID = this.state.post.id;
                              const personID = Cookies.get("user_id");
                              const message = this.state.message;
                              axios.post(`http://localhost:8080/posts/addMessage/`+this.state.post.id,{ "text":message,"post_id":postID,"person_id":personID})
                              .then( this.setState({
                                message:""
                              })
                              )
    }
    }
    ref={(client) => {
        this.clientRef = client
}}/> */}
          <div className="card z-depth-0">
            <div className="card-content">
              <span className="card-title"> Project Title - {this.state.post.title}</span>
              <p> {this.state.post.content}</p>
              {
                Cookies.get("user_id")==this.state.post.personID ? 
                  <button className="waves-effect waves-light btn-large red-text right" onClick={this.handleDelete}>Delete</button>
                  :
                  null
              }
              
            </div>
          </div>
          <div className="card-action gray lighten-4 gray-text">
            <div>Posted by :{this.state.post.author}</div>
            <div>
              <h4>Comments</h4>
              <hr></hr>  
            </div>
             
              <div>    
              {
                this.state.post.messages  ? 
                this.state.post.messages.map(m => 
                        m ? 
                         <React.Fragment key={uuidv4()}>
                             <h6 className="abzatz"><ul> <b>{m.author}</b> :   {m.text}</ul></h6> 
                         </React.Fragment>
                         :
                         null
                    )
                    :
                    <p>...loading </p>
                    }
                
              </div>
            {
            Cookies.get("user_id") ? 
                  <table className="message">
                    <td >
                      <input id="input" type="text" className="white" autoFocus onChange={this.handleChange}></input>
                    </td>
                    <td>
                      <button className="waves-effect waves-light btn-large red-text" onClick={this.sendMessage}>Send</button>
                    </td>
                  </table>
                  :
                  null
           
            }
            
            
          </div>
        </div>
      )
    }
    
  }

  const mapStateToProps = (state) => {
    return {
        messages: state.messages.messages
    }
}

export default connect(mapStateToProps)(ProjectDetails)

