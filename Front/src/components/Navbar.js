import React from 'react';
import { Link } from 'react-router-dom';
import logo  from "../images/pregunta-y-respuesta.png";
import "../index.css";

export const PublicNavbar = () => (
  <nav>
    <Link to="/"><img className='logo' src={logo} alt="" width="50" height="50" /></Link>
    <section>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
    </section>
  </nav>
)

export const PrivateNavbar = () => (
  <nav>
    <Link to="/"><img className='logo' src={logo} alt="" width="50" height="50" /></Link>
    <section>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/new">New</Link>
      <Link to="/list">List</Link>
      <Link to="profile">Profile</Link>
    </section>
  </nav>
)
