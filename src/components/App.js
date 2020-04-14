import React from 'react';
import {store} from "../configureStore";
import {BrowserRouter as Router, Route} from "react-router-dom";
import {Provider} from "react-redux";
import HomePage from '../containers/HomePage';
import SignIn from '../containers/User/pages/SignIn';
import SignUp from "../containers/User/pages/SignUp";
import Profile from '../containers/Profile';
import Studios from '../containers/Studios';
import Studio from '../containers/Studio';
import Masters from '../containers/Masters';
import {SIGN_IN, SIGN_UP, PROFILE, STUDIOS, MASTERS} from "../routes";
import Navbar from "./Navbar";

export default () => (
  <Provider store={store}>
    <Router>
      <Navbar />
      <Route exact path="/">
        <HomePage/>
      </Route>
      <Route exact path={SIGN_IN}>
        <SignIn/>
      </Route>
      <Route exact path={SIGN_UP}>
        <SignUp/>
      </Route>
      <Route exact path={PROFILE}>
        <Profile />
      </Route>
      <Route exact path={STUDIOS}>
        <Studios />
      </Route>
      <Route path={`${STUDIOS}/:studioId`} component={Studio}/>
      <Route path={MASTERS} component={Masters}/>
    </Router>
  </Provider>
);
