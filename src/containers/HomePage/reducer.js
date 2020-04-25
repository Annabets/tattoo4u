import * as constants from './constants';

const initialState = {
  photos:[],
  isLoadingPhotos: false,
  isUploadFailed: false,
};

export function homePageReducer(state = initialState, action) {
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
    default:
      return state;
  }
}