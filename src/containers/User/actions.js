import * as constants from './constants';
import  {api} from "../../api/app";
import {apiErrorHandler, history} from "../../utils";

export function signIn(data) {
  return dispatch => {
    dispatch({
      type: constants.SIGN_IN_USER_REQUEST
    });
    api.signInUser(data).then(
      response => {
        dispatch({
          type: constants.SIGN_IN_USER_SUCCESS,
          userData: response.data,
        });
        history.goBack();
      },
      error => {
        dispatch({
          type: constants.SIGN_IN_USER_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
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
          userData: response.data,
        });
        history.goBack();
      },
      error => {
        dispatch({
          type: constants.SIGN_UP_USER_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
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
