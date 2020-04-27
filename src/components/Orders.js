import React from 'react';
import {Alert, Button, Card, Form, FormLabel, Modal} from "react-bootstrap";

class Orders extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      modalVisible: false,
      file: null,
      description: '',
    }
  }

  showModal = () => this.setState({modalVisible: true});

  closeModal = () => this.setState({modalVisible: false});

  handleSubmit = e => {
    e.preventDefault();
    const {file, description} = this.state;
    const {addOrder, getOrders, studioId} = this.props;
    const cb = () => {
      this.closeModal();
      getOrders(studioId);
    };

    addOrder({
      studioId,
      description,
      file
    }, cb)
  };

  handleChange = field => e => {
    const value = field === 'file' ? e.target.files[0] : e.target.value;
    this.setState({[field]: value});
    this.props.resetError();
  };

  handleAccept = orderId => () => {
    const {acceptOrder, getOrders, studioId} = this.props;

    acceptOrder(orderId, () => getOrders(studioId))
  };

  handleConfirm = orderId => () => {
    const {confirmOrder, getOrders, studioId} = this.props;

    confirmOrder(orderId, () => getOrders(studioId))
  };


  componentDidMount() {
    this.props.getOrders(this.props.studioId);
  }

  renderOrder = (order, variant, btn) => (
    <Card
      key={order.id}
      style={{maxWidth: 500}}
      bg={variant}
      text="white"
      className="mt-4">
      <Card.Body>
        {btn}
        <Card.Title>{order.userName}</Card.Title>
        <Card.Text>{order.description}</Card.Text>
      </Card.Body>
      <Card.Img src={order.photoUrl} />
    </Card>
  );

  render() {
    const { orders, error, role } = this.props;
    const { file, description } = this.state;
    return(
      <>
        <Button className="my-3" onClick={this.showModal}>Left order</Button>
        <div className="row">
          <div className="col-4">
            <h5>New Orders</h5>
            {orders.filter(order => order.status === "NEW").map(order => this.renderOrder(order, 'danger', (
              role === 'MASTER' ? <Button className="float-right" onClick={this.handleAccept(order.id)}>Accept</Button> : null
            )))}
          </div>
          <div className="col-4">
            <h5>Orders In Progress</h5>
            {orders.filter(order => order.status === "IN_PROGRESS").map(order => this.renderOrder(order, 'info', (
              role === 'USER' ? <Button className="float-right" onClick={this.handleConfirm(order.id)}>Confirm</Button> : null
            )))}
          </div>
          <div className="col-4">
            <h5>Confirmed Orders</h5>
            {orders.filter(order => order.status === "CONFIRMED").map(order => this.renderOrder(order, 'success'))}
          </div>
        </div>

        <Modal show={this.state.modalVisible} onHide={this.closeModal}>
          <Modal.Header closeButton>
            <Modal.Title>Left Order</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={this.handleSubmit}>
              <Form.Group>
                <FormLabel>Sketch</FormLabel>
                <Form.Control
                  type="file"
                  files={[file]}
                  onChange={this.handleChange('file')}
                  required
                />
              </Form.Group>
              <Form.Group>
                <FormLabel>Order Comments</FormLabel>
                <Form.Control
                  type="text"
                  as="textarea"
                  rows={4}
                  value={description}
                  onChange={this.handleChange('description')}
                  required
                />
              </Form.Group>
              {error && <Alert variant="danger" className="mt-2">{error}</Alert>}
              <div className="float-right">
                <Button variant="secondary" onClick={this.closeModal} className="mr-2">
                  Close
                </Button>
                <Button variant="primary" type="submit">
                  Left Order
                </Button>
              </div>
            </Form>
          </Modal.Body>
        </Modal>
      </>
    )
  }
}

export default Orders;
