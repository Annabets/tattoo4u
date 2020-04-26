import React from 'react';
import {Button, Card} from "react-bootstrap";

class Favorites extends React.Component{

  componentDidMount() {
    this.props.getFavoriteStudios();
  }

  handleRemoveStudio = studioId => () => {
    const { getFavoriteStudios, removeStudioFromFavorites } = this.props;
    removeStudioFromFavorites(studioId, () => getFavoriteStudios());
  };

  render() {
    const { favoriteStudios } = this.props;
    return (
      <div className="favorites">
        <div className="w-50 p-3">
          <h3>Favorite Studios</h3>
          <div className="d-flex flex-wrap">
          {favoriteStudios.map(studio => (
            <Card key={studio.id} className="mr-2 mt-2" style={{width: '20rem'}}>
              <Card.Body>
                <Card.Title className="d-flex justify-content-between">
                  <div>{studio.name}</div>
                  <div>{studio.rating}</div>
                </Card.Title>
                <Card.Text>{studio.description}</Card.Text>
                <Button onClick={this.handleRemoveStudio(studio.id)}>Remove </Button>
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
