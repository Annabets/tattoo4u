import {photoGridConstants as _} from "./constants";

const initialState = {
    columns:[],
    modalPhoto: {},
    isModalOpen: false
}

export function photoGridReducer(state = initialState,action){
    switch (action.type) {
        case _.SET_COLUMNS:
            return{
                ...state,
                columns: action.payload
            }
        case _.SET_MODAL_OPEN_FLAG:
            return {
                ...state,
                isModalOpen: action.payload
            }
        case _.SET_MODAL_PHOTO:
            return {
                ...state,
                modalPhoto: action.payload
            }
        case _.TOGGLE_MODAL_PHOTO_LIKE:
            return {
                ...state,
                modalPhoto: {
                    ...state.modalPhoto,
                    liked: !state.modalPhoto.liked,
                    likesNumber: state.modalPhoto.liked ? state.modalPhoto.likesNumber - 1 : state.modalPhoto.likesNumber + 1
                }
            }

        default:
            return state
    }
}
