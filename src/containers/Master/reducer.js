import * as constants from './constants';

const initialState = {
  masterData: {}
};

export function masterReducer(state = initialState, action) {
  switch (action.type) {
    case constants.GET_MASTER_DATA_SUCCESS:
      return {...state, masterData: action.data};
    case constants.TOGGLE_LIKE:
      return {
        ...state,
        masterData: {
          ...state.masterData,
          posts: state.masterData.posts.map(post => post.id === action.payload ? {
            ...post,
            liked: !post.liked,
            likesNumber: post.liked ? post.likesNumber - 1 : post.likesNumber + 1
          } : post)
        }
      };
    case constants.DELETE_PHOTO:
      return {
        ...state,
        masterData: {
          ...state.masterData,
          posts: state.masterData.posts.filter(post => post.id !== action.id)
        }

      };

    default:
      return state;
  }
}
