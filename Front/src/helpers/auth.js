import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import "firebase/compat/firestore";

firebase.initializeApp({
    apiKey: "AIzaSyCTySyvuIDPg7RWF6ceuuwC2t3BEiAK38o",
    authDomain: "question-app-demo.firebaseapp.com",
    projectId: "question-app-demo",
    storageBucket: "question-app-demo.appspot.com",
    messagingSenderId: "1038673531562",
    appId: "1:1038673531562:web:da90421f639a3115dcf6d3",
});
  
export const auth = firebase.auth();

export function signInWithGoogle() {
    const provider = new firebase.auth.GoogleAuthProvider();
    return auth.signInWithPopup(provider);
}

export function logoutApp() {
    return auth.signOut();
}

export function signup(email, password){
    return auth.createUserWithEmailAndPassword(email, password);
}

export function signin(email, password){
    return auth.signInWithEmailAndPassword(email, password);
}