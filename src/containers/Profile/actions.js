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
