import {
  API_URL,
  SIGN_IN,
  SIGN_UP,
  UPLOAD_PHOTO,
  PHOTOS,
  DELETE_PHOTO,
  GET_PHOTOS, STUDIOS, STUDIO, SIGN_OUT, MASTERS, MASTER, USERS, LIKE_PHOTO, TREND_PHOTOS, COMMENT
} from "../constants";
import axios from 'axios';
import {getToken} from "../utils";

axios.defaults.baseURL = API_URL;

axios.interceptors.request.use(config => {
  config.headers['Authorization'] = getToken() ? `Bearer_${getToken()}` : null;

  return config;
});

axios.interceptors.response.use(null, error => {
  if (error.response && error.response.data && error.response.data.error)
    error.message = error.response.data.error;

  return Promise.reject(error);
});

const formData = data => {
  const formData = new FormData();
  for(let prop in data){
    formData.append(prop, data[prop])
  }
  return formData
};

const signInUser = data => axios.post(SIGN_IN, data);

const signUpUser = data => axios.post(SIGN_UP, data);

const signOutUser = () => axios.get(SIGN_OUT);

const uploadPhoto = data => axios.post(UPLOAD_PHOTO, formData(data));

const getPhotos = () => axios.get(PHOTOS);

const getTrendPhotos = () => axios.get(TREND_PHOTOS);

const searchPhotos = tags => axios.get(GET_PHOTOS, {params: {tags: tags}});

const deletePhoto = photoId => axios.delete(`${DELETE_PHOTO}/${photoId}`);

const getMasterPhotos = masterId => axios.get(`${GET_PHOTOS}/${masterId}`);

const getStudios = (searchString, page = 0, size = 10) => axios.get(STUDIOS, {
  params: {
    name: searchString,
    page: page,
    size: size
  }
});

const getStudioData = studioId => axios.get(STUDIO, {params: {studioId: studioId}});

const registerStudio = studioData => axios.post(STUDIO, formData(studioData));

const getMasters = (searchString, page = 0, size = 10) => axios.get(MASTERS, {
  params: {
    name: searchString,
    page: page,
    size: size
  }
});

const registerMaster = data => axios.post(MASTER, data);

const getCurrentUser = () => axios.get(`${USERS}/currentUser`);

const likePhoto = photoId => axios.get(`${LIKE_PHOTO}/${photoId}`);

const getComments = photoId => axios.get(`${COMMENT}/${photoId}`);

const addComment = (photoId, data) => axios.post(`${COMMENT}/${photoId}`, data);

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
  registerMaster,
  deletePhoto,
  getMasterPhotos,
  searchPhotos,
  likePhoto,
  getTrendPhotos,
  getComments,
  addComment,
};
