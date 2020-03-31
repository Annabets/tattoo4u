import {AUTH_KEY} from "./constants";
import {SIGN_IN, SIGN_UP} from "./routes";

export function authHeader() {
  let authKey = JSON.parse(localStorage.getItem(AUTH_KEY));

  if (authKey) {
    return {'Authorization': 'Bearer_' + authKey};
  } else {
    return {};
  }
}

export function getToken() {
  return localStorage.getItem(AUTH_KEY);
}

export function isAuth() {
  return !!localStorage.getItem(AUTH_KEY);
}

export function withNavigation(pathname){
  return pathname !== SIGN_IN && pathname !== SIGN_UP
}
