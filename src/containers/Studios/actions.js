import * as constants from "./constants";
import {api} from "../../api/app";

export function getStudios() {
  return dispatch => {
    dispatch({
      type: constants.GET_STUDIOS_REQUEST,
    });
    api.getStudios().then(
      userData => {
        dispatch({
          type: constants.GET_STUDIOS_SUCCESS,
          userData,
        })
        console.log(userData)
      },
      errorMessage => {
        dispatch({
          type: constants.GET_STUDIOS_FAILURE,
          payload: errorMessage
        })
      }
    )
  }
}
