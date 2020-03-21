import * as constants from './constants';
import  {api} from "../../api/app";
import {AUTH_KEY} from "../../constants";

export function signIn(data) {
  return dispatch => {
    dispatch({
      type: constants.SIGN_IN_USER_REQUEST
    });
    api.signInUser(data).then(
      userData => {
        dispatch({
          type: constants.SIGN_IN_USER_SUCCESS,
          userData,
        })
        window.location.replace('/');
      },
      errorMessage => {
        dispatch({
          type: constants.SIGN_IN_USER_FAILURE,
          payload: errorMessage
        })
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
      userData => {
        dispatch({
          type: constants.SIGN_UP_USER_SUCCESS,
          userData,
        })
        window.location.replace('/');
      },
      errorMessage => {
        dispatch({
          type: constants.SIGN_UP_USER_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}

export function signOut() {
  return dispatch => {
    dispatch({
      type: constants.SIGN_OUT_USER
    });
    window.location.replace('/');
  }

}
