import {combineReducers} from "redux";
import {userReducer} from './containers/User/reducer';

export const rootReducer = combineReducers({
  user: userReducer,
});
