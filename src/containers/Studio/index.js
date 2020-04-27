import React from 'react';
import {connect} from "react-redux";
import {
  getStudioData,
  getMasters,
  registerMaster,
  addStudioToFavorites,
  removeStudioFromFavorites,
  getStudioFeebacks,
  giveStudioFeedback
} from "./actions";
import {Button, Form, FormControl, Card} from "react-bootstrap";
import Rating from 'react-rating';
import {isAuth} from "../../utils";
import EmptyRatingSymbol from '../../assets/icons/star-empty.svg';
import FullRatingSymbol from '../../assets/icons/star-full.svg';

class Studios extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      masterId: null,
      feedback: '',
      rating: 0,
    }
  }



  componentDidMount() {
    this.props.getStudioData(this.props.match.params.studioId);
    this.props.getMasters();
    this.props.getStudioFeebacks(this.props.match.params.studioId);
  }

  selectMaster = e => this.setState({masterId: e.target.value});

  handleChangeFeedback = e => this.setState({feedback: e.target.value});

  handleChangeRating = value => this.setState({rating: value});

  getStudioData = () => {
    this.props.getStudioData(this.props.match.params.studioId)
  };

  handleAddMaster = e => {
    e.preventDefault();

    this.props.registerMaster({
      masterId: this.state.masterId,
      studioId: this.props.studioData.id
    }, this.getStudioData)
  };

  handleAddFeedback = e => {
    e.preventDefault();
    const {giveStudioFeedback, studioData} = this.props;
    const {feedback, rating } = this.state;

    const cb = () => {
      this.setState({feedback: '', rating: 0});
    };

    giveStudioFeedback({
      studioId: studioData.id,
      feedback,
      rating
    }, cb)
  };

  render() {
    const {
      studioData : {
        id,
        name,
        rating,
        description,
        address,
        owner,
        contact,
        ownerId,
        masters,
        favourite,
      },
      mastersToSelect,
      userId,
      addToFavorites,
      removeFromFavorites,
      feedbacks,
    } = this.props;
    const { feedback } = this.state;

    return (
      <div className="container-fluid">
        {isAuth() ? favourite === "true" ?
          <Button
            className="float-right mt-2"
            onClick={() => removeFromFavorites(id, this.getStudioData)}>
            Remove from favorites
          </Button> :
          <Button
            className="float-right mt-2"
            onClick={() => addToFavorites(id, this.getStudioData)}>
            Add to favorites
          </Button> :
          null}
        <h1>{name}</h1>
        <p>Rating: {rating}</p>
        <p>Owner: {owner}</p>
        <p>Contact: {contact}</p>
        <div className="d-flex align-items-center">
          {masters && <p>Masters: {masters.map(master => master.username).toString()}</p>}
          {ownerId === userId &&
          <Form onSubmit={this.handleAddMaster}>
            <Form.Group controlId="mastersSelect" className="d-flex ml-3">
              <Form.Control as="select" custom="true" onChange={this.selectMaster}>
                <option hidden value={null}>Select master</option>
                {mastersToSelect.map(master => (
                  <option key={master.id} value={master.id}>{master.username}</option>
                ))}
              </Form.Control>
              <Button type="submit" disabled={!this.state.masterId} className="ml-3">Add</Button>
            </Form.Group>
          </Form>}
        </div>
        <p>{address}</p>
        <p>{description}</p>
        <div>
          <h4>Feedbacks</h4>
          {feedbacks.length ?
            feedbacks.map(fbck => (
              <Card className="mb-3">
                <Card.Body>
                  <Card.Text>
                    <b className="d-flex justify-content-between">
                      {fbck.username}
                      <Rating
                        emptySymbol={<img width={10} height={10} src={EmptyRatingSymbol} alt=""/>}
                        fullSymbol={<img width={10} height={10} src={FullRatingSymbol} alt=""/>}
                        initialRating={fbck.rating}
                        readonly/>
                    </b>
                  </Card.Text>
                  {fbck.feedback && <Card.Text>{fbck.feedback}</Card.Text>}
                </Card.Body>
              </Card>
            )) :
            <div className="mb-3"><i className="text-muted">No feedbacks yet</i></div>}
          {isAuth() &&
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
              value={feedback}
              onChange={this.handleChangeFeedback}
            />
            <Button className="mt-2" type="submit">Add feedback</Button>
          </Form>}
        </div>
      </div>
    )
  }
}

export default connect(
  state => ({
    userId: state.user.id,
    studioData: state.studio.studioData,
    mastersToSelect: state.studio.mastersToSelect,
    feedbacks: state.studio.feedbacks,
  }),
  dispatch => ({
    getStudioData: (studioId) => dispatch(getStudioData(studioId)),
    getMasters: () => dispatch(getMasters()),
    registerMaster: (data, cb) => dispatch(registerMaster(data, cb)),
    addToFavorites: (studioIid, cb) => dispatch(addStudioToFavorites(studioIid, cb)),
    removeFromFavorites: (studioId, cb) => dispatch(removeStudioFromFavorites(studioId, cb)),
    getStudioFeebacks: studioId => dispatch(getStudioFeebacks(studioId)),
    giveStudioFeedback: (data, cb) => dispatch(giveStudioFeedback(data, cb))
  }))(Studios);
