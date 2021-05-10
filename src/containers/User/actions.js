import * as constants from './constants';
import  {api} from "../../api/app";
import {history} from "../../utils";

export function getUserInfo() {
  return dispatch => {
    dispatch({
      type: constants.GET_USER_INFO_REQUEST,
    });
    api.getUserInfo().then(
      resp => {
        dispatch({
          type: constants.GET_USER_INFO_SUCCESS,
          payload: resp.data,
        });
      },
      error => {
        dispatch({
          type: constants.GET_USER_INFO_FAILURE,
          payload: error.message,
        })
      }
    )
  }
}

export function signIn(data) {
  return dispatch => {
    dispatch({
      type: constants.SIGN_IN_USER_REQUEST
    });
    api.signInUser(data).then(
      response => {
        dispatch({
          type: constants.SIGN_IN_USER_SUCCESS,
          payload: response.data,
        });
        dispatch(getUserInfo());
        history.push('/');
      },
      error => {
        dispatch({
          type: constants.SIGN_IN_USER_FAILURE,
          payload: error.message
        });
      }
    )
  }
}

export function signUp(data) {
  return dispatch => {
    dispatch({
      type: constants.SIGN_UP_USER_REQUEST,
    });
    api.signUpUser(data).then(
      response => {
        dispatch({
          type: constants.SIGN_UP_USER_SUCCESS,
        });
        dispatch(signIn(data));
      },
      error => {
        dispatch({
          type: constants.SIGN_UP_USER_FAILURE,
          payload: error.message
        });
      }
    )
  }
}

export function signOut() {
  return dispatch => {
    api.signOutUser().finally(() => {
      dispatch({
        type: constants.SIGN_OUT_USER
      });
      history.go(0);
    })
  }
}

export function resetError() {
  return {
    type: constants.RESET_ERROR
  }
}
