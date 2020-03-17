import {API_URL, SIGN_IN, SIGN_UP, GET, POST} from "../constants";

const handleResponse = resp =>
  resp.text()
    .then(text => {
      if (!resp.ok)
        return Promise.reject(resp.statusText);
      else
        return Promise.resolve(JSON.parse(text));
    })
    .catch(error => Promise.reject(error.message));


const signInUser = data => fetch(`${API_URL + SIGN_IN}`, {method: POST, body: JSON.stringify(data)}).then(handleResponse);

const signUpUser = data => fetch(`${API_URL + SIGN_UP}`, {method: POST, body: JSON.stringify(data)}).then(handleResponse);


export const api = {
  signInUser,
  signUpUser,
};
