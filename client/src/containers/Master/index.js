import React from 'react';
import {connect} from "react-redux";
import {getMasterData, addMasterToFavorites, removeMasterFromFavorites, deletePhoto, getMasterFeebacks, giveMasterFeedback} from "./actions";
import {isAuth} from "../../utils";
import {Button, Tabs, Tab, Card, Form, FormControl} from "react-bootstrap";
import Rating from "react-rating";
import EmptyRatingSymbol from "../../assets/icons/star-empty.svg";
import FullRatingSymbol from "../../assets/icons/star-full.svg";
import PhotoGridContainer from "../PhotoGrid";

class Master extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      comment: '',
      rating: 0,
    }
  }

  getMasterData = () => {
    this.props.getMasterData(this.props.match.params.masterId);
  };

  componentDidMount() {
    this.getMasterData();
    this.props.getFeedbacks(this.props.match.params.masterId);
  }

  handleChangeComment = e => this.setState({comment: e.target.value});

  handleChangeRating = value => this.setState({rating: value});

  handleAddFeedback = e => {
    e.preventDefault();
    const {addFeedback, masterData, getFeedbacks} = this.props;

    const cb = () => {
      this.setState({feedback: '', rating: 0});
      getFeedbacks(masterData.id);
    };

    addFeedback({
      masterId: masterData.id,
      ...this.state,
    }, cb)
  };

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
      currentUsername,
      addMasterToFavorites,
      removeMasterFromFavorites,
      deletePhoto,
      comments,
    } = this.props;
    const {comment} = this.state;

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
            <>
              {comments.length ?
                comments.map((comment, i) => (
                  <Card className="my-3" key={i}>
                    <Card.Body>
                      <Card.Text>
                        <b className="d-flex justify-content-between">
                          {comment.username}
                          <Rating
                            emptySymbol={<img width={10} height={10} src={EmptyRatingSymbol} alt=""/>}
                            fullSymbol={<img width={10} height={10} src={FullRatingSymbol} alt=""/>}
                            initialRating={comment.rating}
                            readonly/>
                        </b>
                      </Card.Text>
                      {comment.comment && <Card.Text>{comment.comment}</Card.Text>}
                    </Card.Body>
                  </Card>
                )) :
                <div className="mb-3"><i className="text-muted">No feedbacks yet</i></div>}
              {isAuth() && !comments.some(comment => comment.username === currentUsername) &&
              <Form onSubmit={this.handleAddFeedback} className="border p-3">
                Rating:
                <Rating
                  start={0}
                  stop={5}
                  emptySymbol={<img width={15} height={15} src={EmptyRatingSymbol} alt=""/>}
                  fullSymbol={<img width={15} height={15} src={FullRatingSymbol} alt=""/>}
                  fractions={2}
                  initialRating={this.state.rating}
                  onChange={this.handleChangeRating}
                  className="mb-2 ml-2"
                />
                <FormControl
                  type="text"
                  as="textarea"
                  rows={3}
                  value={comment}
                  onChange={this.handleChangeComment}
                />
                <Button className="mt-2" type="submit">Add feedback</Button>
              </Form>}
            </>
          </Tab>
        </Tabs>
      </div>
    )
  }
}

export default connect(
  state => ({
    userId: state.user.id,
    currentUsername: state.user.username,
    masterData: state.master.masterData,
    comments: state.master.comments,
  }),
  dispatch => ({
    getMasterData: masterId => dispatch(getMasterData(masterId)),
    addMasterToFavorites: (masterId, cb) => dispatch(addMasterToFavorites(masterId, cb)),
    removeMasterFromFavorites: (masterId, cb) => dispatch(removeMasterFromFavorites(masterId, cb)),
    deletePhoto: id => dispatch(deletePhoto(id)),
    getFeedbacks: (masterId, page, size) => dispatch(getMasterFeebacks(masterId, page,size)),
    addFeedback: (data, cb) => dispatch(giveMasterFeedback(data, cb)),
  })
)(Master)
