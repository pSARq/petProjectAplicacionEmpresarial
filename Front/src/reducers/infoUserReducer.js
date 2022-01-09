import * as actions from '../actions/infoUser'

export const initialState = {
  loading: false,
  hasErrors: null,
  infoUser: {},
  redirect: null,
  successfull: null
}

export default function userReducer(state = initialState, action) {
  switch (action.type) {
    case actions.LOADING:
      return { ...state, loading: true }
    case actions.LOADED_SUCCESS:
      return { ...state, ...action.payload, loading: false, hasErrors: null }
    case actions.LOADED_FAILURE:
      return { ...state, hasErrors: action.payload, loading: false, successfull: null }
    default:
      return state
  }
}