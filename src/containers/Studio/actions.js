import * as constants from "./constants";
import {api} from "../../api/app";

export function getStudioData(studioId) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIO_DATA_REQUEST,
    });
    api.getStudioData(studioId).then(
      data => {
        dispatch({
          type: constants.GET_STUDIO_DATA_SUCCESS,
          data,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.GET_STUDIO_DATA_FAILURE,
          payload: errorMessage
        })
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
      data => {
        dispatch({
          type: constants.GET_MASTERS_SUCCESS,
          data,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.GET_MASTERS_FAILURE,
          payload: errorMessage
        })
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
      data => {
        dispatch({
          type: constants.REGISTER_MASTERS_SUCCESS,
          data,
        });
        cb && cb(data)
      },
      errorMessage => {
        dispatch({
          type: constants.REGISTER_MASTERS_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
