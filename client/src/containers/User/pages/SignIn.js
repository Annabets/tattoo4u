import React from 'react';
import PropTypes from 'prop-types';
import {Form, Button, Alert} from "react-bootstrap";
import {signIn, resetError} from '../actions'
import bgImg from '../../../assets/images/tattoo.jpg';
import {connect} from "react-redux";
import {SIGN_UP} from "../../../routes";
import {history} from "../../../utils";
import * as yup from 'yup';

const schema = yup.object({
  username: yup.string().required('Username required'),
  password: yup.string().required('Password required')
});

class SignIn extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
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
      .then(() => this.props.signIn(this.state))
      .catch(reason => reason.inner.forEach(er => this.setError(er.path, er.message)))
  };

  render() {
    const {username, password, errors} = this.state;
    const { error } = this.props;
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
                  onChange={this.handleChange('username')}
                  isInvalid={!!errors.username}
                />
                <span className="text-danger">{errors.username}</span>
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
              {error && <Alert variant="danger">{error}</Alert>}
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
  error: PropTypes.string,
  resetError: PropTypes.func,
};

export default connect(
  state => ({
    error: state.user.error
  }),
  dispatch => ({
    signIn: data => dispatch(signIn(data)),
    resetError: () => dispatch(resetError())
  })
)(SignIn);
