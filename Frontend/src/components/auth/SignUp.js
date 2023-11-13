import React, { Component } from 'react'
import axios from 'axios'
import {connect} from 'react-redux'
import {addUsers} from '../../store/actions/usersActions'

class SignUp extends Component {
  state={
    email:"",
    password:"",
    confirm_password:"",
    name:"",
    country:""

  }
  handleChange =(e) =>{
    this.setState({
      [e.target.id]:e.target.value
    })
  }
  handleSubmit =(e)=>{
     if(this.state.email === "" || this.state.password==="" || this.state.name==="" || this.state.country ==="" || this.state.confirm_password ===""){
      
       alert("you have to fill all the data")
      }
      else if(this.state.password !== this.state.confirm_password){
          alert("Your passwords does not match")
      }

     else{
      this.props.addUsers(this.state)
      this.setState({
        email:"",
        password:"",
        firstName:"",
        lastName:''
      })
     }
      
  }
  render() {
    return (
      <div className="container">
        <form onSubmit={this.handleSubmit} className="white">
          <h5 className="gray-text text-darken-3">Sign Up</h5>
          <div className="input-field">
            <label htmlFor="email">Email</label>
            <input type="email" id="email" onChange={this.handleChange}/>
          </div>
          <div className="input-field">
            <label htmlFor="firstName">Name</label>
            <input type="text" id="name" onChange={this.handleChange}/>
          </div>
          <div className="input-field">
            <label htmlFor="password">Password</label>
            <input type="password" id="password" onChange={this.handleChange}/>
          </div>
          <div className="input-field">
            <label htmlFor="password">Confirm Password</label>
            <input type="password" id="confirm_password" onChange={this.handleChange}/>
          </div>
          <div className="input-field">
            <label htmlFor="country">Country</label>
            <input type="text" id="country" onChange={this.handleChange}/>
          </div>
          <div className="input-field center-align" >
            <button className="waves-effect btn-large pink hoverable">Sign Up</button>
          </div>
        </form>
      </div>
    )
  }
}
const mapStateToProps = (state) => ({
  users:state.users
})

export default connect(mapStateToProps,{addUsers})(SignUp);
