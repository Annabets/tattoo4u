import * as constants from './constants';
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getUsersList() {
  return dispatch => {
    dispatch({
      type: constants.GET_USERS_LIST_REQUEST
    });
    api.getUsersList().then(
      response => {
        dispatch({
          type: constants.GET_USERS_LIST_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_USERS_LIST_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function banUser(userId, cb) {
  return dispatch => {
    dispatch({
      type: constants.BAN_USER_REQUEST
    });
    api.banUser(userId).then(
      () => {
        dispatch({
          type: constants.BAN_USER_SUCCESS,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: constants.BAN_USER_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function unbanUser(userId, cb) {
  return dispatch => {
    dispatch({
      type: constants.UNBAN_USER_REQUEST
    });
    api.unbanUser(userId).then(
      () => {
        dispatch({
          type: constants.UNBAN_USER_SUCCESS,
        });
        cb && cb();
      },
      error => {
        dispatch({
          type: constants.UNBAN_USER_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function getNotConfirmedStudios() {
  return dispatch => {
    dispatch({
      type: constants.GET_NOT_CONFIRMED_STUDIOS_REQUEST
    });
    api.getNotConfirmedStudios().then(
      response => {
        dispatch({
          type: constants.GET_NOT_CONFIRMED_STUDIOS_SUCCESS,
          data: response.data,
        });
      },
      error => {
        dispatch({
          type: constants.GET_NOT_CONFIRMED_STUDIOS_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function confirmStudio(studioId) {
  return dispatch => {
    dispatch({
      type: constants.CONFIRM_STUDIO_REQUEST
    });
    api.confirmStudio(studioId).then(
      response => {
        dispatch({
          type: constants.CONFIRM_STUDIO_SUCCESS,
          data: response.data,
        });
      },
      error => {
        dispatch({
          type: constants.CONFIRM_STUDIO_FAILURE,
          payload: error.message,
        });
        apiErrorHandler(error)
      }
    )
  }
}
