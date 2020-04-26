import {photoGridConstants as _} from "./constants";
import {api} from "../../api/app";
import {apiErrorHandler} from "../../utils";

const getArr = (arrSize)=>{
    let i=0, temp=[];
    while(i < arrSize) {temp[i] = i++};
    return temp;
}

function setColumns(colNum) {
    return{
        type: _.SET_COLUMNS,
        payload: getArr(colNum)
    }
}

function setModalOpenFlag(flag) {
    return{
        type: _.SET_MODAL_OPEN_FLAG,
        payload: flag
    }
}

function setModalPhoto(photo) {
    return{
        type: _.SET_MODAL_PHOTO,
        payload: photo
    }
}

function toggleModalPhotoLike() {
    return {
        type: _.TOGGLE_MODAL_PHOTO_LIKE
    }
}

function deletePhoto(photoId, cb) {
    return dispatch => {
        dispatch({
            type: _.DELETE_PHOTO_REQUEST
        });
        api.deletePhoto(photoId).then(
          () => {
              dispatch({
                  type: _.DELETE_PHOTO_SUCCESS,
              });
              cb && cb();
          },
          error => {
              dispatch({
                  type: _.DELETE_PHOTO_FAILURE,
                  payload: error.message
              });
              apiErrorHandler(error)
          }
        )
    }
}

export const photoGridActions = {
    setColumns,
    setModalOpenFlag,
    setModalPhoto,
    deletePhoto,
};
