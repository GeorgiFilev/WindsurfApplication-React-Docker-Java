import React, { Component } from 'react';
import { socket } from '../../services/socket.service';
import {connect } from 'react-redux';
import Cookies from 'js-cookie';
import axios from 'axios';

class Notifications extends Component{
  state={
    // postIDs:[],
    posts:[],
    notifications:[],
    updated: false
  }
  
  componentDidMount(){
    
    axios.get(`http://localhost:8080/posts/personPosts/`+ Cookies.get("user_id"))
    .then(res =>{
      // console.log(res.data);
      const posts = res.data;
      // let postIDs = []; 
      // posts.map( post => postIDs.push(post.id));
      this.setState({
        // postIDs
        posts
      })
    })
  }
  componentDidUpdate(prevProps, prevState, snapshot){
    let nqma = prevProps.messages;
    if(nqma == undefined){
      nqma = 0;
    }
    if(this.props.messages.length && nqma != this.props.messages){
       //&& this.state.postIDs.contains(this.props.messages[this.props.messages.length-1].post_id

       let allMessages = this.props.messages;
        let lastMessage = this.props.messages[this.props.messages.length-1];
        console.log(allMessages);
        this.state.posts.map(post =>{
        if(post.id == lastMessage.post_id){
          console.log(post);
          // this.state.notifications.push(lastMessage);
          let notifikacii = this.state.notifications;
          
          // notifikacii.push(lastMessage.post_id);
          notifikacii.push(post.title); 
          console.log(notifikacii);
          this.setState({
            postIDs: this.state.postIDs,
            notifications:notifikacii,
            updated:true
          });
          
        }
      });
  }
}
  render(){
    console.log(this.props);
    return(
      <div className="section">
        <div className="card z-depth-0">
          <div className="card-content">
            <span className="card-title"> Notifications</span>
            <ul className="notifications">
              {
                this.state.notifications[0] ? 
                this.state.notifications.map( notification =>
                  <li> A new message on post "{notification}"</li>
                )
              
                :
                null
              }
            </ul>
          </div>
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

export default connect(mapStateToProps)(Notifications)