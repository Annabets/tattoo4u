import React from 'react';
import {Nav, Navbar, NavDropdown} from "react-bootstrap";

export default ({user}) => {
  return (
    <Navbar bg="primary" variant="dark">
      <Navbar.Brand href="/">TATTOO4U</Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link href="/gallery">Gallery</Nav.Link>
        <Nav.Link href="/studios">Studios</Nav.Link>
        <Nav.Link href="/masters">Masters</Nav.Link>
      </Nav>
      <Nav>
        {user ?
          <NavDropdown title={user.userName} id="profile-nav-dropdown">
            <NavDropdown.Item href="/profile">My profile</NavDropdown.Item>
            <NavDropdown.Divider/>
            <NavDropdown.Item>Sign Out</NavDropdown.Item>
          </NavDropdown> :
          <Nav.Link href="/signIn">SIGN IN</Nav.Link>
        }
      </Nav>
    </Navbar>
  )
};
