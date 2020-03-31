import * as constants from "./constants";
import {api} from "../../api/app";

export function uploadPhoto(file, tags, description) {
  return dispatch => {
    dispatch({
      type: constants.UPLOAD_PHOTO_REQUEST,
    });
    api.uploadPhoto(file,tags,description).then(
      userData => {
        dispatch({
          type: constants.UPLOAD_PHOTO_SUCCESS,
          userData,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.UPLOAD_PHOTO_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
