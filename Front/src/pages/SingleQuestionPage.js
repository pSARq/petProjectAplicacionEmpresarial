import React, { useEffect } from 'react'
import { connect } from 'react-redux'

import { fetchQuestion, votar } from '../actions/questionActions'

import { Question } from '../components/Question'
import { Answer } from '../components/Answer'
import { Link } from 'react-router-dom'
import { auth } from "../helpers/firebase";
import { useAuthState } from "react-firebase-hooks/auth";


const SingleQuestionPage = ({
  match,
  dispatch,
  question,
  hasErrors,
  loading,
  userId,
  redirect
}) => {
  const { id } = match.params
  const [user] = useAuthState(auth);

  useEffect(() => {
    dispatch(fetchQuestion(id))
  }, [dispatch, id, redirect])

  const renderQuestion = () => {
    if (loading.question) return <p>Loading question...</p>
    if (hasErrors.question) return <p>Unable to display question.</p>

    return <Question question={question} />
  }

  const realizarVoto = (answer, voto) => {
    let data = {
      userId: user.uid,
      questionId: answer.questionId,
      answerId: answer.id,
      voto: voto
    };
    dispatch(votar(data))
  }
  

  const renderAnswers = () => {
    return(
      (question.answers && question.answers.length) ? question.answers.map(answer => (
        <Answer key={answer.id} answer={answer} realizarVoto={realizarVoto} user={user} />
      )) : <p>Empty answer!</p>
    ) 
  }

  return (
    <section>
      {renderQuestion()}
      {userId && <Link to={"/answer/" + id} className="button right">
        Reply
      </Link>}

      <h2>Answers</h2>
      {renderAnswers()}
    </section>
  )
}

const mapStateToProps = state => ({
  question: state.question.question,
  loading: state.question.loading,
  hasErrors: state.question.hasErrors,
  userId: state.auth.uid,
  redirect: state.question.redirect
})

export default connect(mapStateToProps)(SingleQuestionPage)
