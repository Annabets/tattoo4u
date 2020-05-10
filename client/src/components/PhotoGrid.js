import React from 'react';
import PropTypes from 'prop-types';
import Modal from './Modal';
import notLikedBtn from '../assets/icons/n-active-like-btn.svg';
import likedBtn from '../assets/icons/active-like-btn.svg';
import {isAuth} from "../utils";
import {MASTERS} from "../routes";

class PhotoGrid extends React.Component {
  constructor(props) {
    super(props)
  }

  handleGetMorePhotos = () => {
    const {getMorePhotos} = this.props;
    getMorePhotos();
  }

  renderColumn = (colNum, colLen) => {
    const {photos, deletePhoto, username, userRole, likePhoto} = this.props;
    let columnPhotos = photos.filter((item, index) => {
      return (index % colLen === colNum)
    })
    return columnPhotos.map((photo) => {
      return (
        <div className="Column-item" key={photo.id} id={photo.id} onClick={this.handleGridItemClick}>
          <img className="Column-item-img" src={`${photo.photoUrl}`} alt=""/>
          <div className="Column-item-content">
            <a href={`${MASTERS}/${photo.authorId}`}>
              {`${photo.authorName}`}
            </a>
            {isAuth() &&
            <button className="transparent-btn" value={photo.id} onClick={() => likePhoto(photo.id)}>
              {photo.liked || <img src={notLikedBtn} width="24" height="24" alt=""/>}
              {photo.liked && <img src={likedBtn} width="24" height="24" alt=""/>}
            </button>}
          </div>
          {(userRole === "ADMIN" || username === photo.authorName) &&
          <span className="--delete text-danger" onClick={() => deletePhoto(photo.id)}>&times;</span>}
        </div>
      )
    })
  }

  renderContainerColumns = (columns) => {
    return columns.map((item, index) => {
      return (
        <div key={index} className="Grid-column">{this.renderColumn(index, columns.length)}</div>
      )
    })
  }

  handleGridItemClick = (e) => {
    const {setModalOpenFlag, setModalPhoto, photos} = this.props;
    if (e.target.className === "Column-item-content" || e.target.className === "Column-item-img") {
      setModalPhoto(photos.find((photo) => {
        return photo.id === Number(e.currentTarget.id)
      }));
      setModalOpenFlag(true);
      document.body.style = `overflow-y: hidden`;
    }
  }


  handleResize = () => {
    const {columns, setColumns} = this.props;
    const colNum = columns.length;
    const width = window.innerWidth;
    if (width < 625 && colNum !== 1)
      setColumns(1)
    else if (width > 625 && width < 1000 && colNum !== 2)
      setColumns(2)
    else if (width > 1000 && width < 1500 && colNum !== 3)
      setColumns(3)
    else if (width > 1500 && colNum !== 4)
      setColumns(4)
  }

  handleScroll = () => {
    const {isLoadingPhotos, isUploadFailed} = this.props;
    if (((window.innerHeight * 2 + window.scrollY) >= document.body.scrollHeight) && !isLoadingPhotos && !isUploadFailed) {
      this.handleGetMorePhotos();
    }
  }

  componentDidMount = () => {
    this.props.onMount && this.props.onMount();

    window.addEventListener("resize", this.handleResize);
    window.addEventListener("scroll", this.handleScroll);

    this.handleGetMorePhotos();
    this.handleResize();
  }

  componentWillUnmount = () => {
    window.removeEventListener("resize", this.handleResize);
    window.removeEventListener("scroll", this.handleScroll);
  }

  render() {
    const {
      columns,
      photos,
      isLoadingPhotos,
      isUploadFailed,
      isModalOpen,
      modalPhoto,
      setModalOpenFlag,
      likePhoto,
      comments,
      getComments,
      addComment,
    } = this.props;
    return (
      <>
        <section className="Photo-grid">
          {photos.length > 0 ?
            <div className="Grid-container">
              {this.renderContainerColumns(columns)}
            </div> :
            !isLoadingPhotos && !isUploadFailed &&
            <div className="h-100 w-100 text-center"><i className="fs-18">No photos</i></div>}
          {isLoadingPhotos &&
          <div className="Loading-indicator">
            Loading...
          </div>}
          {isUploadFailed && <h3>{'Failed to upload photos'}</h3>}
        </section>
        {isModalOpen &&
        <Modal
          modalPhoto={modalPhoto}
          setModalOpenFlag={setModalOpenFlag}
          likePhoto={likePhoto}
          comments={comments}
          getComments={getComments}
          addComment={addComment}
        />}
      </>
    )
  }
}

PhotoGrid.propTypes = {
  isLoadingPhotos: PropTypes.bool.isRequired,
  isUploadFailed: PropTypes.bool.isRequired,
  columns: PropTypes.array.isRequired,
  photos: PropTypes.array.isRequired,
  getMorePhotos: PropTypes.func.isRequired,
  modalPhoto: PropTypes.object.isRequired,
  isModalOpen: PropTypes.bool.isRequired,
  setColumns: PropTypes.func.isRequired,
  setModalOpenFlag: PropTypes.func.isRequired,
  setModalPhoto: PropTypes.func.isRequired,
  deletePhoto: PropTypes.func,
};

export default PhotoGrid;
