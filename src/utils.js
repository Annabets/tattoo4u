import {AUTH_KEY, EMAIL, ROLE, USER_ID, USER_NAME} from "./constants";
import {SIGN_IN, SIGN_UP} from "./routes";
import {createBrowserHistory} from "history";

export const history = createBrowserHistory();

export function getToken() {
  return localStorage.getItem(AUTH_KEY);
}

export function isAuth() {
  return !!localStorage.getItem(AUTH_KEY);
}

export function withNavigation(pathname){
  return pathname !== SIGN_IN && pathname !== SIGN_UP
}
