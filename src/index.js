import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';
import {BrowserRouter as Router, Route} from "react-router-dom";
import {Provider} from 'react-redux';
import {store} from './configureStore';
import 'bootswatch/dist/flatly/bootstrap.min.css';
import Navbar from './components/Navbar';
import HomePage from './containers/HomePage';

ReactDOM.render(
  <Provider store={store}>
    <Router>
      <Route exact path="/">
        <Navbar />
        <HomePage />
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app'));


serviceWorker.unregister();

