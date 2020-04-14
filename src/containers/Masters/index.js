import React from 'react';
import {connect} from "react-redux";
import { Card, Form, FormControl, Button } from 'react-bootstrap';
import {getMasters} from "./actions";

class Masters extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      searchString: '',
    }
  }

  componentDidMount() {
    this.props.getMasters();
  }

  handleChange = e => this.setState({searchString: e.target.value})

  searchMasters = () => {
    this.props.getMasters(this.state.searchString);
  };

  renderMaster = master => (
    <Card className="m-2" style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>{master.username}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">{master.email}</Card.Subtitle>
      </Card.Body>
    </Card>
  );

  render() {
    const {searchString} = this.state;
    return (
      <div className="container-fluid">
        <Form inline className="mx-2 my-3 d-flex flex-nowrap">
          <FormControl type="text" placeholder="Search" className="mr-sm-2 w-100" value={searchString} onChange={this.handleChange} />
          <Button variant="outline-success" onClick={this.searchMasters}>Search</Button>
        </Form>
        <div className="d-flex">
          {this.props.mastersList.map((master, index) => (
            <React.Fragment key={index}>
              {this.renderMaster(master)}
            </React.Fragment>
          ))}
        </div>
      </div>

    )
  }
}

export default connect(
  state => ({
    mastersList: state.masters.mastersList
  }),
  dispatch => ({
    getMasters: (searchString) => dispatch(getMasters(searchString)),
  }))(Masters);
