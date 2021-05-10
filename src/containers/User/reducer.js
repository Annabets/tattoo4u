import * as constants from './constants';
import {AUTH_KEY, EMAIL, PHOTO_URL, ROLE, USER_ID, USER_NAME} from "../../constants";
import {clearLocalStorage} from "../../utils";

const initialState = {
  id: localStorage.getItem(USER_ID) || '',
  username: localStorage.getItem(USER_NAME) || '',
  photoUrl: localStorage.getItem(PHOTO_URL) || '',
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
    case constants.GET_USER_INFO_REQUEST:
      return {...state, isLoadingUserData: true};
    case constants.SIGN_IN_USER_SUCCESS:
      localStorage.setItem(AUTH_KEY, action.payload.token);
      return {
        ...state,
        authKey: action.payload.token,
      };
    case constants.GET_USER_INFO_SUCCESS:
      localStorage.setItem(USER_NAME, action.payload.username);
      localStorage.setItem(PHOTO_URL, action.payload.photo);
      localStorage.setItem(EMAIL, action.payload.email);
      localStorage.setItem(USER_ID, action.payload.id);
      localStorage.setItem(ROLE, action.payload.role);
      return {
        ...state,
        isLoadingUserData: false,
        id: action.payload.id,
        email: action.payload.email,
        username: action.payload.username,
        role: action.payload.role,
      };
    case constants.SIGN_UP_USER_FAILURE:
    case constants.SIGN_IN_USER_FAILURE:
    case constants.GET_USER_INFO_FAILURE:
      return {...state, isLoadingUserData: false, error: action.payload};
    case constants.SIGN_OUT_USER:
      clearLocalStorage();
      return initialState;
    case constants.RESET_ERROR:
      return {...state, error: ''};
    default:
      return state;
  }
}
