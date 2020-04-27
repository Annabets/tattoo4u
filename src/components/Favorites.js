import React from 'react';
import {Button, Card} from "react-bootstrap";
import {Link} from "react-router-dom";
import {MASTERS, STUDIOS} from "../routes";

class Favorites extends React.Component{

  componentDidMount() {
    this.props.getFavoriteStudios();
    this.props.getFavoriteMasters();
  }

  handleRemoveStudio = studioId => () => {
    const { getFavoriteStudios, removeStudioFromFavorites } = this.props;
    removeStudioFromFavorites(studioId, () => getFavoriteStudios());
  };

  handleRemoveMaster = masterId => () => {
    const {removeMasterFromFavorites} = this.props;
    removeMasterFromFavorites(masterId);
  };

  render() {
    const { favoriteStudios, favoriteMasters } = this.props;
    return (
      <div className="favorites d-flex">
        <div className="w-50 p-3">
          <h3>Favorite Studios</h3>
          <div className="d-flex flex-wrap">
          {favoriteStudios.map(studio => (
            <Card key={studio.id} className="mr-2 mt-2" style={{width: '20rem'}}>
              <Card.Body>
                <Card.Title className="d-flex justify-content-between">
                  <div><Link to={`${STUDIOS}/${studio.id}`}>{studio.name}</Link></div>
                  <div>{studio.rating}</div>
                </Card.Title>
                <Card.Text>{studio.description}</Card.Text>
                <Button onClick={this.handleRemoveStudio(studio.id)}>Remove</Button>
              </Card.Body>
            </Card>
          ))}
          </div>
        </div>
        <div className="w-50 p-3">
          <h3>Favorite Masters</h3>
          <div className="d-flex flex-wrap">
            {favoriteMasters.map(master => (
              <Card key={master.id} className="mr-2 mt-2" style={{width: '20rem'}}>
                <Card.Body>
                  <Card.Title className="d-flex justify-content-between">
                    <div><Link to={`${MASTERS}/${master.id}`}>{master.username}</Link></div>
                    <div>{master.rating}</div>
                  </Card.Title>
                  <Card.Text>{master.email}</Card.Text>
                  <Button onClick={this.handleRemoveMaster(master.id)}>Remove</Button>
                </Card.Body>
              </Card>
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default Favorites;
