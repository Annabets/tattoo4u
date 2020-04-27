import * as constants from './constants';

const initialState = {
  studioData: {},
  mastersToSelect: [],
  feedbacks: [],
  studioOrders: [],
  error: '',
};

export function studioReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_STUDIO_DATA_SUCCESS:
      return {...state, studioData: action.data};
    case constants.GET_MASTERS_SUCCESS:
      return {...state, mastersToSelect: action.data};
    case constants.GIVE_STUDIO_FEEDBACK_SUCCESS:
      return {
        ...state,
        feedbacks: action.data.feedbacks,
        studioData: {
          ...state.studioData,
          rating: action.data.rating,
        }
      };
    case constants.GET_STUDIO_FEEDBACKS_SUCCESS:
      return {...state, feedbacks: action.data};
    case constants.GET_STUDIO_ORDERS_SUCCESS:
    case constants.GET_USER_ORDERS_SUCCESS:
      return {...state, studioOrders: action.data};
    case constants.ADD_ORDER_FAILURE:
      return {...state, error: action.payload};
    case constants.RESET_ERROR:
      return {...state, error: ''};

    default:
      return state
  }
}
