import * as constants from './constants';

const initialState = {
  studioData: {},
  mastersToSelect: [],
};

export function studioReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_STUDIO_DATA_SUCCESS:
      return {...state, studioData: action.data};
    case constants.GET_MASTERS_SUCCESS:
      return {...state, mastersToSelect: action.data};

    default:
      return state
  }
}
