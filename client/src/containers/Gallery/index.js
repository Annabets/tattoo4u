import React from 'react';
import {connect} from "react-redux";
import {Tabs, Tab, Form, FormControl, Button} from 'react-bootstrap';
import {} from "./actions";
import PhotoGridContainer from "../PhotoGrid";
import {clearPhotos, deletePhoto, getNewPhotos, getTrendingPhotos, searchPhotos} from "./actions";

class Gallery extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      searchString: '',
    }
  }

  handleChange = e => this.setState({searchString: e.target.value});

  searchPhotos = e => {
    e && e.preventDefault();
    this.props.clearPhotos();
    this.props.searchPhotos(this.state.searchString);
  };

  render() {
    const {
      photos,
      isLoadingPhotos,
      isUploadFailed,
      getNewPhotos,
      getTrendingPhotos,
      deletePhoto,
      clearPhotos,
    } = this.props;
    return (
      <>
        <Tabs defaultActiveKey="newPhotos" id="uncontrolled-tab-example" unmountOnExit>
          <Tab eventKey="newPhotos" title="New Photos">
            <PhotoGridContainer
              pages={photos}
              getMorePhotos={getNewPhotos}
              isLoadingPhotos={isLoadingPhotos}
              isUploadFailed={isUploadFailed}
              onDeletePhoto={deletePhoto}
              onMount={clearPhotos}
            />
          </Tab>
          <Tab eventKey="trendingPhotos" title="Trending Photos">
            <PhotoGridContainer
              pages={photos}
              getMorePhotos={getTrendingPhotos}
              isLoadingPhotos={isLoadingPhotos}
              isUploadFailed={isUploadFailed}
              onDeletePhoto={deletePhoto}
              onMount={clearPhotos}
            />
          </Tab>
          <Tab eventKey="searchPhotos" title="Search Photos">
            <Form inline className="mx-2 my-3 d-flex flex-nowrap" onSubmit={this.searchPhotos}>
              <FormControl type="text" placeholder="Search" className="mr-sm-2 w-100" value={this.state.searchString} onChange={this.handleChange} />
              <Button variant="outline-success" onClick={this.searchPhotos}>Search</Button>
            </Form>
            <PhotoGridContainer
              pages={photos}
              getMorePhotos={this.searchPhotos}
              isLoadingPhotos={isLoadingPhotos}
              isUploadFailed={isUploadFailed}
              onDeletePhoto={deletePhoto}
              onMount={clearPhotos}
            />
          </Tab>
        </Tabs>
      </>
    )
  }
}

export default connect(
  state => ({
    photos: state.gallery.photos,
    isLoadingPhotos: state.gallery.isLoadingPhotos,
    isUploadFailed: state.gallery.isUploadFailed,
  }),
  dispatch => ({
    getNewPhotos: (page, size) => dispatch(getNewPhotos(page, size)),
    getTrendingPhotos: () => dispatch(getTrendingPhotos()),
    searchPhotos: tags => dispatch(searchPhotos(tags)),
    deletePhoto: id => dispatch(deletePhoto(id)),
    clearPhotos: () => dispatch(clearPhotos()),
  }))(Gallery);
