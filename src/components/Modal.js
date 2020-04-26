import React from 'react';
import PropTypes from 'prop-types';
import notLikedBtn from '../assets/icons/n-active-like-btn-black.svg';
import likedBtn from '../assets/icons/active-like-btn.svg';
import {isAuth} from "../utils";

class Modal extends React.Component{
    constructor(props){
        super(props)
    }


    handleCloseModal=(e)=>{
        if (e.target.className === "Modal" || e.target.className === "close-btn"){
            this.props.setModalOpenFlag(false);
            document.body.style = `overflow-y: visible`;
        }
    }

    render() {
        const {modalPhoto, likePhoto} = this.props;
        return(
            <>
                <div className="Modal" onClick={this.handleCloseModal}>
                    <span className="close-btn">&times;</span>
                    <div className="Modal-content">
                        <div className="Modal-action-bar">
                            <a className="modal-like-btn" onClick={isAuth() ? () => likePhoto(modalPhoto.id) : () => {}}>
                                {modalPhoto.liked ||
                                <img src={notLikedBtn} width="24" height="24" alt=""/>}
                                {modalPhoto.liked &&
                                <img src={likedBtn} width="24" height="24" alt=""/>}
                                <span className="ml-1">{modalPhoto.likesNumber}</span>
                            </a>
                        </div>
                        <div className="Modal-photo">
                            <img src={`${modalPhoto.photoUrl}`} alt=""/>
                        </div>
                    </div>
                </div>
            </>
        )
    }
}

Modal.propTypes = {
    modalPhoto: PropTypes.object.isRequired,
    setModalOpenFlag: PropTypes.func.isRequired,
}

export default Modal;
