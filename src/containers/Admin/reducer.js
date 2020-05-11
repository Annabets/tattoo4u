import * as constants from './constants';

const initialState = {
  users: [],
  studios: [],
};

export function adminReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_USERS_LIST_SUCCESS:
      return {...state, users: action.data};
    case constants.GET_NOT_CONFIRMED_STUDIOS_SUCCESS:
    case constants.CONFIRM_STUDIO_SUCCESS:
      return {...state, studios: action.data};

    default:
      return state;
  }
}
