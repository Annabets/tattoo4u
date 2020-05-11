import * as constants from './constants';

const initialState = {
  studiosList: []
};

export function studiosReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_STUDIOS_SUCCESS:
      return {
        studiosList: action.data.studios,
      };

    default:
      return state;
  }
}
