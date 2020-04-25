import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button} from "react-bootstrap";
import {signIn} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_UP} from "../../../routes";
import {history} from "../../../utils";

class SignIn extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
    }
  }

  handleSubmit = e => {
    e.preventDefault();
    this.props.signIn(this.state);
  };

  render() {
    const {username, password} = this.state;
    return (
      <div className="sign-in" style={{backgroundImage: `url(${bgImg})`}}>
        <Button variant="link" className="--close" onClick={() => history.goBack()}>Back</Button>
        <div className="form-container">
          <div className="form-content">
            <h2 className="form-title">Sign In</h2>
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
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={e => this.setState({password: e.target.value})}
                />
              </Form.Group>
              <Button variant="primary" type="submit" block className="form-btn">
                Sign In
              </Button>
              <Form.Label className="text-secondary mt-2">
                <span className="align-middle">Or </span>
                <Button variant="link" className="p-0" onClick={() => history.replace(SIGN_UP)}>sign up now!</Button>
              </Form.Label>
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
