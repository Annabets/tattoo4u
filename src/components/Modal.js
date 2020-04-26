import React from 'react';
import PropTypes from 'prop-types';
import notLikedBtn from '../assets/icons/n-active-like-btn-black.svg';
import likedBtn from '../assets/icons/active-like-btn.svg';
import {isAuth} from "../utils";
import {Button, Form, FormControl} from "react-bootstrap";
import moment from "moment";

class Modal extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      comment: '',
    }
  }

  getComments = () => {
    this.props.getComments(this.props.modalPhoto.id);
  };

  componentDidMount() {
    this.getComments();
  }

  handleCloseModal = (e) => {
    if (e.target.className === "Modal" || e.target.className === "close-btn") {
      this.props.setModalOpenFlag(false);
      document.body.style = `overflow-y: visible`;
    }
  }

  handleChange = e => this.setState({comment: e.target.value});

  handleAddComment = e => {
    e.preventDefault();
    const {addComment, modalPhoto} = this.props;
    const cb = () => {
      this.setState({comment: ''});
      this.getComments();
    };

    addComment(modalPhoto.id, this.state, cb);
  };

  render() {
    const {modalPhoto, likePhoto, comments} = this.props;
    const { comment } = this.state;
    return (
      <>
        <div className="Modal" onClick={this.handleCloseModal}>
          <span className="close-btn">&times;</span>
          <div className="Modal-content">
            <div className="Modal-action-bar">
              <a className="modal-like-btn" onClick={isAuth() ? () => likePhoto(modalPhoto.id) : () => {
              }}>
                {modalPhoto.liked ||
                <img src={notLikedBtn} width="24" height="24" alt=""/>}
                {modalPhoto.liked &&
                <img src={likedBtn} width="24" height="24" alt=""/>}
                <span className="ml-1">{modalPhoto.likesNumber}</span>
              </a>
            </div>
            <div className="d-flex">
              <div className="Modal-photo flex-grow-1">
                <img src={`${modalPhoto.photoUrl}`} alt=""/>
              </div>
              <div className="comments-section">
                <p className="border-bottom">{modalPhoto.description}</p>
                <h5>Comments</h5>
                {comments.length ?
                  comments.map(comment => (
                    <div key={comment.id}>
                      <div className="d-flex justify-content-between">
                        <b>{comment.author}</b>
                        <span>{moment(comment.date).format('DD MMM hh:mm').toString()}</span>
                      </div>
                      <div>{comment.text}</div>
                    </div>
                  )) :
                  <i className="text-muted">no comments yet</i>}
                  {isAuth() &&
                  <Form className="mt-3" onSubmit={this.handleAddComment}>
                    <FormControl
                      type="text"
                      as="textarea"
                      rows={3}
                      required
                      value={comment}
                      onChange={this.handleChange}
                    />
                    <Button className="mt-2" type="submit">Add comment</Button>
                  </Form>}
              </div>
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
  comments: PropTypes.array,
  getComments: PropTypes.func,
  addComment: PropTypes.func,
};

export default Modal;
