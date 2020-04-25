import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button, Nav} from "react-bootstrap";
import {signUp} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_IN} from "../../../routes";
import {history} from "../../../utils";

class SignUp extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
      confirmPassword: '',
      email: '',
      role: 'USER',
    }
  }

  handleSubmit = e => {
    e.preventDefault();
    if (this.state.password === this.state.confirmPassword){
      this.props.signUp(this.state);
    }
  };

  topNav = () => (
    <Nav fill variant="pills" defaultActiveKey="USER" onSelect={eventKey => this.setState({role: eventKey})}>
      <Nav.Item>
        <Nav.Link eventKey="USER">User</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link eventKey="MASTER">Tattoo master</Nav.Link>
      </Nav.Item>
    </Nav>
  )

  render() {
    const {username, password, confirmPassword, email, role} = this.state;
    return (
      <>
        <div className="sign-in" style={{backgroundImage: `url(${bgImg})`}}>
          <Button variant="link" className="--close" onClick={() => history.goBack()}>Back</Button>
          <div className="form-container">
            <div className="form-content">
              {this.topNav()}
              <h2 className="form-title">Sign Up as {role === "USER" ? 'User' : 'Master'}</h2>
              <Form onSubmit={this.handleSubmit}>
                <Form.Group>
                  <Form.Control
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={e => this.setState({username: e.target.value})}
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={e => this.setState({email: e.target.value})}
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={e => this.setState({password: e.target.value})}
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={e => this.setState({confirmPassword: e.target.value})}
                  />
                </Form.Group>
                <Button variant="primary" type="submit" block className="form-btn">
                  Sign Up
                </Button>
                <Form.Label className="text-secondary mt-2">
                  <span className="align-middle">Or </span>
                  <Button variant="link" className="p-0" onClick={() => history.replace(SIGN_IN)}>sign in now!</Button>
                </Form.Label>
              </Form>
            </div>
          </div>
        </div>

      </>
    )
  }
}

SignUp.propTypes = {
  signUp: PropTypes.func,
};

export default connect(null, dispatch => ({signUp: data => dispatch(signUp(data))}))(SignUp);
