import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button} from "react-bootstrap";
import {signUp} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_IN} from "../../../routes";

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

  render() {
    const {username, password, confirmPassword, email } = this.state;
    return (
      <div className="sign-in" style={{backgroundImage: `url(${bgImg})`}}>
        <div className="form-container">
          <div className="form-content">
            <h2 className="form-title">Sign Up as User</h2>
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
              <Form.Text className="text-secondary mt-2">
                Or <a href={SIGN_IN}>sign in now!</a>
              </Form.Text>
            </Form>
          </div>
        </div>
      </div>
    )
  }
}

SignUp.propTypes = {
  signUp: PropTypes.func,
};

export default connect(null, dispatch => ({signUp: data => dispatch(signUp(data))}))(SignUp);
