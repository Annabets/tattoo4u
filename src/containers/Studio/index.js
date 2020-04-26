import React from 'react';
import {connect} from "react-redux";
import {getStudioData, getMasters, registerMaster, addStudioToFavorites, removeStudioFromFavorites} from "./actions";
import {Button, Form,} from "react-bootstrap";
import {isAuth} from "../../utils";

class Studios extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      masterId: null
    }
  }

  componentDidMount() {
    this.props.getStudioData(this.props.match.params.studioId);
    this.props.getMasters();
  }

  selectMaster = e => this.setState({masterId: e.target.value})

  getStudioData = () => {
    this.props.getStudioData(this.props.match.params.studioId)
  };

  handleSubmit = e => {
    e.preventDefault();

    this.props.registerMaster({
      masterId: this.state.masterId,
      studioId: this.props.studioData.id
    }, this.getStudioData)
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
      removeFromFavorites
    } = this.props;
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
        {masters && <p>Masters: {masters.map(master => master.username).toString()}</p>}
        <p>{address}</p>
        <p>{description}</p>
        {ownerId === userId &&
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="mastersSelect">
            <Form.Control as="select" custom="true" onChange={this.selectMaster}>
              <option hidden value={null}>Select master</option>
              {mastersToSelect.map(master => (
                <option key={master.id} value={master.id}>{master.username}</option>
              ))}
            </Form.Control>
          </Form.Group>
          <Button type="submit" disabled={!this.state.masterId}>Add</Button>
        </Form>}
      </div>
    )
  }
}

export default connect(
  state => ({
    userId: state.user.id,
    studioData: state.studio.studioData,
    mastersToSelect: state.studio.mastersToSelect,
  }),
  dispatch => ({
    getStudioData: (studioId) => dispatch(getStudioData(studioId)),
    getMasters: () => dispatch(getMasters()),
    registerMaster: (data, cb) => dispatch(registerMaster(data, cb)),
    addToFavorites: (studioIid, cb) => dispatch(addStudioToFavorites(studioIid, cb)),
    removeFromFavorites: (studioId, cb) => dispatch(removeStudioFromFavorites(studioId, cb))
  }))(Studios);
