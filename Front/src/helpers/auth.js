import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import "firebase/compat/firestore";

firebase.initializeApp({
    apiKey: "AIzaSyA3ZbgfvHyDuFyApJ3BkDN39TROXpQ1rdg",
    authDomain: "front-aplicacion-empresarial.firebaseapp.com",
    projectId: "front-aplicacion-empresarial",
    storageBucket: "front-aplicacion-empresarial.appspot.com",
    messagingSenderId: "361174735029",
    appId: "1:361174735029:web:d0386ac3c3ee4c1afb06fc"
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