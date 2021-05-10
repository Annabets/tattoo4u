import React from 'react';
import {Alert, Button, Form, FormLabel, Popover, OverlayTrigger} from "react-bootstrap";
import * as yup from "yup";
import Info from '../../../assets/icons/info.svg';

const schema = yup.object({
  file: yup.mixed().required('Photo is required'),
});

class UploadPhotoForm extends React.Component {
  constructor(props) {
    super(props);

    this.initialState = {
      file: null,
      tags: '',
      description: '',
      errors: {},
    };

    this.state = this.initialState;
  }

  setError = (field, error) => this.setState(state => ({errors: {...state.errors, [field]: error}}));

  removeError = (field) => this.setState(state => ({errors: field ? {...state.errors, [field]: ''} : {}}));

  handleChange = field => e => {
    const value = field === 'file' ? e.target.files[0] : e.target.value;
    this.setState({[field]: value});
    this.removeError(field);
    this.props.resetError();
  };

  handleSubmit = e => {
    e.preventDefault();
    const {file, tags, description} = this.state;

    const cb = () => {
      document.getElementById('uploadPhotoForm').reset();
      this.setState(this.initialState)
    };

    this.removeError();
    schema.validate(this.state, {abortEarly: false})
      .then(() => this.props.uploadPhoto({
        photo: file,
        dto: new Blob([JSON.stringify({description, tags: tags.split(',')})], {type: 'application/json'})
      }, cb))
      .catch(reason => reason.inner.forEach(er => this.setError(er.path, er.message)))
  };

  render() {
    const {file, tags, description, errors} = this.state;
    const {error} = this.props;

    const popover = (
      <Popover id="popover-tags">
        <Popover.Content className="fs-14">
          Enter tags that describes tattoo on the photo. Separate tags by commas.
          Try enter as much tags as possible because it affects on how successfully your photo will be found in the
          search.
          If photo can be described by phrase, enter it's words separated by commas too.
        </Popover.Content>
      </Popover>
    );

    return (
      <div className="container-fluid">
        <h3>Upload photo</h3>
        <br/>
        <Form id="uploadPhotoForm" onSubmit={this.handleSubmit}>
          <Form.Group>
            <Form.Control
              type="file"
              files={[file]}
              onChange={this.handleChange('file')}
            />
            <span className="text-danger">{errors.file}</span>
            {error && <Alert variant="danger" className="mt-2">{error}</Alert>}
          </Form.Group>
          <Form.Group>
            <FormLabel>
              Tags
              <OverlayTrigger placement="right" overlay={popover}>
                <img src={Info} alt="info" height={13} width={13} className="ml-2 cursor-help"/>
              </OverlayTrigger>
            </FormLabel>
            <Form.Control
              type="text"
              value={tags}
              onChange={this.handleChange('tags')}
            />
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
            <Button type="submit" block>Upload</Button>
          </Form.Group>
        </Form>
      </div>
    )
  }
}

export default UploadPhotoForm;
