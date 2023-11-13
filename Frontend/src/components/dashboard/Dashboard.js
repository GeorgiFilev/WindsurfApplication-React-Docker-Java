import React, { Component } from 'react';
import Notifications from './Notifications';
import ProjectList from '../projects/ProjectList'
import {connect} from 'react-redux'
import { getPosts } from '../../store/actions/postsActions'

class Dashboard extends Component {
  componentDidMount(){
    this.props.getPosts()

  }
  render(){
    //console.log(this.props)
    const { posts } = this.props.posts;
    // console.log(posts)

    return(
      <div className="dashboard container">
        <div className="row">
          <div className="col s12 m6">
            <ProjectList projects={posts}/>
          </div>
          <div className="col s12 m5 offset-m1">
            <Notifications/>
          </div>
         
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({posts:state.posts})
export default connect(mapStateToProps,{getPosts})(Dashboard);