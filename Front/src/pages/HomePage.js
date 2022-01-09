import React, {useState} from "react";
import { Link } from "react-router-dom";
import Google from "../images/google.png";
import { useForm } from "react-hook-form";
import { signInWithGoogle, signin, logoutApp, auth } from "../helpers/firebase";
import { useAuthState } from "react-firebase-hooks/auth";
import { login, logout } from "../actions/authActions";
import { connect } from "react-redux";

const HomePage = ({ dispatch }) => {
  const { register, handleSubmit, formState: { errors }} = useForm();
  const [user] = useAuthState(auth);
  const [errorLogin, setErrorLogin] = useState("")

  const onSubmit = async (data) => {
    try {
      await signin(data.email, data.password);
      dispatch(login(user.email, user.uid));
    } catch (error) {
      setErrorLogin(error.message)
    }
  };

  const onLogout = () => {
    logoutApp();
    dispatch(logout);
  };

  return (
    <div>
      {user ? 
      (
        <div className="mt-5 text-center">
          <button className="btn btn-outline-danger" onClick={onLogout}>
            logout
          </button>
        </div>
      ) : (
        <form
          autoComplete="off"
          onSubmit={handleSubmit(onSubmit)}
          className="container col-5 text-center background-login mt-4"
        >
          <h1> Login to question application </h1>
          <p>welcome to the question and answer app.</p>
          <div className="mb-3">
            <input
              className="form-control"
              placeholder="Email"
              type="email"
              id="email"
              {...register("email", {
                required: {
                  value: true,
                  message: "Required",
                },
              })}
            />
            <div className="text-danger">{errors?.email?.message}</div>
          </div>

          <div>
            <input
              type="password"
              className="form-control"
              id="password"
              placeholder="password"
              {...register("password", {
                required: {
                  value: true,
                  message: "Required",
                },
              })}
            />
            <div className="text-danger">{errors?.password?.message}</div>
          </div>

          {errorLogin && 
          <div>{errorLogin}</div>}

          <div className="mb-3">
            <button type="submit" className="btn btn-outline-primary">
              Login
            </button>
          </div>

          <div className="mb-2">
            <button onClick={signInWithGoogle} type="button" className="btn btn-outline-success">
              <img src={Google} width={30} /> Login with Google
            </button>
          </div>
          <hr />
          <p>
            Don't have an account? <Link to="/signup">Sign up</Link>
          </p>
        </form>
      )}
    </div>
  );
};

const mapStateToProps = (state) => ({
  loading: state.question.loading,
  email: state.auth.email,
  uid: state.auth.uid,
});

export default connect(mapStateToProps)(HomePage);
