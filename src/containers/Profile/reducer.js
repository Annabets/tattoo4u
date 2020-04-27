import * as constants from './constants';

const initialState = {
  photos:[],
  isLoadingPhotos: false,
  isUploadFailed: false,
  favoriteStudios: [],
  favoriteMasters: [],
  error: '',
};

export function profileReducer(state = initialState, action) {
  switch (action.type) {
    case constants.UPLOAD_PHOTO_FAILURE:
    case constants.REGISTER_STUDIO_FAILURE:
      return { ...state, error: action.payload};
    case constants.RESET_ERROR:
      return { ...state, error: ''};
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
        error: action.payload,
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
    case constants.TOGGLE_LIKE:
      return {
        ...state,
        photos: state.photos.map(photo => photo.id === action.payload ? {
          ...photo,
          liked: !photo.liked,
          likesNumber: photo.liked ? photo.likesNumber - 1 : photo.likesNumber + 1
        } : photo)
      };
    case constants.GET_FAVORITE_STUDIOS_SUCCESS:
      return {
        ...state,
        favoriteStudios: action.data,
      };
    case constants.GET_FAVORITE_MASTERS_SUCCESS:
    case constants.REMOVE_MASTER_FROM_FAVORITES_SUCCESS:
      return {...state, favoriteMasters: action.data};

    default:
      return state;
  }
}
