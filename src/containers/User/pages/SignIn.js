import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button} from "react-bootstrap";
import {signIn} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_UP} from "../../../routes";

class SignIn extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      userName: '',
      password: '',
    }
  }

  handleSubmit = e => {
    e.preventDefault();
    this.props.signIn(this.state);
  };

  render() {
    const {userName, password} = this.state;
    return (
      <div className="sign-in" style={{backgroundImage: `url(${bgImg})`}}>
        <div className="form-container">
          <div className="form-content">
            <h2 className="form-title">Sign In</h2>
            <Form onSubmit={this.handleSubmit}>
              <Form.Group>
                <Form.Control
                  type="text"
                  placeholder="Username"
                  value={userName}
                  onChange={e => this.setState({userName: e.target.value})}
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
              <Button variant="primary" type="submit" block className="form-btn">
                Sign In
              </Button>
              <Form.Text className="text-secondary mt-2">
                Or <a href={SIGN_UP}>sign up now!</a>
              </Form.Text>
            </Form>
          </div>
        </div>
      </div>
    )
  }
}

SignIn.propTypes = {
  signIn: PropTypes.func,
};

export default connect(null, dispatch => ({signIn: data => dispatch(signIn(data))}))(SignIn);
