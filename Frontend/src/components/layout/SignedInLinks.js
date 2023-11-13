import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
import AuthService from '../../services/auth.service';

class SignedInLinks extends Component{
  
  handleClick =() => {
    AuthService.logout();
  };
  render(){

    const initials = (this.props.currentUser).substring(0,2)
    return (
      <ul className="opitai right">
          <li><NavLink to='/create'> New Post</NavLink></li>
          <li>
            {/* <NavLink to='/'> Log Out</NavLink> */}
            <a href="/login" className="nav-link" onClick={this.handleClick}> Log Out</a>
          </li>
          <li><NavLink to='/profile' className='krug btn btn-floating pink lightend-1 hoverable'>{initials}</NavLink></li>
      </ul>
    )
  }
}


export default SignedInLinks;