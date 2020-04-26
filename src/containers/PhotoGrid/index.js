import React from 'react';
import {connect} from 'react-redux';
import PhotoGrid from '../../components/PhotoGrid';
import PropTypes from 'prop-types';
import {photoGridActions} from "./actions";

function PhotoGridContainer(props) {
  const {
    pages,
    photoGrid,
    setColumns,
    setModalOpenFlag,
    getMorePhotos,
    isLoadingPhotos,
    isUploadFailed,
    setModalPhoto,
    deletePhoto,
    onDeletePhoto,
    username,
    userRole,
    onMount,
    likePhoto,
    toggleModalPhotoLike,
  } = props;
  return (
    <>
      <PhotoGrid
        photos={pages}
        getMorePhotos={getMorePhotos}
        columns={photoGrid.columns}
        isLoadingPhotos={isLoadingPhotos}
        isUploadFailed={isUploadFailed}
        modalPhoto={photoGrid.modalPhoto}
        isModalOpen={photoGrid.isModalOpen}
        setColumns={setColumns}
        setModalOpenFlag={setModalOpenFlag}
        setModalPhoto={setModalPhoto}
        deletePhoto={id => deletePhoto(id, () => onDeletePhoto(id))}
        username={username}
        userRole={userRole}
        onMount={onMount}
        likePhoto={likePhoto}
      />
    </>
  )
}

PhotoGridContainer.propTypes = {
  getMorePhotos: PropTypes.func.isRequired,
  isLoadingPhotos: PropTypes.bool.isRequired,
  isUploadFailed: PropTypes.bool.isRequired,
  pages: PropTypes.array.isRequired,
};

const mapStateToProps = store => {
  return {
    photoGrid: store.photoGrid,
    username: store.user.username,
    userRole: store.user.role,
  }
};

const mapDispatchToProps = dispatch => {
  return {
    setColumns: colNum => dispatch(photoGridActions.setColumns(colNum)),
    setModalOpenFlag: flag => dispatch(photoGridActions.setModalOpenFlag(flag)),
    setModalPhoto: photo => dispatch(photoGridActions.setModalPhoto(photo)),
    deletePhoto: (photoId, cb) => dispatch(photoGridActions.deletePhoto(photoId, cb)),
    likePhoto: photoId => dispatch(photoGridActions.likePhoto(photoId))
  }
};

export default connect(mapStateToProps,mapDispatchToProps)(PhotoGridContainer);
