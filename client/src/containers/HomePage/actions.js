import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getPhotos(page, size) {
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
