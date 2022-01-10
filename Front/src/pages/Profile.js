import React, { useEffect, useState } from "react";
import { getInfoUser, updateInfoUser } from "../actions/infoUser";
import { connect } from "react-redux";

import { useForm } from "react-hook-form";
import { auth } from "../helpers/firebase";
import { useAuthState } from "react-firebase-hooks/auth";


const Profile = ({ dispatch, loading, email, infoUser, redirect, successful, hasErrors }) => {
  const {register, handleSubmit} = useForm();
  const [user] = useAuthState(auth);
  const [name, setName] = useState()
  const [lastName, setLastName] = useState()

  const valores = () => {
    setName(infoUser.name)
    setLastName(infoUser.lastName)
  }
  
  const onSubmit = (data) => {
    data.id = infoUser.id
    data.userId = user.uid;
    data.name = name
    data.lastName = lastName
    dispatch(updateInfoUser(data));
  };

  useEffect(() => {
    dispatch(getInfoUser(user.uid));
    valores()
  }, [dispatch, redirect]);

  return (
    <div>
      <form
        autoComplete="off"
        onSubmit={handleSubmit(onSubmit)}
        className="container col-5 text-center mt-4"
      >
        <h1> Profile </h1>
        <div className="mb-3">
          <input
            className="form-control"
            placeholder="Name"
            type="name"
            id="name"
            value={name}
            {...register("name")}

            onChange={(e) => {
                setName(e.target.value);
            }}
          />
        </div>

        <div className="mb-3">
          <input
            className="form-control"
            placeholder="Lastname"
            type="lastName"
            id="lastName"
            value={lastName}
            {...register("lastName")}

            onChange={(e) => {
                setLastName(e.target.value);
            }}
          />
        </div>

        <div className="mb-3">
          <input className="form-control" disabled value={email} />
        </div>
        
        {successful &&
        <h3 className="text-success">{successful}</h3>}

        {hasErrors &&
        <h3 className="text-danger">{hasErrors}</h3>}

        <div className="mb-3">
          <button type="submit" className="btn btn-outline-dark opacity-75" disabled={loading}>
            {loading ? "Saving ...." : "Save"}
          </button>
        </div>
      </form>
    </div>
  );
};

const mapStateToProps = (state) => ({
  loading: state.infoUser.loading,
  hasErrors: state.infoUser.hasErrors,
  userId: state.auth.uid,
  infoUser: state.infoUser.infoUser,
  email: state.auth.email,
  redirect: state.infoUser.redirect,
  successful: state.infoUser.successful
});

export default connect(mapStateToProps)(Profile);
