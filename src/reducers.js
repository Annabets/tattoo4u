import {combineReducers} from "redux";
import {userReducer} from './containers/User/reducer';
import {profileReducer} from "./containers/Profile/reducer";
import {homePageReducer} from "./containers/HomePage/reducer";
import {studiosReducer} from "./containers/Studios/reducer";
import {photoGridReducer} from "./containers/PhotoGrid/reducer";
import {studioReducer} from "./containers/Studio/reducer";
import {mastersReducer} from "./containers/Masters/reducer";

export const rootReducer = combineReducers({
  user: userReducer,
  profile: profileReducer,
  homePage: homePageReducer,
  studios: studiosReducer,
  studio: studioReducer,
  photoGrid: photoGridReducer,
  masters: mastersReducer,
});
