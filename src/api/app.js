import {API_URL, SIGN_IN, SIGN_UP, GET, POST} from "../constants";
import axios from 'axios';
import {getToken} from "../utils";

const handleResponse = resp =>
  resp.text()
    .then(text => {
      if (!resp.ok)
        return Promise.reject(resp.statusText);
      else
        return Promise.resolve(JSON.parse(text));
    })
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

const getStudios = () => fetch(`${API_URL + '/getStudios'}`, {method: GET}).then(handleResponse);

export const api = {
  signInUser,
  signUpUser,
  uploadPhoto,
  getPhotos,
  getStudios,
};
