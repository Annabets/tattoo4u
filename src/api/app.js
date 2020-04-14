import {API_URL, SIGN_IN, SIGN_UP, GET, POST} from "../constants";
import axios from 'axios';
import {getToken} from "../utils";

const handleResponse = resp =>
  resp.text()
    .then(text => Promise.resolve(JSON.parse(text)))
    .catch(error => Promise.reject(error.message));


const signInUser = data => fetch(`${API_URL + SIGN_IN}`, {method: POST, headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data)}).then(handleResponse);

const signUpUser = data => fetch(`${API_URL + SIGN_UP}`, {method: POST, headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data)}).then(handleResponse);

const uploadPhoto = (file, tags, description) => {
  const data = new FormData();
  data.append('file', file);
  data.append('tags', tags);
  data.append('description', description);

  return axios.post(`${API_URL + '/add-post'}`, data, {
    headers: {
      'content-type': 'multipart/form-data',
      Authorization: `Bearer_${getToken()}`
    }
  }).then(resp => Promise.resolve(resp))
    .catch(error => Promise.reject(error))

}

const getPhotos = () => fetch(`${API_URL + '/posts'}`, {method: GET, headers: {'Authorization':`Bearer_${getToken()}`}}).then(handleResponse)

const getStudios = searchString => fetch(`${API_URL + '/studios' + (searchString ? `?name=${searchString}` : '')}`, {method: GET}).then(handleResponse);

const getStudioData = studioId => fetch(API_URL + `/studio?studioId=${studioId}`, {method: GET}).then(handleResponse);

const registerStudio = data => fetch(`${API_URL + '/studio'}`, {
  method: POST,
  headers: {
    'Authorization':`Bearer_${getToken()}`,
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(data)
}).then(handleResponse);

const getMasters = () => fetch(`${API_URL + '/users/masters'}`, {method: GET, headers:{'Authorization':`Bearer_${getToken()}`}}).then(handleResponse);

const registerMaster = data => fetch(API_URL + '/master', {
  method: POST,
  headers: {
    'Authorization':`Bearer_${getToken()}`,
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(data),
})

const signOutUser = () => fetch(`${API_URL + '/signOut'}`, {method: GET, headers:{'Authorization':`Bearer_${getToken()}`}}).then(handleResponse);

const getCurrentUser = () => fetch(
  API_URL + '/users/currentUser',
  {
    method: GET,
    headers: {
      'Authorization':`Bearer_${getToken()}`
    }
  }
).then(handleResponse);

export const api = {
  signInUser,
  signUpUser,
  uploadPhoto,
  getPhotos,
  getStudios,
  getStudioData,
  signOutUser,
  registerStudio,
  getCurrentUser,
  getMasters,
  registerMaster
};
