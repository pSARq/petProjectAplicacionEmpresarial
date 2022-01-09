import React, { useEffect } from "react";
import { getInfoUser, updateInfoUser } from "../actions/infoUser";
import { connect } from "react-redux";

import { useForm } from "react-hook-form";
import { auth } from "../helpers/firebase";
import { useAuthState } from "react-firebase-hooks/auth";

const Profile = ({ dispatch, loading, email, infoUser }) => {
  const {register, handleSubmit} = useForm();
  const [user] = useAuthState(auth);

  const onSubmit = (data) => {
    data.userId = user.uid;
    data.email = email
    // data.id = infoUser.id
    console.log("infoUser.id: "+infoUser.id)
    dispatch(updateInfoUser(data));
  };

  useEffect(() => {
    dispatch(getInfoUser(user.uid));

    console.log("data.id: "+infoUser.id)
    console.log("data.userId: "+ infoUser.userId)
    console.log("data.name: "+ infoUser.name)
    console.log("data.lastName: "+ infoUser.lastName)
    console.log("data.email: "+ infoUser.email)

    console.log("infoUser.id: "+infoUser.id)
  }, [user.uid]);

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
            {...register("name")}
          />
        </div>

        <div className="mb-3">
          <input
            className="form-control"
            placeholder="LastName"
            type="lastName"
            id="lastName"
            {...register("lastName")}
          />
        </div>

        <div className="mb-3">
          <input className="form-control" disabled value={email} />
        </div>

        <div className="mb-3">
          <button type="submit" className="button" disabled={loading}>
            {loading ? "Saving ...." : "Save"}
          </button>
        </div>
      </form>
    </div>
  );
};

const mapStateToProps = (state) => ({
  loading: state.question.loading,
  hasErrors: state.question.hasErrors,
  userId: state.auth.uid,
  infoUser: state.infoUser.infoUser,
  email: state.auth.email
});

export default connect(mapStateToProps)(Profile);
