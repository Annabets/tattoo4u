import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getStudioData(studioId) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIO_DATA_REQUEST,
    });
    api.getStudioData(studioId).then(
      response => {
        dispatch({
          type: constants.GET_STUDIO_DATA_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_STUDIO_DATA_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function getMasters() {
  return dispatch => {
    dispatch({
      type: constants.GET_MASTERS_REQUEST,
    });
    api.getMasters().then(
      response => {
        dispatch({
          type: constants.GET_MASTERS_SUCCESS,
          data: response.data,
        })
      },
      error => {
        dispatch({
          type: constants.GET_MASTERS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function registerMaster(data, cb) {
  return dispatch => {
    dispatch({
      type: constants.REGISTER_MASTERS_REQUEST,
    });
    api.registerMaster(data).then(
      response => {
        dispatch({
          type: constants.REGISTER_MASTERS_SUCCESS,
          data: response.data,
        });
        cb && cb(data)
      },
      error => {
        dispatch({
          type: constants.REGISTER_MASTERS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function addStudioToFavorites(studioId, cb) {
  return dispatch => {
    dispatch({
      type: constants.ADD_STUDIO_TO_FAVORITES_REQUEST,
    });
    api.addStudioToFav(studioId).then(
      () => {
        dispatch({
          type: constants.ADD_STUDIO_TO_FAVORITES_SUCCESS,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.ADD_STUDIO_TO_FAVORITES_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function removeStudioFromFavorites(studioId, cb) {
  return dispatch => {
    dispatch({
      type: constants.REMOVE_STUDIO_FROM_FAVORITES_REQUEST,
    });
    api.removeStudioFromFav(studioId).then(
      () => {
        dispatch({
          type: constants.REMOVE_STUDIO_FROM_FAVORITES_SUCCESS,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.REMOVE_STUDIO_FROM_FAVORITES_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}

export function getStudioFeebacks(studioId) {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIO_FEEDBACKS_REQUEST
    });
    api.getStudioFeedbacks(studioId).then(
      response => {
        dispatch({
          type: constants.GET_STUDIO_FEEDBACKS_SUCCESS,
          data: response.data
        })
      },
      error => {
        dispatch({
          type: constants.GET_STUDIO_FEEDBACKS_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error);
      }
    )
  }
}

export function giveStudioFeedback(data, cb) {
  return dispatch => {
    dispatch({
      type: constants.GIVE_STUDIO_FEEDBACK_REQUEST,
    });
    api.giveStudioFeedback(data).then(
      response => {
        dispatch({
          type: constants.GIVE_STUDIO_FEEDBACK_SUCCESS,
          data: response.data,
        });
        cb && cb()
      },
      error => {
        dispatch({
          type: constants.GIVE_STUDIO_FEEDBACK_FAILURE,
          payload: error.message
        });
        apiErrorHandler(error)
      }
    )
  }
}
