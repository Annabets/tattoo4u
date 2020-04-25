import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getStudioData(studioId) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIO_DATA_REQUEST,
    });
    api.getStudioData(studioId).then(
      response => {
        dispatch({
          type: constants.GET_STUDIO_DATA_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_STUDIO_DATA_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function getMasters() {
  return dispatch => {
    dispatch({
      type: constants.GET_MASTERS_REQUEST,
    });
    api.getMasters().then(
      response => {
        dispatch({
          type: constants.GET_MASTERS_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_MASTERS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function registerMaster(data, cb) {
  return dispatch => {
    dispatch({
      type: constants.REGISTER_MASTERS_REQUEST,
    });
    api.registerMaster(data).then(
      response => {
        dispatch({
          type: constants.REGISTER_MASTERS_SUCCESS,
          data: response.data,
        });
        cb && cb(data)
      },
      error => {
        dispatch({
          type: constants.REGISTER_MASTERS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}
