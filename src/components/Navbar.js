import React from 'react';
import {Nav, Navbar, NavDropdown} from "react-bootstrap";
import * as routes from '../routes';
import {isAuth, withNavigation} from "../utils";
import {connect} from "react-redux";


export default connect(null)(() => {
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
          <NavDropdown title="User " id="profile-nav-dropdown">
            <NavDropdown.Item href={routes.PROFILE}>Your Profile</NavDropdown.Item>
            <NavDropdown.Divider/>
            <NavDropdown.Item>Sign Out</NavDropdown.Item>
          </NavDropdown> :
          <Nav.Link href={routes.SIGN_IN}>SIGN IN</Nav.Link>
        }
      </Nav>
    </Navbar>
  ) : null
});
