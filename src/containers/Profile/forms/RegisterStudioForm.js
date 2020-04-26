import React from 'react';
import {Alert, Button, Form, FormLabel} from "react-bootstrap";
import * as yup from 'yup';

const schema = yup.object({
  file: yup.mixed().required('Photo is required'),
  name: yup.string().required('Studio name is required'),
  address: yup.string().required('Studio address is required'),
  contact: yup.string().required('Studio contact(s) is required'),
});

class RegisterStudioForm extends React.Component {
  constructor(props){
    super(props);

    this.initialState = {
      file: null,
      name: '',
      description: '',
      address: '',
      contact: '',
      errors: {},
    };

    this.state = this.initialState;
  }

  setError = (field, error) => this.setState(state => ({errors: {...state.errors, [field]: error}}));

  removeError = (field) => this.setState(state => ({errors: field ? {...state.errors, [field]: ''} : {}}));

  handleSubmit = e => {
    e.preventDefault();

    const cb = () => {
      document.getElementById('registerStudioForm').reset();
      this.setState(this.initialState)
    };

    this.removeError();
    schema.validate(this.state, {abortEarly: false})
      .then(() => this.props.registerStudio(this.state, cb))
      .catch(reason => reason.inner.forEach(er => this.setError(er.path, er.message)))
  };

  handleChange = field => e => {
    const value = field === 'file' ? e.target.files[0] : e.target.value;
    this.setState({[field]: value});
    this.removeError(field);
    this.props.resetError();
  };

  render() {
    const {file, name, description, address, contact, errors} = this.state;
    const { error } = this.props;
    return(
      <>
        <h3>Register studio</h3>
        <br/>
        <Form id="registerStudioForm" onSubmit={this.handleSubmit}>
          <Form.Group>
            <Form.Group>
              <FormLabel>Studio Photo</FormLabel>
              <Form.Control
                type="file"
                files={[file]}
                onChange={this.handleChange('file')}
              />
              <span className="text-danger">{errors.file}</span>
              {error && <Alert variant="danger" className="mt-2">{error}</Alert>}
            </Form.Group>
            <FormLabel>Name</FormLabel>
            <Form.Control
              type="text"
              value={name}
              onChange={this.handleChange('name')}
              isInvalid={!!errors.name}
            />
            <span className="text-danger">{errors.name}</span>
          </Form.Group>
          <Form.Group>
            <FormLabel>Description</FormLabel>
            <Form.Control
              type="text"
              value={description}
              onChange={this.handleChange('description')}
            />
          </Form.Group>
          <Form.Group>
            <FormLabel>Address</FormLabel>
            <Form.Control
              type="text"
              value={address}
              onChange={this.handleChange('address')}
              isInvalid={!!errors.address}
            />
            <span className="text-danger">{errors.address}</span>
          </Form.Group>
          <Form.Group>
            <FormLabel>Contact</FormLabel>
            <Form.Control
              type="text"
              value={contact}
              onChange={this.handleChange('contact')}
              isInvalid={!!errors.contact}
            />
            <span className="text-danger">{errors.contact}</span>
          </Form.Group>
          <Form.Group>
            <Button type="submit" block>Register</Button>
          </Form.Group>
        </Form>
      </>
    )
  }
}

export default RegisterStudioForm;
