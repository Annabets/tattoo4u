import React from 'react';
import {connect} from "react-redux";
import {getUsersList, banUser, unbanUser, getNotConfirmedStudios, confirmStudio} from "./actions";
import {Table, Button, Tabs, Tab} from "react-bootstrap";
import {MASTERS, STUDIOS} from "../../routes";

class Admin extends React.Component {
  constructor(props){
    super(props)
  }

  componentDidMount() {
    this.props.getUsersList();
    this.props.getNotConfirmedStudios();
  }

  handleBanUser = userId => () => {
    this.props.banUser(userId, () => this.props.getUsersList());
  };

  handleUnbanUser = userId => () => {
    this.props.unbanUser(userId, () => this.props.getUsersList());
  };

  handleConfirmStudio = studioId => () => this.props.confirmStudio(studioId);

  renderUsersList = users => (
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
  );

  renderStudiosList = studios => (
    <Table>
      <thead>
      <tr>
        <th>id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Address</th>
        <th>Contact</th>
        <th>Owner</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      {studios.map(studio => (
        <tr key={studio.id}>
          <td>{studio.id}</td>
          <td><a href={`${STUDIOS}/${studio.id}`}>{studio.name}</a></td>
          <td>{studio.description}</td>
          <td>{studio.address}</td>
          <td>{studio.contact}</td>
          <td><a href={`${MASTERS}/${studio.ownerId}`}>{studio.owner}</a></td>
          <td><Button block onClick={this.handleConfirmStudio(studio.id)}>Confirm</Button></td>
        </tr>
      ))}
      </tbody>
    </Table>
  );

  render() {
    const {users, studios} = this.props;
    return(
      <div className="container-fluid">
        <Tabs defaultActiveKey="manageUsers">
          <Tab eventKey="manageUsers" title="Manage Users">
            {this.renderUsersList(users)}
          </Tab>
          <Tab eventKey="manageStudios" title="Manage Studios">
            {this.renderStudiosList(studios)}
          </Tab>
        </Tabs>

      </div>
    )
  }
}

export default connect(
  state => ({
    users: state.admin.users,
    studios: state.admin.studios,
  }),
  dispatch => ({
    getUsersList: () => dispatch(getUsersList()),
    banUser: (userId, cb) => dispatch(banUser(userId, cb)),
    unbanUser: (userId, cb) => dispatch(unbanUser(userId, cb)),
    getNotConfirmedStudios: () => dispatch(getNotConfirmedStudios()),
    confirmStudio: studioId => dispatch(confirmStudio(studioId)),
  })
)(Admin)
