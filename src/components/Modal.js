import React from 'react';
import PropTypes from 'prop-types';

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
        const {modalPhoto} = this.props;
        return(
            <>
                <div className="Modal" onClick={this.handleCloseModal}>
                    <span className="close-btn">&times;</span>
                    <div className="Modal-content">
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
