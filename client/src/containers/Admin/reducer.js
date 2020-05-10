import * as constants from './constants';

const initialState = {
  users: [],
};

export function adminReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_USERS_LIST_SUCCESS:
      return {...state, users: action.data};

    default:
      return state;
  }
}
