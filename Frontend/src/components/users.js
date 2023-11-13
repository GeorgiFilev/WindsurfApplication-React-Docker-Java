import React, { Component } from 'react'
import {connect} from 'react-redux'
import {getUsers} from '../store/actions/usersActions'

 class users extends Component {
    componentDidMount(){
        this.props.getUsers()
        
    }
    render() {
        const {users} = this.props.users
        console.log(users)

        if(users.length>0){
            return (
                <div className="brown">
                
                    {users.map(u => 
                         <React.Fragment key={u.id}>
                             <h6 className="center ">{u.name} - {u.email}</h6> 
                         </React.Fragment>
                    )}
                </div>
            )
        }
        else{
            return(
                <div>
                    <h1> no users</h1>
                </div>
            )
        } 
     
    }
}

const mapStateToProps  = (state) => ({users:state.users})

export default connect(mapStateToProps, {getUsers})(users)
