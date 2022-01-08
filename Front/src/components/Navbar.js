import React from 'react';
import { Link } from 'react-router-dom';
import logo  from "../images/pregunta-y-respuesta.png";

export const PublicNavbar = () => (
  <nav>
    <img src={logo} alt="" width="50" height="50" />
    <section>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
    </section>
  </nav>
)

export const PrivateNavbar = () => (
  <nav>
    <img src={logo} alt="" width="50" height="50" />
    <section>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/new">New</Link>
      <Link to="/list">List</Link>
    </section>
  </nav>
)
