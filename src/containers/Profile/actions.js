import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";
import * as constants from "./constants";

export function uploadPhoto(data, cb) {
  return dispatch => {
    dispatch({
      type: constants.UPLOAD_PHOTO_REQUEST,
    });
    api.uploadPhoto(data).then(
      response => {
        dispatch({
          type: constants.UPLOAD_PHOTO_SUCCESS,
          userData: response.data,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: constants.UPLOAD_PHOTO_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function registerStudio(data, cb) {
  return dispatch => {
    dispatch({
      type: constants.REGISTER_STUDIO_REQUEST,
    });
    api.registerStudio(data).then(
      response => {
        dispatch({
          type: constants.REGISTER_STUDIO_SUCCESS,
          userData: response.data,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: constants.REGISTER_STUDIO_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function resetError() {
  return {
    type: constants.RESET_ERROR
  }
}

export function getSelfPhotos() {
  return (dispatch, getState) => {
    dispatch({
      type: constants.GET_PHOTOS_REQUEST,
    });
    api.getMasterPhotos(getState().user.id).then(
      response => {
        dispatch({
          type: constants.GET_PHOTOS_SUCCESS,
          payload: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_PHOTOS_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function clearPhotos() {
  return {
    type: constants.CLEAR_PHOTOS,
  }
}

export function deletePhoto(id) {
  return {
    type: constants.DELETE_PHOTO,
    id
  }
}

export function getFavoriteStudios() {
  return dispatch => {
    dispatch({
      type: constants.GET_FAVORITE_STUDIOS_REQUEST,
    });
    api.getFavStudios().then(
      response => {
        dispatch({
          type: constants.GET_FAVORITE_STUDIOS_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_FAVORITE_STUDIOS_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function removeStudioFromFavorites(studioId, cb) {
  return dispatch => {
    dispatch({
      type: constants.REMOVE_STUDIO_FROM_FAVORITES_REQUEST,
    });
    api.removeStudioFromFav(studioId).then(
      () => {
        dispatch({
          type: constants.REMOVE_STUDIO_FROM_FAVORITES_SUCCESS,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.REMOVE_STUDIO_FROM_FAVORITES_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}
