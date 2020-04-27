import React from 'react';
import {Nav, Navbar, NavDropdown} from "react-bootstrap";
import * as routes from '../routes';
import {isAdmin, isAuth, withNavigation} from "../utils";
import {connect} from "react-redux";
import {signOut} from "../containers/User/actions";


export default connect(
  state => ({
    username: state.user.username
  }),
    dispatch => ({
      signOut: () => dispatch(signOut())
    })
)(({username, signOut}) => {
  return withNavigation(window.location.pathname) ? (
    <Navbar bg="primary" variant="dark">
      <Navbar.Brand href="/">TATTOO4U</Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link href={routes.GALLERY}>Gallery</Nav.Link>
        <Nav.Link href={routes.STUDIOS}>Studios</Nav.Link>
        <Nav.Link href={routes.MASTERS}>Masters</Nav.Link>
      </Nav>
      <Nav>
        {isAuth() ?
          <NavDropdown bsPrefix="nav-link user-dropdown" title={username} id="profile-nav-dropdown">
            <NavDropdown.Item href={routes.PROFILE}>Your Profile</NavDropdown.Item>
            {isAdmin() && <NavDropdown.Item href={routes.ADMIN}>Manage users</NavDropdown.Item>}
            <NavDropdown.Divider/>
            <NavDropdown.Item onClick={signOut}>Sign Out</NavDropdown.Item>
          </NavDropdown> :
          <Nav.Link href={routes.SIGN_IN}>SIGN IN</Nav.Link>
        }
      </Nav>
    </Navbar>
  ) : null
});
