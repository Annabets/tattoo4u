import * as constants from './constants';
import {AUTH_KEY, USER_NAME} from "../../constants";

const initialState = {
  id: '',
  username: localStorage.getItem(USER_NAME) || '',
  email: '',
  authKey: localStorage.getItem(AUTH_KEY) || '',
  isLoadingUserData: false,
  error: '',
};

export function userReducer(state = initialState, action) {
  switch (action.type) {
    case constants.SIGN_IN_USER_REQUEST:
      return {...state, isLoadingUserData: true};
    case constants.SIGN_IN_USER_SUCCESS:
      localStorage.setItem(AUTH_KEY, action.userData.token);
      localStorage.setItem(USER_NAME, action.userData.username);
      return {...state, isLoadingUserData: false, username: action.userData.username, authKey: action.userData.token};
    case constants.SIGN_IN_USER_FAILURE:
      return {isLoadingUserData: false, error: action.payload};
    case constants.SIGN_UP_USER_REQUEST:
      return {...state, isLoadingUserData: true};
    case constants.SIGN_UP_USER_SUCCESS:
      return {
        ...state,
        isLoadingUserData: false,
        id: action.userData.id,
        email: action.userData.email,
        username: action.userData.username
      };
    case constants.SIGN_UP_USER_FAILURE:
      return {...state, isLoadingUserData: false, error: action.payload};
    case constants.SIGN_OUT_USER:
      localStorage.removeItem(AUTH_KEY);
      localStorage.removeItem(USER_NAME);
      return initialState;
    default:
      return state;
  }
}
