import {photoGridConstants as _} from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

const getArr = (arrSize) => {
  let i = 0, temp = [];
  while (i < arrSize) {
    temp[i] = i++
  }
  return temp;
};

function setColumns(colNum) {
  return {
    type: _.SET_COLUMNS,
    payload: getArr(colNum)
  }
}

function setModalOpenFlag(flag) {
  return {
    type: _.SET_MODAL_OPEN_FLAG,
    payload: flag
  }
}

function setModalPhoto(photo) {
  return {
    type: _.SET_MODAL_PHOTO,
    payload: photo
  }
}

function toggleModalPhotoLike() {
  return {
    type: _.TOGGLE_MODAL_PHOTO_LIKE
  }
}

function deletePhoto(photoId, cb) {
  return dispatch => {
    dispatch({
      type: _.DELETE_PHOTO_REQUEST
    });
    api.deletePhoto(photoId).then(
      () => {
        dispatch({
          type: _.DELETE_PHOTO_SUCCESS,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: _.DELETE_PHOTO_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

function likePhoto(photoId) {
  return dispatch => {
    dispatch({
      type: _.LIKE_PHOTO_REQUEST
    });
    api.likePhoto(photoId).then(
      () => {
        dispatch({
          type: _.LIKE_PHOTO_SUCCESS,
        });
        dispatch({
          type: _.TOGGLE_LIKE,
          payload: photoId,
        });
        dispatch({
          type: _.TOGGLE_MODAL_PHOTO_LIKE,
        })
      },
      error => {
        dispatch({
          type: _.LIKE_PHOTO_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error);
      }
    )
  }
}

function getComments(photoId) {
  return dispatch => {
    dispatch({
      type: _.GET_COMMENTS_REQUEST
    });
    api.getComments(photoId).then(
      response => {
        dispatch({
          type: _.GET_COMMENTS_SUCCESS,
          data: response.data,
        });
      },
      error => {
        dispatch({
          type: _.GET_COMMENTS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error);
      }
    )
  }
}

function addComment(data, cb) {
  return dispatch => {
    dispatch({
      type: _.ADD_COMMENT_REQUEST
    });
    api.addComment(data).then(
      () => {
        dispatch({
          type: _.ADD_COMMENT_SUCCESS,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: _.ADD_COMMENT_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error);
      }
    )
  }
}

export const photoGridActions = {
  setColumns,
  setModalOpenFlag,
  setModalPhoto,
  deletePhoto,
  likePhoto,
  getComments,
  addComment
};
