import * as constants from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

export function getMasters(searchString) {
  return dispatch => {
    dispatch({
      type: constants.GET_MASTERS_REQUEST,
    });
    api.getMasters(searchString).then(
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
