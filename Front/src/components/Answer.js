import React from 'react'
import meMusta from "../images/me-gusta.png";
import noMeGusta from "../images/disgusto.png";

export const Answer = ({ answer }) => (
  <aside className="answer">
    <p>
      {answer.answer} 
      <span className='float-end'>
      <img src={meMusta} alt="" width="50" height="50" />
      <img src={noMeGusta} alt="" width="50" height="50" />
      </span>
    </p>
  </aside>
)
