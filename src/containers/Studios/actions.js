import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getStudios(searchString) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIOS_REQUEST,
    });
    api.getStudios(searchString).then(
      response => {
        dispatch({
          type: constants.GET_STUDIOS_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_STUDIOS_FAILURE,
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
