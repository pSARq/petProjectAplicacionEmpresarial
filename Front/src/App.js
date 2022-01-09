import React from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from 'react-router-dom'
// import firebase from "firebase/compat/app";
// import "firebase/compat/firestore";
// import "firebase/compat/auth";
// import { login, logout } from './actions/authActions';
import { login} from './actions/authActions';

import { PublicNavbar, PrivateNavbar } from './components/Navbar'
import HomePage from './pages/HomePage'
import SingleQuestionPage from './pages/SingleQuestionPage'
import QuestionsPage from './pages/QuestionsPage'
import QuestionFormPage from './pages/QuestionFormPage'
import AnswerFormPage from './pages/AnswerFormPage'
import OwnerQuestionsPage from './pages/OwnerQuestionsPage'
import { useAuthState } from "react-firebase-hooks/auth";
import Footer from "./components/Footer"

import { signInWithGoogle, logout, auth } from './helpers/auth';
import SignUp from './pages/SignUp';



// firebase.initializeApp({
//   apiKey: "AIzaSyCTySyvuIDPg7RWF6ceuuwC2t3BEiAK38o",
//   authDomain: "question-app-demo.firebaseapp.com",
//   projectId: "question-app-demo",
//   storageBucket: "question-app-demo.appspot.com",
//   messagingSenderId: "1038673531562",
//   appId: "1:1038673531562:web:da90421f639a3115dcf6d3"
// });

// const auth = firebase.auth();



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
            <Route exact path="/questions" component={QuestionsPage} />
            <Route exact path="/question/:id" component={SingleQuestionPage} />
            <Route exact path="/list" component={OwnerQuestionsPage} />
            <Route exact path="/answer/:id" component={AnswerFormPage} />
            <Route exact path="/new" component={QuestionFormPage} />
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


// function SignIn() {
//   const signInWithGoogle = () => {
//     const provider = new firebase.auth.GoogleAuthProvider();
//     auth.signInWithPopup(provider);
//   };
//   return <button className="button right" onClick={signInWithGoogle}>Sign in with google</button>;
// }

// function SignOut({ dispatch }) {
//   return (
//     auth.currentUser && (
//       <button
//         className="button right"
//         onClick={() => {
//           dispatch(logout())
//           auth.signOut();
//         }}
//       >
//         Sign out
//       </button>
//     )
//   );
// }


export default App
