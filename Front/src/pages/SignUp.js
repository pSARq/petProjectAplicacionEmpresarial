import React from "react";
import { Link } from "react-router-dom";
import Google from "../images/google.png";
import { useForm } from "react-hook-form";
import { signInWithGoogle, signup, logoutApp, auth } from "../helpers/auth";
import { useAuthState } from "react-firebase-hooks/auth";
import { login, logout } from "../actions/authActions";
import { connect } from "react-redux";

const SignUp = ({ dispatch }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [user] = useAuthState(auth);

  const onSubmit = (data) => {
    signup(data.email, data.password);
    dispatch(login(user.email, user.uid));
  };

  const onLogout = () => {
    logoutApp();
    dispatch(logout);
  };

  return (
    <div>
        <form
          autoComplete="off"
          onSubmit={handleSubmit(onSubmit)}
          className="container col-5 text-center background-login mt-4"
        >
          <h1> Sign up to question application </h1>
          <p>Fill un the form below to create an account</p>
          <div className="mb-3">
            <input
              className="form-control"
              placeholder="Email"
              type="email"
              id="email"
              {...register("email", {
                required: {
                  value: true,
                  message: "required",
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
                  message: "required",
                },
              })}
            />
            <div className="text-danger">{errors?.password?.message}</div>
          </div>

          <div className="mb-3">
            <button type="submit" className="btn btn-outline-primary">
              Sign up
            </button>
          </div>

          <div className="mb-2">
            <button onClick={signInWithGoogle} type="button" className="btn btn-outline-success">
              <img src={Google} width={30} /> Sign up with Google
            </button>
          </div>
          <hr />
          <p>
          Already have a account? <Link to="/">Login</Link>
          </p>
        </form>
    </div>
  );
};

const mapStateToProps = (state) => ({
  loading: state.question.loading,
  email: state.auth.email,
  uid: state.auth.uid,
});

export default connect(mapStateToProps)(SignUp);