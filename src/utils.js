import {AUTH_KEY, EMAIL, PHOTO_URL, ROLE, USER_ID, USER_NAME} from "./constants";
import {SIGN_IN, SIGN_UP} from "./routes";
import {createBrowserHistory} from "history";

export const history = createBrowserHistory();

export function getToken() {
  return localStorage.getItem(AUTH_KEY);
}

export function isAuth() {
  return !!localStorage.getItem(AUTH_KEY);
}

export function isAdmin() {
  return localStorage.getItem(ROLE) === 'ADMIN';
}

export function withNavigation(pathname){
  return pathname !== SIGN_IN && pathname !== SIGN_UP
}

export function clearLocalStorage() {
  localStorage.removeItem(AUTH_KEY);
  localStorage.removeItem(USER_NAME);
  localStorage.removeItem(PHOTO_URL);
  localStorage.removeItem(EMAIL);
  localStorage.removeItem(USER_ID);
  localStorage.removeItem(ROLE);
}

export function apiErrorHandler(error) {
  if (error && error.response) {

    if (error.response.status === 401) {
      clearLocalStorage();
      history.replace('/');
    }

    if (error.response.status === 403) {
      clearLocalStorage();
      history.replace('/');
    }

    if (error.response.status === 500 && error.response.error.includes('JWT expired')) {
      clearLocalStorage();
      history.replace('/');
    }
  }
}
