import React from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom'

import { login} from './actions/authActions';
import { useAuthState } from "react-firebase-hooks/auth";
import Footer from "./components/Footer"
import { auth } from './helpers/firebase';
import { PublicNavbar, PrivateNavbar } from './components/Navbar'

import HomePage from './pages/HomePage'
import SingleQuestionPage from './pages/SingleQuestionPage'
import QuestionsPage from './pages/QuestionsPage'
import QuestionFormPage from './pages/QuestionFormPage'
import AnswerFormPage from './pages/AnswerFormPage'
import OwnerQuestionsPage from './pages/OwnerQuestionsPage'
import SignUp from './pages/SignUp';
import Profile from './pages/Profile';



const App = ({ dispatch }) => {
  const [user] = useAuthState(auth);

  if(user){
    dispatch(login(user.email, user.uid))
  }
  
  return (
    <Router>
      {user ?
        <>
          <PrivateNavbar />
          <Switch>
            <Route exact path="/" component={HomePage}></Route>
            <Route exact path="/question/profile" component={Profile} />
            <Route exact path="/questions" component={QuestionsPage} />
            <Route exact path="/question/:id" component={SingleQuestionPage} />
            <Route exact path="/list" component={OwnerQuestionsPage} />
            <Route exact path="/answer/:id" component={AnswerFormPage} />
            <Route exact path="/new" component={QuestionFormPage} />
            <Route exact path="/profile" component={Profile} />
            <Redirect to="/" />
          </Switch>
        </> :
        <>
          <PublicNavbar />
          <Switch>
            <Route exact path="/" component={HomePage}></Route>
            <Route exact path="/questions" component={QuestionsPage} />
            <Route exact path="/question/:id" component={SingleQuestionPage} />
            <Route exact path="/answer/:id" component={AnswerFormPage} />
            <Route exact path="/signup" component={SignUp} />
            <Redirect to="/" />
          </Switch>
        </>
      }
      <Footer />
    </Router>
  )
}

export default App
