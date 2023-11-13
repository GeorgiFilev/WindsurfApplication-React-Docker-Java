// // 

// import React, { Component } from "react";
// import { Redirect } from 'react-router-dom';
// import { connect } from "react-redux";
// import Cookies from 'js-cookie';
// import AuthService from '../../services/auth.service';
// import axios from 'axios'

// class Profile extends Component {

//   state={
//     currentUser:null
//   }

//   getUser =(id) =>{
//     axios
//     .get("http://localhost:8080/people/" + id)
//     .then(data => this.setState({currentUser:data.data}))
//     .catch(err =>{
//       console.log(err);
//       return null;
//     });
//   };

//   componentDidMount(){
//     const user = AuthService.getCurrentUser();
//     console.log(user);
//     // this.getUser(userID);
//   }

//   render() {
//     // const { user: currentUser } = this.props;
//     // const userID= Cookies.get("user_id");
//     //   const currentUser = AuthService.getUserByID(userID);
//     //   console.log(currentUser);
//     // if (this.state.currentUser == null ) {
//     //   return <Redirect to="/login" />;
//     // }
//     console.log(this.state);
//     return (
//       <div className="container">
//         <header className="jumbotron">
//           <h3>
//             <strong>{this.state.currentUser.username}</strong> Profile
//           </h3>
//         </header>
//         <p>
//           {/* <strong>Token:</strong> {currentUser.accessToken.substring(0, 20)} ...{" "}
//           {currentUser.accessToken.substr(currentUser.accessToken.length - 20)} */}
//         </p>
//         <p>
//           <strong>Id:</strong> {this.state.currentUser.id}
//         </p>
//         <p>
//           <strong>Email:</strong> {this.state.currentUser.email}
//         </p>
//         <strong>Authorities:</strong>
//         <ul>
//           {this.state.currentUser.roles &&
//             this.state.currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
//         </ul>
//       </div>
//     );
//   }
// }

// export default Profile;
import React, { Component } from "react";
import AuthService from "../../services/auth.service";
import Cookies from 'js-cookie';
import axios from 'axios';
import Users from '../users';

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser(),
    };
  }

  componentDidMount(){
    const roles = Cookies.get("user_roles");
    console.log(roles);
    if(roles){
      if(roles.includes("ROLE_MODERATOR")){
        this.setState({
          showModeratorBoard:true
        })
      }
      if(roles.includes("ROLE_ADMIN")){
        this.setState({
          showAdminBoard:true
        })
      }
    }
  }
  
  handleChange =(e) =>{
    this.setState({
      [e.target.id]:e.target.value
    })
  }
  handleSubmit=(e)=>{
    const password = this.state.password;
    console.log(" length of the pass =" +password.length );
    e.preventDefault();
    if(password.length >= 6){
      axios.put(`http://localhost:8080/people/update/` + this.state.currentUser.id, {password : password,username : this.state.currentUser.username, email : this.state.currentUser.email });
      Cookies.remove("password");
      Cookies.set("password",this.state.password,{expires: 700000});

          this.props.history.push("/profile");
          window.location.reload();
    }
    else{
      alert("your password is too weak");
    }
    
  }

  render() {
    const  currentUser  = this.state.currentUser;
    const password = Cookies.get("password");
    console.log(this.state.password);

    return (
   
      <div className="container pink">
         {
        this.state.showAdminBoard==true ? 
        <div>
          <h1 className="white">Hello Admin</h1>
          <section>
            <Users /> 
          </section>
          
        </div>
        :
        <div>
        </div>
      }
      <div className="section">
        <header className="jumbotron">
          <h3>
            <strong>{currentUser.username}</strong> Profile
          </h3>
        </header>
        <hr></hr>
        <h4>
          <strong>Email:</strong>{" "}
          {currentUser.email}
        </h4>
        <h5>
          <strong>Token:</strong>{" "}
          {currentUser.accessToken.substring(0, 20)} ...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
        </h5>
        <strong className="center">Authorities:</strong>
        <ul>
          {currentUser.roles &&
            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
        </ul>
        
        <h5>
          Your current password is: {password}
          <input id="password" placeholder="set your new password here" onChange={this.handleChange}/>
        </h5>
        <form onSubmit={this.handleSubmit}>
            <button className="waves-effect waves-light btn " >Done</button>
        </form>
        
        </div>
     
    

        
      
     
      </div>
    );
  }
}