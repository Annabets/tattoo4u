import React from 'react';
import {connect} from "react-redux";
import {Tabs, Tab} from 'react-bootstrap';
import {
  registerStudio,
  uploadPhoto,
  resetError,
  getSelfPhotos,
  deletePhoto,
  clearPhotos,
  getFavoriteStudios,
  removeStudioFromFavorites,
  getFavoriteMasters,
  removeMasterFromFavorites,
} from "./actions";
import UploadPhotoForm from './forms/UploadPhotoForm';
import RegisterStudioForm from './forms/RegisterStudioForm';
import PhotoGridContainer from "../PhotoGrid";
import Favorites from '../../components/Favorites';

class Profile extends React.Component {
  constructor(props){
    super(props)
  }

  render() {
    const {
      role,
      error,
      uploadPhoto,
      registerStudio,
      resetError,
      photos,
      getSelfPhotos,
      isLoadingPhotos,
      isUploadFailed,
      deletePhoto,
      clearPhotos,
      favoriteStudios,
      getFavoriteStudios,
      removeStudioFromFavorites,
      favoriteMasters,
      getFavoriteMasters,
      removeMasterFromFavorites,
    } = this.props;
    const isMaster = role === 'MASTER';
    return (
      <>
        <Tabs defaultActiveKey={isMaster ? "selfPhotos" : "favorites"} id="uncontrolled-tab-example" unmountOnExit>
          {isMaster &&
          <Tab eventKey="selfPhotos" title="My photos">
            <PhotoGridContainer
              pages={photos}
              getMorePhotos={getSelfPhotos}
              isLoadingPhotos={isLoadingPhotos}
              isUploadFailed={isUploadFailed}
              onDeletePhoto={deletePhoto}
              onMount={clearPhotos}
            />
          </Tab>}
          {isMaster &&
          <Tab eventKey="addPhoto" title="Upload Photo">
            <UploadPhotoForm uploadPhoto={uploadPhoto} error={error} resetError={resetError}/>
          </Tab>}
          {isMaster &&
          <Tab eventKey="registerStudio" title="Register Studio">
            <RegisterStudioForm registerStudio={registerStudio} error={error} resetError={resetError}/>
          </Tab>}
          <Tab eventKey="favorites" title="Favorites">
            <Favorites
              favoriteStudios={favoriteStudios}
              getFavoriteStudios={getFavoriteStudios}
              removeStudioFromFavorites={removeStudioFromFavorites}
              favoriteMasters={favoriteMasters}
              getFavoriteMasters={getFavoriteMasters}
              removeMasterFromFavorites={removeMasterFromFavorites}
            />
          </Tab>
        </Tabs>
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
    favoriteStudios: state.profile.favoriteStudios,
    favoriteMasters: state.profile.favoriteMasters,
  }),
  dispatch => ({
    uploadPhoto: (data, cb) => dispatch(uploadPhoto(data, cb)),
    registerStudio: (data, cb) => dispatch(registerStudio(data, cb)),
    resetError: () => dispatch(resetError()),
    getSelfPhotos: (page, size) => dispatch(getSelfPhotos(page, size)),
    deletePhoto: id => dispatch(deletePhoto(id)),
    clearPhotos: () => dispatch(clearPhotos()),
    getFavoriteStudios: () => dispatch(getFavoriteStudios()),
    removeStudioFromFavorites: (studioId, cb) => dispatch(removeStudioFromFavorites(studioId, cb)),
    getFavoriteMasters: () => dispatch(getFavoriteMasters()),
    removeMasterFromFavorites: masterId => dispatch(removeMasterFromFavorites(masterId)),
  }))(Profile);
