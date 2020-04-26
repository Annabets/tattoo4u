import React from 'react';
import {connect} from "react-redux";
import {Tabs, Tab} from 'react-bootstrap';
import {registerStudio, uploadPhoto, resetError, getSelfPhotos, deletePhoto, clearPhotos} from "./actions";
import UploadPhotoForm from './forms/UploadPhotoForm';
import RegisterStudioForm from './forms/RegisterStudioForm';
import PhotoGridContainer from "../PhotoGrid";

class Profile extends React.Component {
  constructor(props){
    super(props)
  }

  render() {
    const {
      error,
      uploadPhoto,
      registerStudio,
      resetError,
      photos,
      getSelfPhotos,
      isLoadingPhotos,
      isUploadFailed,
      deletePhoto,
      clearPhotos
    } = this.props;
    return (
      <>
        {this.props.role === 'MASTER' &&
        <Tabs defaultActiveKey="selfPhotos" id="uncontrolled-tab-example" unmountOnExit>
          <Tab eventKey="selfPhotos" title="My photos">
            <PhotoGridContainer
              pages={photos}
              getMorePhotos={getSelfPhotos}
              isLoadingPhotos={isLoadingPhotos}
              isUploadFailed={isUploadFailed}
              onDeletePhoto={deletePhoto}
              onMount={clearPhotos}
            />
          </Tab>
          <Tab eventKey="addPhoto" title="Upload Photo">
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
    photos: state.profile.photos,
    isLoadingPhotos: state.profile.isLoadingPhotos,
    isUploadFailed: state.profile.isUploadFailed,
  }),
  dispatch => ({
    uploadPhoto: (data, cb) => dispatch(uploadPhoto(data, cb)),
    registerStudio: (data, cb) => dispatch(registerStudio(data, cb)),
    resetError: () => dispatch(resetError()),
    getSelfPhotos: () => dispatch(getSelfPhotos()),
    deletePhoto: id => dispatch(deletePhoto(id)),
    clearPhotos: () => dispatch(clearPhotos()),
  }))(Profile);
