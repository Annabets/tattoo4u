import React from 'react';
import {store} from "../configureStore";
import {Router, Route, Redirect} from "react-router";
import {Provider} from "react-redux";
import HomePage from '../containers/HomePage';
import SignIn from '../containers/User/pages/SignIn';
import SignUp from "../containers/User/pages/SignUp";
import Profile from '../containers/Profile';
import Studios from '../containers/Studios';
import Studio from '../containers/Studio';
import Masters from '../containers/Masters';
import Gallery from '../containers/Gallery';
import {SIGN_IN, SIGN_UP, PROFILE, STUDIOS, MASTERS, GALLERY} from "../routes";
import Navbar from "./Navbar";
import {history, isAuth} from "../utils";

export default () => (
  <Provider store={store}>
    <Router history={history}>
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
        {isAuth() ? <Profile /> : <Redirect to="/"/>}
      </Route>
      <Route exact path={STUDIOS}>
        <Studios />
      </Route>
      <Route path={`${STUDIOS}/:studioId`} component={Studio}/>
      <Route path={MASTERS} component={Masters}/>
      <Route path={GALLERY} component={Gallery}/>
    </Router>
  </Provider>
);
