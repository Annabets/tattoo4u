import {combineReducers} from "redux";
import {userReducer} from './containers/User/reducer';
import {profileReducer} from "./containers/Profile/reducer";
import {homePageReducer} from "./containers/HomePage/reducer";
import {photoGridReducer} from "./containers/PhotoGrid/reducer";

export const rootReducer = combineReducers({
  user: userReducer,
  profile: profileReducer,
  homePage: homePageReducer,
  photoGrid: photoGridReducer,
});
