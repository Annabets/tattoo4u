import React from 'react';
import {connect} from "react-redux";
import {getUsersList, banUser, unbanUser} from "./actions";
import {Table, Button} from "react-bootstrap";

class Admin extends React.Component {
  constructor(props){
    super(props)
  }

  componentDidMount() {
    this.props.getUsersList();
  }

  handleBanUser = userId => () => {
    this.props.banUser(userId, () => this.props.getUsersList());
  };

  handleUnbanUser = userId => () => {
    this.props.unbanUser(userId, () => this.props.getUsersList());
  };

  render() {
    const {users} = this.props;
    return(
      <div className="container-fluid">
        <Table>
          <thead>
          <tr>
            <th>id</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>{user.role}</td>
              <td>{user.banned ? <span className="text-danger">banned</span> : <span className="text-success">active</span>}</td>
              <td>{user.banned ? <Button block onClick={this.handleUnbanUser(user.id)}>Unban</Button> :
                <Button block onClick={this.handleBanUser(user.id)}>Ban</Button>}</td>
            </tr>
          ))}
          </tbody>
        </Table>
      </div>
    )
  }
}

export default connect(
  state => ({
    users: state.admin.users,
  }),
  dispatch => ({
    getUsersList: () => dispatch(getUsersList()),
    banUser: (userId, cb) => dispatch(banUser(userId, cb)),
    unbanUser: (userId, cb) => dispatch(unbanUser(userId, cb))
  })
)(Admin)
