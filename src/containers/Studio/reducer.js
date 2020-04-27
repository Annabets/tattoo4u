import * as constants from './constants';

const initialState = {
  studioData: {},
  mastersToSelect: [],
  feedbacks: [],
};

export function studioReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_STUDIO_DATA_SUCCESS:
      return {...state, studioData: action.data};
    case constants.GET_MASTERS_SUCCESS:
      return {...state, mastersToSelect: action.data};
    case constants.GIVE_STUDIO_FEEDBACK_SUCCESS:
    case constants.GET_STUDIO_FEEDBACKS_SUCCESS:
      return {...state, feedbacks: action.data};

    default:
      return state
  }
}
