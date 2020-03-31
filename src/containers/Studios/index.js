import React from 'react';
import {connect} from "react-redux";
import {getStudios} from "../Studios/actions";

class Studios extends React.Component {

  render() {
    this.props.getStudios();
    return (
      <>

      </>
    )
  }
}

export default connect(null, dispatch => ({getStudios: () => dispatch(getStudios())}))(Studios);
