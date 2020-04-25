import * as constants from './constants';
import {AUTH_KEY, EMAIL, ROLE, USER_ID, USER_NAME} from "../../constants";
import {clearLocalStorage} from "../../utils";

const initialState = {
  id: localStorage.getItem(USER_ID) || '',
  username: localStorage.getItem(USER_NAME) || '',
  email: localStorage.getItem(EMAIL) || '',
  authKey: localStorage.getItem(AUTH_KEY) || '',
  role: localStorage.getItem(ROLE) || '',
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
      localStorage.setItem(USER_ID, action.userData.id);
      localStorage.setItem(ROLE, action.userData.role);
      return {
        ...state,
        isLoadingUserData: false,
        id: action.userData.id,
        email: action.userData.email,
        username: action.userData.username,
        role: action.userData.role,
      };
    case constants.SIGN_UP_USER_FAILURE:
    case constants.SIGN_IN_USER_FAILURE:
      return {...state, isLoadingUserData: false, error: action.payload};
    case constants.SIGN_OUT_USER:
      clearLocalStorage();
      return initialState;
    default:
      return state;
  }
}
