import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button, Nav, Alert} from "react-bootstrap";
import {resetError, signUp} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_IN} from "../../../routes";
import {history} from "../../../utils";
import * as yup from "yup";

const schema = yup.object({
  username: yup.string()
    .required('Username required'),
  email: yup.string()
    .email('Invalid email')
    .required('Email required'),
  password: yup
    .string()
    .matches(
      /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}/,
      'Password must contain at least one uppercase letter, one lowercase letter, one digit and its minimum length is 6')
    .required('Password required'),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref('password'), ''], 'Passwords must match')
    .required('Confirm password')
});

class SignUp extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
      confirmPassword: '',
      email: '',
      role: 'USER',
      errors: {},
    }
  }

  setError = (field, error) => this.setState(state => ({errors: {...state.errors, [field]: error}}));

  removeError = (field) => this.setState(state => ({errors: field ? {...state.errors, [field]: ''} : {}}));

  handleChange = field => e => {
    this.setState({[field]: e.target.value});
    this.removeError(field);
    this.props.resetError();
  };

  handleSubmit = e => {
    e.preventDefault();

    this.removeError();
    schema.validate(this.state, {abortEarly: false})
      .then(() => this.props.signUp(this.state))
      .catch(reason => reason.inner.forEach(er => this.setError(er.path, er.message)))
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
    const {username, password, confirmPassword, email, role, errors} = this.state;
    const { error } = this.props;
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
                    onChange={this.handleChange('username')}
                    isInvalid={!!errors.username}
                  />
                  <span className="text-danger">{errors.username}</span>
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={this.handleChange('email')}
                    isInvalid={!!errors.email}
                  />
                  <span className="text-danger">{errors.email}</span>
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={this.handleChange('password')}
                    isInvalid={!!errors.password}
                  />
                  <span className="text-danger">{errors.password}</span>
                </Form.Group>
                <Form.Group>
                  <Form.Control
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={this.handleChange('confirmPassword')}
                    isInvalid={!!errors.confirmPassword}
                  />
                  <span className="text-danger">{errors.confirmPassword}</span>
                </Form.Group>
                {error && <Alert variant="danger">{error}</Alert>}
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
  error: PropTypes.string,
  resetError: PropTypes.func,
};

export default connect(
  state => ({
    error: state.user.error
  }),
  dispatch => ({
    signUp: data => dispatch(signUp(data)),
    resetError: () => dispatch(resetError())
  })
)(SignUp);
