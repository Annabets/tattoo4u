import React from 'react';
import ReactDOM from 'react-dom';

import * as serviceWorker from './serviceWorker';

import 'bootswatch/dist/flatly/bootstrap.min.css';
import './styles/app.scss';

import App from './components/App';

ReactDOM.render(<App/>, document.getElementById('app'));


serviceWorker.unregister();

