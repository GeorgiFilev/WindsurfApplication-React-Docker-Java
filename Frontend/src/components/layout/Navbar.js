import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import SignedInLinks from './SignedInLinks'
import SignedOutLinks from './SignedOutLink'
import AuthService from '../../services/auth.service'

class Navbar extends Component{
  constructor(props) {
    super(props);

    this.state = {
      currentUser: undefined
    };
  }

  componentDidMount() {
  
    const user = AuthService.getCurrentUser();
    console.log(user)
    if (user) {
      this.setState({
        currentUser: user
      });
    }
  }

  render(){
  return (
    <nav className="nav-wrapper grey darken-3">
      <div className="container">
        <Link to='/' className="waves-effect brand-logo hoverable" > Windsurfing</Link>
        {
       
          
          this.state.currentUser !== undefined 
            ? <SignedInLinks currentUser={this.state.currentUser.username}/> 
            : <SignedOutLinks/>
        }
      </div>
    </nav>
  )}
}

export default Navbar;