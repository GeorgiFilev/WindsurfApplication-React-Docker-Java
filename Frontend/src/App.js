import './App.css';
import React, { Component } from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Navbar from './components/layout/Navbar';
import Dashboard from './components/dashboard/Dashboard';
import ProjectDetails from './components/projects/ProjectDetails';
import SignIn from './components/auth/SignIn';
import SignUp from './components/auth/SignUp';
import CreateProject from './components/projects/CreateProject';
import Users from './components/users';
import Register from './components/Authentication/register.component';
import Login from '../src/components/Authentication/login.component';
import Profile from './components/Authentication/profile.component';
import AuthService from './services/auth.service'
import {getUser} from './store/actions/userLogingAction'
import {connect} from 'react-redux';
import Cookies from 'js-cookie'
import axios from 'axios';
import Message from './components/auth/Message'
import { socket } from './services/socket.service';

class App extends Component {
  
  constructor(props) {
    super(props);

    this.state = {
      showModeratorBoard: false,
      showAdminBoard: false,
       currentUser: undefined,
      userRoles:false
    };
  }
  // const readCookie =() =>{
  //   const user = Cookies.get("user_id");
  //   if(user){

  //   }
  // }

  componentDidMount() {
      socket.connect();
  // const user = this.props.user.user;
    const user = AuthService.getCurrentUser();
    // if(user != null){this.props.getUser(user.id)}

    // const user = Cookies.get("user");
      console.log(user);
      if (user) {
        this.setState({
          currentUser: user
          // showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
          // showAdminBoard: user.roles.includes("ROLE_ADMIN"),
        });
      }
   
     

      // console.log(roles);
      // if(roles){
      //   this.setState({
      //     userRoles:roles
      //   })
      // }
  }

  // logOut() {
  //   AuthService.logout();
  // }

  render(){
  
    return (
      <BrowserRouter>
        <div className="App">
          <Navbar currentUser={this.state.currentUser}/>
          <Switch >
            <Route exact path='/' component={Dashboard} />
            <Route path='/project/:id' component={ProjectDetails}/>
            {/* <Route path='/signin' component={SignIn}/>
            <Route path='/signup' component={SignUp}/> */}
            <Route path='/create' component={CreateProject}/>
            <Route path='/register' component={Register}/>
            <Route path="/login" component={Login}/>
            {/* <Route path='/settings' component={Settings}/> */}
            <Route path="/profile" component={Profile}/>
            
          </Switch>
        </div>
      </BrowserRouter>
    );
  }
}
export default App;
// const mapStateToProps  = (state) => ({user:state.user,posts:state.posts,users:state.users})

// export default connect(mapStateToProps)(App);



// import React, { Component } from "react";
// import { Switch, Route, Link } from "react-router-dom";
// // import "bootstrap/dist/css/bootstrap.min.css";
// import "./App.css";

// import AuthService from "./services/auth.service";

// import Login from "./components/Authentication/login.component";
// import Register from "./components/Authentication/register.component";
// import Home from "./components/pages/home.component";
// import Profile from "./components/Authentication/profile.component";
// import BoardUser from "./components/pages/board-user.component";
// import BoardModerator from "./components/pages/board-moderator.component";
// import BoardAdmin from "./components/pages/board-admin.component";

// class App extends Component {
//   constructor(props) {
//     super(props);
//     this.logOut = this.logOut.bind(this);

//     this.state = {
//       showModeratorBoard: false,
//       showAdminBoard: false,
//       currentUser: undefined,
//     };
//   }

//   componentDidMount() {
//     const user = AuthService.getCurrentUser();

//     if (user) {
//       this.setState({
//         currentUser: user,
//         showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
//         showAdminBoard: user.roles.includes("ROLE_ADMIN"),
//       });
//     }
//   }

//   logOut() {
//     AuthService.logout();
//   }

//   render() {
//     const { currentUser, showModeratorBoard, showAdminBoard } = this.state;

//     return (
//       <div>
      
//         <nav className="navbar navbar-expand navbar-dark bg-dark">
//           <Link to={"/"} className="navbar-brand">
//             bezKoder
//           </Link>
//           <div className="navbar-nav mr-auto">
//             <li className="nav-item">
//               <Link to={"/home"} className="nav-link">
//                 Home
//               </Link>
//             </li>

//             {showModeratorBoard && (
//               <li className="nav-item">
//                 <Link to={"/mod"} className="nav-link">
//                   Moderator Board
//                 </Link>
//               </li>
//             )}

//             {showAdminBoard && (
//               <li className="nav-item">
//                 <Link to={"/admin"} className="nav-link">
//                   Admin Board
//                 </Link>
//               </li>
//             )}

//             {currentUser && (
//               <li className="nav-item">
//                 <Link to={"/user"} className="nav-link">
//                   User
//                 </Link>
//               </li>
//             )}
//           </div>

//           {currentUser ? (
//             <div className="navbar-nav ml-auto">
//               <li className="nav-item">
//                 <Link to={"/profile"} className="nav-link">
//                   {currentUser.username}
//                 </Link>
//               </li>
//               <li className="nav-item">
//                 <a href="/login" className="nav-link" onClick={this.logOut}>
//                   LogOut
//                 </a>
//               </li>
//             </div>
//           ) : (
//             <div className="navbar-nav ml-auto">
//               <li className="nav-item">
//                 <Link to={"/login"} className="nav-link">
//                   Login
//                 </Link>
//               </li>

//               <li className="nav-item">
//                 <Link to={"/register"} className="nav-link">
//                   Sign Up
//                 </Link>
//               </li>
//             </div>
//           )}
//         </nav>

//         <div className="container mt-3">
//           <Switch>
//             <Route exact path={["/", "/home"]} component={Home} />
//             <Route exact path="/login" component={Login} />
//             <Route exact path="/register" component={Register} />
//             <Route exact path="/profile" component={Profile} />
//             <Route path="/user" component={BoardUser} />
//             <Route path="/mod" component={BoardModerator} />
//             <Route path="/admin" component={BoardAdmin} />
//           </Switch>
//         </div>
//       </div>
//     );
//   }
// }

// export default App;