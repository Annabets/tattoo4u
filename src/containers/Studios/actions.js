import * as constants from "./constants";
import {api} from "../../api/app";

export function getStudios(searchString) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIOS_REQUEST,
    });
    api.getStudios(searchString).then(
      data => {
        dispatch({
          type: constants.GET_STUDIOS_SUCCESS,
          data,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.GET_STUDIOS_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}

export function registerStudio() {
  return dispatch => {
    dispatch({
      type: constants.REGISTER_STUDIO_REQUEST,
    });
    api.registerStudio().then(
      userData => {
        dispatch({
          type: constants.REGISTER_STUDIO_SUCCESS,
          userData,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.REGISTER_STUDIO_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
