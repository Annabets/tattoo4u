import React from 'react';
import {connect} from "react-redux";
import {getPhotos} from "./actions";
import PhotoGrid from '../PhotoGrid';

class HomePage extends React.Component {

  render() {
    const {homePage, getPhotos} = this.props;

    return (
      <>
        <PhotoGrid
          pages={homePage.photos}
          getMorePhotos={getPhotos}
          isLoadingPhotos={homePage.isLoadingPhotos}
          isUploadFailed={homePage.isUploadFailed}
        />
      </>
    )
  }
}

const mapStateToProps = store => {
  return {
    homePage: store.homePage
  }
};

const mapDispatchToProps = dispatch => {
  return {
    getPhotos: (page, size) => dispatch(getPhotos(page, size)),
  }
};

export default connect(mapStateToProps,mapDispatchToProps)(HomePage);
