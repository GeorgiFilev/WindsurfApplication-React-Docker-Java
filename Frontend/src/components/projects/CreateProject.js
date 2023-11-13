import React, { Component } from 'react'
import {connect} from 'react-redux'
// import { createProject } from "../../store/actions/projectActions"
import { addPost} from '../../store/actions/postsActions';
import AuthService from '../../services/auth.service'

class CreateProject extends Component {
  state={
    title:'',
    content:'',
    currentUser: AuthService.getCurrentUser()
  }
  componentDidMount(){
    console.log(this.state.currentUser.id)
  }
  handleChange =(e) =>{
    this.setState({
      [e.target.id]:e.target.value
    })
  }
  handleSubmit =(e)=>{
    
      console.log(this.state);
      this.props.addPost(this.state.currentUser.id,this.state.title, this.state.content);
      // this.props.createProject(this.state)

  }
  render() {
    return (
      <div className="container">
        <form onSubmit={this.handleSubmit} className="white">
          <h5 className="gray-text text-darken-3">Create New Post</h5>
          <div className="input-field">
            <label htmlFor="title">Title</label>
            <input type="text" id="title" onChange={this.handleChange}/>
          </div>
          <div className="input-field">
            <label htmlFor="content">Project Content</label>
            <textarea id="content" className="materialize-textarea" onChange={this.handleChange}></textarea>
          </div>
          <div className="input-field">
            <button className="waves-effect btn-large pink hoverable">Create</button>
          </div>
        </form>
      </div>
    )
  }
}
// const mapDispatchToProps = (dispatch) =>{
//   return {
//     createProject: (project) => dispatch(createProject(project))
//   }
// }

// export default connect(null,mapDispatchToProps)(CreateProject);
const mapStateToProps = (state) => ({posts:state.posts})
export default connect(mapStateToProps,{addPost})(CreateProject);
