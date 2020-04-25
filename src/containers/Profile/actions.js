import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

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
