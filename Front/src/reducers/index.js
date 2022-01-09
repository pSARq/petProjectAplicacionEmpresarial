import { combineReducers } from 'redux'
import questionsReducer from './questionsReducer';
import authReducer from './authReducer';
import userReducer from './infoUserReducer';

const rootReducer = combineReducers({
    question: questionsReducer,
    auth: authReducer,
    infoUser: userReducer
})

export default rootReducer
