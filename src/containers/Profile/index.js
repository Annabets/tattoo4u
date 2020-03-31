import React from 'react';
import {connect} from "react-redux";
import {Form, Button, FormLabel} from 'react-bootstrap';
import {uploadPhoto} from "../Profile/actions";

class Profile extends React.Component {
  constructor(props){
    super(props)
  }

  handleSubmit = e => {
    e.preventDefault();

    const tags = document.getElementById('tags').value.split(',');
    const selectedFile = document.getElementById('customFile').files[0];
    const description = document.getElementById('description').value;

    this.props.uploadPhoto(selectedFile, tags, description);
  }

  render() {
    return (
      <>
        <h3>Upload photo</h3>
        <br/>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group>
            <Form.Control
              type="file"
              id="customFile"
            />
          </Form.Group>
          <Form.Group>
            <FormLabel>Tags</FormLabel>
            <Form.Control
              type="text"
              id="tags"
            />
          </Form.Group>
          <Form.Group>
            <FormLabel>Description</FormLabel>
            <Form.Control
              type="text"
              id="description"
            />
          </Form.Group>
          <Form.Group>
            <Button type="submit" block>Upload</Button>
          </Form.Group>

        </Form>

      </>
    )
  }
}

export default connect(null, dispatch => ({uploadPhoto: (file,tags,description) => dispatch(uploadPhoto(file,tags,description))}))(Profile);
