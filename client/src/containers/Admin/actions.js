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
