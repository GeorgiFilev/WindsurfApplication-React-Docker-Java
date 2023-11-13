import React from 'react';
import {NavLink} from 'react-router-dom';

const SignedOutLinks =() =>{
  return (
    <ul className="right">
        <li className="signIn"><NavLink to='/register'> Sign up</NavLink></li>
        <li className="logIn"><NavLink to='/login'> Log in</NavLink></li>
    </ul>
  )
}

export default SignedOutLinks;