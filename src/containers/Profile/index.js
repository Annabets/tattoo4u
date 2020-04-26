import React from 'react';
import {connect} from "react-redux";
import {Tabs, Tab} from 'react-bootstrap';
import {registerStudio, uploadPhoto, resetError} from "./actions";
import UploadPhotoForm from './forms/UploadPhotoForm';
import RegisterStudioForm from './forms/RegisterStudioForm';

class Profile extends React.Component {
  constructor(props){
    super(props)
  }

  render() {
    const {error, uploadPhoto, registerStudio, resetError} = this.props;
    return (
      <>
        {this.props.role === 'MASTER' &&
        <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
          <Tab eventKey="addPost" title="Upload Photo">
            <UploadPhotoForm uploadPhoto={uploadPhoto} error={error} resetError={resetError}/>
          </Tab>
          <Tab eventKey="registerStudio" title="Register Studio">
            <RegisterStudioForm registerStudio={registerStudio} error={error} resetError={resetError}/>
          </Tab>
        </Tabs>}
      </>
    )
  }
}

export default connect(
  state => ({
    role: state.user.role,
    error: state.profile.error,
  }),
  dispatch => ({
    uploadPhoto: (data, cb) => dispatch(uploadPhoto(data, cb)),
    registerStudio: (data, cb) => dispatch(registerStudio(data, cb)),
    resetError: () => dispatch(resetError()),
  }))(Profile);
