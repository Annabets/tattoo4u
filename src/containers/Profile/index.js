import React from 'react';
import {connect} from "react-redux";
import {Form, Button, FormLabel, Tabs, Tab} from 'react-bootstrap';
import {uploadPhoto} from "./actions";
import {registerStudio} from "../Studios/actions";
import {ROLE} from "../../constants";

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

  handleRegisterStudio = e => {
    e.preventDefault();

    const selectedFile = document.getElementById('studioPhoto').files[0];
    const name = document.getElementById('studioName').value;
    const description = document.getElementById('studioDescription').value;
    const address = document.getElementById('studioAddress').value;
    const contact = document.getElementById('studioContact').value;

    this.props.registerStudio({
      selectedFile,
      name,
      description,
      address,
      contact,
    })
  }

  render() {
    return (
      <>
        {localStorage.getItem(ROLE) === 'MASTER' &&
        <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
          <Tab eventKey="addPost" title="Upload Photo">
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
          </Tab>
          <Tab eventKey="registerStudio" title="Register Studio">
            <h3>Register studio</h3>
            <br/>
            <Form onSubmit={this.handleRegisterStudio}>
              <Form.Group>
                <Form.Group>
                  <FormLabel>Studio Photo</FormLabel>
                  <Form.Control
                    type="file"
                    id="studioPhoto"
                  />
                </Form.Group>
                <FormLabel>Name</FormLabel>
                <Form.Control
                  type="text"
                  id="studioName"
                />
              </Form.Group>
              <Form.Group>
                <FormLabel>Description</FormLabel>
                <Form.Control
                  type="text"
                  id="studioDescription"
                />
              </Form.Group>
              <Form.Group>
                <FormLabel>Address</FormLabel>
                <Form.Control
                  type="text"
                  id="studioAddress"
                />
              </Form.Group>
              <Form.Group>
                <FormLabel>Contact</FormLabel>
                <Form.Control
                  type="text"
                  id="studioContact"
                />
              </Form.Group>
              <Form.Group>
                <Button type="submit" block>Register</Button>
              </Form.Group>
            </Form>
          </Tab>
      </Tabs>}
      </>
    )
  }
}

export default connect(null, dispatch => ({
  uploadPhoto: (file,tags,description) => dispatch(uploadPhoto(file,tags,description)),
  registerStudio: data => dispatch(registerStudio(data))
}))(Profile);
