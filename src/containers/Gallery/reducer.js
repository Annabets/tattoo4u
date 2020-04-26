import * as constants from './constants';

const initialState = {
  photos:[],
  isLoadingPhotos: false,
  isUploadFailed: false,
};

export function galleryReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_PHOTOS_REQUEST:
      return {
        ...state,
        isLoadingPhotos: true,
      };
    case constants.GET_PHOTOS_SUCCESS:
      return {
        ...state,
        photos: state.photos.concat(action.payload),
        isLoadingPhotos: false
      };
    case constants.GET_PHOTOS_FAILURE:
      return {
        ...state,
        isLoadingPhotos: false,
        isUploadFailed: true,
      };
    case constants.TOGGLE_LIKE:
      return {
        ...state,
        photos: state.photos.map(photo => photo.id === action.payload ? {...photo, liked: !photo.liked} : photo)
      };
    case constants.DELETE_PHOTO:
      return {
        ...state,
        photos: state.photos.filter(photo => photo.id !== action.id)
      };
    case constants.CLEAR_PHOTOS:
      return {
        ...state,
        photos: [],
      };
    default:
      return state;
  }
}
