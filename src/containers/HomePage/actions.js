import * as constants from "./constants";
import {api} from "../../api/app";

export function getPhotos() {
  return dispatch => {
    dispatch({
      type: constants.GET_PHOTOS_REQUEST,
    });
    api.getPhotos().then(
      userData => {
        dispatch({
          type: constants.GET_PHOTOS_SUCCESS,
          payload: userData,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.GET_PHOTOS_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
