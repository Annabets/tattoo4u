import * as constants from './constants';

const initialState = {
  error: '',
};

export function profileReducer(state = initialState, action) {
  switch (action.type) {
    case constants.UPLOAD_PHOTO_FAILURE:
    case constants.REGISTER_STUDIO_FAILURE:
      return {error: action.payload};
    case constants.RESET_ERROR:
      return {error: ''}

    default:
      return state;
  }
}
