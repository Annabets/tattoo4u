import * as constants from './constants';
import {AUTH_KEY} from "../../constants";

const initialState = {
  id: '',
  userName: '',
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
      return {...state, isLoadingUserData: false, userName: action.userData.username, authKey: action.userData.token};
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
        userName: action.userData.username
      };
    case constants.SIGN_UP_USER_FAILURE:
      return {...state, isLoadingUserData: false, error: action.payload};
    default:
      return state;
  }
}
