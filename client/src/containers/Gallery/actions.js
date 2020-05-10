import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getNewPhotos(page, size) {
  return dispatch => {
    dispatch({
      type: constants.GET_PHOTOS_REQUEST,
    });
    api.getPhotos(page, size).then(
      response => {
        dispatch({
          type: constants.GET_PHOTOS_SUCCESS,
          payload: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_PHOTOS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function getTrendingPhotos() {
  return dispatch => {
    dispatch({
      type: constants.GET_PHOTOS_REQUEST,
    });
    api.getTrendPhotos().then(
      response => {
        dispatch({
          type: constants.GET_PHOTOS_SUCCESS,
          payload: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_PHOTOS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function searchPhotos(tags) {
  return dispatch => {
    dispatch({
      type: constants.GET_PHOTOS_REQUEST,
    });
    api.searchPhotos(tags).then(
      response => {
        dispatch({
          type: constants.GET_PHOTOS_SUCCESS,
          payload: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_PHOTOS_FAILURE,
          payload: error.message
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
