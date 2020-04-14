import * as constants from "./constants";
import {api} from "../../api/app";

export function getMasters(searchString) {
  return dispatch => {
    dispatch({
      type: constants.GET_MASTERS_REQUEST,
    });
    api.getMasters(searchString).then(
      data => {
        dispatch({
          type: constants.GET_MASTERS_SUCCESS,
          data,
        })
      },
      errorMessage => {
        dispatch({
          type: constants.GET_MASTERS_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
