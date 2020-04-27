import * as constants from './constants';
import {api} from '../../api/app';
import {apiErrorHandler} from "../../utils";

export function getMasterData(masterId) {
  return dispatch => {
    dispatch({
      type: constants.GET_MASTER_DATA_REQUEST
    });
    api.getMasterData(masterId).then(
      response => {
        dispatch({
          type: constants.GET_MASTER_DATA_SUCCESS,
          data: response.data
        })
      },
      error => {
        dispatch({
          type: constants.GET_MASTER_DATA_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function addMasterToFavorites(masterId, cb) {
  return dispatch => {
    dispatch({
      type: constants.ADD_MASTER_TO_FAVORITES_REQUEST,
    });
    api.addMasterToFav(masterId).then(
      response => {
        dispatch({
          type: constants.ADD_MASTER_TO_FAVORITES_SUCCESS,
          data: response.data,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.ADD_MASTER_TO_FAVORITES_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function removeMasterFromFavorites(masterId, cb) {
  return dispatch => {
    dispatch({
      type: constants.REMOVE_MASTER_FROM_FAVORITES_REQUEST,
    });
    api.removeMasterFromFav(masterId).then(
      response => {
        dispatch({
          type: constants.REMOVE_MASTER_FROM_FAVORITES_SUCCESS,
          data: response.data,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.REMOVE_MASTER_FROM_FAVORITES_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function deletePhoto(id) {
  return {
    type: constants.DELETE_PHOTO,
    id
  }
}
