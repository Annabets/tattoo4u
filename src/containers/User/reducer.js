import * as constants from './constants';
import {AUTH_KEY, EMAIL, USER_NAME} from "../../constants";

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
    case constants.SIGN_UP_USER_REQUEST:
      return {...state, isLoadingUserData: true};
    case constants.SIGN_IN_USER_SUCCESS:
      case constants.SIGN_UP_USER_SUCCESS:
      localStorage.setItem(AUTH_KEY, action.userData.token);
      localStorage.setItem(USER_NAME, action.userData.username);
      localStorage.setItem(EMAIL, action.userData.email);
      return {
        ...state,
        isLoadingUserData: false,
        id: action.userData.id,
        email: action.userData.email,
        username: action.userData.username
      };
    case constants.SIGN_UP_USER_FAILURE:
    case constants.SIGN_IN_USER_FAILURE:
      return {...state, isLoadingUserData: false, error: action.payload};
    case constants.SIGN_OUT_USER:
      localStorage.removeItem(AUTH_KEY);
      localStorage.removeItem(USER_NAME);
      localStorage.removeItem(EMAIL);
      return initialState;
    default:
      return state;
  }
}
