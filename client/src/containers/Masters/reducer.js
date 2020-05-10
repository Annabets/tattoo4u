import * as constants from './constants';

const initialState = {
  mastersList: []
};

export function mastersReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_MASTERS_SUCCESS:
      return {
        mastersList: action.data
      };

    default:
      return state;
  }
}
