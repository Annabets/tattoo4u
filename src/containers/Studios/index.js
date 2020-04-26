import React from 'react';
import {connect} from "react-redux";
import { Card, Form, FormControl, Button } from 'react-bootstrap';
import {getStudios} from "../Studios/actions";
import {STUDIOS} from "../../routes";
import StudioLogo from '../../assets/images/tattoo-machine-logo-vector.jpg';

class Studios extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      searchString: '',
    }
  }

  componentDidMount() {
    this.props.getStudios();
  }

  handleChange = e => this.setState({searchString: e.target.value})

  searchStudios = () => {
    this.props.getStudios(this.state.searchString || null);
  };

  renderStudio = studio => (
    <Card className="m-2" style={{ width: '18rem' }}>
      <Card.Img variant="top" src={studio.photo || StudioLogo}  />
      <Card.Body>
        <Card.Title>{studio.name}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">{studio.address}</Card.Subtitle>
        <Card.Subtitle className="mb-2 text-muted">{studio.contact}</Card.Subtitle>
        <Card.Text>
          {studio.description}
        </Card.Text>
        <Card.Link href={`${STUDIOS}/${studio.id}`}>more details</Card.Link>
      </Card.Body>
    </Card>
  );

  render() {
    const {searchString} = this.state;
    return (
      <div className="container-fluid">
        <Form inline className="mx-2 my-3 d-flex flex-nowrap">
          <FormControl type="text" placeholder="Search" className="mr-sm-2 w-100" value={searchString} onChange={this.handleChange} />
          <Button variant="outline-success" onClick={this.searchStudios}>Search</Button>
        </Form>
        <div className="d-flex flex-wrap">
          {this.props.studiosList.map((studio, index) => (
            <React.Fragment key={index}>
              {this.renderStudio(studio)}
            </React.Fragment>
          ))}
        </div>
      </div>

    )
  }
}

export default connect(
  state => ({
    studiosList: state.studios.studiosList
  }),
  dispatch => ({
    getStudios: (searchString) => dispatch(getStudios(searchString)),
  }))(Studios);
