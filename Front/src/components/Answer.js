import React from 'react'
import meMusta from "../images/me-gusta.png";
import noMeGusta from "../images/disgusto.png";

export const Answer = ({ answer, realizarVoto, user }) => (
  <aside className="answers d-flex">

    <span className='answer'>{answer.answer}</span>

    {user && 
    <span className='parte-votacion'>
      <div className='posicion'>{answer.position} like</div>
      <img src={meMusta} alt="" width="35" height="35" onClick={() => realizarVoto(answer, 1)}  />
      <img src={noMeGusta} alt="" width="35" height="35" onClick={() => realizarVoto(answer, -1)}/>
    </span>}

  </aside>
)