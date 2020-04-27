import React from 'react';
import {connect} from "react-redux";
import {getMasterData, addMasterToFavorites, removeMasterFromFavorites, deletePhoto} from "./actions";
import {isAuth} from "../../utils";
import {Button, Tabs, Tab} from "react-bootstrap";
import Rating from "react-rating";
import EmptyRatingSymbol from "../../assets/icons/star-empty.svg";
import FullRatingSymbol from "../../assets/icons/star-full.svg";
import PhotoGridContainer from "../PhotoGrid";

class Master extends React.Component {
  constructor(props) {
    super(props)
  }

  getMasterData = () => {
    this.props.getMasterData(this.props.match.params.masterId);
  };

  componentDidMount() {
    this.getMasterData()
  }

  render() {
    const {
      masterData : {
        id,
        username,
        email,
        rating,
        posts,
        inFavorites
      },
      userId,
      addMasterToFavorites,
      removeMasterFromFavorites,
      deletePhoto,
    } = this.props;
    return(
      <div className="container-fluid position-relative">
        {isAuth() && id !== userId ? inFavorites ?
          <Button
            className="position-absolute mt-2"
            style={{top: 10, right: 15}}
            onClick={() => removeMasterFromFavorites(id, this.getMasterData)}
          >
            Remove from favorites
          </Button> :
          <Button
            className="position-absolute mt-2"
            style={{top: 10, right: 15}}
            onClick={() => addMasterToFavorites(id, this.getMasterData)}
          >
            Add to favorites
          </Button> :
          null}
        <div className="text-center">
          <h2>{username}</h2>
          <Rating
            emptySymbol={<img width={30} height={30} src={EmptyRatingSymbol} alt=""/>}
            fullSymbol={<img width={30} height={30} src={FullRatingSymbol} alt=""/>}
            initialRating={rating}
            readonly/>
          <p>{email}</p>
        </div>
        <Tabs defaultActiveKey="lastWorks">
          <Tab eventKey="lastWorks" title="Last Works">
            <PhotoGridContainer
              pages={posts || []}
              getMorePhotos={()=>{}}
              isLoadingPhotos={false}
              isUploadFailed={false}
              onDeletePhoto={deletePhoto}
            />
          </Tab>
          <Tab  eventKey="feedbacks" title="Feedbacks">
          </Tab>
        </Tabs>
      </div>
    )
  }
}

export default connect(
  state => ({
    userId: state.user.id,
    masterData: state.master.masterData,
  }),
  dispatch => ({
    getMasterData: masterId => dispatch(getMasterData(masterId)),
    addMasterToFavorites: (masterId, cb) => dispatch(addMasterToFavorites(masterId, cb)),
    removeMasterFromFavorites: (masterId, cb) => dispatch(removeMasterFromFavorites(masterId, cb)),
    deletePhoto: id => dispatch(deletePhoto(id)),
  })
)(Master)
